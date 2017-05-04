package com.jebao.erp.web.responseModel.article;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.article.TbArticleType;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ArticleTypeVM extends ViewModel {
    public ArticleTypeVM(TbArticleType entity){
        this.id = entity.getAtId();
        this.name = entity.getAtName();
    }
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}