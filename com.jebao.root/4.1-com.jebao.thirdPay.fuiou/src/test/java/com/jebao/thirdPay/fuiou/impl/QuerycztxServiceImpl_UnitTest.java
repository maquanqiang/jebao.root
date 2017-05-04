package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.querycztx.QuerycztxRequest;
import com.jebao.thirdPay.fuiou.model.querycztx.QuerycztxResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class QuerycztxServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        QuerycztxRequest reqData = new QuerycztxRequest();
        reqData.setVer("0.44");
        reqData.setMchnt_cd("0006510F0106121");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setBusi_tp("PW11");
        reqData.setTxn_ssn("");
        reqData.setStart_time("2014-01-01 00:00:00");
        reqData.setEnd_time("2014-01-02 23:59:59");
        reqData.setCust_no("");
        reqData.setTxn_st("");
        reqData.setPage_no("");
        reqData.setPage_size("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/querycztx.action";
        QuerycztxServiceImpl querycztxService = new QuerycztxServiceImpl();
        QuerycztxResponse result = querycztxService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[充值提现]-测试通过");
        }
    }
}
