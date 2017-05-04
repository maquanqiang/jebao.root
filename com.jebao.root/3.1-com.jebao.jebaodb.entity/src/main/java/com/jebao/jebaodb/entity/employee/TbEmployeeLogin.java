package com.jebao.jebaodb.entity.employee;

import java.util.Date;

public class TbEmployeeLogin {
    private Integer lgId;

    private Integer lgEmpId;

    private String lgUsername;

    private String lgPassword;

    private Date lgFirstLoginTime;

    private Date lgLastLoginTime;

    private Integer lgStatus;

    private Boolean lgIsDel;

    public Integer getLgId() {
        return lgId;
    }

    public void setLgId(Integer lgId) {
        this.lgId = lgId;
    }

    public Integer getLgEmpId() {
        return lgEmpId;
    }

    public void setLgEmpId(Integer lgEmpId) {
        this.lgEmpId = lgEmpId;
    }

    public String getLgUsername() {
        return lgUsername;
    }

    public void setLgUsername(String lgUsername) {
        this.lgUsername = lgUsername == null ? null : lgUsername.trim();
    }

    public String getLgPassword() {
        return lgPassword;
    }

    public void setLgPassword(String lgPassword) {
        this.lgPassword = lgPassword == null ? null : lgPassword.trim();
    }

    public Date getLgFirstLoginTime() {
        return lgFirstLoginTime;
    }

    public void setLgFirstLoginTime(Date lgFirstLoginTime) {
        this.lgFirstLoginTime = lgFirstLoginTime;
    }

    public Date getLgLastLoginTime() {
        return lgLastLoginTime;
    }

    public void setLgLastLoginTime(Date lgLastLoginTime) {
        this.lgLastLoginTime = lgLastLoginTime;
    }

    public Integer getLgStatus() {
        return lgStatus;
    }

    public void setLgStatus(Integer lgStatus) {
        this.lgStatus = lgStatus;
    }

    public Boolean getLgIsDel() {
        return lgIsDel;
    }

    public void setLgIsDel(Boolean lgIsDel) {
        this.lgIsDel = lgIsDel;
    }
}