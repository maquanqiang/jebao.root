package com.jebao.jebaodb.entity.user;

import java.math.BigDecimal;
import java.util.Date;

public class TbFundsDetails {
    private Long fdId;

    private Long fdLoginId;

    private String fdThirdAccount;

    private String fdSerialNumber;

    private Date fdSerialTime;

    private Date fdCreateTime;

    private Integer fdSerialTypeId;

    private String fdSerialTypeName;

    private BigDecimal fdSerialAmount;

    private BigDecimal fdBalanceBefore;

    private BigDecimal fdBalanceAfter;

    private BigDecimal fdCommissionCharge;

    private Long fdBpId;

    private String fdBpName;

    private Integer fdBalanceStatus;

    private Integer fdSerialStatus;

    private Integer fdIsDel;

    private Integer fdPlatform;

    private Integer fdPlatformType;

    private Integer fdChannel;

    private Integer fdChannelType;

    public Long getFdId() {
        return fdId;
    }

    public void setFdId(Long fdId) {
        this.fdId = fdId;
    }

    public Long getFdLoginId() {
        return fdLoginId;
    }

    public void setFdLoginId(Long fdLoginId) {
        this.fdLoginId = fdLoginId;
    }

    public String getFdThirdAccount() {
        return fdThirdAccount;
    }

    public void setFdThirdAccount(String fdThirdAccount) {
        this.fdThirdAccount = fdThirdAccount == null ? null : fdThirdAccount.trim();
    }

    public String getFdSerialNumber() {
        return fdSerialNumber;
    }

    public void setFdSerialNumber(String fdSerialNumber) {
        this.fdSerialNumber = fdSerialNumber == null ? null : fdSerialNumber.trim();
    }

    public Date getFdSerialTime() {
        return fdSerialTime;
    }

    public void setFdSerialTime(Date fdSerialTime) {
        this.fdSerialTime = fdSerialTime;
    }

    public Date getFdCreateTime() {
        return fdCreateTime;
    }

    public void setFdCreateTime(Date fdCreateTime) {
        this.fdCreateTime = fdCreateTime;
    }

    public Integer getFdSerialTypeId() {
        return fdSerialTypeId;
    }

    public void setFdSerialTypeId(Integer fdSerialTypeId) {
        this.fdSerialTypeId = fdSerialTypeId;
    }

    public String getFdSerialTypeName() {
        return fdSerialTypeName;
    }

    public void setFdSerialTypeName(String fdSerialTypeName) {
        this.fdSerialTypeName = fdSerialTypeName == null ? null : fdSerialTypeName.trim();
    }

    public BigDecimal getFdSerialAmount() {
        return fdSerialAmount;
    }

    public void setFdSerialAmount(BigDecimal fdSerialAmount) {
        this.fdSerialAmount = fdSerialAmount;
    }

    public BigDecimal getFdBalanceBefore() {
        return fdBalanceBefore;
    }

    public void setFdBalanceBefore(BigDecimal fdBalanceBefore) {
        this.fdBalanceBefore = fdBalanceBefore;
    }

    public BigDecimal getFdBalanceAfter() {
        return fdBalanceAfter;
    }

    public void setFdBalanceAfter(BigDecimal fdBalanceAfter) {
        this.fdBalanceAfter = fdBalanceAfter;
    }

    public BigDecimal getFdCommissionCharge() {
        return fdCommissionCharge;
    }

    public void setFdCommissionCharge(BigDecimal fdCommissionCharge) {
        this.fdCommissionCharge = fdCommissionCharge;
    }

    public Long getFdBpId() {
        return fdBpId;
    }

    public void setFdBpId(Long fdBpId) {
        this.fdBpId = fdBpId;
    }

    public String getFdBpName() {
        return fdBpName;
    }

    public void setFdBpName(String fdBpName) {
        this.fdBpName = fdBpName == null ? null : fdBpName.trim();
    }

    public Integer getFdBalanceStatus() {
        return fdBalanceStatus;
    }

    public void setFdBalanceStatus(Integer fdBalanceStatus) {
        this.fdBalanceStatus = fdBalanceStatus;
    }

    public Integer getFdSerialStatus() {
        return fdSerialStatus;
    }

    public void setFdSerialStatus(Integer fdSerialStatus) {
        this.fdSerialStatus = fdSerialStatus;
    }

    public Integer getFdIsDel() {
        return fdIsDel;
    }

    public void setFdIsDel(Integer fdIsDel) {
        this.fdIsDel = fdIsDel;
    }

    public Integer getFdPlatform() {
        return fdPlatform;
    }

    public void setFdPlatform(Integer fdPlatform) {
        this.fdPlatform = fdPlatform;
    }

    public Integer getFdPlatformType() {
        return fdPlatformType;
    }

    public void setFdPlatformType(Integer fdPlatformType) {
        this.fdPlatformType = fdPlatformType;
    }

    public Integer getFdChannel() {
        return fdChannel;
    }

    public void setFdChannel(Integer fdChannel) {
        this.fdChannel = fdChannel;
    }

    public Integer getFdChannelType() {
        return fdChannelType;
    }

    public void setFdChannelType(Integer fdChannelType) {
        this.fdChannelType = fdChannelType;
    }
}