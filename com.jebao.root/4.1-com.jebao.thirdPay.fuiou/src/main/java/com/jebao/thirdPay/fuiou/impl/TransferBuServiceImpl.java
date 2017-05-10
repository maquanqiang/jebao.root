package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuRequest;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--划拨(个人与个人之间)
 * Created by Administrator on 2016/9/26.
 */
@Service
public class TransferBuServiceImpl {
    public TransferBuResponse post(TransferBuRequest reqData) throws Exception {
        String httpUrl= FuiouConfig.url+"transferBu.action";

        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        TransferBuResponse transferBuResponse= XmlUtil.fromXML(xmlData, TransferBuResponse.class);
        PrintUtil.printLn(transferBuResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,transferBuResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[划拨(个人与个人之间)]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return transferBuResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        TransferBuRequest reqData=new TransferBuRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setOut_cust_no("13678424821");
        reqData.setIn_cust_no("13678424822");
        reqData.setAmt("10000");
        reqData.setContract_no("00000012312312");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/transferBu.action";
        TransferBuServiceImpl transferBuService = new TransferBuServiceImpl();
        TransferBuResponse result = transferBuService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[划拨(个人与个人之间)]-测试通过");
        }
    }*/

}
