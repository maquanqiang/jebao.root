package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.queryChangeCard.QueryChangeCardRequest;
import com.jebao.thirdPay.fuiou.model.queryChangeCard.QueryChangeCardResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--用户更换银行卡查询接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class QueryChangeCardServiceImpl {
    public QueryChangeCardResponse post(QueryChangeCardRequest reqData)  {
        String httpUrl= FuiouConfig.url+"queryChangeCard.action";
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        try {
            return post(httpUrl,reqData);
        } catch (Exception e) {
            return null;
        }
    }
    public QueryChangeCardResponse post(String httpUrl, QueryChangeCardRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        String resSignature= RegexUtil.getFirstMatch(xmlData, "(?<=<signature>)[\\s\\S]+(?=</signature>)");
        PrintUtil.printLn(resSignature);
        boolean isOk = SecurityUtils.verifySign(verifyPlain,resSignature);
        if(!isOk)
        {
            throw new Exception("[用户更换银行卡查询接口]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        QueryChangeCardResponse regResponse= XmlUtil.fromXML(verifyPlain, QueryChangeCardResponse.class);

        return regResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        QueryChangeCardRequest reqData=new QueryChangeCardRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setLogin_id("");
        reqData.setTxn_ssn("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/queryChangeCard.action";
        QueryChangeCardServiceImpl service = new QueryChangeCardServiceImpl();
        QueryChangeCardResponse result = service.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[用户更换银行卡查询接口]-测试通过");
        }
    }*/

}
