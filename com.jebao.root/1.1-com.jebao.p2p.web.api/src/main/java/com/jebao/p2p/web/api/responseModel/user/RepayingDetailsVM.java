package com.jebao.p2p.web.api.responseModel.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.common.utils.incomeDetail.IncomeDetailUtil;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收支明细
 * Created by Administrator on 2016/12/3.
 */
public class RepayingDetailsVM extends ViewModel {
    public RepayingDetailsVM(TbIncomeDetail entity){
        this.bpId = entity.getIndBpId();
        this.bpName = entity.getIndBpName();
        this.bpPeriod = entity.getIndPeriods();
        this.nextRepayTime = entity.getIndDateTime();
        this.repayMoney = entity.getIndMoney();
        this.dueMoney = entity.getIndOverdueMoney();
        this.bpNumber = entity.getIndBpNumber();
        this.status = entity.getIndStatus();
    }

    private Long bpId;
    private String bpName;
    private Integer bpPeriod;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd")
    private Date nextRepayTime;
    private BigDecimal repayMoney;
    private BigDecimal dueMoney;
    private String bpNumber;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
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

    public Integer getBpPeriod() {
        return bpPeriod;
    }

    public void setBpPeriod(Integer bpPeriod) {
        this.bpPeriod = bpPeriod;
    }

    public Date getNextRepayTime() {
        return nextRepayTime;
    }

    public void setNextRepayTime(Date nextRepayTime) {
        this.nextRepayTime = nextRepayTime;
    }

    public BigDecimal getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }

    public BigDecimal getDueMoney() {
        return dueMoney;
    }

    public void setDueMoney(BigDecimal dueMoney) {
        this.dueMoney = dueMoney;
    }
}
