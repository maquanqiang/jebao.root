package com.jebao.jebaodb.entity.account;

import java.math.BigDecimal;

/**
 * Created by Lee on 2017/4/10.
 */
public class AccountCount {

    //新增数量
    private Integer createCount;
    //登录数量
    private Integer loginCount;
    //待审核标的数
    private Integer nonCheckCount;
    //昨日开标数量
    private Integer yesterdayCount;
    //总开标数
    private Integer planTotal;
    //昨日借款
    private BigDecimal yesterdayLoan;
    //借款总额
    private BigDecimal loanTotal;
    //昨日提现笔数
    private Integer yesterdayWithdraw;
    //累计提现笔数
    private Integer withdrawCount;
    //本月累计投资额
    private BigDecimal investCount;
    //今日应还
    private BigDecimal yesterdayIncome;

    public Integer getCreateCount() {
        return createCount;
    }

    public void setCreateCount(Integer createCount) {
        this.createCount = createCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getNonCheckCount() {
        return nonCheckCount;
    }

    public void setNonCheckCount(Integer nonCheckCount) {
        this.nonCheckCount = nonCheckCount;
    }

    public Integer getYesterdayCount() {
        return yesterdayCount;
    }

    public void setYesterdayCount(Integer yesterdayCount) {
        this.yesterdayCount = yesterdayCount;
    }

    public Integer getPlanTotal() {
        return planTotal;
    }

    public void setPlanTotal(Integer planTotal) {
        this.planTotal = planTotal;
    }

    public BigDecimal getYesterdayLoan() {
        return yesterdayLoan;
    }

    public void setYesterdayLoan(BigDecimal yesterdayLoan) {
        this.yesterdayLoan = yesterdayLoan;
    }

    public BigDecimal getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(BigDecimal loanTotal) {
        this.loanTotal = loanTotal;
    }

    public Integer getYesterdayWithdraw() {
        return yesterdayWithdraw;
    }

    public void setYesterdayWithdraw(Integer yesterdayWithdraw) {
        this.yesterdayWithdraw = yesterdayWithdraw;
    }

    public Integer getWithdrawCount() {
        return withdrawCount;
    }

    public void setWithdrawCount(Integer withdrawCount) {
        this.withdrawCount = withdrawCount;
    }

    public BigDecimal getInvestCount() {
        return investCount;
    }

    public void setInvestCount(BigDecimal investCount) {
        this.investCount = investCount;
    }

    public BigDecimal getYesterdayIncome() {
        return yesterdayIncome;
    }

    public void setYesterdayIncome(BigDecimal yesterdayIncome) {
        this.yesterdayIncome = yesterdayIncome;
    }
}
