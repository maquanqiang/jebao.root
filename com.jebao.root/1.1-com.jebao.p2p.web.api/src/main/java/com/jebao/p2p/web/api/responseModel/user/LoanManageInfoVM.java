package com.jebao.p2p.web.api.responseModel.user;

import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/16.
 */
public class LoanManageInfoVM extends ViewModel {

    private BigDecimal loanMoneyTotal;             //借款总额
    private BigDecimal overdueMoneyTotal;          //逾期总额
    private BigDecimal repaymentMoneyTotal;        //待还总额
    private BigDecimal afterTenMoney;              //未来十天应还

    public BigDecimal getLoanMoneyTotal() {
        return loanMoneyTotal;
    }

    public void setLoanMoneyTotal(BigDecimal loanMoneyTotal) {
        this.loanMoneyTotal = loanMoneyTotal;
    }

    public BigDecimal getOverdueMoneyTotal() {
        return overdueMoneyTotal;
    }

    public void setOverdueMoneyTotal(BigDecimal overdueMoneyTotal) {
        this.overdueMoneyTotal = overdueMoneyTotal;
    }

    public BigDecimal getRepaymentMoneyTotal() {
        return repaymentMoneyTotal;
    }

    public void setRepaymentMoneyTotal(BigDecimal repaymentMoneyTotal) {
        this.repaymentMoneyTotal = repaymentMoneyTotal;
    }

    public BigDecimal getAfterTenMoney() {
        return afterTenMoney;
    }

    public void setAfterTenMoney(BigDecimal afterTenMoney) {
        this.afterTenMoney = afterTenMoney;
    }
}
