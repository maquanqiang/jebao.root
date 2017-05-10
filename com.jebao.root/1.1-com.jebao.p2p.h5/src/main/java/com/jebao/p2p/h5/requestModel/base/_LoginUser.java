package com.jebao.p2p.h5.requestModel.base;

import com.jebao.p2p.h5.utils.session.CurrentUser;
import com.jebao.p2p.h5.utils.session.CurrentUserContextHolder;
import org.apache.commons.lang.StringUtils;

/**
 * Created by wenyq on 2017/2/17.
 */
public class _LoginUser {
    private boolean isLogin = false;
    private long userId;
    private String userName;
    private boolean isFundAccount = false;
    private long loanerId;

    public _LoginUser() {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser != null) {
            userId = currentUser.getId();
            userName = currentUser.getName();
            isLogin = true;
            isFundAccount = !StringUtils.isBlank(currentUser.getFundAccount());
            loanerId = currentUser.getLoanerId();
        }
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    public boolean getIsFundAccount() {
        return isFundAccount;
    }

    public long getLoanerId() {
        return loanerId;
    }
}
