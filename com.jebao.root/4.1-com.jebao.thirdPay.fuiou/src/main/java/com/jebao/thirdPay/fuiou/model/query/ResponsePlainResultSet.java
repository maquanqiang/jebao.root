package com.jebao.thirdPay.fuiou.model.query;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ResponsePlainResultSet {
    private String user_id;
    private String ct_balance;
    private String ca_balance;
    private String cf_balance;
    private String cu_balance;
    @XmlElementWrapper(name="details")
    @XmlElement(name="detail")
    List<ResponsePlainResultSetDetail> details=new ArrayList<ResponsePlainResultSetDetail>();

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCt_balance() {
        return ct_balance;
    }

    public void setCt_balance(String ct_balance) {
        this.ct_balance = ct_balance;
    }

    public String getCa_balance() {
        return ca_balance;
    }

    public void setCa_balance(String ca_balance) {
        this.ca_balance = ca_balance;
    }

    public String getCf_balance() {
        return cf_balance;
    }

    public void setCf_balance(String cf_balance) {
        this.cf_balance = cf_balance;
    }

    public String getCu_balance() {
        return cu_balance;
    }

    public void setCu_balance(String cu_balance) {
        this.cu_balance = cu_balance;
    }
}
