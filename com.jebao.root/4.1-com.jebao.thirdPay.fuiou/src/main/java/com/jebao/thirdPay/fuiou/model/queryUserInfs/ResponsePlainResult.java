package com.jebao.thirdPay.fuiou.model.queryUserInfs;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ResponsePlainResult {
    private String mobile_no;
    private String cust_nm;
    private String login_id;
    private String certif_id;
    private String email;
    private String city_id;
    private String parent_bank_id;
    private String bank_nm;
    private String capAcntNo;
    private String card_pwd_verify_st;
    private String id_nm_verify_st;
    private String contract_st;
    private String user_st;

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

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
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

    public String getCard_pwd_verify_st() {
        return card_pwd_verify_st;
    }

    public void setCard_pwd_verify_st(String card_pwd_verify_st) {
        this.card_pwd_verify_st = card_pwd_verify_st;
    }

    public String getId_nm_verify_st() {
        return id_nm_verify_st;
    }

    public void setId_nm_verify_st(String id_nm_verify_st) {
        this.id_nm_verify_st = id_nm_verify_st;
    }

    public String getContract_st() {
        return contract_st;
    }

    public void setContract_st(String contract_st) {
        this.contract_st = contract_st;
    }

    public String getUser_st() {
        return user_st;
    }

    public void setUser_st(String user_st) {
        this.user_st = user_st;
    }
}
