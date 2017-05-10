package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.FuiouRecharge.FuiouRechargeRequest;
import com.jebao.thirdPay.fuiou.model.FuiouRecharge.FuiouRechargeResponse;
import com.jebao.thirdPay.fuiou.model.FuiouRecharge.RechargeResult;
import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import com.jebao.thirdPay.fuiou.model.freeze.FreezeResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Lee on 2017/3/29.
 */
@Service
public class FuiouRechargeServiceImpl {

    public RechargeResult responseRecharge(FuiouRechargeRequest reqData) {

        RechargeResult result = new RechargeResult();

        boolean isOk = SecurityUtils.verifySign(reqData.requestSignPlain(),reqData.getSignature());
        BasePlain plain = new BasePlain();
        plain.setMchnt_txn_ssn(reqData.getMchnt_txn_ssn());
        plain.setMchnt_cd(reqData.getMchnt_cd());
        if(isOk){
            plain.setResp_code("0000");
        }else{
            plain.setResp_code("5002");
        }

        FuiouRechargeResponse response = new FuiouRechargeResponse();
        response.setPlain(plain);

        String xmlData = XmlUtil.toXML(response);
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");

        String signatureStr = SecurityUtils.sign(verifyPlain);
        response.setSignature(signatureStr);

        String respData = XmlUtil.toXML(response);
        result.setFlag(isOk);
        result.setXmlResp(respData);
        PrintUtil.printLn(respData);

        return result;
    }
}
