package com.jebao.thirdPay.fuiou.model.webReg;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 * Created by Administrator on 2016/9/26.
 */
public class WebRegRequest extends BaseRequest {
    private String ver = FuiouConfig.documentVersion;
    private String user_id_from; //用户在商户系统的标志
    private String mobile_no; //手机号码
    private String cust_nm; //客户姓名
    private String certif_tp; //证件类型
    private String certif_id; //身份证号码/证件
    private String email; //邮箱地址
    private String city_id; //开户行地区代码
    private String parent_bank_id; //开户行行别
    private String bank_nm; //开户行支行名称
    private String capAcntNo; //帐号
    private String page_notify_url; //商户返回地址
    private String back_notify_url; //商户后台通知地址
    private String signature; //签名数据

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(String user_id_from) {
        this.user_id_from = user_id_from;
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

    public String getCertif_tp() {
        return certif_tp;
    }

    public void setCertif_tp(String certif_tp) {
        this.certif_tp = certif_tp;
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
     * 个人用户自助开户注册（网页版）时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = back_notify_url+"|"+bank_nm+"|"+capAcntNo+"|"+certif_id+"|"+city_id+"|"+cust_nm+"|"+email+"|"+mchnt_cd+"|"+mchnt_txn_ssn+"|"+mobile_no+"|"+ page_notify_url +"|"+parent_bank_id+"|"+user_id_from+"|"+ver;

        return src;
    }
}
