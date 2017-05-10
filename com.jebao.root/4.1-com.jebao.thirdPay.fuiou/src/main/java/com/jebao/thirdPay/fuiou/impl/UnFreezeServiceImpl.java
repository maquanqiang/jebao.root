package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.unFreeze.UnFreezeRequest;
import com.jebao.thirdPay.fuiou.model.unFreeze.UnFreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * Created by Administrator on 2016/9/28.
 */
@Service
public class UnFreezeServiceImpl {
    public UnFreezeResponse post(String httpUrl, UnFreezeRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        UnFreezeResponse regResponse= XmlUtil.fromXML(xmlData, UnFreezeResponse.class);
        PrintUtil.printLn(regResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,regResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[解冻（7，8，9，10的冻结资金解冻）]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return regResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        UnFreezeRequest reqData=new UnFreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setCust_no("");
        reqData.setAmt("");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/unFreeze.action";
        UnFreezeServiceImpl service = new UnFreezeServiceImpl();
        UnFreezeResponse result = service.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[解冻（7，8，9，10的冻结资金解冻]-测试通过");
        }
    }*/
}
