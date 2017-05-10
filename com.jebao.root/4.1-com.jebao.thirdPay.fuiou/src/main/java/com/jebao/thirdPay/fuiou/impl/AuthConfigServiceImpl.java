package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.authConfig.AuthConfigRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--PC金账户免登陆授权配置（短信通知+委托交易）（网页版）
 * Created by Administrator on 2016/9/27.
 */
@Service
public class AuthConfigServiceImpl {
    public String post(String httpUrl, AuthConfigRequest reqData) throws Exception {
        PrintUtil.printLn("Sign:" + reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        AuthConfigRequest reqData = new AuthConfigRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("");
        reqData.setBusi_tp("");
        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //
        String httpUrl = "http://www-1.fuiou.com:9057/jzh/authConfig.action";
        AuthConfigServiceImpl authConfigService = new AuthConfigServiceImpl();
        String result = authConfigService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[PC金账户免登陆授权配置（短信通知+委托交易）（网页版）]-测试通过");
        }
    }*/
}
