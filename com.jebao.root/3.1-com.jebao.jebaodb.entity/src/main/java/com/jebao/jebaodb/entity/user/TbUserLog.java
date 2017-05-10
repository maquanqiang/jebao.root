package com.jebao.jebaodb.entity.user;

import java.util.Date;

public class TbUserLog {
    private Long ulId;

    private Long ulUserId;

    private String ulContent;

    private Long ulCreateUserId;

    private Date ulCreateUserTime;

    public Long getUlId() {
        return ulId;
    }

    public void setUlId(Long ulId) {
        this.ulId = ulId;
    }

    public Long getUlUserId() {
        return ulUserId;
    }

    public void setUlUserId(Long ulUserId) {
        this.ulUserId = ulUserId;
    }

    public String getUlContent() {
        return ulContent;
    }

    public void setUlContent(String ulContent) {
        this.ulContent = ulContent == null ? null : ulContent.trim();
    }

    public Long getUlCreateUserId() {
        return ulCreateUserId;
    }

    public void setUlCreateUserId(Long ulCreateUserId) {
        this.ulCreateUserId = ulCreateUserId;
    }

    public Date getUlCreateUserTime() {
        return ulCreateUserTime;
    }

    public void setUlCreateUserTime(Date ulCreateUserTime) {
        this.ulCreateUserTime = ulCreateUserTime;
    }
}