package com.jebao.erp.web.requestModel.article;

import com.jebao.erp.web.requestModel.ParamModel;

/**
 * Created by Administrator on 2016/11/29.
 */
public class ArticleSM extends ParamModel {
    private int typeId;

    private String title;

    private int pageIndex;

    private int pageSize;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}