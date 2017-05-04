package com.jebao.p2p.web.api.requestModel.user;

/**
 * Created by Administrator on 2016/12/3.
 */
public class FundsDetailsSM {
    private int pageIndex;

    private int pageSize;

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