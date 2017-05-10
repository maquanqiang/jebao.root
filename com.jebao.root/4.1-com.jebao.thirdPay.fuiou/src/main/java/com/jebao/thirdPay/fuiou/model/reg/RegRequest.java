package com.jebao.thirdPay.fuiou.model.reg;

import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 *
 * Created by Administrator on 2016/9/26.
 */
public class RegRequest extends BaseRequest{
    private String cust_nm = "";      //注册企业名称
    private String certif_id = "";    //法人身份证号
    private String mobile_no = "";    //手机号
    private String email = "";        //邮箱地址
    private String rem = "";          //备注信息（企业号）
    private String city_id = "";      //开户区县代码
    private String parent_bank_id = "";//开户银行总行号
    private String capAcntNo = "";    //账号
    private String capAcntNm = "";    //账号户名
    private String lpassword = "";    //登录密码
    private String password = "";     //支付密码
    private String signature = "";    //签名数据

    private String bank_nm = "";

    public String getBank_nm() {
        return bank_nm;
    }

    public void setBank_nm(String bank_nm) {
        this.bank_nm = bank_nm;
    }

    //其他get\set方法省略
    //.......
    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
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

    public String getCapAcntNo() {
        return capAcntNo;
    }

    public void setCapAcntNo(String capAcntNo) {
        this.capAcntNo = capAcntNo;
    }

    public String getCapAcntNm() {
        return capAcntNm;
    }

    public void setCapAcntNm(String capAcntNm) {
        this.capAcntNm = capAcntNm;
    }

    public String getLpassword() {
        return lpassword;
    }

    public void setLpassword(String lpassword) {
        this.lpassword = lpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    /**
     * 注册时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = bank_nm + "|" + capAcntNm + "|"
                + capAcntNo + "|" + certif_id + "|" + city_id + "|" + cust_nm + "|"
                + email + "|" + lpassword + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|"
                + mobile_no + "|" + parent_bank_id + "|" + password + "|" + rem;
        return src;
    }
}
