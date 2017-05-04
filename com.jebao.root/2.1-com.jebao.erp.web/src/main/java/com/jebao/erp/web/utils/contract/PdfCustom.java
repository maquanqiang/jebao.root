package com.jebao.erp.web.utils.contract;

import com.jebao.erp.service.inf.investment.IInvestInfoServiceInf;
import com.jebao.erp.web.utils.constants.Constants;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/19.
 */
@Component
public class PdfCustom {

    @Autowired
    private TbInvestInfoDao investInfoDao;
    //文件下载服务器的地址
    private static final String FILE_UPLOAD_SERVICE_URL = Constants.FILE_UPLOAD_SERVICE_URL;
    private static final String PHANTONJS_EXE_PATH = Constants.CONTRACT_PDF_PHANTONJS_EXE_PATH;
    private static final String PHANTONJS_SCRIPT_PATH = Constants.CONTRACT_PDF_PHANTONJS_SCRIPT_PATH;
    private static final String PDF_FILE_SAVE_PATH = Constants.CONTRACT_PDF_FILE_SAVE_PATH;
    public PdfCustom() {}
    //创建合同任务
    public void doTaskForCreateContractPdf()
    {
        Consumer consumer = new Consumer();
        ExecutorService service = Executors.newFixedThreadPool(2);//控制最大线程池
        for (int i=0;i<2;i++) service.execute(consumer);
    }
    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                try {
                    PdfInfo pdfInfo= PdfQueueSingleton.getSingleton().take();
                    System.out.println("从队列取走一个元素，队列剩余" + PdfQueueSingleton.getSingleton().size() + "个元素");
                    String result=callPhantonjsCreatePDF(pdfInfo.getUrl(),pdfInfo.getFileName());
                    if(result!=null&&result.trim().equals("[OK]")){
                        System.out.println("PDF合同生成-成功");
                        //合同的下载地址
                        String fileDownloadUrl=FILE_UPLOAD_SERVICE_URL+"/contractFiles/pdfFile/"+pdfInfo.getFileName();
                        System.out.println(fileDownloadUrl);
                        //todo 将合同地址更新到数据库相应的字段
                        TbInvestInfo tbInvestInfo = new TbInvestInfo();
                        tbInvestInfo.setIiContractUrl(fileDownloadUrl);
                        tbInvestInfo.setIiId(pdfInfo.getIiId());
                        int count = investInfoDao.updateByPrimaryKeySelective(tbInvestInfo);
                        System.out.println("合同生成保存个数："+count);
                    }else{
                        System.out.println("PDF合同生成-失败："+result);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        private  String callPhantonjsCreatePDF(String url,String fileName) throws IOException, InterruptedException {
                String fullFileName=  Paths.get(PDF_FILE_SAVE_PATH, fileName).toString();
                //调用时不要加双引号-
                //String command=String.format("%s %s \"%s\" \"%s\"",PHANTONJS_EXE_PATH,PHANTONJS_SCRIPT_PATH,url,fullFileName);
                String command=String.format("%s %s %s %s",PHANTONJS_EXE_PATH,PHANTONJS_SCRIPT_PATH,url,fullFileName);
                System.out.println(command);
                BufferedReader bufferedReader=null;
                // 读取Shell的输出内容
                StringBuffer stringBuffer = new StringBuffer();
                try{
                    Process process;
                    process = Runtime.getRuntime().exec(command);
                    InputStream is = process.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(is));
                    process.waitFor();
                    String line = null;
                    while (bufferedReader != null && (line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line).append("\r\n");
                    }
                    String str = stringBuffer.toString();
                    System.out.println(str);
                    return str;
                }catch (Exception ex) {
                    stringBuffer.append("执行命令时发生异常：\r\n")
                            .append("[命令]"+command).append("\r\n")
                            .append(ex.getMessage()).append("\r\n");
                    return "[ERROR]" + stringBuffer.toString();
                }finally{
                    if (bufferedReader != null){
                        bufferedReader.close();
                    }
                }
        }
    }
}
