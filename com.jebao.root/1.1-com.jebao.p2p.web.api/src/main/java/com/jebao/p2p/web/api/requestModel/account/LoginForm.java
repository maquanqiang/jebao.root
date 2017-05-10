package com.jebao.p2p.web.api.requestModel.account;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2016/10/20.
 */
public class LoginForm {

    @NotBlank(message="用户名不能为空")
    private String jebUsername;
    @NotBlank(message="密码不能为空")
    private String password;
    private boolean remember;
    private String verifyCode;
    private String redirectUrl;

    public String getJebUsername() {
        return jebUsername;
    }

    public void setJebUsername(String jebUsername) {
        this.jebUsername = jebUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public boolean getRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
