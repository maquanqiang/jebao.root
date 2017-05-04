package com.jebao.thirdPay.fuiou.model.queryChangeCard;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2016/9/27.
 */
@XmlRootElement(name = "plain")
public class QueryChangeCardResponse {
    private String resp_code; //响应码
    private String desc_code; //返回码说明
    private String mchnt_txn_ssn; //请求流水号
    private String login_id; //个人用户
    private String mchnt_cd; //商户代码
    private String txn_ssn; //交易流水
    private String bank_nm; //开户行支行名称
    private String card_no; //银行卡号
    private String examine_st; //审核状态
    private String remark; //备注
    private String signature; //签名数据

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getDesc_code() {
        return desc_code;
    }

    public void setDesc_code(String desc_code) {
        this.desc_code = desc_code;
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

    public String getTxn_ssn() {
        return txn_ssn;
    }

    public void setTxn_ssn(String txn_ssn) {
        this.txn_ssn = txn_ssn;
    }

    public String getBank_nm() {
        return bank_nm;
    }

    public void setBank_nm(String bank_nm) {
        this.bank_nm = bank_nm;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getExamine_st() {
        return examine_st;
    }

    public void setExamine_st(String examine_st) {
        this.examine_st = examine_st;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
