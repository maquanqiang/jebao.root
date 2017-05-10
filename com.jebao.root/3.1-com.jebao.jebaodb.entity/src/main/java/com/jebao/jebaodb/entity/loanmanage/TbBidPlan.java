package com.jebao.jebaodb.entity.loanmanage;

import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;

import java.math.BigDecimal;
import java.util.Date;

public class TbBidPlan {

    public final static int STATUS_UNAUDITED = 0;       //未审核
    public final static int STATUS_AUDITE_FAIL = 1;     //审核失败
    public final static int STATUS_AUDITE_SUCCESS = 2;  //审核通过 招标中
    public final static int STATUS_BID_FULL = 3;        //满标
    public final static int STATUS_BID_LOSE = 4;        //过期 未满标
    public final static int STATUS_REPAYING = 7;        //还款中
    public final static int STATUS_COMPLETE = 10;       //完成

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

    private Date bpStartTime;

    private Date bpEndTime;

    private BigDecimal bpStartMoney;

    private BigDecimal bpRiseMoney;

    private BigDecimal bpTopMoney;

    private Date bpFullTime;

    private Integer bpStatus;

    private Date bpCreateTime;

    private Date bpUpdateTime;

    private Date bpLoanTime;

    private Date bpRepayTime;

    private Long bpLoanerId;

    private String bpTrueName;

    private Long bpLoginId;

    private Integer bpLoanerType;

    private Integer bpInterestPayType;

    private Integer bpOpenTime;

    private Date bpInterestSt;

    private String bpRemark;

    private Date bpExpectLoanDate;

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

    private Integer bpIsDel;

    private String bpDesc;

    private Integer bpType;

    private String bpUpRate;

    private Integer bpDisplayIsPc;

    private Integer bpDisplayIsMobile;

    private Integer bpMonthTerm;   //月周期           由日、月、季、年转换来

    public Integer getBpMonthTerm() {
        return bpMonthTerm;
    }

    public void setBpMonthTerm(Integer bpMonthTerm) {
        this.bpMonthTerm = bpMonthTerm;
    }

    public String getBpUpRate() {
        return bpUpRate;
    }

    public void setBpUpRate(String bpUpRate) {
        this.bpUpRate = bpUpRate;
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
        this.bpName = bpName == null ? null : bpName.trim();
    }

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber == null ? null : bpNumber.trim();
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
        this.bpTrueName = bpTrueName == null ? null : bpTrueName.trim();
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
        this.bpRemark = bpRemark == null ? null : bpRemark.trim();
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
        this.bpBorrowDesc = bpBorrowDesc == null ? null : bpBorrowDesc.trim();
    }

    public String getBpFundsPurpose() {
        return bpFundsPurpose;
    }

    public void setBpFundsPurpose(String bpFundsPurpose) {
        this.bpFundsPurpose = bpFundsPurpose == null ? null : bpFundsPurpose.trim();
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

    public Integer getBpIsDel() {
        return bpIsDel;
    }

    public void setBpIsDel(Integer bpIsDel) {
        this.bpIsDel = bpIsDel;
    }

    public String getBpDesc() {
        return bpDesc;
    }

    public void setBpDesc(String bpDesc) {
        this.bpDesc = bpDesc;
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