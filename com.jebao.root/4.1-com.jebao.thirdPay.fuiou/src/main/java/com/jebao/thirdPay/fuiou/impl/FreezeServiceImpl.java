package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.freeze.FreezeRequest;
import com.jebao.thirdPay.fuiou.model.freeze.FreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--冻结接口
 * Created by Administrator on 2016/9/28.
 */
@Service
public class FreezeServiceImpl {
    public FreezeResponse post(String httpUrl, FreezeRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        FreezeResponse freezeResponse= XmlUtil.fromXML(xmlData, FreezeResponse.class);
        PrintUtil.printLn(freezeResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,freezeResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[冻结]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return freezeResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        FreezeRequest reqData = new FreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setCust_no("13678424821");
        reqData.setAmt("10000");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/freeze.action";
        FreezeServiceImpl freezeService = new FreezeServiceImpl();
        FreezeResponse result = freezeService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[冻结]-测试通过");
        }
    }*/
}
