package com.jebao.jebaodb.entity.investment;

import java.math.BigDecimal;
import java.util.Date;

public class TbLoanerRepaymentDetail {
    private Long lrdId;

    private Long lrdBpId;

    private String lrdBpNumber;

    private String lrdBpName;

    private Date lrdDateTime;

    private Integer lrdPeriods;

    private Integer lrdFundType;

    private BigDecimal lrdMoney;

    private BigDecimal lrdFactMoney;

    private Date lrdInterestSt;

    private Date lrdInterestEt;

    private Integer lrdOverdueDays;

    private BigDecimal lrdOverdueMoney;

    private Integer lrdStatus;

    private Date lrdCreateTime;

    private Date lrdUpdateTime;

    private Integer lrdIsDel;

    private Date lrdFactDateTime;

    public Long getLrdId() {
        return lrdId;
    }

    public void setLrdId(Long lrdId) {
        this.lrdId = lrdId;
    }

    public Long getLrdBpId() {
        return lrdBpId;
    }

    public void setLrdBpId(Long lrdBpId) {
        this.lrdBpId = lrdBpId;
    }

    public String getLrdBpNumber() {
        return lrdBpNumber;
    }

    public void setLrdBpNumber(String lrdBpNumber) {
        this.lrdBpNumber = lrdBpNumber == null ? null : lrdBpNumber.trim();
    }

    public String getLrdBpName() {
        return lrdBpName;
    }

    public void setLrdBpName(String lrdBpName) {
        this.lrdBpName = lrdBpName == null ? null : lrdBpName.trim();
    }

    public Date getLrdDateTime() {
        return lrdDateTime;
    }

    public void setLrdDateTime(Date lrdDateTime) {
        this.lrdDateTime = lrdDateTime;
    }

    public Integer getLrdPeriods() {
        return lrdPeriods;
    }

    public void setLrdPeriods(Integer lrdPeriods) {
        this.lrdPeriods = lrdPeriods;
    }

    public Integer getLrdFundType() {
        return lrdFundType;
    }

    public void setLrdFundType(Integer lrdFundType) {
        this.lrdFundType = lrdFundType;
    }

    public BigDecimal getLrdMoney() {
        return lrdMoney;
    }

    public void setLrdMoney(BigDecimal lrdMoney) {
        this.lrdMoney = lrdMoney;
    }

    public BigDecimal getLrdFactMoney() {
        return lrdFactMoney;
    }

    public void setLrdFactMoney(BigDecimal lrdFactMoney) {
        this.lrdFactMoney = lrdFactMoney;
    }

    public Date getLrdInterestSt() {
        return lrdInterestSt;
    }

    public void setLrdInterestSt(Date lrdInterestSt) {
        this.lrdInterestSt = lrdInterestSt;
    }

    public Date getLrdInterestEt() {
        return lrdInterestEt;
    }

    public void setLrdInterestEt(Date lrdInterestEt) {
        this.lrdInterestEt = lrdInterestEt;
    }

    public Integer getLrdOverdueDays() {
        return lrdOverdueDays;
    }

    public void setLrdOverdueDays(Integer lrdOverdueDays) {
        this.lrdOverdueDays = lrdOverdueDays;
    }

    public BigDecimal getLrdOverdueMoney() {
        return lrdOverdueMoney;
    }

    public void setLrdOverdueMoney(BigDecimal lrdOverdueMoney) {
        this.lrdOverdueMoney = lrdOverdueMoney;
    }

    public Integer getLrdStatus() {
        return lrdStatus;
    }

    public void setLrdStatus(Integer lrdStatus) {
        this.lrdStatus = lrdStatus;
    }

    public Date getLrdCreateTime() {
        return lrdCreateTime;
    }

    public void setLrdCreateTime(Date lrdCreateTime) {
        this.lrdCreateTime = lrdCreateTime;
    }

    public Date getLrdUpdateTime() {
        return lrdUpdateTime;
    }

    public void setLrdUpdateTime(Date lrdUpdateTime) {
        this.lrdUpdateTime = lrdUpdateTime;
    }

    public Integer getLrdIsDel() {
        return lrdIsDel;
    }

    public void setLrdIsDel(Integer lrdIsDel) {
        this.lrdIsDel = lrdIsDel;
    }

    public Date getLrdFactDateTime() {
        return lrdFactDateTime;
    }

    public void setLrdFactDateTime(Date lrdFactDateTime) {
        this.lrdFactDateTime = lrdFactDateTime;
    }
}