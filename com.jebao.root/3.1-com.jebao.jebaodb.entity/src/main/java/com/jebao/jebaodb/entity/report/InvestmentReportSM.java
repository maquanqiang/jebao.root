package com.jebao.jebaodb.entity.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/11.
 */
public class InvestmentReportSM {
    private Integer bpPeriods;

    public Integer getBpPeriods() {
        return bpPeriods;
    }

    public void setBpPeriods(Integer bpPeriods) {
        this.bpPeriods = bpPeriods;
    }

    //标的编号
    private String indBpNumber;
    //投资人
    private String indTrueName;


    //投资金额
    private BigDecimal iiMoney;
    //联系电话
    private String liLoginName;
    //投资日期

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")//转换格式
    private Date liCreateTime;
    //起息日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date bpInterestSt;
    //到期日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date bpRepayTime;
    //投资期限
    private int bpCycleType;
    //
//    bp_cycle_size,
    //付息方式
    private int bpInterestPayType;
    //年化收益率
    private BigDecimal bpRate;
    //到期分红金额(利息+本金)
    private BigDecimal indMoney;
    public String getIndBpNumber() {
        return indBpNumber;
    }

    public void setIndBpNumber(String indBpNumber) {
        this.indBpNumber = indBpNumber;
    }

    public String getIndTrueName() {
        return indTrueName;
    }

    public void setIndTrueName(String indTrueName) {
        this.indTrueName = indTrueName;
    }

    public BigDecimal getIiMoney() {
        return iiMoney;
    }

    public void setIiMoney(BigDecimal iiMoney) {
        this.iiMoney = iiMoney;
    }

    public String getLiLoginName() {
        return liLoginName;
    }

    public void setLiLoginName(String liLoginName) {
        this.liLoginName = liLoginName;
    }

    public Date getLiCreateTime() {
        return liCreateTime;
    }

    public void setLiCreateTime(Date liCreateTime) {
        this.liCreateTime = liCreateTime;
    }

    public Date getBpInterestSt() {
        return bpInterestSt;
    }

    public void setBpInterestSt(Date bpInterestSt) {
        this.bpInterestSt = bpInterestSt;
    }

    public Date getBpRepayTime() {
        return bpRepayTime;
    }

    public void setBpRepayTime(Date bpRepayTime) {
        this.bpRepayTime = bpRepayTime;
    }

    public int getBpCycleType() {
        return bpCycleType;
    }

    public void setBpCycleType(int bpCycleType) {
        this.bpCycleType = bpCycleType;
    }

    public int getBpInterestPayType() {
        return bpInterestPayType;
    }

    public void setBpInterestPayType(int bpInterestPayType) {
        this.bpInterestPayType = bpInterestPayType;
    }

    public BigDecimal getBpRate() {
        return bpRate;
    }

    public void setBpRate(BigDecimal bpRate) {
        this.bpRate = bpRate;
    }

    public BigDecimal getIndMoney() {
        return indMoney;
    }

    public void setIndMoney(BigDecimal indMoney) {
        this.indMoney = indMoney;
    }
}
