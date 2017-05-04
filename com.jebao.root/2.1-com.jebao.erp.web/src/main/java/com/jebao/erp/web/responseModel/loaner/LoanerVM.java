package com.jebao.erp.web.responseModel.loaner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loaner.TbLoaner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class LoanerVM extends ViewModel {
    public LoanerVM(TbLoaner entity){
        this.id = entity.getlId();
        this.loginId = entity.getlLoginId();
        this.nickName = entity.getlNickName();
        this.phone = entity.getlPhone();
        this.trueName = entity.getlTrueName();
        this.idNumber = entity.getlIdNumber();
        this.email = entity.getlEmail();
        this.sex = entity.getlSex();
        this.age = entity.getlAge();
        this.registerTime = entity.getlRegisterTime();
        this.lastLoginTime = entity.getlLastLoginTime();
        this.homeAdd = entity.getlHomeAdd();
        this.hkadr = entity.getlHkadr();
        this.maritalStatus = entity.getlMaritalStatus();
        this.ishaveHouse = entity.getlIshaveHouse();
        this.ishaveCar = entity.getlIshaveCar();
        this.politicsStatus = entity.getlPoliticsStatus();
        this.creditStatus = entity.getlCreditStatus();
        this.monthlySalary = entity.getlMonthlySalary();
        this.education = entity.getlEducation();
        this.workCity = entity.getlWorkCity();
        this.borrowCount = 0;
        this.borrowAmount = new BigDecimal(0);
    }
    private Long id;

    private Long loginId;

    private String nickName;

    private String phone;

    private String trueName;

    private String idNumber;

    private String email;

    private Integer sex;

    private Integer age;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date registerTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date lastLoginTime;

    private String homeAdd;

    private String hkadr;

    private Integer maritalStatus;

    private Integer ishaveHouse;

    private Integer ishaveCar;

    private Integer politicsStatus;

    private String creditStatus;

    private Integer monthlySalary;

    private Integer education;

    private String workCity;

    //实际借款笔数
    private Integer borrowCount;

    //实际借款金额
    private BigDecimal borrowAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getHkadr() {
        return hkadr;
    }

    public void setHkadr(String hkadr) {
        this.hkadr = hkadr;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getIshaveHouse() {
        return ishaveHouse;
    }

    public void setIshaveHouse(Integer ishaveHouse) {
        this.ishaveHouse = ishaveHouse;
    }

    public Integer getIshaveCar() {
        return ishaveCar;
    }

    public void setIshaveCar(Integer ishaveCar) {
        this.ishaveCar = ishaveCar;
    }

    public Integer getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(Integer politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Integer monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }
}