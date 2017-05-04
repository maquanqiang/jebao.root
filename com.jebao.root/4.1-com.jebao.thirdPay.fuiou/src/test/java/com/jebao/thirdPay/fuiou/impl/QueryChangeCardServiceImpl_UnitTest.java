package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.queryChangeCard.QueryChangeCardRequest;
import com.jebao.thirdPay.fuiou.model.queryChangeCard.QueryChangeCardResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class QueryChangeCardServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        QueryChangeCardRequest reqData=new QueryChangeCardRequest();
        reqData.setLogin_id("");
        reqData.setTxn_ssn("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/queryChangeCard.action";
        QueryChangeCardServiceImpl service = new QueryChangeCardServiceImpl();
        QueryChangeCardResponse result = service.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[用户更换银行卡查询接口]-测试通过");
        }
    }
}
