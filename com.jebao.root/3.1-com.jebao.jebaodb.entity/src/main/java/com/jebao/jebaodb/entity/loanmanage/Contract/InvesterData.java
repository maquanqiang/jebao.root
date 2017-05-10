package com.jebao.jebaodb.entity.loanmanage.Contract;

import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/26.
 */
public class InvesterData {

    private String name;    //投资人姓名
    private String investMoney; //金额
    private String investerNumber;  //身份证号码
    private String totalMoney;      //本息总和
    private String investMoneyRMB;  //人民币
    private String totalMoneyRMB;   //总和人民币

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(String investMoney) {
        this.investMoney = investMoney;
    }

    public String getInvesterNumber() {
        return investerNumber;
    }

    public void setInvesterNumber(String investerNumber) {
        this.investerNumber = investerNumber;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getInvestMoneyRMB() {
        return investMoneyRMB;
    }

    public void setInvestMoneyRMB(String investMoneyRMB) {
        this.investMoneyRMB = investMoneyRMB;
    }

    public String getTotalMoneyRMB() {
        return totalMoneyRMB;
    }

    public void setTotalMoneyRMB(String totalMoneyRMB) {
        this.totalMoneyRMB = totalMoneyRMB;
    }
}
