package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.resetPassWord.ResetPassWordRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ResetPassWordServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        ResetPassWordRequest reqData = new ResetPassWordRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("13901909090");
        /*业务类型;
        1:重置登录密码,
        2:修改登录密码,
        3:支付密码重置*/
        reqData.setBusi_tp("1");

        //此处是错误的地址-- //成功后返回地址
        reqData.setBack_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/resetPassWord.action";
        ResetPassWordServiceImpl resetPassWordService = new ResetPassWordServiceImpl();
        String result = resetPassWordService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[用户密码修改重置免登陆接口(网页版)]-测试通过");
        }
    }
}
