package com.jebao.thirdPay.fuiou.model.authConfig;

/**
 * Created by Administrator on 2016/9/27.
 */
public class AuthConfigResponse {
    private String resp_code; //响应码
    private String resp_desc; //响应消息
    private String mchnt_txn_ssn; //请求流水号
    private String login_id; //交易用户
    private String mchnt_cd; //商户代码
    private String signature; //签名数据

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_desc() {
        return resp_desc;
    }

    public void setResp_desc(String resp_desc) {
        this.resp_desc = resp_desc;
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

    public String getMchnt_cd() {
        return mchnt_cd;
    }

    public void setMchnt_cd(String mchnt_cd) {
        this.mchnt_cd = mchnt_cd;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * PC金账户免登陆授权配置（短信通知+委托交易）时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = login_id + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|" + resp_code + "|" + resp_desc;
        return src;
    }
}
