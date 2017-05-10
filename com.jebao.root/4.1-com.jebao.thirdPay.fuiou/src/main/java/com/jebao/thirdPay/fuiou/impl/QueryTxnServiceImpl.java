package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.queryTxn.QueryTxnRequest;
import com.jebao.thirdPay.fuiou.model.queryTxn.QueryTxnResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--交易查询接口
 * Created by Administrator on 2016/9/26.
 */
@Service
public class QueryTxnServiceImpl {
    public QueryTxnResponse post(String httpUrl, QueryTxnRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        QueryTxnResponse queryTxnResponse= XmlUtil.fromXML(xmlData, QueryTxnResponse.class);
        PrintUtil.printLn(queryTxnResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,queryTxnResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[交易查询]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return queryTxnResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        QueryTxnRequest reqData = new QueryTxnRequest();
        reqData.setMchnt_cd("0001000F0339593");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setBusi_tp("PWPC");
        reqData.setStart_day("20120901");
        reqData.setEnd_day("20120910");
        reqData.setTxn_ssn("");
        reqData.setCust_no("18610001234");
        reqData.setTxn_st("");
        reqData.setRemark("");
        reqData.setPage_no("1");
        reqData.setPage_size("10");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/queryTxn.action";
        QueryTxnServiceImpl queryTxnService = new QueryTxnServiceImpl();
        QueryTxnResponse result = queryTxnService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[交易查询]-测试通过");
        }
    }*/
}
