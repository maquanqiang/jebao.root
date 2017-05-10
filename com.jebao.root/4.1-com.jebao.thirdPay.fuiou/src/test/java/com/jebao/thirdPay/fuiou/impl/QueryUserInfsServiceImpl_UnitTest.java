package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.queryUserInfs.QueryUserInfsRequest;
import com.jebao.thirdPay.fuiou.model.queryUserInfs.QueryUserInfsResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class QueryUserInfsServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        QueryUserInfsRequest reqData = new QueryUserInfsRequest();
        reqData.setVer("0.44");
        reqData.setMchnt_txn_dt("20110519");
        reqData.setUser_ids("13678424821|13312324854");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/queryUserInfs.action";
        QueryUserInfsServiceImpl queryUserInfsService = new QueryUserInfsServiceImpl();
        QueryUserInfsResponse result = queryUserInfsService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[用户信息查询]-测试通过");
        }
    }
}
