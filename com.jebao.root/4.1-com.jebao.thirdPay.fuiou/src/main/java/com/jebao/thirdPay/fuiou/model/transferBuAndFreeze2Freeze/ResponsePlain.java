package com.jebao.thirdPay.fuiou.model.transferBuAndFreeze2Freeze;

import com.jebao.thirdPay.fuiou.model.base.BasePlain;

/**
 * Created by Administrator on 2016/9/28.
 */
public class ResponsePlain extends BasePlain {
    private String amt; //请求付款冻结金额
    private String suc_amt; //成功付款冻结金额

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getSuc_amt() {
        return suc_amt;
    }

    public void setSuc_amt(String suc_amt) {
        this.suc_amt = suc_amt;
    }
}
