package com.jebao.thirdPay.fuiou.model.base;

/**
 * Created by Administrator on 2016/9/26.
 */
public class BaseResponse {
    private BasePlain plain;
    private String signature;//签名数据

    public BasePlain getPlain() {
        return plain;
    }

    public void setPlain(BasePlain plain) {
        this.plain = plain;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
