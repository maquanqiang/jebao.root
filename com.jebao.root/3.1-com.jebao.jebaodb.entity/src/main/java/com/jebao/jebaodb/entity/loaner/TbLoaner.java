package com.jebao.jebaodb.entity.loaner;

import java.util.Date;

public class TbLoaner {
    private Long lId;

    private Long lLoginId;

    private String lNickName;

    private String lPhone;

    private String lTrueName;

    private String lIdNumber;

    private String lEmail;

    private Integer lSex;

    private Integer lAge;

    private Date lRegisterTime;

    private Date lLastLoginTime;

    private String lHomeAdd;

    private String lHkadr;

    private Integer lMaritalStatus;

    private Integer lIshaveHouse;

    private Integer lIshaveCar;

    private Integer lPoliticsStatus;

    private String lCreditStatus;

    private Integer lMonthlySalary;

    private Integer lEducation;

    private String lWorkCity;

    private String lThirdAccount;

    private String lThirdLoginPassword;

    private String lThirdPayPassword;

    private String lBankProvincesCode;

    private String lBankProvincesName;

    private String lBankCityCode;

    private String lBankCityName;

    private String lBankParentBankCode;

    private String lBankParentBankName;

    private Date lCreateTime;

    private Date lUpdateTime;

    private String lBankCardNo;

    private Integer lIsDel;

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public Long getlLoginId() {
        return lLoginId;
    }

    public void setlLoginId(Long lLoginId) {
        this.lLoginId = lLoginId;
    }

    public String getlNickName() {
        return lNickName;
    }

    public void setlNickName(String lNickName) {
        this.lNickName = lNickName == null ? null : lNickName.trim();
    }

    public String getlPhone() {
        return lPhone;
    }

    public void setlPhone(String lPhone) {
        this.lPhone = lPhone == null ? null : lPhone.trim();
    }

    public String getlTrueName() {
        return lTrueName;
    }

    public void setlTrueName(String lTrueName) {
        this.lTrueName = lTrueName == null ? null : lTrueName.trim();
    }

    public String getlIdNumber() {
        return lIdNumber;
    }

    public void setlIdNumber(String lIdNumber) {
        this.lIdNumber = lIdNumber == null ? null : lIdNumber.trim();
    }

    public String getlEmail() {
        return lEmail;
    }

    public void setlEmail(String lEmail) {
        this.lEmail = lEmail == null ? null : lEmail.trim();
    }

    public Integer getlSex() {
        return lSex;
    }

    public void setlSex(Integer lSex) {
        this.lSex = lSex;
    }

    public Integer getlAge() {
        return lAge;
    }

    public void setlAge(Integer lAge) {
        this.lAge = lAge;
    }

    public Date getlRegisterTime() {
        return lRegisterTime;
    }

    public void setlRegisterTime(Date lRegisterTime) {
        this.lRegisterTime = lRegisterTime;
    }

    public Date getlLastLoginTime() {
        return lLastLoginTime;
    }

    public void setlLastLoginTime(Date lLastLoginTime) {
        this.lLastLoginTime = lLastLoginTime;
    }

    public String getlHomeAdd() {
        return lHomeAdd;
    }

    public void setlHomeAdd(String lHomeAdd) {
        this.lHomeAdd = lHomeAdd == null ? null : lHomeAdd.trim();
    }

    public String getlHkadr() {
        return lHkadr;
    }

    public void setlHkadr(String lHkadr) {
        this.lHkadr = lHkadr == null ? null : lHkadr.trim();
    }

    public Integer getlMaritalStatus() {
        return lMaritalStatus;
    }

    public void setlMaritalStatus(Integer lMaritalStatus) {
        this.lMaritalStatus = lMaritalStatus;
    }

    public Integer getlIshaveHouse() {
        return lIshaveHouse;
    }

    public void setlIshaveHouse(Integer lIshaveHouse) {
        this.lIshaveHouse = lIshaveHouse;
    }

    public Integer getlIshaveCar() {
        return lIshaveCar;
    }

