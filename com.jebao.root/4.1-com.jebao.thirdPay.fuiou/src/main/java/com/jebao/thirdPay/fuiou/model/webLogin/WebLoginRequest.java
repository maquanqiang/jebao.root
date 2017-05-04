package com.jebao.thirdPay.fuiou.model.webLogin;

/**
 * Created by Administrator on 2016/9/27.
 */
public class WebLoginRequest {
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号
    private String cust_no; //登录账户
    private String location; //成功登录后跳转页面代码
    private String amt; //跳转充值、提现页面锁定金额
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

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 商户端个人用户跳转登录页面（网页版）时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = amt+"|"+cust_no +"|"+ location +"|"+mchnt_cd+"|"+mchnt_txn_ssn;
        return src;
    }
}
