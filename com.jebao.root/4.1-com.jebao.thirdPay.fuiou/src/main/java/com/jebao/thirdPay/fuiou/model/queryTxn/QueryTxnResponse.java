package com.jebao.thirdPay.fuiou.model.queryTxn;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2016/9/26.
 */
@XmlRootElement(name = "ap")
public class QueryTxnResponse {
    private QueryTxnPlain plain;
    private String signature;//签名数据

    public QueryTxnPlain getPlain() {
        return plain;
    }

    public void setPlain(QueryTxnPlain plain) {
        this.plain = plain;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
