package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.freeze.FreezeRequest;
import com.jebao.thirdPay.fuiou.model.freeze.FreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class FreezeServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        FreezeRequest reqData = new FreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setCust_no("13678424821");
        reqData.setAmt("10000");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/freeze.action";
        FreezeServiceImpl freezeService = new FreezeServiceImpl();
        FreezeResponse result = freezeService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[冻结]-测试通过");
        }
    }
}
