package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.personQuickPay.PersonQuickPayRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class PersonQuickPayServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        PersonQuickPayRequest reqData = new PersonQuickPayRequest();
/*        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");*/
        reqData.setLogin_id("");
        reqData.setAmt("");
        reqData.setBack_notify_url("");

        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/500405.action";
        PersonQuickPayServiceImpl personQuickPayService = new PersonQuickPayServiceImpl();
        String result = personQuickPayService.post(reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[PC端个人用户免登录快捷充值]-测试通过");
        }
    }
}
