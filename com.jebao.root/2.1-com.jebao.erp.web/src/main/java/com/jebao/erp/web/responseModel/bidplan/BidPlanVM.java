package com.jebao.erp.web.responseModel.bidplan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.employee.TbRank;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jack on 2016/11/21.
 */
public class BidPlanVM extends ViewModel {
    public BidPlanVM(TbBidPlan entity) {
        this.bpId = entity.getBpId();
        this.bpName = entity.getBpName();
        this.bpNumber = entity.getBpNumber();
        this.bpPeriodsDisplay = entity.getBpPeriodsDisplay();
        this.bpPeriods = entity.getBpPeriods();
        this.bpCycleType = entity.getBpCycleType();
        this.bpCycleSize = entity.getBpCycleSize();
        this.bpSurplusMoney = entity.getBpSurplusMoney();
        this.bpLoanMoney = entity.getBpLoanMoney();
        this.bpBidMoney = entity.getBpBidMoney();
        this.bpRate = entity.getBpRate();
        this.bpStartTime = entity.getBpStartTime();
        this.bpEndTime = entity.getBpEndTime();
        this.bpStartMoney = entity.getBpStartMoney();
        this.bpRiseMoney = entity.getBpRiseMoney();
        this.bpTopMoney = entity.getBpTopMoney();
        this.bpFullTime = entity.getBpFullTime();
        this.bpStatus = entity.getBpStatus();
        this.bpCreateTime = entity.getBpCreateTime();
        this.bpUpdateTime = entity.getBpUpdateTime();
        this.bpLoanTime = entity.getBpLoanTime();
        this.bpRepayTime = entity.getBpRepayTime();
        this.bpTrueName = entity.getBpTrueName();
        this.bpLoanerType = entity.getBpLoanerType();
        this.bpInterestPayType = entity.getBpInterestPayType();
        this.bpOpenTime = entity.getBpOpenTime();
        this.bpInterestSt = entity.getBpInterestSt();
        this.bpRemark = entity.getBpRemark();
        this.bpExpectLoanDate = entity.getBpExpectLoanDate();
        this.bpExpectRepayDate = entity.getBpExpectRepayDate();
        this.bpServiceChargeRate = entity.getBpServiceChargeRate();
        this.bpLateRate = entity.getBpLateRate();
        this.bpBorrowDesc = entity.getBpBorrowDesc();
        this.bpFundsPurpose = entity.getBpFundsPurpose();
        this.bpRepayingSource = entity.getBpRepayingSource();
        this.bpPersonalCredit = entity.getBpPersonalCredit();
        this.bpMortgageInfo = entity.getBpMortgageInfo();
        this.bpRiskOpinion = entity.getBpRiskOpinion();
        this.bpRepayedPeriods = entity.getBpRepayedPeriods();
        this.bpDesc = entity.getBpDesc();
        this.bpType = entity.getBpType();
        this.bpUpRate = entity.getBpUpRate();
        this.progress=(entity.getBpBidMoney().subtract(entity.getBpSurplusMoney())).multiply(new BigDecimal(100)).
                divide(entity.getBpBidMoney(), 0, BigDecimal.ROUND_DOWN).toString();
        this.bpLoanerId = entity.getBpLoanerId();
        this.bpDisplayIsPc = entity.getBpDisplayIsPc();
        this.bpDisplayIsMobile = entity.getBpDisplayIsMobile();
    }

    private Long bpId;

    private String bpName;

    private String bpNumber;

    private Integer bpPeriodsDisplay;

    private Integer bpPeriods;

    private Integer bpCycleType;

    private Integer bpCycleSize;

    private BigDecimal bpSurplusMoney;

    private BigDecimal bpLoanMoney;

    private BigDecimal bpBidMoney;

    private BigDecimal bpRate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpStartTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpEndTime;

    private BigDecimal bpStartMoney;

    private BigDecimal bpRiseMoney;

    private BigDecimal bpTopMoney;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpFullTime;

    private Integer bpStatus;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpCreateTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpUpdateTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date bpLoanTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date bpRepayTime;

    private Long bpLoanerId;

    private String bpTrueName;

    private Long bpLoginId;

    private Integer bpLoanerType;

    private Integer bpInterestPayType;

    private Integer bpOpenTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date bpInterestSt;

    private String bpRemark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date bpExpectLoanDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date bpExpectRepayDate;

    private BigDecimal bpServiceChargeRate;

    private BigDecimal bpLateRate;

    private String bpBorrowDesc;

    private String bpFundsPurpose;

    private String bpRepayingSource;

    private String bpPersonalCredit;

    private String bpMortgageInfo;

    private String bpRiskOpinion;

    private Integer bpRepayedPeriods;

    private String bpDesc;

    private Integer bpType;

    private String bpUpRate;

    private String progress;

