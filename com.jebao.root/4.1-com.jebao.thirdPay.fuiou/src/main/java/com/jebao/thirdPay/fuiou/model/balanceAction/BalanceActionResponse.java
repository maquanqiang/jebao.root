package com.jebao.thirdPay.fuiou.model.balanceAction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2016/9/26.
 */
@XmlRootElement(name = "ap")
public class BalanceActionResponse {
    private ResponsePlain plain;
    private String signature;//签名数据

    @XmlElement(name="plain")
    public ResponsePlain getPlain() {
        return plain;
    }

    public void setPlain(ResponsePlain plain) {
        this.plain = plain;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
