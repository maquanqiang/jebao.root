package com.jebao.jebaodb.entity.postLoan.search;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/11/17.
 */
public class RepaymentDetailSM {

    private Long indId;

    private Long indBpId;

    private Long indIiId;

    private Long indLoginId;

    private String indTrueName;

    private String indThirdAccount;

    private String indBpNumber;

    private String indBpName;

    private Integer indPeriods;

    private Date indDateTime;

    private Date indFactDateTime;

    private Integer indOverdueDays;

    private BigDecimal indOverdueMoney;

    private Integer indFundType;

    private BigDecimal indMoney;

    private BigDecimal indFactMoeny;

    private Date indInterestSt;

    private Date indInterestEt;

    private Integer indStatus;

    private Date indCreateTime;

    private Date indUpdateTime;

    private Integer indIsDel;

    private String bpTrueName;

    //------------------时间条件查询接收-----------------
    private String searchDateSt;

    private String searchDateEnd;

    private String searchDateType;



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

    public Long getIndIiId() {
        return indIiId;
    }

    public void setIndIiId(Long indIiId) {
        this.indIiId = indIiId;
    }

    public Long getIndId() {
        return indId;
    }

    public void setIndId(Long indId) {
        this.indId = indId;
    }

    public Long getIndBpId() {
        return indBpId;
    }

    public void setIndBpId(Long indBpId) {
        this.indBpId = indBpId;
    }

    public Long getIndLoginId() {
        return indLoginId;
    }

    public void setIndLoginId(Long indLoginId) {
        this.indLoginId = indLoginId;
    }

    public String getIndTrueName() {
        return indTrueName;
    }

    public void setIndTrueName(String indTrueName) {
        this.indTrueName = indTrueName;
    }

    public String getIndThirdAccount() {
        return indThirdAccount;
    }

    public void setIndThirdAccount(String indThirdAccount) {
        this.indThirdAccount = indThirdAccount;
    }

    public String getIndBpNumber() {
        return indBpNumber;
    }

    public void setIndBpNumber(String indBpNumber) {
        this.indBpNumber = indBpNumber;
    }

    public String getIndBpName() {
        return indBpName;
    }

    public void setIndBpName(String indBpName) {
        this.indBpName = indBpName;
    }

    public Integer getIndPeriods() {
        return indPeriods;
    }

    public void setIndPeriods(Integer indPeriods) {
        this.indPeriods = indPeriods;
    }

    public Date getIndDateTime() {
        return indDateTime;
    }

    public void setIndDateTime(Date indDateTime) {
        this.indDateTime = indDateTime;
    }

    public Date getIndFactDateTime() {
        return indFactDateTime;
    }

    public void setIndFactDateTime(Date indFactDateTime) {
        this.indFactDateTime = indFactDateTime;
    }

    public Integer getIndOverdueDays() {
        return indOverdueDays;
    }

    public void setIndOverdueDays(Integer indOverdueDays) {
        this.indOverdueDays = indOverdueDays;
    }

    public BigDecimal getIndOverdueMoney() {
        return indOverdueMoney;
    }

    public void setIndOverdueMoney(BigDecimal indOverdueMoney) {
        this.indOverdueMoney = indOverdueMoney;
    }

    public Integer getIndFundType() {
        return indFundType;
    }

    public void setIndFundType(Integer indFundType) {
        this.indFundType = indFundType;
    }

    public BigDecimal getIndMoney() {
        return indMoney;
    }

    public void setIndMoney(BigDecimal indMoney) {
        this.indMoney = indMoney;
    }

    public BigDecimal getIndFactMoeny() {
        return indFactMoeny;
    }

    public void setIndFactMoeny(BigDecimal indFactMoeny) {
        this.indFactMoeny = indFactMoeny;
    }

    public Date getIndInterestSt() {
        return indInterestSt;
    }

    public void setIndInterestSt(Date indInterestSt) {
        this.indInterestSt = indInterestSt;
    }

    public Date getIndInterestEt() {
        return indInterestEt;
    }

    public void setIndInterestEt(Date indInterestEt) {
        this.indInterestEt = indInterestEt;
    }

    public Integer getIndStatus() {
        return indStatus;
    }

    public void setIndStatus(Integer indStatus) {
        this.indStatus = indStatus;
    }

    public Date getIndCreateTime() {
        return indCreateTime;
    }

    public void setIndCreateTime(Date indCreateTime) {
        this.indCreateTime = indCreateTime;
    }

    public Date getIndUpdateTime() {
        return indUpdateTime;
    }

    public void setIndUpdateTime(Date indUpdateTime) {
        this.indUpdateTime = indUpdateTime;
    }

    public Integer getIndIsDel() {
        return indIsDel;
    }

    public void setIndIsDel(Integer indIsDel) {
        this.indIsDel = indIsDel;
    }
}