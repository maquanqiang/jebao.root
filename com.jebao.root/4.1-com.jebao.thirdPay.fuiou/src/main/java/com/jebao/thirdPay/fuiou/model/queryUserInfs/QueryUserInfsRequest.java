package com.jebao.thirdPay.fuiou.model.queryUserInfs;

import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 * Created by Administrator on 2016/9/27.
 */
public class QueryUserInfsRequest extends BaseRequest {
    private String ver = FuiouConfig.documentVersion;//文档版本号
    private String mchnt_txn_dt; //交易日期
    private String user_ids; //待查询的登录帐户列表
    private String signature; //签名数据

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

/*    public String getMchnt_cd() {
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
    }*/

    public String getMchnt_txn_dt() {
        return mchnt_txn_dt;
    }

    public void setMchnt_txn_dt(String mchnt_txn_dt) {
        this.mchnt_txn_dt = mchnt_txn_dt;
    }

    public String getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(String user_ids) {
        this.user_ids = user_ids;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 用户信息查询时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = mchnt_cd+"|"+mchnt_txn_dt+"|"+mchnt_txn_ssn+"|"+user_ids+"|"+ver;
        return src;
    }
}
