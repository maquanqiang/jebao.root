package com.jebao.thirdPay.fuiou.model.webReg;

/**
 * Created by Administrator on 2016/9/26.
 */
public class WebRegResponse {
    private String resp_code; //响应码
    private String resp_desc; //响应消息
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //请求流水号
    private String mobile_no; //手机号码
    private String cust_nm; //客户姓名
    private String certif_id; //身份证号码
    private String email; //邮箱地址
    private String city_id; //开户行地区代码
    private String parent_bank_id; //开户行行别
    private String bank_nm; //开户行支行名称
    private String capAcntNo; //帐号
    private String user_id_from; //用户在商户系统的标志
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

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCust_nm() {
        return cust_nm;
    }

    public void setCust_nm(String cust_nm) {
        this.cust_nm = cust_nm;
    }

    public String getCertif_id() {
        return certif_id;
    }

    public void setCertif_id(String certif_id) {
        this.certif_id = certif_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getParent_bank_id() {
        return parent_bank_id;
    }

    public void setParent_bank_id(String parent_bank_id) {
        this.parent_bank_id = parent_bank_id;
    }

    public String getBank_nm() {
        return bank_nm;
    }

    public void setBank_nm(String bank_nm) {
        this.bank_nm = bank_nm;
    }

    public String getCapAcntNo() {
        return capAcntNo;
    }

    public void setCapAcntNo(String capAcntNo) {
        this.capAcntNo = capAcntNo;
    }

    public String getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(String user_id_from) {
        this.user_id_from = user_id_from;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String requestSignPlain() {
        String src = bank_nm+"|"+capAcntNo+"|"+certif_id+"|"+city_id+"|"+cust_nm+"|"+email+"|"+mchnt_cd+"|"+mchnt_txn_ssn+"|"+mobile_no
                +"|"+parent_bank_id+"|"+resp_code+"|"+user_id_from;

        return src;
    }

}
