package com.jebao.thirdPay.fuiou.model.FuiouRecharge;

import com.jebao.thirdPay.fuiou.model.base.BasePlain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Lee on 2017/3/29.
 */
@XmlRootElement(name="ap")
public class FuiouRechargeResponse {


    private BasePlain plain;

    private String signature;

    @XmlElement(name="plain")
    public BasePlain getPlain() {
        return plain;
    }

    public void setPlain(BasePlain plain) {
        this.plain = plain;
    }
    @XmlElement(name="signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
