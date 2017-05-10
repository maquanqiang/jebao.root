package com.jebao.p2p.web.api.responseModel.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/12/7.
 */
public class ProductVm extends ViewModel {

    public ProductVm() {
    }

    public ProductVm(TbBidPlan plan){
        this.bpId=plan.getBpId();
        this.bpBidMoney=plan.getBpBidMoney();
        this.bpCycleSize=plan.getBpCycleSize();
        this.bpCycleType=plan.getBpCycleType();
        this.bpEndTime=plan.getBpEndTime();
        this.bpStartTime=plan.getBpStartTime();
        this.bpName=plan.getBpName();
        this.bpNumber=plan.getBpNumber();
        this.bpStatus=(plan.getBpStatus() == 2 && plan.getBpEndTime().before(new Date()))?4:plan.getBpStatus();
        this.bpPeriodsDisplay=plan.getBpPeriodsDisplay();
        this.bpRate=plan.getBpRate();
        this.bpSurplusMoney=plan.getBpStatus() ==  7? new BigDecimal(0) :plan.getBpSurplusMoney();
        this.bpType=plan.getBpType();
        this.bpFullTime=plan.getBpFullTime();
        this.progress=(plan.getBpBidMoney().subtract( plan.getBpStatus() ==  7? new BigDecimal(0) : plan.getBpSurplusMoney())).multiply(new BigDecimal(100)).
                divide(plan.getBpBidMoney(), 0, BigDecimal.ROUND_DOWN).toString();
    }

    private Long bpId;
    private String bpName;
    private String bpNumber;
    private Integer bpPeriodsDisplay;
    private Integer bpCycleType;
    private Integer bpCycleSize;
    private BigDecimal bpSurplusMoney;
    private BigDecimal bpBidMoney;
    private BigDecimal bpRate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpStartTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpEndTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpFullTime;
    private Integer bpStatus;
    private Integer bpType;
    private String progress;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
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

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
    }

    public Integer getBpPeriodsDisplay() {
        return bpPeriodsDisplay;
    }

    public void setBpPeriodsDisplay(Integer bpPeriodsDisplay) {
        this.bpPeriodsDisplay = bpPeriodsDisplay;
    }

    public Integer getBpCycleType() {
        return bpCycleType;
    }

    public void setBpCycleType(Integer bpCycleType) {
        this.bpCycleType = bpCycleType;
    }

    public Integer getBpCycleSize() {
        return bpCycleSize;
    }

    public void setBpCycleSize(Integer bpCycleSize) {
        this.bpCycleSize = bpCycleSize;
    }

    public BigDecimal getBpSurplusMoney() {
        return bpSurplusMoney;
    }

    public void setBpSurplusMoney(BigDecimal bpSurplusMoney) {
        this.bpSurplusMoney = bpSurplusMoney;
    }

    public BigDecimal getBpBidMoney() {
        return bpBidMoney;
    }

    public void setBpBidMoney(BigDecimal bpBidMoney) {
        this.bpBidMoney = bpBidMoney;
    }

    public BigDecimal getBpRate() {
        return bpRate;
    }

    public void setBpRate(BigDecimal bpRate) {
        this.bpRate = bpRate;
    }

    public Date getBpStartTime() {
        return bpStartTime;
    }

    public void setBpStartTime(Date bpStartTime) {
        this.bpStartTime = bpStartTime;
    }

    public Date getBpEndTime() {
        return bpEndTime;
    }

    public void setBpEndTime(Date bpEndTime) {
        this.bpEndTime = bpEndTime;
    }

    public Date getBpFullTime() {
        return bpFullTime;
    }

    public void setBpFullTime(Date bpFullTime) {
        this.bpFullTime = bpFullTime;
    }

    public Integer getBpStatus() {
        return bpStatus;
    }

    public void setBpStatus(Integer bpStatus) {
        this.bpStatus = bpStatus;
    }

    public Integer getBpType() {
        return bpType;
    }

    public void setBpType(Integer bpType) {
        this.bpType = bpType;
    }
}
