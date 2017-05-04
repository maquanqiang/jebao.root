package com.jebao.p2p.web.api.requestModel.article;

/**
 * Created by Administrator on 2016/12/27.
 */
public class ArticleSM {
    //文章类别
    private int typeId;

    private int pageIndex;

    private int pageSize;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
