package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.balanceAction.BalanceActionRequest;
import com.jebao.thirdPay.fuiou.model.balanceAction.BalanceActionResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class BalanceActionServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        BalanceActionRequest reqData = new BalanceActionRequest();
        reqData.setMchnt_txn_dt("20110519");
        reqData.setCust_no("13678424821|13312324854");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/BalanceAction.action";
        BalanceActionServiceImpl balanceActionService = new BalanceActionServiceImpl();
        BalanceActionResponse result = balanceActionService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[余额查询]-测试通过");
        }
    }
}
