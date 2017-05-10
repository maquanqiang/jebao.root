package com.jebao.jebaodb.entity.investment;

/**
 * Created by Lee on 2016/12/14.
 */
public class FundDetailSM {

    private Long loanLoginId;       //借款人登录id
    private Long investLoginId;     //投资人登录id
    private Integer detailStatus;         //还款状态
    private Integer planStatus;         //标的状态
    private Integer period;             //是否是下一期   0:否  1:是

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Long getLoanLoginId() {
        return loanLoginId;
    }

    public void setLoanLoginId(Long loanLoginId) {
        this.loanLoginId = loanLoginId;
    }

    public Long getInvestLoginId() {
        return investLoginId;
    }

    public void setInvestLoginId(Long investLoginId) {
        this.investLoginId = investLoginId;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }
}
