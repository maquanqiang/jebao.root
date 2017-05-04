package com.jebao.thirdPay.fuiou.model.transferBu;

import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 * Created by Administrator on 2016/9/26.
 */
public class TransferBuRequest extends BaseRequest {
    private String out_cust_no; //付款登录账户
    private String in_cust_no; //收款登录账户
    private String amt; //划拨金额
    private String contract_no; //预授权合同号
    private String rem; //备注
    private String signature; //签名数据


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

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
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
     * 划拨(个人与个人之间)时请求的明文
     *
     * @return
     */
    public String requestSignPlain() {
        String src =amt + "|" +contract_no+"|"+in_cust_no+"|"+ mchnt_cd + "|" + mchnt_txn_ssn+"|"+ out_cust_no +"|"+ rem;
        return src;
    }
}
