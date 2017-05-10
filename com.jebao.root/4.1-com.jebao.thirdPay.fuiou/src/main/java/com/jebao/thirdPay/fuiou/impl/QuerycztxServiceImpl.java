package com.jebao.thirdPay.fuiou.impl;

import com.jebao.thirdPay.fuiou.http.WebUtils;
import com.jebao.thirdPay.fuiou.model.querycztx.QuerycztxRequest;
import com.jebao.thirdPay.fuiou.model.querycztx.QuerycztxResponse;
import com.jebao.thirdPay.fuiou.util.PrintUtil;
import com.jebao.thirdPay.fuiou.util.RegexUtil;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.jebao.thirdPay.fuiou.util.XmlUtil;

/**
 * 富友--充值提现接口
 * Created by Administrator on 2016/9/27.
 */
@Service
public class QuerycztxServiceImpl {
    public QuerycztxResponse post(String httpUrl, QuerycztxRequest reqData) throws Exception {
        String signatureStr = SecurityUtils.sign(reqData.requestSignPlain());
        reqData.setSignature(signatureStr);
        String xmlData = WebUtils.sendHttp(httpUrl, reqData);
        PrintUtil.printLn(xmlData);
        QuerycztxResponse querycztxResponse= XmlUtil.fromXML(xmlData, QuerycztxResponse.class);
        PrintUtil.printLn(querycztxResponse.getSignature());
        String verifyPlain= RegexUtil.getFirstMatch(xmlData, "<plain>[\\s\\S]+</plain>");
        boolean isOk = SecurityUtils.verifySign(verifyPlain,querycztxResponse.getSignature());
        if(!isOk)
        {
            throw new Exception("[充值提现]-富友返回报文签名验证失败-验签时数据与富友返回报文的signature不一致！");
        }
        return querycztxResponse;
    }
    //测试
    /*public static void main(String[] args) throws Exception {
        QuerycztxRequest reqData = new QuerycztxRequest();
        reqData.setVer("0.44");
        reqData.setMchnt_cd("0006510F0106121");
        reqData.setMchnt_txn_ssn("11032302065863805732");
        reqData.setBusi_tp("PW11");
        reqData.setTxn_ssn("");
        reqData.setStart_time("2014-01-01 00:00:00");
        reqData.setEnd_time("2014-01-02 23:59:59");
        reqData.setCust_no("");
        reqData.setTxn_st("");
        reqData.setPage_no("");
        reqData.setPage_size("");
        //
        String httpUrl = "https://jzh-test.fuiou.com/jzh/querycztx.action";
        QuerycztxServiceImpl querycztxService = new QuerycztxServiceImpl();
        QuerycztxResponse result = querycztxService.post(httpUrl,reqData);
        if(result!=null)
        {
            PrintUtil.printLn("[充值提现]-测试通过");
        }
    }*/

}
