package com.jebao.erp.web.requestModel.bidplan;

import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/11/17.
 */
public class BidPlanForm {

    public static BidPlanSM toEntity(BidPlanForm form){
        BidPlanSM bidPlanSM = new BidPlanSM();
        if(StringUtils.isNotBlank(form.getBpName())){
            bidPlanSM.setBpName(form.getBpName());
        }
        bidPlanSM.setBpNumber(form.getBpNumber());
        bidPlanSM.setBpPeriodsDisplay(form.getBpPeriodsDisplay());
        bidPlanSM.setBpPeriods(form.getBpPeriods());
        bidPlanSM.setBpCycleType(form.getBpCycleType());
        bidPlanSM.setBpLoanMoney(form.getBpLoanMoney());             //实际放款金额
        bidPlanSM.setBpBidMoney(form.getBpBidMoney());
        bidPlanSM.setBpRate(form.getBpRate());
        bidPlanSM.setBpStartMoney(form.getBpStartMoney());
        bidPlanSM.setBpRiseMoney(form.getBpRiseMoney());
        bidPlanSM.setBpTopMoney(form.getBpTopMoney());
        bidPlanSM.setBpStatus(form.getBpStatus());
        if(StringUtils.isNotBlank(form.getBpTrueName())){
            bidPlanSM.setBpTrueName(form.getBpTrueName());
        }
        bidPlanSM.setBpLoanerType(form.getBpLoanerType());
        bidPlanSM.setBpInterestPayType(form.getBpInterestPayType());
        bidPlanSM.setSearchDateSt(form.getSearchDateSt());
        bidPlanSM.setSearchDateEnd(form.getSearchDateEnd());
        bidPlanSM.setSearchDateType(form.getSearchDateType());
        bidPlanSM.setBpStatusSear(form.getBpStatusSear());
        if(StringUtils.isNotBlank(form.getBpNumberSear())){
            bidPlanSM.setBpNumberSear("'"+form.getBpNumberSear()+"%'");
        }
        return bidPlanSM;
    }

    private String bpName;

    private String bpNumber;

    private Integer bpPeriodsDisplay;

    private Integer bpPeriods;

    private Integer bpCycleType;

    private Integer bpCycleSize;

    private BigDecimal bpLoanMoney;             //实际放款金额

    private BigDecimal bpBidMoney;

    private BigDecimal bpRate;

    private Date bpStartTime;

    private Date bpEndTime;

    private BigDecimal bpStartMoney;

    private BigDecimal bpRiseMoney;

    private BigDecimal bpTopMoney;

    private Integer bpStatus;

    private Date bpLoanTime;

    private Date bpRepayTime;

    private Long bpLoanerId;

    private String bpTrueName;

    private Integer bpLoanerType;

    private Integer bpInterestPayType;

    private Integer bpOpenTime;

//    private Date bpInterestSt;

//    private Date bpInterestEt;

    private String bpRemark;

    private BigDecimal bpServiceChargeRate;

    private BigDecimal bpLateRate;

    private Integer bpType;

    //------------------时间条件查询接收-----------------
    private String searchDateSt;

    private String searchDateEnd;

    private String searchDateType;

    private String bpStatusSear;

    private String bpNumberSear;

    public String getBpStatusSear() {
        return bpStatusSear;
    }

    public void setBpStatusSear(String bpStatusSear) {
        this.bpStatusSear = bpStatusSear;
    }

    public String getBpNumberSear() {
        return bpNumberSear;
    }

    public void setBpNumberSear(String bpNumberSear) {
        this.bpNumberSear = bpNumberSear;
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

    public Integer getBpPeriods() {
        return bpPeriods;
    }

    public void setBpPeriods(Integer bpPeriods) {
        this.bpPeriods = bpPeriods;
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

    public BigDecimal getBpStartMoney() {
        return bpStartMoney;
    }

    public void setBpStartMoney(BigDecimal bpStartMoney) {
        this.bpStartMoney = bpStartMoney;
    }

    public BigDecimal getBpRiseMoney() {
        return bpRiseMoney;
    }

    public void setBpRiseMoney(BigDecimal bpRiseMoney) {
        this.bpRiseMoney = bpRiseMoney;
    }

    public BigDecimal getBpTopMoney() {
        return bpTopMoney;
    }

    public void setBpTopMoney(BigDecimal bpTopMoney) {
        this.bpTopMoney = bpTopMoney;
    }

    public Long getBpLoanerId() {
        return bpLoanerId;
    }

    public void setBpLoanerId(Long bpLoanerId) {
        this.bpLoanerId = bpLoanerId;
    }

    public Integer getBpLoanerType() {
        return bpLoanerType;
    }

    public void setBpLoanerType(Integer bpLoanerType) {
        this.bpLoanerType = bpLoanerType;
    }

    public Integer getBpInterestPayType() {
        return bpInterestPayType;
    }

    public void setBpInterestPayType(Integer bpInterestPayType) {
        this.bpInterestPayType = bpInterestPayType;
    }

    public Integer getBpOpenTime() {
        return bpOpenTime;
    }

    public void setBpOpenTime(Integer bpOpenTime) {
        this.bpOpenTime = bpOpenTime;
    }

    public String getBpRemark() {
        return bpRemark;
    }

    public void setBpRemark(String bpRemark) {
        this.bpRemark = bpRemark;
    }


    public BigDecimal getBpServiceChargeRate() {
        return bpServiceChargeRate;
    }

    public void setBpServiceChargeRate(BigDecimal bpServiceChargeRate) {
        this.bpServiceChargeRate = bpServiceChargeRate;
    }

    public BigDecimal getBpLateRate() {
        return bpLateRate;
    }

    public void setBpLateRate(BigDecimal bpLateRate) {
        this.bpLateRate = bpLateRate;
    }

    public BigDecimal getBpLoanMoney() {
        return bpLoanMoney;
    }

    public void setBpLoanMoney(BigDecimal bpLoanMoney) {
        this.bpLoanMoney = bpLoanMoney;
    }

    public Integer getBpType() {
        return bpType;
    }

    public void setBpType(Integer bpType) {
        this.bpType = bpType;
    }

    public Integer getBpStatus() {
        return bpStatus;
    }

    public void setBpStatus(Integer bpStatus) {
        this.bpStatus = bpStatus;
    }

    public Date getBpLoanTime() {
        return bpLoanTime;
    }

    public void setBpLoanTime(Date bpLoanTime) {
        this.bpLoanTime = bpLoanTime;
    }

    public Date getBpRepayTime() {
        return bpRepayTime;
    }

    public void setBpRepayTime(Date bpRepayTime) {
        this.bpRepayTime = bpRepayTime;
    }

    public String getBpTrueName() {
        return bpTrueName;
    }

    public void setBpTrueName(String bpTrueName) {
        this.bpTrueName = bpTrueName;
    }

    public String getSearchDateSt() {
        return searchDateSt;
    }

    public void setSearchDateSt(String searchDateSt) {
        this.searchDateSt = searchDateSt;
    }

    public String getSearchDateEnd() {
        return searchDateEnd;
    }

    public void setSearchDateEnd(String searchDateEnd) {
        this.searchDateEnd = searchDateEnd;
    }

    public String getSearchDateType() {
        return searchDateType;
    }

    public void setSearchDateType(String searchDateType) {
        this.searchDateType = searchDateType;
    }
}