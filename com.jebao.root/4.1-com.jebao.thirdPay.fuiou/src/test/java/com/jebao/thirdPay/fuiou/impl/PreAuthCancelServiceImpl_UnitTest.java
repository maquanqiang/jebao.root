package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.preAuthCancel.PreAuthCancelRequest;
import com.jebao.thirdPay.fuiou.model.preAuthCancel.PreAuthCancelResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class PreAuthCancelServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        PreAuthCancelRequest reqData = new PreAuthCancelRequest();
        reqData.setMchnt_cd("0006510F0093601");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setOut_cust_no("13678424821");
        reqData.setIn_cust_no("13678424821");
        reqData.setContract_no("");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/preAuthCancel.action";
        PreAuthCancelServiceImpl preAuthCancelService = new PreAuthCancelServiceImpl();
        PreAuthCancelResponse result = preAuthCancelService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[预授权撤销接口]-测试通过");
        }
    }
}
