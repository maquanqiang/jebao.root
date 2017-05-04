package com.jebao.p2p.web.api.responseModel.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.util.Date;

/**
 * 文章列表
 * Created by Administrator on 2016/12/27.
 */
public class ArticleListVM extends ViewModel {
    public ArticleListVM(ArticleInfo entity){
        this.id = entity.getaId();
        this.editDate = entity.getaEditDate();
        this.title = entity.getaTitle();
    }

    private Long id;

    //标题
    private String title;

    //编辑日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date editDate;

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
}
