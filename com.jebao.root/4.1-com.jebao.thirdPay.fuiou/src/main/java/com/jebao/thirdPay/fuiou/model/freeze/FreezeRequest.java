package com.jebao.thirdPay.fuiou.model.freeze;

/**
 * Created by Administrator on 2016/9/28.
 */
public class FreezeRequest {
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号
    private String cust_no; //冻结目标登录账户
    private String amt; //冻结金额
    private String rem; //备注
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
    /**
     * 冻结时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = amt + "|" + cust_no+"|"+ mchnt_cd + "|" + mchnt_txn_ssn+"|"+ rem;
        return src;
    }
}
