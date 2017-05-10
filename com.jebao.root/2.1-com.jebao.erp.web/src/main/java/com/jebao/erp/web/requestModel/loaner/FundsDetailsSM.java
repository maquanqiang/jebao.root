package com.jebao.erp.web.requestModel.loaner;

import com.jebao.erp.web.requestModel.ParamModel;

/**
 * Created by Administrator on 2016/12/2.
 */
public class FundsDetailsSM extends ParamModel {
    private Long loginId;
    private int pageIndex;
    private int pageSize;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
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
