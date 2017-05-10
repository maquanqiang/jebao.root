package com.jebao.p2p.web.api.requestModel.account;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jack on 2016/12/9.
 */
public class RegisterModel {
    @NotBlank(message="手机号不能为空")
    private String mobile;
    @NotBlank(message="密码不能为空")
    private String password;
    @NotBlank(message="两次密码不一致")
    private String passwordAgain;
    @NotBlank(message="图形验证码不能为空")
    private String imgCode;
    @NotBlank(message="短信验证码不能为空")
    private String messageCode;
    private String invitationCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
