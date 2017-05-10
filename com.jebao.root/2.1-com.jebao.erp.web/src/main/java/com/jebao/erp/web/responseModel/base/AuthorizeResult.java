package com.jebao.erp.web.responseModel.base;

/**
 * 登录权限的过滤结果
 * Created by Administrator on 2016/10/20.
 */
public class AuthorizeResult {
    public AuthorizeResult()
    {
        authorizeFilterResult=true;
        state=403;
        error="当前客户没有登录";
    }
    private int state;
    private String error;
    private boolean authorizeFilterResult;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isAuthorizeFilterResult() {
        return authorizeFilterResult;
    }

    public void setAuthorizeFilterResult(boolean authorizeFilterResult) {
        this.authorizeFilterResult = authorizeFilterResult;
    }
}
