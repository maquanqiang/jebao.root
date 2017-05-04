package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.changeCard.ChangeCardRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ChangeCardServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        ChangeCardRequest reqData = new ChangeCardRequest();
        reqData.setLogin_id("");

        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/changeCard2.action";
        ChangeCardServiceImpl changeCardService = new ChangeCardServiceImpl();
        String result = changeCardService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户P2P网站免登录用户更换银行卡接口]-测试通过");
        }
    }
}
