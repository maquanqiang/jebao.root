package com.jebao.p2p.web.api.requestModel.user;

/**
 * Created by Administrator on 2016/12/8.
 */
public class InvestSM {
    //投资状态
    private int freezeStatus;

    private int pageIndex;

    private int pageSize;

    public int getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(int freezeStatus) {
        this.freezeStatus = freezeStatus;
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
