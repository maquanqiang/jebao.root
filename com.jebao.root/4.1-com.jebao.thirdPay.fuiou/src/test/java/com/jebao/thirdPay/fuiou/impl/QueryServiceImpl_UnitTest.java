package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.query.QueryRequest;
import com.jebao.thirdPay.fuiou.model.query.QueryResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/3.
 */

public class QueryServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        QueryRequest reqData = new QueryRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setUser_ids("13900000000|13900000001");
        reqData.setStart_day("20120901");
        reqData.setEnd_day("20120910");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/query.action";
        QueryServiceImpl queryService = new QueryServiceImpl();
        QueryResponse result = queryService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[明细查询]-测试通过");
        }
    }

}
