package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.unFreeze.UnFreezeRequest;
import com.jebao.thirdPay.fuiou.model.unFreeze.UnFreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class UnFreezeServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        UnFreezeRequest reqData=new UnFreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setCust_no("");
        reqData.setAmt("");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/unFreeze.action";
        UnFreezeServiceImpl service = new UnFreezeServiceImpl();
        UnFreezeResponse result = service.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[解冻（7，8，9，10的冻结资金解冻]-测试通过");
        }
    }
}
