package com.jebao.thirdPay.fuiou.model.queryTxn;

/**
 * Created by Administrator on 2016/9/26.
 */
public class QueryTxnRequest {
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号
    private String busi_tp; //交易类型
    private String start_day; //起始时间
    private String end_day; //截止时间
    private String txn_ssn; //交易流水
    private String cust_no; //交易用户
    private String txn_st; //交易状态
    private String remark; //交易备注
    private String page_no; //页码
    private String page_size; //每页条数
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

    public String getBusi_tp() {
        return busi_tp;
    }

    public void setBusi_tp(String busi_tp) {
        this.busi_tp = busi_tp;
    }

    public String getStart_day() {
        return start_day;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getEnd_day() {
        return end_day;
    }

    public void setEnd_day(String end_day) {
        this.end_day = end_day;
    }

    public String getTxn_ssn() {
        return txn_ssn;
    }

    public void setTxn_ssn(String txn_ssn) {
        this.txn_ssn = txn_ssn;
    }

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public String getTxn_st() {
        return txn_st;
    }

    public void setTxn_st(String txn_st) {
        this.txn_st = txn_st;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 交易查询时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = busi_tp+"|"+cust_no+"|"+end_day+"|"+mchnt_cd+"|"+mchnt_txn_ssn+"|"+page_no+"|"+page_size+"|"+remark+"|"+start_day+"|"+txn_ssn+"|"+txn_st;
        return src;
    }
}
