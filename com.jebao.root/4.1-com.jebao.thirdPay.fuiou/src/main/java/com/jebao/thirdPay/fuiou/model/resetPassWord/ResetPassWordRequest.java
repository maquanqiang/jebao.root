package com.jebao.thirdPay.fuiou.model.resetPassWord;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ResetPassWordRequest {
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号
    private String login_id; //用户登录ID
    private String busi_tp; //业务类型
    private String back_url; //返回地址
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

    public String getBusi_tp() {
        return busi_tp;
    }

    public void setBusi_tp(String busi_tp) {
        this.busi_tp = busi_tp;
    }

    public String getBack_url() {
        return back_url;
    }

    public void setBack_url(String back_url) {
        this.back_url = back_url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 用户密码修改重置免登陆接口(网页版)
     *
     * @return
     */
    public String requestSignPlain() {
        String src = busi_tp+"|"+login_id+"|"+mchnt_cd+"|"+mchnt_txn_ssn;
        return src;
    }
}
