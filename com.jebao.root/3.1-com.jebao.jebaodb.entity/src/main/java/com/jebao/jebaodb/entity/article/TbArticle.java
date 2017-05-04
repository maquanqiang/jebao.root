package com.jebao.jebaodb.entity.article;

import java.util.Date;

public class TbArticle {
    private Long aId;

    private Integer aTypeId;

    private String aTitle;

    private Date aEditDate;

    private String aEditUser;

    private Integer aCreateUserId;

    private Date aCreateTime;

    private Date aUpdateTime;

    private Integer aIsDel;

    private String aContent;

    private Integer aWeight;

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Integer getaTypeId() {
        return aTypeId;
    }

    public void setaTypeId(Integer aTypeId) {
        this.aTypeId = aTypeId;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle == null ? null : aTitle.trim();
    }

    public Date getaEditDate() {
        return aEditDate;
    }

    public void setaEditDate(Date aEditDate) {
        this.aEditDate = aEditDate;
    }

    public String getaEditUser() {
        return aEditUser;
    }

    public void setaEditUser(String aEditUser) {
        this.aEditUser = aEditUser == null ? null : aEditUser.trim();
    }

    public Integer getaCreateUserId() {
        return aCreateUserId;
    }

    public void setaCreateUserId(Integer aCreateUserId) {
        this.aCreateUserId = aCreateUserId;
    }

    public Date getaCreateTime() {
        return aCreateTime;
    }

    public void setaCreateTime(Date aCreateTime) {
        this.aCreateTime = aCreateTime;
    }

    public Date getaUpdateTime() {
        return aUpdateTime;
    }

    public void setaUpdateTime(Date aUpdateTime) {
        this.aUpdateTime = aUpdateTime;
    }

    public Integer getaIsDel() {
        return aIsDel;
    }

    public void setaIsDel(Integer aIsDel) {
        this.aIsDel = aIsDel;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent == null ? null : aContent.trim();
    }

    public Integer getaWeight() {
        return aWeight;
    }

    public void setaWeight(Integer aWeight) {
        this.aWeight = aWeight;
    }
}