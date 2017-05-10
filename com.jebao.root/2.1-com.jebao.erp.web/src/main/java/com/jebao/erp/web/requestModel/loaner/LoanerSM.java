package com.jebao.erp.web.requestModel.loaner;

import com.jebao.erp.web.requestModel.ParamModel;

/**
 * Created by wangwei on 2016/11/22.
 */
public class LoanerSM extends ParamModel {
    private String nickName;
    private String phone;
    private String trueName;
    private int pageIndex;
    private int pageSize;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
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
