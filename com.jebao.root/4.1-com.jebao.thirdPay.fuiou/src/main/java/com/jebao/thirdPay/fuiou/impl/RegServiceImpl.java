package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.reg.RegRequest;
import com.jebao.thirdPay.fuiou.model.reg.RegResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.springframework.stereotype.Service;

/**
 * 富友--开户注册
 * Created by Administrator on 2016/9/26.
 */
@Service
public class RegServiceImpl {
    public RegResponse post(RegRequest reqData) {
        String httpUrl= FuiouConfig.url+"reg.action";
        try {
            return post(httpUrl,reqData);
        } catch (Exception e) {
            return null;
        }
    }

    public RegResponse post(String httpUrl, RegRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        RegResponse regResponse= XmlUtil.fromXML(xmlData, RegResponse.class);
        PrintUtil.printLn(regResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,regResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[开户注册]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return regResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        RegRequest reqData = new RegRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setCust_nm("猪");
        reqData.setCertif_id("330382111111010110");
        reqData.setMobile_no("11111111111");
        reqData.setEmail("");
        reqData.setCity_id("3333");
        reqData.setParent_bank_id("0103");
        reqData.setBank_nm("中国农业银行");
        reqData.setCapAcntNm("");
        reqData.setCapAcntNo("62284803111111111111");
        reqData.setPassword("97b149a269795ef98a7e31b66d1f105e");
        reqData.setLpassword("97b149a269795ef98a7e31b66d1f105e");
        reqData.setRem("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/reg.action";
        RegServiceImpl regService = new RegServiceImpl();
        RegResponse result = regService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[开户注册]-测试通过");
        }
    }*/
}

