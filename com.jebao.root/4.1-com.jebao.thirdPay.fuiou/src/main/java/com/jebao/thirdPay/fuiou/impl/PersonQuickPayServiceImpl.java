package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.personQuickPay.PersonQuickPayRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--PC端个人用户免登录快捷充值
 * Created by Administrator on 2016/9/27.
 */
@Service
public class PersonQuickPayServiceImpl {
    public String post(PersonQuickPayRequest reqData) {
        String httpUrl= FuiouConfig.url+"500405.action";
        reqData.setPage_notify_url(FuiouConfig.Jebao_Notify_Origin+"api/user/quickPayNotify");
        try {
            return post(httpUrl,reqData);
        } catch (Exception e) {
            return null;
        }
    }

    public String post(String httpUrl, PersonQuickPayRequest reqData) throws Exception {
        PrintUtil.printLn("Sign:" + reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        PersonQuickPayRequest reqData = new PersonQuickPayRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("");
        reqData.setAmt("");
        reqData.setBack_notify_url("");

        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/500405.action";
        PersonQuickPayServiceImpl personQuickPayService = new PersonQuickPayServiceImpl();
        String result = personQuickPayService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[PC端个人用户免登录快捷充值]-测试通过");
        }
    }*/
}
