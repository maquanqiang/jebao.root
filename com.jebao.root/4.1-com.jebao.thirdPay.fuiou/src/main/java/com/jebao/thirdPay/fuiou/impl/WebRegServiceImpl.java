package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.webReg.WebRegRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--个人用户自助开户注册（网页版）
 * Created by Administrator on 2016/9/26.
 */
@Service
public class WebRegServiceImpl {
    public String post(WebRegRequest reqData) {
        String httpUrl= FuiouConfig.url+"webReg.action";
        reqData.setPage_notify_url(FuiouConfig.Jebao_Notify_Origin+"api/userfund/registerNotify");
        try {
            return post(httpUrl,reqData);
        } catch (Exception e) {
            return null;
        }
    }
    public String post(String httpUrl, WebRegRequest reqData) throws Exception {
        String signature = reqData.requestSignPlain();
        String signatureStr = SecurityUtils.sign(signature);
        PrintUtil.printLn(signatureStr);
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }

    //测试
    /*public static void main(String[] args) throws Exception {
        WebRegRequest reqData = new WebRegRequest();
        reqData.setVer("0.44");
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("75720160927152211");
        reqData.setUser_id_from("830");
        reqData.setMobile_no("13712012345");
        reqData.setCust_nm("梅梦香");
        reqData.setCertif_tp("0");
        reqData.setCertif_id("431301198109297486");
        reqData.setEmail("");
        reqData.setCity_id("");
        reqData.setParent_bank_id("");
        reqData.setBank_nm("");
        reqData.setCapAcntNo("");
        reqData.setPage_notify_url("http://localhost:8081/p2p/pay/getFuioureturnDatePay.do");
        reqData.setBack_notify_url("");

        String httpUrl = "https://jzh-test.fuiou.com/jzh/webReg.action";
        WebRegServiceImpl webRegService = new WebRegServiceImpl();
        String result = webRegService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[个人用户自助开户注册（网页版）]-测试通过");
        }
    }*/
}
