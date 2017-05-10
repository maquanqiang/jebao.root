package com.jebao.thirdPay.fuiou.model.balanceAction;

import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 * Created by Administrator on 2016/9/26.
 */
public class BalanceActionRequest extends BaseRequest {
    private String mchnt_txn_dt; //交易日期
    private String cust_no; //待查询的登录帐户
    private String signature; //签名数据

    public String getMchnt_txn_dt() {
        return mchnt_txn_dt;
    }

    public void setMchnt_txn_dt(String mchnt_txn_dt) {
        this.mchnt_txn_dt = mchnt_txn_dt;
    }

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 余额查询时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = cust_no+"|"+ mchnt_cd+"|"+mchnt_txn_dt+"|"+ mchnt_txn_ssn;
        return src;
    }
}
