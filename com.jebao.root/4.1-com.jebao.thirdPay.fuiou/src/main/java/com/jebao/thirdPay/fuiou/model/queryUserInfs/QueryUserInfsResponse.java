package com.jebao.thirdPay.fuiou.model.queryUserInfs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2016/9/27.
 */
@XmlRootElement(name = "ap")
public class QueryUserInfsResponse {

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