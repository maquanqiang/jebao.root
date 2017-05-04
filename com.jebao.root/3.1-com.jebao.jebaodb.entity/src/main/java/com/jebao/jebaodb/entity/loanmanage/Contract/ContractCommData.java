package com.jebao.jebaodb.entity.loanmanage.Contract;

import com.jebao.jebaodb.entity.investment.TbInvestInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Lee on 2016/12/21.
 */
public class ContractCommData {

    private String loanerTrueName;      //借款人姓名
    private String loanerNumber;        //证件号码
    private String interestSt;          //受让日期
    private String interestEt;          //结束日期
    private String repayTime;           //还款日期
    private String loanMoney;           //标的金额--实际放款金额
    private String loanMoneyRMB;        //标的金额人民币大写
    private String interestPayType;     //还款方式
    private String bidPeriods;          //标的周期
    private String cycleType;           //周期类型
    private String loanTime;            //放款日期  签字日期
    private String bidNumber;           //标的编号

    public String getInterestEt() {
        return interestEt;
    }

    public void setInterestEt(String interestEt) {
        this.interestEt = interestEt;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    private List<InvestInfoData> infos;   //列表

    public List<InvestInfoData> getInfos() {
        return infos;
    }

    public void setInfos(List<InvestInfoData> infos) {
        this.infos = infos;
    }

    public String getLoanerTrueName() {
        return loanerTrueName;
    }

    public void setLoanerTrueName(String loanerTrueName) {
        this.loanerTrueName = loanerTrueName;
    }

    public String getLoanerNumber() {
        return loanerNumber;
    }

    public void setLoanerNumber(String loanerNumber) {
        this.loanerNumber = loanerNumber;
    }

    public String getInterestSt() {
        return interestSt;
    }

    public void setInterestSt(String interestSt) {
        this.interestSt = interestSt;
    }

    public String getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(String repayTime) {
        this.repayTime = repayTime;
    }

    public String getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getLoanMoneyRMB() {
        return loanMoneyRMB;
    }

    public void setLoanMoneyRMB(String loanMoneyRMB) {
        this.loanMoneyRMB = loanMoneyRMB;
    }

    public String getInterestPayType() {
        return interestPayType;
    }

    public void setInterestPayType(String interestPayType) {
        this.interestPayType = interestPayType;
    }

    public String getBidPeriods() {
        return bidPeriods;
    }

    public void setBidPeriods(String bidPeriods) {
        this.bidPeriods = bidPeriods;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }
}
