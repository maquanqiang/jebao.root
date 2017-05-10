package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.changeCard.ChangeCardRequest;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--商户P2P网站免登录用户更换银行卡接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class ChangeCardServiceImpl {
    public String post(ChangeCardRequest reqData) {
        String httpUrl= FuiouConfig.url+"changeCard2.action";
        reqData.setPage_notify_url(FuiouConfig.Jebao_Notify_Origin+"api/userfund/changeCardNotify");
        try {
            return post(httpUrl,reqData);
        } catch (Exception e) {
            return null;
        }
    }
    public String post(String httpUrl, ChangeCardRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        ChangeCardRequest reqData = new ChangeCardRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("");


        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/changeCard2.action";
        ChangeCardServiceImpl changeCardService = new ChangeCardServiceImpl();
        String result = changeCardService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户P2P网站免登录用户更换银行卡接口]-测试通过");
        }
    }*/
}
