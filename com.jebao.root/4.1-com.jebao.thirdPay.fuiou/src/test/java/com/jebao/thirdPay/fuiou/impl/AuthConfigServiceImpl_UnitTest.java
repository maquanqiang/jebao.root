package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.authConfig.AuthConfigRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class AuthConfigServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        AuthConfigRequest reqData = new AuthConfigRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("");
        reqData.setBusi_tp("");
        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //
        String httpUrl = "http://www-1.fuiou.com:9057/jzh/authConfig.action";
        AuthConfigServiceImpl authConfigService = new AuthConfigServiceImpl();
        String result = authConfigService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[PC金账户免登陆授权配置（短信通知+委托交易）（网页版）]-测试通过");
        }
    }
}
