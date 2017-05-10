package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.fastRecharge.FastRechargeRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--商户P2P网站免登录快速充值接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class FastRechargeServiceImpl {
    public String post(FastRechargeRequest reqData) {
        String httpUrl= FuiouConfig.url+"500001.action";
        reqData.setPage_notify_url(FuiouConfig.Jebao_Notify_Origin+"api/user/fastRechargeNotify");
        try {
            return post(httpUrl,reqData);
        } catch (Exception e) {
            return null;
        }
    }
    public String post(String httpUrl,FastRechargeRequest reqData) throws Exception {
        PrintUtil.printLn("Sign:" + reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }

    //测试
    /*public static void main(String[] args) throws Exception {
        FastRechargeRequest reqData = new FastRechargeRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("13678424821");
        reqData.setAmt("10000");
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        reqData.setBack_notify_url("");
        String httpUrl = "https://jzh-test.fuiou.com/jzh/500001.action";
        FastRechargeServiceImpl fastRechargeService = new FastRechargeServiceImpl();
        String result = fastRechargeService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户P2P网站免登录快速充值接口]-测试通过");
        }
    }*/
}
