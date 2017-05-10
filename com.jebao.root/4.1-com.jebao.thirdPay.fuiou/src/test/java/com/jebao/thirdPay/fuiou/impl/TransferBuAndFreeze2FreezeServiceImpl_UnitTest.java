package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze2Freeze.TransferBuAndFreeze2FreezeRequest;
import com.jebao.thirdPay.fuiou.model.transferBuAndFreeze2Freeze.TransferBuAndFreeze2FreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class TransferBuAndFreeze2FreezeServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        TransferBuAndFreeze2FreezeRequest reqData=new TransferBuAndFreeze2FreezeRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setOut_cust_no("");
        reqData.setIn_cust_no("");
        reqData.setAmt("");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/transferBuAndFreeze2Freeze.action";
        TransferBuAndFreeze2FreezeServiceImpl service = new TransferBuAndFreeze2FreezeServiceImpl();
        TransferBuAndFreeze2FreezeResponse result = service.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[冻结到冻结接口]-测试通过");
        }
    }
}
