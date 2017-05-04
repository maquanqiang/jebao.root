package com.jebao.thirdPay.fuiou.model.changeCard;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ChangeCardResponse {
    private String resp_code; //响应码
    private String resp_desc; //返回码说明
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //请求流水号
    private String signature; //签名数据

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_desc() {
        return resp_desc;
    }

    public void setResp_desc(String resp_desc) {
        this.resp_desc = resp_desc;
    }

    public String getMchnt_cd() {
        return mchnt_cd;
    }

    public void setMchnt_cd(String mchnt_cd) {
        this.mchnt_cd = mchnt_cd;
    }

    public String getMchnt_txn_ssn() {
        return mchnt_txn_ssn;
    }

    public void setMchnt_txn_ssn(String mchnt_txn_ssn) {
        this.mchnt_txn_ssn = mchnt_txn_ssn;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 商户P2P网站免登录用户更换银行卡接口
     *
     * @return
     */
    public String requestSignPlain() {
        String src = mchnt_cd + "|" + mchnt_txn_ssn + "|" + resp_code + "|" + resp_desc;
        return src;
    }
}
