package com.jebao.jebaodb.entity.user;

import java.util.Date;

public class TbUserDetails {
    private Long udId;

    private Long udLoginId;

    private String udNickName;

    private String udTrueName;

    private String udIdNumber;

    private String udEmail;

    private String udPhone;

    private String udInvitationCode;

    private Integer udCustomerManagerId;

    private Long udLoanerId;

    private Integer udPlatform;

    private String udThirdAccount;

    private String udThirdLoginPassword;

    private String udThirdPayPassword;

    private String udBankProvincesCode;

    private String udBankProvincesName;

    private String udBankCityCode;

    private String udBankCityName;

    private String udBankParentBankCode;

    private String udBankParentBankName;

    private String udBankCardNo;

    private Integer udBankCardNoChangeStatus;

    private String udInterfaceMchntTxnSsn;

    private Integer udPosStatus;

    private Date udCreateTime;

    private Date udUpdateTime;

    private Integer udIsDel;

    private Integer udPlatformType;

    private Integer udChannel;

    private Integer udChannelType;

    public Long getUdId() {
        return udId;
    }

    public void setUdId(Long udId) {
        this.udId = udId;
    }

    public Long getUdLoginId() {
        return udLoginId;
    }

    public void setUdLoginId(Long udLoginId) {
        this.udLoginId = udLoginId;
    }

    public String getUdNickName() {
        return udNickName;
    }

    public void setUdNickName(String udNickName) {
        this.udNickName = udNickName == null ? null : udNickName.trim();
    }

    public String getUdTrueName() {
        return udTrueName;
    }

    public void setUdTrueName(String udTrueName) {
        this.udTrueName = udTrueName == null ? null : udTrueName.trim();
    }

    public String getUdIdNumber() {
        return udIdNumber;
    }

    public void setUdIdNumber(String udIdNumber) {
        this.udIdNumber = udIdNumber == null ? null : udIdNumber.trim();
    }

    public String getUdEmail() {
        return udEmail;
    }

    public void setUdEmail(String udEmail) {
        this.udEmail = udEmail == null ? null : udEmail.trim();
    }

    public String getUdPhone() {
        return udPhone;
    }

    public void setUdPhone(String udPhone) {
        this.udPhone = udPhone == null ? null : udPhone.trim();
    }

    public String getUdInvitationCode() {
        return udInvitationCode;
    }

    public void setUdInvitationCode(String udInvitationCode) {
        this.udInvitationCode = udInvitationCode == null ? null : udInvitationCode.trim();
    }

    public Integer getUdCustomerManagerId() {
        return udCustomerManagerId;
    }

    public void setUdCustomerManagerId(Integer udCustomerManagerId) {
        this.udCustomerManagerId = udCustomerManagerId;
    }

    public Long getUdLoanerId() {
        return udLoanerId;
    }

    public void setUdLoanerId(Long udLoanerId) {
        this.udLoanerId = udLoanerId;
    }

    public Integer getUdPlatform() {
        return udPlatform;
    }

    public void setUdPlatform(Integer udPlatform) {
        this.udPlatform = udPlatform;
    }

    public String getUdThirdAccount() {
        return udThirdAccount;
    }

    public void setUdThirdAccount(String udThirdAccount) {
        this.udThirdAccount = udThirdAccount == null ? null : udThirdAccount.trim();
    }

    public String getUdThirdLoginPassword() {
        return udThirdLoginPassword;
    }

    public void setUdThirdLoginPassword(String udThirdLoginPassword) {
        this.udThirdLoginPassword = udThirdLoginPassword == null ? null : udThirdLoginPassword.trim();
    }

    public String getUdThirdPayPassword() {
        return udThirdPayPassword;
    }

    public void setUdThirdPayPassword(String udThirdPayPassword) {
        this.udThirdPayPassword = udThirdPayPassword == null ? null : udThirdPayPassword.trim();
    }

    public String getUdBankProvincesCode() {
        return udBankProvincesCode;
    }

    public void setUdBankProvincesCode(String udBankProvincesCode) {
        this.udBankProvincesCode = udBankProvincesCode == null ? null : udBankProvincesCode.trim();
    }

    public String getUdBankProvincesName() {
        return udBankProvincesName;
    }

    public void setUdBankProvincesName(String udBankProvincesName) {
        this.udBankProvincesName = udBankProvincesName == null ? null : udBankProvincesName.trim();
    }

    public String getUdBankCityCode() {
        return udBankCityCode;
    }

    public void setUdBankCityCode(String udBankCityCode) {
        this.udBankCityCode = udBankCityCode == null ? null : udBankCityCode.trim();
    }

    public String getUdBankCityName() {
        return udBankCityName;
    }

    public void setUdBankCityName(String udBankCityName) {
        this.udBankCityName = udBankCityName == null ? null : udBankCityName.trim();
    }

    public String getUdBankParentBankCode() {
        return udBankParentBankCode;
    }

    public void setUdBankParentBankCode(String udBankParentBankCode) {
        this.udBankParentBankCode = udBankParentBankCode == null ? null : udBankParentBankCode.trim();
    }

    public String getUdBankParentBankName() {
        return udBankParentBankName;
    }

    public void setUdBankParentBankName(String udBankParentBankName) {
        this.udBankParentBankName = udBankParentBankName == null ? null : udBankParentBankName.trim();
    }

    public String getUdBankCardNo() {
        return udBankCardNo;
    }

    public void setUdBankCardNo(String udBankCardNo) {
        this.udBankCardNo = udBankCardNo == null ? null : udBankCardNo.trim();
    }

    public Integer getUdBankCardNoChangeStatus() {
        return udBankCardNoChangeStatus;
    }

    public void setUdBankCardNoChangeStatus(Integer udBankCardNoChangeStatus) {
        this.udBankCardNoChangeStatus = udBankCardNoChangeStatus;
    }

    public String getUdInterfaceMchntTxnSsn() {
        return udInterfaceMchntTxnSsn;
    }

    public void setUdInterfaceMchntTxnSsn(String udInterfaceMchntTxnSsn) {
        this.udInterfaceMchntTxnSsn = udInterfaceMchntTxnSsn == null ? null : udInterfaceMchntTxnSsn.trim();
    }

    public Integer getUdPosStatus() {
        return udPosStatus;
    }

    public void setUdPosStatus(Integer udPosStatus) {
        this.udPosStatus = udPosStatus;
    }

    public Date getUdCreateTime() {
        return udCreateTime;
    }

    public void setUdCreateTime(Date udCreateTime) {
        this.udCreateTime = udCreateTime;
    }

    public Date getUdUpdateTime() {
        return udUpdateTime;
    }

    public void setUdUpdateTime(Date udUpdateTime) {
        this.udUpdateTime = udUpdateTime;
    }

    public Integer getUdIsDel() {
        return udIsDel;
    }

    public void setUdIsDel(Integer udIsDel) {
        this.udIsDel = udIsDel;
    }

    public Integer getUdPlatformType() {
        return udPlatformType;
    }

    public void setUdPlatformType(Integer udPlatformType) {
        this.udPlatformType = udPlatformType;
    }

    public Integer getUdChannel() {
        return udChannel;
    }

    public void setUdChannel(Integer udChannel) {
        this.udChannel = udChannel;
    }

    public Integer getUdChannelType() {
        return udChannelType;
    }

    public void setUdChannelType(Integer udChannelType) {
        this.udChannelType = udChannelType;
    }
}