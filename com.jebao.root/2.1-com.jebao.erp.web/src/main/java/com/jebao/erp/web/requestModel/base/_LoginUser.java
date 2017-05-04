package com.jebao.erp.web.requestModel.base;

import com.jebao.erp.web.utils.session.CurrentUser;
import com.jebao.erp.web.utils.session.CurrentUserContextHolder;

/**
 * Created by Administrator on 2016/11/9.
 */
public class _LoginUser {
    private boolean isLogin = false;
    private int userId;
    private String userName;
    public  _LoginUser()
    {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser != null) {
            userId=currentUser.getId();
            userName=currentUser.getName();
            isLogin=true;
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
    public boolean getIsLogin() {
        return isLogin;
    }
}
