package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthRequest;
import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class PreAuthServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        PreAuthRequest reqData = new PreAuthRequest();
        reqData.setOut_cust_no("13678424821");
        reqData.setIn_cust_no("13678424822");
        reqData.setAmt("10000");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/preAuth.action";
        PreAuthServiceImpl preAuthService = new PreAuthServiceImpl();
        PreAuthResponse result = preAuthService.post(reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[预授权接口]-测试通过");
        }
    }
}
