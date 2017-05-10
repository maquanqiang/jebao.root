package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.model.artifReg.ArtifRegRequest;
import com.jebao.thirdPay.fuiou.model.artifReg.ArtifRegResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ArtifRegServiceImpl_UnitTest {
    @Test
    public void Test() throws Exception {
        ArtifRegRequest reqData = new ArtifRegRequest();
        reqData.setMchnt_cd("0001000F0342510");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setCust_nm("公司");
        reqData.setArtif_nm("个人");
        reqData.setCertif_id("330382111111010110");
        reqData.setMobile_no("11111111111");
        reqData.setEmail("");
        reqData.setCity_id("3333");
        reqData.setParent_bank_id("0103");
        reqData.setBank_nm("中国农业银行");
        reqData.setCapAcntNo("62284803111111111111");
        reqData.setPassword("97b149a269795ef98a7e31b66d1f105e");
        reqData.setLpassword("97b149a269795ef98a7e31b66d1f105e");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/artifReg.action";
        ArtifRegServiceImpl artifRegService = new ArtifRegServiceImpl();
        ArtifRegResponse result = artifRegService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[法人开户注册]-测试通过");
        }
    }
}
