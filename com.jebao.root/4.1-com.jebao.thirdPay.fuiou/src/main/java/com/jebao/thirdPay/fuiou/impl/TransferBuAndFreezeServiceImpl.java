package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze.TransferBuAndFreezeRequest;
import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze.TransferBuAndFreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--划拨预冻结接口
 * Created by Administrator on 2016/9/28.
 */
@Service
public class TransferBuAndFreezeServiceImpl {
    public TransferBuAndFreezeResponse post(String httpUrl, TransferBuAndFreezeRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        TransferBuAndFreezeResponse transferBuAndFreezeResponse= XmlUtil.fromXML(xmlData, TransferBuAndFreezeResponse.class);
        PrintUtil.printLn(transferBuAndFreezeResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,transferBuAndFreezeResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[划拨预冻结]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return transferBuAndFreezeResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        TransferBuAndFreezeRequest reqData = new TransferBuAndFreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setOut_cust_no("1367842482");
        reqData.setIn_cust_no("1367842482");
        reqData.setAmt("10000");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/transferBuAndFreeze.action";
        TransferBuAndFreezeServiceImpl transferBuAndFreezeService = new TransferBuAndFreezeServiceImpl();
        TransferBuAndFreezeResponse result = transferBuAndFreezeService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[划拨预冻结]-测试通过");
        }
    }*/
}
