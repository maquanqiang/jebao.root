package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.webReg.WebRegRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class WebRegServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        WebRegRequest reqData = new WebRegRequest();
        reqData.setUser_id_from("830");
        reqData.setMobile_no("13712012345");
        reqData.setCust_nm("好");
        reqData.setCertif_tp("0");
        reqData.setCertif_id("431301198109297486");
        reqData.setEmail("");
        reqData.setCity_id("");
        reqData.setParent_bank_id("");
        reqData.setBank_nm("");
        reqData.setCapAcntNo("");
//        reqData.setPage_notify_url("http://localhost:9089/api/userfund/registerNotify");
        reqData.setBack_notify_url("");

        String httpUrl = "https://jzh-test.fuiou.com/jzh/webReg.action";
        WebRegServiceImpl webRegService = new WebRegServiceImpl();
        String result = webRegService.post(reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[个人用户自助开户注册（网页版）]-测试通过");
        }

    }
}
