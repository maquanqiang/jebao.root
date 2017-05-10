package com.jebao.jebaodb.entity.employee;

import java.util.Date;

public class TbEmployee {
    private Integer empId;

    private String empName;

    private String empMobilephone;

    private String empCardNo;

    private Integer empSex;

    private Date empBirthday;

    private Integer empStatus;

    private Date empEntryDate;

    private Date empDimissionDate;

    private Boolean empIsDeleted;

    private Date empCreateTime;

    private Integer empCreateUser;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpMobilephone() {
        return empMobilephone;
    }

    public void setEmpMobilephone(String empMobilephone) {
        this.empMobilephone = empMobilephone == null ? null : empMobilephone.trim();
    }

    public String getEmpCardNo() {
        return empCardNo;
    }

    public void setEmpCardNo(String empCardNo) {
        this.empCardNo = empCardNo == null ? null : empCardNo.trim();
    }

    public Integer getEmpSex() {
        return empSex;
    }

    public void setEmpSex(Integer empSex) {
        this.empSex = empSex;
    }

    public Date getEmpBirthday() {
        return empBirthday;
    }

    public void setEmpBirthday(Date empBirthday) {
        this.empBirthday = empBirthday;
    }

    public Integer getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Integer empStatus) {
        this.empStatus = empStatus;
    }

    public Date getEmpEntryDate() {
        return empEntryDate;
    }

    public void setEmpEntryDate(Date empEntryDate) {
        this.empEntryDate = empEntryDate;
    }

    public Date getEmpDimissionDate() {
        return empDimissionDate;
    }

    public void setEmpDimissionDate(Date empDimissionDate) {
        this.empDimissionDate = empDimissionDate;
    }

    public Boolean getEmpIsDeleted() {
        return empIsDeleted;
    }

    public void setEmpIsDeleted(Boolean empIsDeleted) {
        this.empIsDeleted = empIsDeleted;
    }

    public Date getEmpCreateTime() {
        return empCreateTime;
    }

    public void setEmpCreateTime(Date empCreateTime) {
        this.empCreateTime = empCreateTime;
    }

    public Integer getEmpCreateUser() {
        return empCreateUser;
    }

    public void setEmpCreateUser(Integer empCreateUser) {
        this.empCreateUser = empCreateUser;
    }
}