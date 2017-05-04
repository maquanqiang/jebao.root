package com.jebao.jebaodb.entity.user;

import java.util.Date;

public class TbLoginInfo {
    private Long liId;

    private String liLoginName;

    private String liPassword;

    private Date liCreateTime;

    private Date liLastLoginTime;

    private Integer liIsDel;

    public Long getLiId() {
        return liId;
    }

    public void setLiId(Long liId) {
        this.liId = liId;
    }

    public String getLiLoginName() {
        return liLoginName;
    }

    public void setLiLoginName(String liLoginName) {
        this.liLoginName = liLoginName == null ? null : liLoginName.trim();
    }

    public String getLiPassword() {
        return liPassword;
    }

    public void setLiPassword(String liPassword) {
        this.liPassword = liPassword == null ? null : liPassword.trim();
    }

    public Date getLiCreateTime() {
        return liCreateTime;
    }

    public void setLiCreateTime(Date liCreateTime) {
        this.liCreateTime = liCreateTime;
    }

    public Date getLiLastLoginTime() {
        return liLastLoginTime;
    }

    public void setLiLastLoginTime(Date liLastLoginTime) {
        this.liLastLoginTime = liLastLoginTime;
    }

    public Integer getLiIsDel() {
        return liIsDel;
    }

    public void setLiIsDel(Integer liIsDel) {
        this.liIsDel = liIsDel;
    }
}