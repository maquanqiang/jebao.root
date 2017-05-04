package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.preAuthCancel.PreAuthCancelRequest;
import com.jebao.thirdPay.fuiou.model.preAuthCancel.PreAuthCancelResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--预授权撤销接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class PreAuthCancelServiceImpl {
    public PreAuthCancelResponse post(String httpUrl, PreAuthCancelRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        PreAuthCancelResponse preAuthCancelResponse= XmlUtil.fromXML(xmlData, PreAuthCancelResponse.class);
        PrintUtil.printLn(preAuthCancelResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,preAuthCancelResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[预授权撤销接口]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return preAuthCancelResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        PreAuthCancelRequest reqData = new PreAuthCancelRequest();
        reqData.setMchnt_cd("0006510F0093601");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setOut_cust_no("13678424821");
        reqData.setIn_cust_no("13678424821");
        reqData.setContract_no("");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/preAuthCancel.action";
        PreAuthCancelServiceImpl preAuthCancelService = new PreAuthCancelServiceImpl();
        PreAuthCancelResponse result = preAuthCancelService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[预授权撤销接口]-测试通过");
        }
    }*/
}
