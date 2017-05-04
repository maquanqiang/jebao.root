package com.jebao.jebaodb.entity.investment;

import java.math.BigDecimal;
import java.util.Date;

public class TbInvestInfo {


    public final static int STATUS_FREEZE = 1;          //冻结中
    public final static int STATUS_REPAYING = 2;        //还款中
    public final static int STATUS_COMPLETE = 3;        //已完成
    public final static int STATUS_UNFREEZE = 4;        //流标


    private Long iiId;

    private Long iiLoginId;

    private String iiLoginName;

    private String iiTrueName;

    private String iiThirdAccount;

    private String iiContractNo;

    private Long iiBpId;

    private String iiBpName;

    private BigDecimal iiMoney;

    private Integer iiFreezeStatus;

    private String iiContractUrl;

    private Integer iiBpRepayedPeriods;

    private Date iiCreateTime;

    private Date iiUpdateTime;

    private Integer iiIsDel;

    private String iiSsn;

    private Integer iiPlatform;

    private Integer iiPlatformType;

    private Integer iiChannel;

    private Integer iiChannelType;

    private String iiOrderNumber;
    //---------------------------------
    private BigDecimal bpBidMoney;

    public Long getIiId() {
        return iiId;
    }

    public void setIiId(Long iiId) {
        this.iiId = iiId;
    }

    public Long getIiLoginId() {
        return iiLoginId;
    }

    public void setIiLoginId(Long iiLoginId) {
        this.iiLoginId = iiLoginId;
    }

    public String getIiLoginName() {
        return iiLoginName;
    }

    public void setIiLoginName(String iiLoginName) {
        this.iiLoginName = iiLoginName == null ? null : iiLoginName.trim();
    }

    public String getIiTrueName() {
        return iiTrueName;
    }

    public void setIiTrueName(String iiTrueName) {
        this.iiTrueName = iiTrueName == null ? null : iiTrueName.trim();
    }

    public String getIiThirdAccount() {
        return iiThirdAccount;
    }

    public void setIiThirdAccount(String iiThirdAccount) {
        this.iiThirdAccount = iiThirdAccount == null ? null : iiThirdAccount.trim();
    }

    public Long getIiBpId() {
        return iiBpId;
    }

    public void setIiBpId(Long iiBpId) {
        this.iiBpId = iiBpId;
    }

    public String getIiBpName() {
        return iiBpName;
    }

    public void setIiBpName(String iiBpName) {
        this.iiBpName = iiBpName == null ? null : iiBpName.trim();
    }

    public BigDecimal getIiMoney() {
        return iiMoney;
    }

    public void setIiMoney(BigDecimal iiMoney) {
        this.iiMoney = iiMoney;
    }

    public Integer getIiFreezeStatus() {
        return iiFreezeStatus;
    }

    public void setIiFreezeStatus(Integer iiFreezeStatus) {
        this.iiFreezeStatus = iiFreezeStatus;
    }

    public String getIiContractUrl() {
        return iiContractUrl;
    }

    public void setIiContractUrl(String iiContractUrl) {
        this.iiContractUrl = iiContractUrl == null ? null : iiContractUrl.trim();
    }

    public Date getIiCreateTime() {
        return iiCreateTime;
    }

    public void setIiCreateTime(Date iiCreateTime) {
        this.iiCreateTime = iiCreateTime;
    }

    public Date getIiUpdateTime() {
        return iiUpdateTime;
    }

    public void setIiUpdateTime(Date iiUpdateTime) {
        this.iiUpdateTime = iiUpdateTime;
    }

    public Integer getIiIsDel() {
        return iiIsDel;
    }

    public void setIiIsDel(Integer iiIsDel) {
        this.iiIsDel = iiIsDel;
    }

    public BigDecimal getBpBidMoney() {
        return bpBidMoney;
    }

    public void setBpBidMoney(BigDecimal bpBidMoney) {
        this.bpBidMoney = bpBidMoney;
    }

    public String getIiSsn() {
        return iiSsn;
    }

    public void setIiSsn(String iiSsn) {
        this.iiSsn = iiSsn;
    }

    public Integer getIiBpRepayedPeriods() {
        return iiBpRepayedPeriods;
    }

    public void setIiBpRepayedPeriods(Integer iiBpRepayedPeriods) {
        this.iiBpRepayedPeriods = iiBpRepayedPeriods;
    }

    public String getIiContractNo() {
        return iiContractNo;
    }

    public void setIiContractNo(String iiContractNo) {
        this.iiContractNo = iiContractNo;
    }

    public Integer getIiPlatform() {
        return iiPlatform;
    }

    public void setIiPlatform(Integer iiPlatform) {
        this.iiPlatform = iiPlatform;
    }

    public Integer getIiPlatformType() {
        return iiPlatformType;
    }

    public void setIiPlatformType(Integer iiPlatformType) {
        this.iiPlatformType = iiPlatformType;
    }

    public Integer getIiChannel() {
        return iiChannel;
    }

    public void setIiChannel(Integer iiChannel) {
        this.iiChannel = iiChannel;
    }

    public Integer getIiChannelType() {
        return iiChannelType;
    }

    public void setIiChannelType(Integer iiChannelType) {
        this.iiChannelType = iiChannelType;
    }

    public String getIiOrderNumber() {
        return iiOrderNumber;
    }

    public void setIiOrderNumber(String iiOrderNumber) {
        this.iiOrderNumber = iiOrderNumber;
    }
}