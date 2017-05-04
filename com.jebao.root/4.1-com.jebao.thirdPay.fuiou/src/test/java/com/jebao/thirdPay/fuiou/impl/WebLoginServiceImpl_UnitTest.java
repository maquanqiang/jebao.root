package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.webLogin.WebLoginRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class WebLoginServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        WebLoginRequest reqData = new WebLoginRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setCust_no("19012348888");
        reqData.setLocation("");//0020 快速充值,0022 提现,0024 网银充值
        reqData.setAmt("");
        String httpUrl = "https://jzh-test.fuiou.com/jzh/webLogin.action";
        WebLoginServiceImpl webLoginService = new WebLoginServiceImpl();
        String result = webLoginService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[商户端个人用户跳转登录页面（网页版）]-测试通过");
        }
    }
}
