package com.jebao.thirdPay.fuiou.model.queryChangeCard;

import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 * Created by Administrator on 2016/9/27.
 */
public class QueryChangeCardRequest extends BaseRequest{

    private String login_id; //个人用户
    private String txn_ssn; //交易流水
    private String signature; //签名数据

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getTxn_ssn() {
        return txn_ssn;
    }

    public void setTxn_ssn(String txn_ssn) {
        this.txn_ssn = txn_ssn;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 用户更换银行卡查询接口
     *
     * @return
     */
    public String requestSignPlain() {
        String src = login_id+"|"+ mchnt_cd + "|" + mchnt_txn_ssn + "|" + txn_ssn;
        return src;
    }
}