    private Integer bpDisplayIsPc;

    private Integer bpDisplayIsMobile;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getBpUpRate() {
        return bpUpRate;
    }

    public void setBpUpRate(String bpUpRate) {
        this.bpUpRate = bpUpRate;
    }

    public String getBpDesc() {
        return bpDesc;
    }

    public void setBpDesc(String bpDesc) {
        this.bpDesc = bpDesc;
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

    public BigDecimal getBpSurplusMoney() {
        return bpSurplusMoney;
    }

    public void setBpSurplusMoney(BigDecimal bpSurplusMoney) {
        this.bpSurplusMoney = bpSurplusMoney;
    }

    public BigDecimal getBpLoanMoney() {
        return bpLoanMoney;
    }

    public void setBpLoanMoney(BigDecimal bpLoanMoney) {
        this.bpLoanMoney = bpLoanMoney;
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

    public Date getBpCreateTime() {
        return bpCreateTime;
    }

    public void setBpCreateTime(Date bpCreateTime) {
        this.bpCreateTime = bpCreateTime;
    }

    public Date getBpUpdateTime() {
        return bpUpdateTime;
    }

    public void setBpUpdateTime(Date bpUpdateTime) {
        this.bpUpdateTime = bpUpdateTime;
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

    public Long getBpLoanerId() {
        return bpLoanerId;
    }

    public void setBpLoanerId(Long bpLoanerId) {
        this.bpLoanerId = bpLoanerId;
    }

    public String getBpTrueName() {
        return bpTrueName;
    }

    public void setBpTrueName(String bpTrueName) {
        this.bpTrueName = bpTrueName;
    }

    public Long getBpLoginId() {
        return bpLoginId;
    }

    public void setBpLoginId(Long bpLoginId) {
        this.bpLoginId = bpLoginId;
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

    public Date getBpInterestSt() {
        return bpInterestSt;
    }

    public void setBpInterestSt(Date bpInterestSt) {
        this.bpInterestSt = bpInterestSt;
    }

    public String getBpRemark() {
        return bpRemark;
    }

    public void setBpRemark(String bpRemark) {
        this.bpRemark = bpRemark;
    }

    public Date getBpExpectLoanDate() {
        return bpExpectLoanDate;
    }

    public void setBpExpectLoanDate(Date bpExpectLoanDate) {
        this.bpExpectLoanDate = bpExpectLoanDate;
    }

    public Date getBpExpectRepayDate() {
        return bpExpectRepayDate;
    }

    public void setBpExpectRepayDate(Date bpExpectRepayDate) {
        this.bpExpectRepayDate = bpExpectRepayDate;
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

    public String getBpBorrowDesc() {
        return bpBorrowDesc;
    }

    public void setBpBorrowDesc(String bpBorrowDesc) {
        this.bpBorrowDesc = bpBorrowDesc;
    }

    public String getBpFundsPurpose() {
        return bpFundsPurpose;
    }

    public void setBpFundsPurpose(String bpFundsPurpose) {
        this.bpFundsPurpose = bpFundsPurpose;
    }

    public String getBpRepayingSource() {
        return bpRepayingSource;
    }

    public void setBpRepayingSource(String bpRepayingSource) {
        this.bpRepayingSource = bpRepayingSource;
    }

    public String getBpPersonalCredit() {
        return bpPersonalCredit;
    }

    public void setBpPersonalCredit(String bpPersonalCredit) {
        this.bpPersonalCredit = bpPersonalCredit;
    }

    public String getBpMortgageInfo() {
        return bpMortgageInfo;
    }

    public void setBpMortgageInfo(String bpMortgageInfo) {
        this.bpMortgageInfo = bpMortgageInfo;
    }

    public String getBpRiskOpinion() {
        return bpRiskOpinion;
    }

    public void setBpRiskOpinion(String bpRiskOpinion) {
        this.bpRiskOpinion = bpRiskOpinion;
    }

    public Integer getBpRepayedPeriods() {
        return bpRepayedPeriods;
    }

    public void setBpRepayedPeriods(Integer bpRepayedPeriods) {
        this.bpRepayedPeriods = bpRepayedPeriods;
    }

    public Integer getBpType() {
        return bpType;
    }

    public void setBpType(Integer bpType) {
        this.bpType = bpType;
    }

    public Integer getBpDisplayIsPc() {
        return bpDisplayIsPc;
    }

    public void setBpDisplayIsPc(Integer bpDisplayIsPc) {
        this.bpDisplayIsPc = bpDisplayIsPc;
    }

    public Integer getBpDisplayIsMobile() {
        return bpDisplayIsMobile;
    }

    public void setBpDisplayIsMobile(Integer bpDisplayIsMobile) {
        this.bpDisplayIsMobile = bpDisplayIsMobile;
    }
}
