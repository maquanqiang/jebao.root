package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.query.QueryRequest;
import com.jebao.thirdPay.fuiou.model.query.QueryResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--明细查询
 * Created by Administrator on 2016/9/26.
 */
@Service
public class QueryServiceImpl {
    public QueryResponse post(String httpUrl, QueryRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        QueryResponse regResponse= XmlUtil.fromXML(xmlData, QueryResponse.class);
        PrintUtil.printLn(regResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,regResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[明细查询]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return regResponse;
    }
    //测试
  /*  public static void main(String[] args) throws Exception {
        QueryRequest reqData = new QueryRequest();
        reqData.setMchnt_cd("0002900F0041077");
        reqData.setMchnt_txn_ssn("96f14200a794dbcc91cad69b50ef05");
        reqData.setUser_ids("13900000000|13900000001");
        reqData.setStart_day("20120901");
        reqData.setEnd_day("20120910");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/query.action";
        QueryServiceImpl queryService = new QueryServiceImpl();
        QueryResponse result = queryService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[明细查询]-测试通过");
        }
    }*/

}
