package com.jebao.erp.web.responseModel.loaner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/5.
 */
public class LoanRecordVM extends ViewModel {
    public LoanRecordVM(TbBidPlan entity){
        this.id = entity.getBpId();
        this.loanerId = entity.getBpLoanerId();
        this.name = entity.getBpName();
        this.number = entity.getBpNumber();
        this.bidMoney = entity.getBpBidMoney();
        this.periods = String.valueOf(entity.getBpPeriods() * entity.getBpCycleSize()) + getCycleTypeName(entity.getBpCycleType());
        this.rate = entity.getBpRate();
        this.loanMoney = entity.getBpLoanMoney();
        this.interestMoney = entity.getBpLoanMoney().multiply(entity.getBpRate().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.repayTime = entity.getBpRepayTime();
    }

    private String getCycleTypeName(int cycleType){
        String name = "其他";
        switch (cycleType){
            case 1:name="天";break;
            case 2:name="个月";break;
            case 3:name="季";break;
            case 4:name="年";break;
            default:break;
        }
        return name;
    }

    //标的ID
    private Long id;

    //借款人ID
    private Long loanerId;

    //标的名称
    private String name;

    //项目编号
    private String number;

    //募集金额
    private BigDecimal bidMoney;

    //期限
    private String periods;

    //借款利率
    private BigDecimal rate;

    //实际募集金额
    private BigDecimal loanMoney;

    //利息
    private BigDecimal interestMoney;

    //到期（还款）时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date repayTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanerId() {
        return loanerId;
    }

    public void setLoanerId(Long loanerId) {
        this.loanerId = loanerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(BigDecimal loanMoney) {
        this.loanMoney = loanMoney;
    }

    public BigDecimal getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(BigDecimal interestMoney) {
        this.interestMoney = interestMoney;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }
}