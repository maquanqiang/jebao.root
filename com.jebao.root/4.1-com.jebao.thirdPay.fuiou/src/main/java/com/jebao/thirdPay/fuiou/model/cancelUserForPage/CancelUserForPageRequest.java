package com.jebao.thirdPay.fuiou.model.cancelUserForPage;

/**
 * Created by Administrator on 2016/9/27.
 */
public class CancelUserForPageRequest {
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号
    private String login_id; //用户登录ID
    private String page_notify_url; //商户返回地址
    private String signature; //签名数据

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

    public String getPage_notify_url() {
        return page_notify_url;
    }

    public void setPage_notify_url(String page_notify_url) {
        this.page_notify_url = page_notify_url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 22.	用户申请注销免登陆接口(网页版)
     *
     * @return
     */
    public String requestSignPlain() {
        String src = login_id+"|"+mchnt_cd+"|"+mchnt_txn_ssn+"|"+page_notify_url;
        return src;
    }
}
