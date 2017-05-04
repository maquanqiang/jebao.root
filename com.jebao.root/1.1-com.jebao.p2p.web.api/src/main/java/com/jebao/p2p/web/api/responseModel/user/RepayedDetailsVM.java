package com.jebao.p2p.web.api.responseModel.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.p2p.service.inf.user.IFundsDetailsServiceInf;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 已还款列表
 */
public class RepayedDetailsVM extends ViewModel {
    public RepayedDetailsVM(TbIncomeDetail entity){
        this.bpId = entity.getIndBpId();
        this.bpName = entity.getIndBpName();
        this.bidMoney = entity.getBpLoanMoney();
        this.bpStartTime = entity.getBpStartTime();
        this.bpPeriods = entity.getPeriods();
        this.bpRate = entity.getRate();
        this.totalMoney = entity.getIncomeTotal();
        this.repayedDate = entity.getIndFactDateTime();           //还清日期
        this.contractUrl = entity.getContractUrl();
        this.bpLoanMoney = entity.getBpLoanMoney();
        this.cycleType = entity.getCycleType();
        this.bpNumber = entity.getIndBpNumber();
    }

    private Long bpId;
    private String bpName;
    private BigDecimal bidMoney;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd")
    private Date bpStartTime;
    private Integer bpPeriods;
    private BigDecimal bpRate;
    private BigDecimal totalMoney;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd")
    private Date repayedDate;           //还清日期
    private String contractUrl;
    private BigDecimal bpLoanMoney;
    private Integer cycleType;
    private String bpNumber;

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
    }

    public BigDecimal getBpLoanMoney() {
        return bpLoanMoney;
    }

    public void setBpLoanMoney(BigDecimal bpLoanMoney) {
        this.bpLoanMoney = bpLoanMoney;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public String getBpName() {
        return bpName;
    }

    public void setBpName(String bpName) {
        this.bpName = bpName;
    }

    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Date getBpStartTime() {
        return bpStartTime;
    }

    public void setBpStartTime(Date bpStartTime) {
        this.bpStartTime = bpStartTime;
    }

    public Integer getBpPeriods() {
        return bpPeriods;
    }

    public void setBpPeriods(Integer bpPeriods) {
        this.bpPeriods = bpPeriods;
    }

    public BigDecimal getBpRate() {
        return bpRate;
    }

    public void setBpRate(BigDecimal bpRate) {
        this.bpRate = bpRate;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getRepayedDate() {
        return repayedDate;
    }

    public void setRepayedDate(Date repayedDate) {
        this.repayedDate = repayedDate;
    }
}