    public void setlIshaveCar(Integer lIshaveCar) {
        this.lIshaveCar = lIshaveCar;
    }

    public Integer getlPoliticsStatus() {
        return lPoliticsStatus;
    }

    public void setlPoliticsStatus(Integer lPoliticsStatus) {
        this.lPoliticsStatus = lPoliticsStatus;
    }

    public String getlCreditStatus() {
        return lCreditStatus;
    }

    public void setlCreditStatus(String lCreditStatus) {
        this.lCreditStatus = lCreditStatus == null ? null : lCreditStatus.trim();
    }

    public Integer getlMonthlySalary() {
        return lMonthlySalary;
    }

    public void setlMonthlySalary(Integer lMonthlySalary) {
        this.lMonthlySalary = lMonthlySalary;
    }

    public Integer getlEducation() {
        return lEducation;
    }

    public void setlEducation(Integer lEducation) {
        this.lEducation = lEducation;
    }

    public String getlWorkCity() {
        return lWorkCity;
    }

    public void setlWorkCity(String lWorkCity) {
        this.lWorkCity = lWorkCity == null ? null : lWorkCity.trim();
    }

    public String getlThirdAccount() {
        return lThirdAccount;
    }

    public void setlThirdAccount(String lThirdAccount) {
        this.lThirdAccount = lThirdAccount == null ? null : lThirdAccount.trim();
    }

    public String getlThirdLoginPassword() {
        return lThirdLoginPassword;
    }

    public void setlThirdLoginPassword(String lThirdLoginPassword) {
        this.lThirdLoginPassword = lThirdLoginPassword == null ? null : lThirdLoginPassword.trim();
    }

    public String getlThirdPayPassword() {
        return lThirdPayPassword;
    }

    public void setlThirdPayPassword(String lThirdPayPassword) {
        this.lThirdPayPassword = lThirdPayPassword == null ? null : lThirdPayPassword.trim();
    }

    public String getlBankProvincesCode() {
        return lBankProvincesCode;
    }

    public void setlBankProvincesCode(String lBankProvincesCode) {
        this.lBankProvincesCode = lBankProvincesCode == null ? null : lBankProvincesCode.trim();
    }

    public String getlBankProvincesName() {
        return lBankProvincesName;
    }

    public void setlBankProvincesName(String lBankProvincesName) {
        this.lBankProvincesName = lBankProvincesName == null ? null : lBankProvincesName.trim();
    }

    public String getlBankCityCode() {
        return lBankCityCode;
    }

    public void setlBankCityCode(String lBankCityCode) {
        this.lBankCityCode = lBankCityCode == null ? null : lBankCityCode.trim();
    }

    public String getlBankCityName() {
        return lBankCityName;
    }

    public void setlBankCityName(String lBankCityName) {
        this.lBankCityName = lBankCityName == null ? null : lBankCityName.trim();
    }

    public String getlBankParentBankCode() {
        return lBankParentBankCode;
    }

    public void setlBankParentBankCode(String lBankParentBankCode) {
        this.lBankParentBankCode = lBankParentBankCode == null ? null : lBankParentBankCode.trim();
    }

    public String getlBankParentBankName() {
        return lBankParentBankName;
    }

    public void setlBankParentBankName(String lBankParentBankName) {
        this.lBankParentBankName = lBankParentBankName == null ? null : lBankParentBankName.trim();
    }

    public Date getlCreateTime() {
        return lCreateTime;
    }

    public void setlCreateTime(Date lCreateTime) {
        this.lCreateTime = lCreateTime;
    }

    public Date getlUpdateTime() {
        return lUpdateTime;
    }

    public void setlUpdateTime(Date lUpdateTime) {
        this.lUpdateTime = lUpdateTime;
    }

    public String getlBankCardNo() {
        return lBankCardNo;
    }

    public void setlBankCardNo(String lBankCardNo) {
        this.lBankCardNo = lBankCardNo == null ? null : lBankCardNo.trim();
    }

    public Integer getlIsDel() {
        return lIsDel;
    }

    public void setlIsDel(Integer lIsDel) {
        this.lIsDel = lIsDel;
    }
}