package com.jebao.erp.web.responseModel.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.article.ArticleInfo;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ArticleListVM extends ViewModel {
    public ArticleListVM(ArticleInfo info) {
        this.id = info.getaId();
        this.typeName = info.getTypeName();
        this.title = info.getaTitle();
        this.editDate = info.getaEditDate();
        this.editUser = info.getaEditUser();
        this.updateTime = info.getaUpdateTime();
        this.weight = info.getaWeight();
    }

    private Long id;

    private String typeName;

    private String title;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date editDate;

    private String editUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    //权重
    private Integer weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}