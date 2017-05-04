package com.jebao.thirdPay.fuiou.model.query;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 明细查询接口响应
 */
@XmlRootElement(name = "ap")
public class QueryResponse {
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