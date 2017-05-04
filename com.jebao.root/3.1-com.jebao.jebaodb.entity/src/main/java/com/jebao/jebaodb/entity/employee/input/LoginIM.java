package com.jebao.jebaodb.entity.employee.input;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jack on 2016/11/24.
 */
public class LoginIM {
    @NotBlank(message = "请输入用户名")
    private String username;
    @NotBlank(message = "请输入密码")
    private String password;
    @NotBlank(message = "请输入验证码")
    private String verifyCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
