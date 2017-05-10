package com.jebao.erp.web.requestModel.bidplan;

import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/11/24.
 */
public class UpdatePlanForm{

    public static TbBidPlan toEntity(UpdatePlanForm planForm) {
        TbBidPlan bidPlan = new TbBidPlan();
        bidPlan.setBpId(planForm.getBpId());
        bidPlan.setBpName(planForm.getBpName());
        bidPlan.setBpNumber(planForm.getBpNumber());
        bidPlan.setBpPeriodsDisplay(planForm.getBpPeriodsDisplay());
        bidPlan.setBpPeriods(planForm.getBpPeriods());
        bidPlan.setBpCycleType(planForm.getBpCycleType());
        bidPlan.setBpCycleSize(planForm.getBpCycleSize());
        bidPlan.setBpBidMoney(planForm.getBpBidMoney());
        bidPlan.setBpRate(planForm.getBpRate());
        bidPlan.setBpStartTime(planForm.getBpStartTime());
        bidPlan.setBpEndTime(planForm.getBpEndTime());
        bidPlan.setBpStartMoney(planForm.getBpStartMoney());
        bidPlan.setBpRiseMoney(planForm.getBpRiseMoney());
        bidPlan.setBpTopMoney(planForm.getBpTopMoney());
        bidPlan.setBpLoanerId(planForm.getBpLoanerId());
        bidPlan.setBpLoanerType(planForm.getBpLoanerType());
        bidPlan.setBpInterestPayType(planForm.getBpInterestPayType());
        bidPlan.setBpOpenTime(planForm.getBpOpenTime());
        bidPlan.setBpExpectLoanDate(planForm.getBpExpectLoanDate());
        bidPlan.setBpExpectRepayDate(planForm.getBpExpectRepayDate());
        bidPlan.setBpServiceChargeRate(planForm.getBpServiceChargeRate());
        bidPlan.setBpLateRate(planForm.getBpLateRate());
        bidPlan.setBpBorrowDesc(planForm.getBpBorrowDesc());
        bidPlan.setBpFundsPurpose(planForm.getBpFundsPurpose());
        bidPlan.setBpRepayingSource(planForm.getBpRepayingSource());
        bidPlan.setBpPersonalCredit(planForm.getBpPersonalCredit());
        bidPlan.setBpMortgageInfo(planForm.getBpMortgageInfo());
        bidPlan.setBpRiskOpinion(planForm.getBpRiskOpinion());
        bidPlan.setBpRepayedPeriods(planForm.getBpRepayedPeriods());
        bidPlan.setBpDesc(planForm.getBpDesc());
        bidPlan.setBpType(planForm.getBpType());
        bidPlan.setBpSurplusMoney(planForm.getBpBidMoney());
        bidPlan.setBpDisplayIsPc(planForm.getBpDisplayIsPc());
        bidPlan.setBpDisplayIsMobile(planForm.getBpDisplayIsMobile());
        //换算周期为月
        int periods = planForm.getBpPeriods();
        if(planForm.getBpCycleType()==1){
            bidPlan.setBpMonthTerm(periods%30 == 0 ? periods / 30 : periods/30 + 1);
        }else if(planForm.getBpCycleType() == 2){
            bidPlan.setBpMonthTerm(periods);
        }else if(planForm.getBpCycleType() == 3){
            bidPlan.setBpMonthTerm(periods * 3);
        }else if(planForm.getBpCycleType() == 4){
            bidPlan.setBpMonthTerm(periods * 12);
        }
        return bidPlan;
    }
    private Long bpId;

    private String bpName;

    private String bpNumber;

    private Integer bpPeriodsDisplay;

    private Integer bpPeriods;

    private Integer bpCycleType;

    private Integer bpCycleSize;

    private BigDecimal bpBidMoney;

    private BigDecimal bpRate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bpEndTime;

    private BigDecimal bpStartMoney;

    private BigDecimal bpRiseMoney;

    private BigDecimal bpTopMoney;

    private Long bpLoanerId;

    private Integer bpInterestPayType;

    private Integer bpOpenTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bpExpectLoanDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bpExpectRepayDate;

    private BigDecimal bpServiceChargeRate;

    private BigDecimal bpLateRate;

    private Long bpRcptId;

    private String bpBorrowDesc;

    private String bpFundsPurpose;

    private String bpRepayingSource;

    private String bpPersonalCredit;

    private String bpMortgageInfo;

    private String bpRiskOpinion;

    private Integer bpRepayedPeriods;

    private String bpDesc;

    private Integer bpType;

    private Integer bpLoanerType;

    private Integer bpDisplayIsPc;

    private Integer bpDisplayIsMobile;

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

    public Long getBpRcptId() {
        return bpRcptId;
    }

    public void setBpRcptId(Long bpRcptId) {
        this.bpRcptId = bpRcptId;
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

    public Integer getBpLoanerType() {
        return bpLoanerType;
    }

    public void setBpLoanerType(Integer bpLoanerType) {
        this.bpLoanerType = bpLoanerType;
    }

    public Long getBpLoanerId() {
        return bpLoanerId;
    }

    public void setBpLoanerId(Long bpLoanerId) {
        this.bpLoanerId = bpLoanerId;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
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
