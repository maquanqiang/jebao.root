package com.jebao.erp.web.controller;

import com.jebao.erp.web.requestModel.account.LoginForm;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultError;
import com.jebao.erp.web.responseModel.base.JsonResultOk;
import com.jebao.erp.web.utils.captcha.CaptchaUtil;
import com.jebao.erp.web.utils.session.CurrentUser;
import com.jebao.erp.web.utils.session.LoginSessionUtil;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/11/2.
 */
@Controller
@RequestMapping("/account/")
public class AccountController extends _BaseController {


    @RequestMapping("login")
    public String login() {
        //检测是否已登录
        boolean isLogin = LoginSessionUtil.isLogin(request, response);
        if (isLogin) {
            return "redirect:/home/index";
        }
        return "account/login";
    }

    @RequestMapping(value = "doLogin", produces = "application/json")
    public
    @ResponseBody
    JsonResult doLogin(LoginForm form) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg());
        }
        String verifyCode = CaptchaUtil.getCaptchaToken(request, response);
        if (StringUtils.isBlank(verifyCode) ||
                StringUtils.isBlank(form.getVerifyCode()) ||
                !verifyCode.toLowerCase().equals(form.getVerifyCode().toLowerCase())) {
            return new JsonResultError("验证码不正确");
        }
        //todo 登录逻辑实现
        //todo 实际的业务逻辑
        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(50);
        currentUser.setName(form.getName());
        LoginSessionUtil.setLogin(currentUser, request, response);
        //
        return new JsonResultOk("登录成功");
    }

    @RequestMapping("logout")
    public String logout() {
        LoginSessionUtil.logout(request, response);
        //return "redirect:/home/index";
        return "/account/login";
    }

    @RequestMapping("password")
    public String password() {
        return "account/password";
    }

}
