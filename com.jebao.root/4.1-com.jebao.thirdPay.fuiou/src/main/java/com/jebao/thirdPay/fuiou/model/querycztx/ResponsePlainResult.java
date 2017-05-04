package com.jebao.thirdPay.fuiou.model.querycztx;

/**
 * Created by wangwei on 2016/9/26.
 */
public class ResponsePlainResult {
    private String ext_tp;
    private String txn_date;
    private String txn_time;
    private String mchnt_ssn;
    private String txn_amt;
    private String fuiou_acct_no;
    private String cust_no;
    private String artif_nm;
    private String remark;
    private String txn_rsp_cd;
    private String rsp_cd_desc;

    public String getExt_tp() {
        return ext_tp;
    }

    public void setExt_tp(String ext_tp) {
        this.ext_tp = ext_tp;
    }

    public String getTxn_date() {
        return txn_date;
    }

    public void setTxn_date(String txn_date) {
        this.txn_date = txn_date;
    }

    public String getTxn_time() {
        return txn_time;
    }

    public void setTxn_time(String txn_time) {
        this.txn_time = txn_time;
    }

    public String getMchnt_ssn() {
        return mchnt_ssn;
    }

    public void setMchnt_ssn(String mchnt_ssn) {
        this.mchnt_ssn = mchnt_ssn;
    }

    public String getTxn_amt() {
        return txn_amt;
    }

    public void setTxn_amt(String txn_amt) {
        this.txn_amt = txn_amt;
    }

    public String getFuiou_acct_no() {
        return fuiou_acct_no;
    }

    public void setFuiou_acct_no(String fuiou_acct_no) {
        this.fuiou_acct_no = fuiou_acct_no;
    }

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public String getArtif_nm() {
        return artif_nm;
    }

    public void setArtif_nm(String artif_nm) {
        this.artif_nm = artif_nm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTxn_rsp_cd() {
        return txn_rsp_cd;
    }

    public void setTxn_rsp_cd(String txn_rsp_cd) {
        this.txn_rsp_cd = txn_rsp_cd;
    }

    public String getRsp_cd_desc() {
        return rsp_cd_desc;
    }

    public void setRsp_cd_desc(String rsp_cd_desc) {
        this.rsp_cd_desc = rsp_cd_desc;
    }
}
