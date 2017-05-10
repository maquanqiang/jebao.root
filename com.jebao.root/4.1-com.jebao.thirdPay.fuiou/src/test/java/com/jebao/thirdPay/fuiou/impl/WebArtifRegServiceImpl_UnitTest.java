package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.webArtifReg.WebArtifRegRequest;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class WebArtifRegServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        WebArtifRegRequest reqData = new WebArtifRegRequest();
        reqData.setVer("0.44");
        reqData.setMchnt_cd("0002900F0338384");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setUser_id_from("");
        reqData.setMobile_no("");
        reqData.setCust_nm("");
        reqData.setArtif_nm("");
        reqData.setCertif_id("");
        reqData.setEmail("");
        reqData.setCity_id("");
        reqData.setParent_bank_id("");
        reqData.setBank_nm("");
        reqData.setCapAcntNo("");
        reqData.setPage_notify_url("http://www.jebao.net/html/companySingle.do");
        reqData.setBack_notify_url("");

        String httpUrl = "https://jzh-test.fuiou.com/jzh/webArtifReg.action";
        WebArtifRegServiceImpl webArtifRegRegService = new WebArtifRegServiceImpl();
        String result = webArtifRegRegService.post(httpUrl, reqData);
        PrintUtil.printLn(result);
        if (result != null) {
            PrintUtil.printLn("[法人用户自助开户注册（网页版）]-测试通过");
        }
    }
}
