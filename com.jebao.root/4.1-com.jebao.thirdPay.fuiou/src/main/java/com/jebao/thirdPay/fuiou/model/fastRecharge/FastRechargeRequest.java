package com.jebao.thirdPay.fuiou.model.fastRecharge;

import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 * 商户P2P网站免登录快速充值接口 请求参数
 * Created by Administrator on 2016/9/27.
 */
public class FastRechargeRequest extends BaseRequest {
/*    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号*/
    private String login_id; //用户登录名
    private String amt; //充值金额
    private String page_notify_url; //商户返回地址
    private String back_notify_url; //商户后台通知地址
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

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPage_notify_url() {
        return page_notify_url;
    }

    public void setPage_notify_url(String page_notify_url) {
        this.page_notify_url = page_notify_url;
    }

    public String getBack_notify_url() {
        return back_notify_url;
    }

    public void setBack_notify_url(String back_notify_url) {
        this.back_notify_url = back_notify_url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 商户P2P网站免登录快速充值请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = amt + "|" + back_notify_url + "|" + login_id+"|"+ mchnt_cd + "|" + mchnt_txn_ssn+ "|" + page_notify_url;
        return src;
    }
}
