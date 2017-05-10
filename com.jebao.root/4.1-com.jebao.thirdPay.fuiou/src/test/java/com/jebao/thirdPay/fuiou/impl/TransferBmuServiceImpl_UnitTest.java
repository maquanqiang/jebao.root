package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.transferBmu.TransferBmuRequest;
import com.jebao.thirdPay.fuiou.model.transferBmu.TransferBmuResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class TransferBmuServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        TransferBmuRequest reqData = new TransferBmuRequest();
//        reqData.setMchnt_cd("0002900F0041077");
//        reqData.setMchnt_txn_ssn("11032302065863805331");
        reqData.setOut_cust_no("jzh09");
        reqData.setIn_cust_no("15910757156");
        reqData.setAmt("10000");
        reqData.setContract_no("");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/transferBmu.action";
        TransferBmuServiceImpl transferBmuService = new TransferBmuServiceImpl();
        TransferBmuResponse result = transferBmuService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[转账(商户与个人之间)接口]-测试通过");
        }
    }
}
