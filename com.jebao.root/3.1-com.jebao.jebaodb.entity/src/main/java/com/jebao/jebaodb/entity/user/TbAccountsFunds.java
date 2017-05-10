package com.jebao.jebaodb.entity.user;

import java.math.BigDecimal;
import java.util.Date;

public class TbAccountsFunds {
    private Long afId;

    private Long afLoginId;

    private String afThirdAccount;

    private BigDecimal afBalance;

    private Date afCreateTime;

    private Date afUpdateTime;

    private Integer afIsDel;

    public Long getAfId() {
        return afId;
    }

    public void setAfId(Long afId) {
        this.afId = afId;
    }

    public Long getAfLoginId() {
        return afLoginId;
    }

    public void setAfLoginId(Long afLoginId) {
        this.afLoginId = afLoginId;
    }

    public String getAfThirdAccount() {
        return afThirdAccount;
    }

    public void setAfThirdAccount(String afThirdAccount) {
        this.afThirdAccount = afThirdAccount == null ? null : afThirdAccount.trim();
    }

    public BigDecimal getAfBalance() {
        return afBalance;
    }

    public void setAfBalance(BigDecimal afBalance) {
        this.afBalance = afBalance;
    }

    public Date getAfCreateTime() {
        return afCreateTime;
    }

    public void setAfCreateTime(Date afCreateTime) {
        this.afCreateTime = afCreateTime;
    }

    public Date getAfUpdateTime() {
        return afUpdateTime;
    }

    public void setAfUpdateTime(Date afUpdateTime) {
        this.afUpdateTime = afUpdateTime;
    }

    public Integer getAfIsDel() {
        return afIsDel;
    }

    public void setAfIsDel(Integer afIsDel) {
        this.afIsDel = afIsDel;
    }
}