package com.jebao.jebaodb.entity.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/7.
 * 投资人明细表
 */
public class ReportInvestment {
    //通过当前的信息来指定输出的内容
    public static InvestmentReportSM entity(ReportInvestment form){
        InvestmentReportSM investmentReportSM = new InvestmentReportSM();
        investmentReportSM.setBpCycleType(form.getBpCycleType());
        investmentReportSM.setBpInterestPayType(form.getBpInterestPayType());
        investmentReportSM.setBpInterestSt(form.getBpInterestSt());
        investmentReportSM.setBpRate(form.getBpRate());
        investmentReportSM.setBpRepayTime(form.getBpRepayTime());
        investmentReportSM.setIiMoney(form.getIiMoney());
        investmentReportSM.setLiLoginName(form.getLiLoginName());
        investmentReportSM.setLiCreateTime(form.getLiCreateTime());
        investmentReportSM.setBpRepayTime(form.getBpRepayTime());
        investmentReportSM.setIndTrueName(form.getIndTrueName());
        investmentReportSM.setIndBpNumber(form.getIndBpNumber());
        investmentReportSM.setIndMoney(form.getIndMoney());
        investmentReportSM.setBpPeriods(form.getBpPeriods());
        investmentReportSM.setSearchDateSt(form.getSearchDateSt());
        investmentReportSM.setSearchDateEnd(form.getSearchDateEnd());
        return investmentReportSM;
    }

    @JsonPropertyDescription("投资期限")
    private Integer bpPeriods;

    public Integer getBpPeriods() {
        return bpPeriods;
    }

    public void setBpPeriods(Integer bpPeriods) {
        this.bpPeriods = bpPeriods;
    }

    //标的编号
    @JsonPropertyDescription("标的编号")
    private String indBpNumber;
    //投资人
    @JsonPropertyDescription("投资人")
    private String indTrueName;


    //投资金额
    @JsonPropertyDescription("投资金额(元)")
    private BigDecimal iiMoney;
    //联系电话
    @JsonPropertyDescription("联系电话")
    private String liLoginName;
    //投资日期

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")//转换格式
    @JsonPropertyDescription("投资日期")
    private Date liCreateTime;
    //起息日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @JsonPropertyDescription("起息日期")
    private Date bpInterestSt;
    //到期日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @JsonPropertyDescription("到期日期")
    private Date bpRepayTime;
    //投资期限
   // @JsonPropertyDescription("投资期限")
    private int bpCycleType;
    //
//    bp_cycle_size,
    //付息方式
   // @JsonPropertyDescription("付息方式")
    private int bpInterestPayType;
    //年化收益率
    @JsonPropertyDescription("年化收益率(%)")
    private BigDecimal bpRate;
    //到期分红金额(利息+本金)
    @JsonPropertyDescription("到期分红金额(元)")
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

    //添加的字段
    public String searchDateSt;
    public String searchDateEnd;

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



    @Override
    public String toString() {
        return "ReportInvestment{" +
                "indBpNumber='" + indBpNumber + '\'' +
                ", indTrueName='" + indTrueName + '\'' +
                ", iiMoney=" + iiMoney +
                ", liLoginName='" + liLoginName + '\'' +
                ", liCreateTime=" + liCreateTime +
                ", bpInterestSt=" + bpInterestSt +
                ", bpRepayTime=" + bpRepayTime +
                ", bpCycleType=" + bpCycleType +
                ", bpInterestPayType=" + bpInterestPayType +
                ", bpRate=" + bpRate +
                ", indMoney=" + indMoney +
                '}';
    }

}
