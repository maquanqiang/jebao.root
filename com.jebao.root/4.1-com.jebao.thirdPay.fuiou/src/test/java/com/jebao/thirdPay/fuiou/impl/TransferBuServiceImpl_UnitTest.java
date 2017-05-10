package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuRequest;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class TransferBuServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        TransferBuRequest reqData=new TransferBuRequest();
        reqData.setOut_cust_no("13678424821");
        reqData.setIn_cust_no("13678424822");
        reqData.setAmt("10000");
        reqData.setContract_no("00000012312312");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/transferBu.action";
        TransferBuServiceImpl transferBuService = new TransferBuServiceImpl();
        TransferBuResponse result = transferBuService.post(reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[划拨(个人与个人之间)]-测试通过");
        }
    }
}
