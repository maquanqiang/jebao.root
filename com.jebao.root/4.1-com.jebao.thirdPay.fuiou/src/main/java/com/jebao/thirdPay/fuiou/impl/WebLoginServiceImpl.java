package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.webLogin.WebLoginRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--商户端个人用户跳转登录页面（网页版）
 * Created by Administrator on 2016/9/27.
 */
@Service
public class WebLoginServiceImpl {
    public String post(String httpUrl, WebLoginRequest reqData) throws Exception {
        PrintUtil.printLn("Sign:" + reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }

    //测试
    /*public static void main(String[] args) throws Exception {
        WebLoginRequest reqData = new WebLoginRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setCust_no("19012348888");
        reqData.setLocation("");//0020 快速充值,0022 提现,0024 网银充值
        reqData.setAmt("");
        String httpUrl = "https://jzh-test.fuiou.com/jzh/webLogin.action";
        WebLoginServiceImpl webLoginService = new WebLoginServiceImpl();
        String result = webLoginService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户端个人用户跳转登录页面（网页版）]-测试通过");
        }
    }*/
}
