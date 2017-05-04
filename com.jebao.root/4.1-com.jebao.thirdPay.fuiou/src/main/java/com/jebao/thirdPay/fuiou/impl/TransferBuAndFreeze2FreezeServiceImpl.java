package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze2Freeze.TransferBuAndFreeze2FreezeRequest;
import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze2Freeze.TransferBuAndFreeze2FreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * Created by Administrator on 2016/9/28.
 */
@Service
public class TransferBuAndFreeze2FreezeServiceImpl {
    public TransferBuAndFreeze2FreezeResponse post(String httpUrl, TransferBuAndFreeze2FreezeRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        TransferBuAndFreeze2FreezeResponse regResponse= XmlUtil.fromXML(xmlData, TransferBuAndFreeze2FreezeResponse.class);
        PrintUtil.printLn(regResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,regResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[冻结到冻结接口]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return regResponse;
    }
    //测试
/*    public static void main(String[] args) throws Exception {
        TransferBuAndFreeze2FreezeRequest reqData=new TransferBuAndFreeze2FreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setOut_cust_no("");
        reqData.setIn_cust_no("");
        reqData.setAmt("");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/transferBuAndFreeze2Freeze.action";
        TransferBuAndFreeze2FreezeServiceImpl service = new TransferBuAndFreeze2FreezeServiceImpl();
        TransferBuAndFreeze2FreezeResponse result = service.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[冻结到冻结接口]-测试通过");
        }
    }*/
}
