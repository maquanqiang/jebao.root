package com.jebao.p2p.web.controller;

import com.jebao.p2p.web.utils.session.LoginSessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/10/19.
 */
@Controller
@RequestMapping("/account/")
public class AccountController extends _BaseController {
    /**
     * 登录
     */
    @RequestMapping("login")
    public String login(@RequestParam(defaultValue = "/") String redirect) {
        //检测是否已登录
        boolean isLogin = LoginSessionUtil.isLogin(request, response);
        if (isLogin) {
            return "redirect:"+redirect;
        }
        return "account/login";
    }
    /**
     * 暂时没用
     */
    @RequestMapping("token")
    public String token(String code,@RequestParam(defaultValue = "/") String redirectUrl){
        boolean flag = LoginSessionUtil.setToken(code,request,response);
        if (flag){
            return "redirect:"+redirectUrl; //成功之后跳转
        }
        return "redirect:account/login"; //不成功则重新登录
    }
    @RequestMapping("logout")
    public String logout() {
        LoginSessionUtil.logout(request,response);
        return "redirect:/home/index";
    }
    @RequestMapping("register")
    public String register(){
        return "account/register";
    }
    @RequestMapping("registerSuccess")
    public String registerSuccess(){
        return "account/registerSuccess";
    }
    @RequestMapping("forgot")
    public String forgot(){
        return "account/forgot";
    }

}
