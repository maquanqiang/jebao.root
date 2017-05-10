package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.queryUserInfs.QueryUserInfsRequest;
import com.jebao.thirdPay.fuiou.model.queryUserInfs.QueryUserInfsResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--用户信息查询接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class QueryUserInfsServiceImpl {
    public QueryUserInfsResponse post(QueryUserInfsRequest reqData) {
        String httpUrl= FuiouConfig.url+"queryUserInfs.action";
        try {
            return post(httpUrl,reqData);
        } catch (Exception e) {
            return null;
        }
    }

    public QueryUserInfsResponse post(String httpUrl, QueryUserInfsRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        QueryUserInfsResponse queryUserInfsResponse= XmlUtil.fromXML(xmlData, QueryUserInfsResponse.class);
        PrintUtil.printLn(queryUserInfsResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,queryUserInfsResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[用户信息查询]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return queryUserInfsResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        QueryUserInfsRequest reqData = new QueryUserInfsRequest();
        reqData.setVer("0.44");
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setMchnt_txn_dt("20110519");
        reqData.setUser_ids("13678424821|13312324854");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/queryUserInfs.action";
        QueryUserInfsServiceImpl queryUserInfsService = new QueryUserInfsServiceImpl();
        QueryUserInfsResponse result = queryUserInfsService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[用户信息查询]-测试通过");
        }
    }*/
}
