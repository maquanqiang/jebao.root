package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.fastRecharge.FastRechargeRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class FastRechargeServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        FastRechargeRequest reqData = new FastRechargeRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("13678424821");
        reqData.setAmt("10000");
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        reqData.setBack_notify_url("");
        String httpUrl = "https://jzh-test.fuiou.com/jzh/500001.action";
        FastRechargeServiceImpl fastRechargeService = new FastRechargeServiceImpl();
        String result = fastRechargeService.post(reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户P2P网站免登录快速充值接口]-测试通过");
        }
    }
}
