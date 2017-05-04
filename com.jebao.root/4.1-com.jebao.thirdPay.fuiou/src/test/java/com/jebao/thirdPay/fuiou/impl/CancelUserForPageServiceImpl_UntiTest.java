package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.FuiouRecharge.FuiouRechargeRequest;
import com.jebao.thirdPay.fuiou.model.FuiouRecharge.FuiouRechargeResponse;
import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import com.jebao.thirdPay.fuiou.model.cancelUserForPage.CancelUserForPageRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.junit.Test;
import org.springframework.core.env.SystemEnvironmentPropertySource;

/**
 * Created by Administrator on 2016/11/4.
 */
public class CancelUserForPageServiceImpl_UntiTest {
    @Test
    public void Test() throws Exception {
        CancelUserForPageRequest reqData = new CancelUserForPageRequest();
        reqData.setMchnt_cd("0002900F0041270");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setLogin_id("");

        //此处是错误的地址--
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        //接口地址
        String httpUrl = "https://jzh-test.fuiou.com/jzh/cancelUserForPage.action";
        CancelUserForPageServiceImpl cancelUserForPageService = new CancelUserForPageServiceImpl();
        String result = cancelUserForPageService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[用户申请注销免登陆接口(网页版)]-测试通过");
        }
    }

    @Test
    public void test1(){
        BasePlain plain = new BasePlain();
        plain.setMchnt_cd("aaaaa");
        plain.setMchnt_txn_ssn("20170000");
        plain.setResp_code("0000");
        FuiouRechargeResponse resp = new FuiouRechargeResponse();
        resp.setSignature("11111");
        resp.setPlain(plain);
        String s = XmlUtil.toXML(resp);

        PrintUtil.printLn(s);
        String verifyPlain= RegexUtil.getFirstMatch(s, "<plain>[\\s\\S]+</plain>");
        PrintUtil.printLn(verifyPlain);
    }
}
