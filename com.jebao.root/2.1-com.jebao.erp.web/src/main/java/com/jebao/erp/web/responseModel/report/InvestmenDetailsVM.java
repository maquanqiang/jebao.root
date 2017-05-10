package com.jebao.erp.web.responseModel.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.report.InvestmentDetails;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/10.
 */
public class InvestmenDetailsVM extends ViewModel {


    String[] cycleType = {"","日","月","季","年"};

    //封装到当前的对象里面
    public InvestmenDetailsVM(InvestmentDetails reportInvestment) {

//        reportInvestment.getIiMoney() * getBpCycleType();
        this.setIndMoney(reportInvestment.getIndMoney());
        this.setBpRate(reportInvestment.getBpRate());
        //
        this.setBpPeriods(reportInvestment.getBpPeriods());
        //投资期限
        this.setBpCycleType(reportInvestment.getBpCycleType());
        this.setCreatebpCycleType(reportInvestment.getBpPeriods().toString()+cycleType[reportInvestment.getBpCycleType()]);
        this.setLiLoginName(reportInvestment.getLiLoginName());
        //付息方式
        this.setBpInterestPayType(reportInvestment.getBpInterestPayType());
        if(reportInvestment.getBpInterestPayType()==0){
            this.setCreatebpInterestPayType("");

        }else if(reportInvestment.getBpInterestPayType()==1){

            this.setCreatebpInterestPayType("一次性还本付息");
        }else if(reportInvestment.getBpInterestPayType()==2){
            this.setCreatebpInterestPayType("先息后本，按期付息");
        }
        this.setBpInterestSt(reportInvestment.getBpInterestSt());
        if(reportInvestment.getBpInterestSt()==null){
            this.setCreatebpInterestSt("");

        }else {
            this.setCreatebpInterestSt(new SimpleDateFormat("yyyy-MM-dd").format(reportInvestment.getBpInterestSt()));
        }
        this.setBpRepayTime(reportInvestment.getBpRepayTime());
        if(reportInvestment.getBpRepayTime()==null){
            this.setCreatebpRepayTime("");
        }else{
            this.setCreatebpRepayTime(new SimpleDateFormat("yyyy-MM-dd").format(reportInvestment.getBpRepayTime()));
        }
        this.setBpNumber(reportInvestment.getBpNumber());
        this.setIndTrueName(reportInvestment.getIndTrueName());
        this.setLiCreateTime(reportInvestment.getLiCreateTime());
        this.setIiMoney(reportInvestment.getIiMoney());

        if(reportInvestment.getLiCreateTime()==null){
            this.setCreateTimeStr("");

        }else{
            this.setCreateTimeStr(  new SimpleDateFormat("yyyy-MM-dd").format(reportInvestment.getLiCreateTime()));
        }
            double d=reportInvestment.getIiMoney().doubleValue() * reportInvestment.getBpPeriods() /12;
            BigDecimal bg= new BigDecimal(d);
           double f=bg.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            //折标后金额
            this.setFoldMoney(f);
        this.setSearchDateSt(reportInvestment.getSearchDateSt());
        this.setSearchDateEnd(reportInvestment.getSearchDateEnd());
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

    @JsonPropertyDescription("折标后金额")
    private double  foldMoney;

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
    }

    public String[] getCycleType() {

        return cycleType;
    }

    public void setCycleType(String[] cycleType) {
        this.cycleType = cycleType;
    }

    //标的编号
    @JsonPropertyDescription("标的编号")
    private String bpNumber;
    //投资人
    @JsonPropertyDescription("投资人")
    private String indTrueName;


    //投资金额
    @JsonPropertyDescription("投资金额(元)") //加上这个注解可以实现自动下载的功能
    private BigDecimal iiMoney;
    //联系电话
    @JsonPropertyDescription("联系电话")
    private String liLoginName;
    //投资日期

    @JsonPropertyDescription("投资日期")
    private String createTimeStr;
   // @JsonPropertyDescription("投资日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date liCreateTime;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
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

    public double getFoldMoney() {
        return foldMoney;
    }

    public void setFoldMoney(double foldMoney) {
        this.foldMoney = foldMoney;
    }

    public void setFoldMoney(Integer foldMoney) {
        this.foldMoney = foldMoney;
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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")

    private Date bpInterestSt;
    @JsonPropertyDescription("起息日期")
    private String createbpInterestSt;

    public String getCreatebpInterestSt() {
        return createbpInterestSt;
    }

    public void setCreatebpInterestSt(String createbpInterestSt) {
        this.createbpInterestSt = createbpInterestSt;
    }

    //到期日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date  bpRepayTime;
    @JsonPropertyDescription("到期日期")
    private String createbpRepayTime;

    public String getCreatebpRepayTime() {
        return createbpRepayTime;
    }

    public void setCreatebpRepayTime(String createbpRepayTime) {
        this.createbpRepayTime = createbpRepayTime;
    }
    //投资期限


    private int bpCycleType;
    @JsonPropertyDescription("投资期限")
    private String createbpCycleType;
    private Integer bpPeriods;

    public Integer getBpPeriods() {
        return bpPeriods;
    }

    public void setBpPeriods(Integer bpPeriods) {
        this.bpPeriods = bpPeriods;
    }
//    bp_cycle_size,
    //付息方式

    private int bpInterestPayType;
    @JsonPropertyDescription("付息方式")
    private String createbpInterestPayType;

    public String getCreatebpInterestPayType() {
        return createbpInterestPayType;
    }

    public void setCreatebpInterestPayType(String createbpInterestPayType) {
        this.createbpInterestPayType = createbpInterestPayType;
    }

    public String getCreatebpCycleType() {
        return createbpCycleType;
    }

    public void setCreatebpCycleType(String createbpCycleType) {
        this.createbpCycleType = createbpCycleType;
    }


    @JsonPropertyDescription("年化收益率(%)")
    private BigDecimal bpRate;
    @JsonPropertyDescription("到期分红金额(元)")
    private BigDecimal indMoney;


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
