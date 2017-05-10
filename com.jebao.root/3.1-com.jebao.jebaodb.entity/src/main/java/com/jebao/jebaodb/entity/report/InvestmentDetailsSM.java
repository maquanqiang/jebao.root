package com.jebao.jebaodb.entity.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/20 0020.
 * 投资明细
 *
 */
public class InvestmentDetailsSM {
    //添加的字段
    public String searchDateSt;

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

    public String searchDateEnd;
    //投资期限
    @JsonPropertyDescription("投资期限")
    private int bpCycleType;

    public int getBpCycleType() {
        return bpCycleType;
    }

    public void setBpCycleType(int bpCycleType) {
        this.bpCycleType = bpCycleType;
    }

    //标的编号
    @JsonPropertyDescription("标的编号")
    private String bpNumber;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")//转换格式
    @JsonPropertyDescription("充值日期")
    private Date liCreateTime;
    //起息日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @JsonPropertyDescription("起息日期")
    private Date bpInterestSt;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @JsonPropertyDescription("到期日期")
    private Date bpRepayTime;
    //投资人
    @JsonPropertyDescription("投资人")
    private String indTrueName;
    //总部凭证号

    //投资期限
    @JsonPropertyDescription("投资期限")
    private Integer bpPeriods;
    //总部凭证号

    //付息方式
     @JsonPropertyDescription("付息方式")
    private int bpInterestPayType;
    @JsonPropertyDescription("到期分红金额(元)")
    private BigDecimal indMoney;
    //联系电话
    @JsonPropertyDescription("联系电话")
    private String liLoginName;

    //投资金额
    @JsonPropertyDescription("投资金额(元)")
    private BigDecimal iiMoney;
    //折标后金额
    @JsonPropertyDescription("折标后金额")
    private double  foldMoney;
    //年化收益率

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
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

    public String getIndTrueName() {
        return indTrueName;
    }

    public void setIndTrueName(String indTrueName) {
        this.indTrueName = indTrueName;
    }

    public Integer getBpPeriods() {
        return bpPeriods;
    }

    public void setBpPeriods(Integer bpPeriods) {
        this.bpPeriods = bpPeriods;
    }

    public int getBpInterestPayType() {
        return bpInterestPayType;
    }

    public void setBpInterestPayType(int bpInterestPayType) {
        this.bpInterestPayType = bpInterestPayType;
    }

    public BigDecimal getIndMoney() {
        return indMoney;
    }

    public void setIndMoney(BigDecimal indMoney) {
        this.indMoney = indMoney;
    }

    public String getLiLoginName() {
        return liLoginName;
    }

    public void setLiLoginName(String liLoginName) {
        this.liLoginName = liLoginName;
    }

    public BigDecimal getIiMoney() {
        return iiMoney;
    }

    public void setIiMoney(BigDecimal iiMoney) {
        this.iiMoney = iiMoney;
    }

    public double getFoldMoney() {
        return foldMoney;
    }

    public void setFoldMoney(double foldMoney) {
        this.foldMoney = foldMoney;
    }

    public void setFoldMoney(Integer foldMoney) {
        this.foldMoney = foldMoney;
    }

    public BigDecimal getBpRate() {
        return bpRate;
    }

    public void setBpRate(BigDecimal bpRate) {
        this.bpRate = bpRate;
    }

    @JsonPropertyDescription("年化收益率(%)")
    private BigDecimal bpRate;

}

