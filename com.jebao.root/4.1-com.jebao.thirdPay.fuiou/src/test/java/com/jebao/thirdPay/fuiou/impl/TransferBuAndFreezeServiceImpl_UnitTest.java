package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze.TransferBuAndFreezeRequest;
import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze.TransferBuAndFreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class TransferBuAndFreezeServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        TransferBuAndFreezeRequest reqData = new TransferBuAndFreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setOut_cust_no("1367842482");
        reqData.setIn_cust_no("1367842482");
        reqData.setAmt("10000");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/transferBuAndFreeze.action";
        TransferBuAndFreezeServiceImpl transferBuAndFreezeService = new TransferBuAndFreezeServiceImpl();
        TransferBuAndFreezeResponse result = transferBuAndFreezeService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[划拨预冻结]-测试通过");
        }
    }
}
