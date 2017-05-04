package com.jebao.thirdPay.fuiou.model.gotoOnlineBankRecharge;

/**
 * Created by Administrator on 2016/9/27.
 */
public class GotoOnlineBankRechargeRequest {
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号
    private String login_id; //用户登录名
    private String amt; //充值金额
    private String order_pay_type; //支付类型
    private String iss_ins_cd; //银行代码
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

    public String getOrder_pay_type() {
        return order_pay_type;
    }

    public void setOrder_pay_type(String order_pay_type) {
        this.order_pay_type = order_pay_type;
    }

    public String getIss_ins_cd() {
        return iss_ins_cd;
    }

    public void setIss_ins_cd(String iss_ins_cd) {
        this.iss_ins_cd = iss_ins_cd;
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
     * P2P免登录直接跳转网银界面充值接口请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = amt + "|" + back_notify_url + "|" + iss_ins_cd + "|" + login_id + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|" + order_pay_type + "|" + page_notify_url;
        return src;
    }
}
