package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.balanceAction.BalanceActionRequest;
import com.jebao.thirdPay.fuiou.model.balanceAction.BalanceActionResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--余额查询
 * Created by Administrator on 2016/9/26.
 */
@Service
public class BalanceActionServiceImpl {
    public BalanceActionResponse post(BalanceActionRequest reqData) {
        String httpUrl = FuiouConfig.url + "BalanceAction.action";
        try {
            return post(httpUrl, reqData);
        } catch (Exception e) {
            return null;
        }
    }

    public BalanceActionResponse post(String httpUrl, BalanceActionRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        BalanceActionResponse regResponse = XmlUtil.fromXML(xmlData, BalanceActionResponse.class);
        PrintUtil.printLn(regResponse.getSignature());
        String verifyPlain = RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain, regResponse.getSignature());
        if (!isOk) {
            throw new Exception("[余额查询]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return regResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        BalanceActionRequest reqData = new BalanceActionRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setMchnt_txn_dt("20110519");
        reqData.setCust_no("13678424821|13312324854");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/BalanceAction.action";
        BalanceActionServiceImpl balanceActionService = new BalanceActionServiceImpl();
        BalanceActionResponse result = balanceActionService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[余额查询]-测试通过");
        }
    }*/
}
