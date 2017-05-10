package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthRequest;
import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.springframework.stereotype.Service;


/**
 * 富友--预授权接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class PreAuthServiceImpl {

    public PreAuthResponse post(PreAuthRequest reqData) {
        String httpUrl= FuiouConfig.url+"preAuth.action";
        System.out.println(reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);

        try {
            return post(httpUrl, reqData);
        } catch (Exception e) {
            return null;
        }
    }

    public PreAuthResponse post(String httpUrl, PreAuthRequest reqData) throws Exception {

        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        PreAuthResponse preAuthResponse= XmlUtil.fromXML(xmlData, PreAuthResponse.class);
        PrintUtil.printLn(preAuthResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,preAuthResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[预授权接口]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return preAuthResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        PreAuthRequest reqData = new PreAuthRequest();
        reqData.setMchnt_cd("0006410F0026868");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setOut_cust_no("13678424821");
        reqData.setIn_cust_no("13678424822");
        reqData.setAmt("10000");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/preAuth.action";
        PreAuthServiceImpl preAuthService = new PreAuthServiceImpl();
        PreAuthResponse result = preAuthService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[预授权接口]-测试通过");
        }
    }*/
}
