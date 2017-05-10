package com.jebao.jebaodb.entity.employee;

import java.util.Date;

public class TbEmpRankRelationship {
    private Integer errId;

    private Integer errEmpId;

    private Integer errRankId;

    private Date errEffectDate;

    private Date errExpiryDate;

    private Date errCreateTime;

    private Integer errCreateUser;

    public Integer getErrId() {
        return errId;
    }

    public void setErrId(Integer errId) {
        this.errId = errId;
    }

    public Integer getErrEmpId() {
        return errEmpId;
    }

    public void setErrEmpId(Integer errEmpId) {
        this.errEmpId = errEmpId;
    }

    public Integer getErrRankId() {
        return errRankId;
    }

    public void setErrRankId(Integer errRankId) {
        this.errRankId = errRankId;
    }

    public Date getErrEffectDate() {
        return errEffectDate;
    }

    public void setErrEffectDate(Date errEffectDate) {
        this.errEffectDate = errEffectDate;
    }

    public Date getErrExpiryDate() {
        return errExpiryDate;
    }

    public void setErrExpiryDate(Date errExpiryDate) {
        this.errExpiryDate = errExpiryDate;
    }

    public Date getErrCreateTime() {
        return errCreateTime;
    }

    public void setErrCreateTime(Date errCreateTime) {
        this.errCreateTime = errCreateTime;
    }

    public Integer getErrCreateUser() {
        return errCreateUser;
    }

    public void setErrCreateUser(Integer errCreateUser) {
        this.errCreateUser = errCreateUser;
    }
}