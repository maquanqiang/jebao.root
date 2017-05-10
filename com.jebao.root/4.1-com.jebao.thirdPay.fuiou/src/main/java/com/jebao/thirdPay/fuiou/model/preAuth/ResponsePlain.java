package com.jebao.thirdPay.fuiou.model.preAuth;

import com.jebao.thirdPay.fuiou.model.base.BasePlain;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ResponsePlain extends BasePlain {
    private String contract_no="";

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }
}
