package com.jebao.jebaodb.entity.investment;

import java.math.BigDecimal;
import java.util.Date;

public class TbIncomeDetail {
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

    private String incomeDetailMD5;         //台账记录数据md5

    private String indThirdContractNo;      //冻结合同号

    //------------------------------------------
    private String bpTrueName;              //借款人姓名

    private Date investTime;               //投资时间
    private BigDecimal investMoney;         //投资金额
    private Integer periods;                //投资期限
    private BigDecimal rate;                //利率
    private BigDecimal incomeTotal;         //应收总额
    private Date collectTime;               //募集时间
    private String contractUrl;             //合同url
    private BigDecimal bpLoanMoney;         //借款额
    private Date bpStartTime;               //募集时间
    private Integer cycleType;              //周期类型

    public String getIndThirdContractNo() {

        return indThirdContractNo;
    }

    public void setIndThirdContractNo(String indThirdContractNo) {
        this.indThirdContractNo = indThirdContractNo;
    }

    public String getIncomeDetailMD5() {
        return incomeDetailMD5;
    }

    public void setIncomeDetailMD5(String incomeDetailMD5) {
        this.incomeDetailMD5 = incomeDetailMD5;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public Date getBpStartTime() {
        return bpStartTime;
    }

    public void setBpStartTime(Date bpStartTime) {
        this.bpStartTime = bpStartTime;
    }

    public BigDecimal getBpLoanMoney() {
        return bpLoanMoney;
    }

    public void setBpLoanMoney(BigDecimal bpLoanMoney) {
        this.bpLoanMoney = bpLoanMoney;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public Date getInvestTime() {
        return investTime;
    }

    public void setInvestTime(Date investTime) {
        this.investTime = investTime;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Long getIndId() {
        return indId;
    }

    public void setIndId(Long indId) {
        this.indId = indId;
    }

    public Long getIndIiId() {
        return indIiId;
    }

    public void setIndIiId(Long indIiId) {
        this.indIiId = indIiId;
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
        this.indTrueName = indTrueName == null ? null : indTrueName.trim();
    }

    public String getIndThirdAccount() {
        return indThirdAccount;
    }

    public void setIndThirdAccount(String indThirdAccount) {
        this.indThirdAccount = indThirdAccount == null ? null : indThirdAccount.trim();
    }

    public String getIndBpNumber() {
        return indBpNumber;
    }

    public void setIndBpNumber(String indBpNumber) {
        this.indBpNumber = indBpNumber == null ? null : indBpNumber.trim();
    }

    public String getIndBpName() {
        return indBpName;
    }

    public void setIndBpName(String indBpName) {
        this.indBpName = indBpName == null ? null : indBpName.trim();
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

    public Long getIndBpId() {
        return indBpId;
    }

    public void setIndBpId(Long indBpId) {
        this.indBpId = indBpId;
    }

    public String getBpTrueName() {
        return bpTrueName;
    }

    public void setBpTrueName(String bpTrueName) {
        this.bpTrueName = bpTrueName;
    }

    public String toMD5(){
        return indIiId+indBpId+indLoginId+indThirdAccount+indMoney;
    }
}