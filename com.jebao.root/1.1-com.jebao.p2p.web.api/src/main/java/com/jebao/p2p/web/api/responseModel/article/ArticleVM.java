package com.jebao.p2p.web.api.responseModel.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.util.Date;

/**
 * 文章详情
 * Created by Administrator on 2016/12/27.
 */
public class ArticleVM extends ViewModel {
    public ArticleVM(TbArticle entity){
        this.id = entity.getaId();
        this.title = entity.getaTitle();
        this.editDate = entity.getaEditDate();
        this.updateTime = entity.getaUpdateTime();
        this.editUser = entity.getaEditUser();
        this.content = entity.getaContent();
    }

    private Long id;

    private String title;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    private Date editDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String editUser;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
