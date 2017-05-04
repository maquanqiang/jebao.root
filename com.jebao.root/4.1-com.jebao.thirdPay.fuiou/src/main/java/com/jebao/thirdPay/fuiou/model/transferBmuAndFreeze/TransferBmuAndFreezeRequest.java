package com.jebao.thirdPay.fuiou.model.transferBmuAndFreeze;

/**
 * Created by Administrator on 2016/9/28.
 */
public class TransferBmuAndFreezeRequest {
    private String mchnt_cd; //商户代码
    private String mchnt_txn_ssn; //流水号
    private String out_cust_no; //付款登录账户
    private String in_cust_no; //收款登录账户
    private String amt; //转账金额
    private String rem; //备注
    private String signature; //签名数据

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

    public String getOut_cust_no() {
        return out_cust_no;
    }

    public void setOut_cust_no(String out_cust_no) {
        this.out_cust_no = out_cust_no;
    }

    public String getIn_cust_no() {
        return in_cust_no;
    }

    public void setIn_cust_no(String in_cust_no) {
        this.in_cust_no = in_cust_no;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 转账预冻结时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src = amt + "|" + in_cust_no+"|"+ mchnt_cd + "|" + mchnt_txn_ssn+"|"+ out_cust_no +"|"+ rem;
        return src;
    }
}
