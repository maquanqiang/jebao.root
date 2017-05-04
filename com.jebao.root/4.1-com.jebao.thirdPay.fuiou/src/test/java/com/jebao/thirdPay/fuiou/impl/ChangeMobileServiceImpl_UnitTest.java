package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.changeMobile.ChangeMobileRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ChangeMobileServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        ChangeMobileRequest reqData = new ChangeMobileRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("13901909090");

        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/400101.action";
        ChangeMobileServiceImpl changeMobileService = new ChangeMobileServiceImpl();
        String result = changeMobileService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[个人PC端更换手机号]-测试通过");
        }
    }
}
