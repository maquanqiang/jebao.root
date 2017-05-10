package com.jebao.p2p.web.requestModel.account;

import com.jebao.p2p.web.requestModel.base._BaseForm;

/**
 * Created by Administrator on 2016/10/19.
 */
public class LoginForm extends _BaseForm {
    private String name;
    private String password;
    private String redirectUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
