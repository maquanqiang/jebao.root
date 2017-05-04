package com.jebao.thirdPay.fuiou.model.transferBuAndFreeze2Freeze;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2016/9/28.
 */
@XmlRootElement(name = "ap")
public class TransferBuAndFreeze2FreezeResponse {
    private ResponsePlain plain;
    private String signature;//签名数据


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
