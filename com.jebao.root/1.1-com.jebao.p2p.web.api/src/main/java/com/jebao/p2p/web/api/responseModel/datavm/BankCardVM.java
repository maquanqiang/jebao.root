package com.jebao.p2p.web.api.responseModel.datavm;

/**
 * Created by Jack on 2016/12/28.
 */
public class BankCardVM {
    private String bankCardNo;
    private String province;
    private String city;
    private String bankName;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
