package com.jebao.thirdPay.fuiou.model.FuiouRecharge;

/**
 * Created by Lee on 2017/3/31.
 */
public class RechargeResult {

    private boolean flag;
    private String xmlResp;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getXmlResp() {
        return xmlResp;
    }

    public void setXmlResp(String xmlResp) {
        this.xmlResp = xmlResp;
    }
}
