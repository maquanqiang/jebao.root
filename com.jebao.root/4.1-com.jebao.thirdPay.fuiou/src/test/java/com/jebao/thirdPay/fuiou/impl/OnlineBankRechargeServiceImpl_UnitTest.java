package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.onlineBankRecharge.OnlineBankRechargeRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class OnlineBankRechargeServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        OnlineBankRechargeRequest reqData = new OnlineBankRechargeRequest();
        reqData.setLogin_id("13678424821");
        reqData.setAmt("10000");
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        reqData.setBack_notify_url("");
        String httpUrl = "https://jzh-test.fuiou.com/jzh/500002.action";
        OnlineBankRechargeServiceImpl onlineBankRechargeService = new OnlineBankRechargeServiceImpl();
        String result = onlineBankRechargeService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户P2P网站免登录网银充值接口]-测试通过");
        }
    }
}
