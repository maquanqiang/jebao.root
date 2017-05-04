package com.jebao.thirdPay.fuiou.app.impl;

import com.jebao.thirdPay.fuiou.app.model.withdrawDeposit.WithdrawDepositRequest;
import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebFormUtils;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 商户APP个人用户免登录提现 接口
 * Created by Administrator on 2017/1/17.
 */
@Service
public class AppWithdrawDepositServiceImpl {
    public String post(WithdrawDepositRequest reqData) {
        String httpUrl = FuiouConfig.url + "app/500003.action";
        reqData.setPage_notify_url(FuiouConfig.Jebao_Notify_Origin + "mobile/api/withdrawDepositNotify");
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
}
