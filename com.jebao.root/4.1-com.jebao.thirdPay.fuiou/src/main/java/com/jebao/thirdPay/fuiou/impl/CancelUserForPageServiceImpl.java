package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.cancelUserForPage.CancelUserForPageRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--用户申请注销免登陆接口(网页版)
 * Created by Administrator on 2016/9/27.
 */
@Service
public class CancelUserForPageServiceImpl {
    public String post(String httpUrl, CancelUserForPageRequest reqData) throws Exception {
        PrintUtil.printLn("Sign:" + reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        CancelUserForPageRequest reqData = new CancelUserForPageRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("");

        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/cancelUserForPage.action";
        CancelUserForPageServiceImpl cancelUserForPageService = new CancelUserForPageServiceImpl();
        String result = cancelUserForPageService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[用户申请注销免登陆接口(网页版)]-测试通过");
        }
    }*/
}
