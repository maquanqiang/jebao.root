package com.jebao.thirdPay.fuiou.model.onlineBankRecharge;

/**
 * 商户P2P网站免登录网银充值接口 响应参数
 * Created by Administrator on 2016/9/27.
 */
public class OnlineBankRechargeResponse {
    private String resp_code; //响应码
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //请求流水号
    private String login_id; //交易用户
    private String amt; //交易金额
    private String rem;//备注
    private String signature; //签名数据

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getMchnt_cd() {
        return mchnt_cd;
    }

    public void setMchnt_cd(String mchnt_cd) {
        this.mchnt_cd = mchnt_cd;
    }

    public String getMchnt_txn_ssn() {
        return mchnt_txn_ssn;
    }

    public void setMchnt_txn_ssn(String mchnt_txn_ssn) {
        this.mchnt_txn_ssn = mchnt_txn_ssn;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String requestSignPlain() {
        String src = amt + "|" + login_id + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|" + rem + "|" + resp_code;
        return src;
    }
}
