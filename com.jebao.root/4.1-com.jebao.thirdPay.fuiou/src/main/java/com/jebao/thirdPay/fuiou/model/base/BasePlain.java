package com.jebao.thirdPay.fuiou.model.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Administrator on 2016/9/26.
 */
@XmlType(propOrder = { "resp_code", "mchnt_cd", "mchnt_txn_ssn"})
public class BasePlain {
    private String resp_code;//返回码
    private String mchnt_cd;//商户代码
    private String mchnt_txn_ssn;//商户流水号

    @XmlElement(name="resp_code")
    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }
    @XmlElement(name="mchnt_cd")
    public String getMchnt_cd() {
        return mchnt_cd;
    }

    public void setMchnt_cd(String mchnt_cd) {
        this.mchnt_cd = mchnt_cd;
    }
    @XmlElement(name="mchnt_txn_ssn")
    public String getMchnt_txn_ssn() {
        return mchnt_txn_ssn;
    }

    public void setMchnt_txn_ssn(String mchnt_txn_ssn) {
        this.mchnt_txn_ssn = mchnt_txn_ssn;
    }
}
