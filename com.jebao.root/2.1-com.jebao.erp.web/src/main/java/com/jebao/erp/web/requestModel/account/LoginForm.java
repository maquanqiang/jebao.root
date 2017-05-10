package com.jebao.erp.web.requestModel.account;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2016/11/2.
 */
public class LoginForm {
    //转为数据库实体数据
    public static Object toEntity(LoginForm form)
    {
        /**
        TbTempTest result=new TbTempTest();
        result.setUsername(form.getName());
        result.setPassword(form.getPassword());
        return result;
         */
        return null;
    }
    //===================================================
    @NotBlank(message="用户名不能为空")
    private String name;
    @NotBlank(message="密码不能为空")
    @Pattern(regexp = "[\\w]{6,12}",message = "密码为6~12位的字符！")
    private String password;
    private String redirectUrl;
    @NotBlank(message="验证码不能为空")
    private String verifyCode;

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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
