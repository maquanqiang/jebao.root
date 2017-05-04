package com.jebao.erp.web.responseModel.loaner;

import com.jebao.erp.web.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/6.
 */
public class FundsVM extends ViewModel {
    //账户可用余额
    private BigDecimal balance;

    //实际借款金额
    private BigDecimal jkAmounts;

    //借款利息
    private BigDecimal jkInterests;

    //服务费（手续费）
    private BigDecimal serviceCharge;

    //待还金额
    private BigDecimal dhAmounts;

    //待还利息
    private BigDecimal dhInterests;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getJkAmounts() {
        return jkAmounts;
    }

    public void setJkAmounts(BigDecimal jkAmounts) {
        this.jkAmounts = jkAmounts;
    }

    public BigDecimal getJkInterests() {
        return jkInterests;
    }

    public void setJkInterests(BigDecimal jkInterests) {
        this.jkInterests = jkInterests;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getDhAmounts() {
        return dhAmounts;
    }

    public void setDhAmounts(BigDecimal dhAmounts) {
        this.dhAmounts = dhAmounts;
    }

    public BigDecimal getDhInterests() {
        return dhInterests;
    }

    public void setDhInterests(BigDecimal dhInterests) {
        this.dhInterests = dhInterests;
    }
}
