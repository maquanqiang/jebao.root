package com.jebao.erp.web.utils.contract;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ContractUtil {

    public static String create(String url,String fileName, Long iiId) throws InterruptedException {
        if (!PdfQueueSingleton.getSingleton().isFree()) {
            return "系统繁忙，暂时无法生成合同请稍后再试";
        }
        PdfInfo pdfInfo = new PdfInfo();
        pdfInfo.setFileName(fileName);
        pdfInfo.setUrl(url);
        pdfInfo.setIiId(iiId);
        PdfQueueSingleton.getSingleton().put(pdfInfo);
        return "ok";
    }
}
