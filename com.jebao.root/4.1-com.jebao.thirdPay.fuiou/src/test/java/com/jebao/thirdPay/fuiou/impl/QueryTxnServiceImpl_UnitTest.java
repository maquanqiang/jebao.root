package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.queryTxn.QueryTxnRequest;
import com.jebao.thirdPay.fuiou.model.queryTxn.QueryTxnResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class QueryTxnServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        QueryTxnRequest reqData = new QueryTxnRequest();
        reqData.setMchnt_cd("0001000F0339593");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setBusi_tp("PWPC");
        reqData.setStart_day("20120901");
        reqData.setEnd_day("20120910");
        reqData.setTxn_ssn("");
        reqData.setCust_no("18610001234");
        reqData.setTxn_st("");
        reqData.setRemark("");
        reqData.setPage_no("1");
        reqData.setPage_size("10");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/queryTxn.action";
        QueryTxnServiceImpl queryTxnService = new QueryTxnServiceImpl();
        QueryTxnResponse result = queryTxnService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[交易查询]-测试通过");
        }
    }
}
