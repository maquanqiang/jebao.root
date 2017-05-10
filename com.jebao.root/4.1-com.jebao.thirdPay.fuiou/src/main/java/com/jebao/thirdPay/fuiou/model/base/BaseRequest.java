package com.jebao.thirdPay.fuiou.model.base;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.util.Common;

/**
 * Created by Lee on 2016/12/13.
 */
public class BaseRequest {
    protected String mchnt_cd = FuiouConfig.platNumber;
    protected String mchnt_txn_ssn = Common.mchntTxnSsn();

    public String getMchnt_cd() {
        return mchnt_cd;
    }

    public String getMchnt_txn_ssn() {
        return mchnt_txn_ssn;
    }
}
