package com.jebao.jebaodb.entity.loaner;

import java.math.BigDecimal;

/**
 * 借款金额汇总
 * Created by Administrator on 2016/12/6.
 */
public class LoanTotal {
    //借款人ID
    private Long loanerId;

    //借款总次数
    private int totalTrades;

    //借款总金额
    private BigDecimal totalAmounts;

    //服务费（手续费）
    private BigDecimal serviceCharge;

    public int getTotalTrades() {
        return totalTrades;
    }

    public void setTotalTrades(int totalTrades) {
        this.totalTrades = totalTrades;
    }

    public BigDecimal getTotalAmounts() {
        return totalAmounts;
    }

    public void setTotalAmounts(BigDecimal totalAmounts) {
        this.totalAmounts = totalAmounts;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Long getLoanerId() {
        return loanerId;
    }

    public void setLoanerId(Long loanerId) {
        this.loanerId = loanerId;
    }
}