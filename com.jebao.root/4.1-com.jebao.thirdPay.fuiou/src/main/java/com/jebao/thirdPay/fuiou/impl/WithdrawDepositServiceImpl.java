package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.model.withdrawDeposit.WithdrawDepositRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 富友--商户P2P网站免登录提现接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class WithdrawDepositServiceImpl {
    public String post(WithdrawDepositRequest reqData) {
        String httpUrl = FuiouConfig.url + "500003.action";
        reqData.setPage_notify_url(FuiouConfig.Jebao_Notify_Origin + "api/user/withdrawDepositNotify");
        try {
            return post(httpUrl, reqData);
        } catch (Exception e) {
            return null;
        }
    }

    public String post(String httpUrl, WithdrawDepositRequest reqData) throws Exception {
        PrintUtil.printLn("Sign:" + reqData.requestSignPlain());
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String formHtml = WebFormUtils.createFormHtml(httpUrl, reqData);
        return formHtml;
    }

    //测试
    /*public static void main(String[] args) throws Exception {
        WithdrawDepositRequest reqData = new WithdrawDepositRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805738");
        reqData.setLogin_id("13678424821");
        reqData.setAmt("10000");
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        reqData.setBack_notify_url("");
        String httpUrl = "https://jzh-test.fuiou.com/jzh/500003.action";
        WithdrawDepositServiceImpl withdrawDepositService = new WithdrawDepositServiceImpl();
        String result = withdrawDepositService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户P2P网站免登录提现接口]-测试通过");
        }
    }*/
}
