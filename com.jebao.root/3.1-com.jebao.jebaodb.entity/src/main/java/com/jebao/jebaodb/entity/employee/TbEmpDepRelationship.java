package com.jebao.jebaodb.entity.employee;

import java.util.Date;

public class TbEmpDepRelationship {
    private Integer edrId;

    private Integer edrEmpId;

    private Integer edrDepId;

    private Date edrEffectDate;

    private Date edrExpiryDate;

    private Date edrCreateTime;

    private Integer edrCreateUser;

    public Integer getEdrId() {
        return edrId;
    }

    public void setEdrId(Integer edrId) {
        this.edrId = edrId;
    }

    public Integer getEdrEmpId() {
        return edrEmpId;
    }

    public void setEdrEmpId(Integer edrEmpId) {
        this.edrEmpId = edrEmpId;
    }

    public Integer getEdrDepId() {
        return edrDepId;
    }

    public void setEdrDepId(Integer edrDepId) {
        this.edrDepId = edrDepId;
    }

    public Date getEdrEffectDate() {
        return edrEffectDate;
    }

    public void setEdrEffectDate(Date edrEffectDate) {
        this.edrEffectDate = edrEffectDate;
    }

    public Date getEdrExpiryDate() {
        return edrExpiryDate;
    }

    public void setEdrExpiryDate(Date edrExpiryDate) {
        this.edrExpiryDate = edrExpiryDate;
    }

    public Date getEdrCreateTime() {
        return edrCreateTime;
    }

    public void setEdrCreateTime(Date edrCreateTime) {
        this.edrCreateTime = edrCreateTime;
    }

    public Integer getEdrCreateUser() {
        return edrCreateUser;
    }

    public void setEdrCreateUser(Integer edrCreateUser) {
        this.edrCreateUser = edrCreateUser;
    }
}