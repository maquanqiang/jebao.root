package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.gotoOnlineBankRecharge.GotoOnlineBankRechargeRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class GotoOnlineBankRechargeServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        GotoOnlineBankRechargeRequest reqData = new GotoOnlineBankRechargeRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805738");
        reqData.setLogin_id("13678424821");
        reqData.setAmt("10000");
        reqData.setOrder_pay_type("B2C");
        reqData.setIss_ins_cd("0803090000");
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        reqData.setBack_notify_url("");
        String httpUrl = "https://jzh-test.fuiou.com/jzh/500012.action";
        GotoOnlineBankRechargeServiceImpl gotoonlineBankRechargeService = new GotoOnlineBankRechargeServiceImpl();
        String result = gotoonlineBankRechargeService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[P2P免登录直接跳转网银界面充值接口]-测试通过");
        }
    }
}
