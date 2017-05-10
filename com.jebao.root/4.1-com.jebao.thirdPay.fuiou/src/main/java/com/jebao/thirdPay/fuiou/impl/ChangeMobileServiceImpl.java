package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.changeMobile.ChangeMobileRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--个人PC端更换手机号
 * Created by Administrator on 2016/9/27.
 */
@Service
public class ChangeMobileServiceImpl {
    public String post(String httpUrl, ChangeMobileRequest reqData) throws Exception {
        PrintUtil.printLn("Sign:" + reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        ChangeMobileRequest reqData = new ChangeMobileRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("13901909090");

        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/400101.action";
        ChangeMobileServiceImpl changeMobileService = new ChangeMobileServiceImpl();
        String result = changeMobileService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[个人PC端更换手机号]-测试通过");
        }
    }*/
}
