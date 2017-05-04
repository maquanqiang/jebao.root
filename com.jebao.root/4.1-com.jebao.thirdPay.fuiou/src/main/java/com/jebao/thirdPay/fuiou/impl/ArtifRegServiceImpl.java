package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.artifReg.ArtifRegRequest;
import com.jebao.thirdPay.fuiou.model.artifReg.ArtifRegResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.springframework.stereotype.Service;

/**
 * 富友--法人开户注册接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class ArtifRegServiceImpl {

    public ArtifRegResponse post(ArtifRegRequest reqData) throws Exception {
        String httpUrl= FuiouConfig.url+"artifReg.action";
        return post(httpUrl,reqData);
    }
    public ArtifRegResponse post(String httpUrl, ArtifRegRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        ArtifRegResponse artifRegResponse= XmlUtil.fromXML(xmlData, ArtifRegResponse.class);
        PrintUtil.printLn(artifRegResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,artifRegResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[法人开户注册]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return artifRegResponse;
    }
    //测试
    /**
     *
     * @param args
     * @throws Exception
     */
    /*public static void main(String[] args) throws Exception {
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
    }*/
}
