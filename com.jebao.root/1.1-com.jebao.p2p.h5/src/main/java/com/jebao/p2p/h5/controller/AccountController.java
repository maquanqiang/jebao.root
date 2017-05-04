package com.jebao.p2p.h5.controller;

import com.jebao.p2p.h5.utils.session.LoginSessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wenyq on 2017/2/20.
 */
@Controller
@RequestMapping("/account/")
public class AccountController  extends _BaseController {

    @RequestMapping(value = {"/","/index"})
    public String index() {
        boolean isLogin = LoginSessionUtil.isLogin(request, response);
        if (!isLogin) {
            return "account/login";
        }
        return "account/index";
    }
    @RequestMapping("login")
    public String login(@RequestParam(defaultValue = "/") String redirect) {
//        LoginSessionUtil.logout(request,response);
        //检测是否已登录
        boolean isLogin = LoginSessionUtil.isLogin(request, response);
        if (isLogin) {
            return "redirect:"+redirect;
        }
        return "account/login";
    }
    @RequestMapping("register")
    public String register(){
        return "account/register";
    }
    @RequestMapping("registerSuccess")
    public String registerSuccess(){
        return "account/registerSuccess";
    }
    @RequestMapping("sendcorde")
    public String sendcorde(String tel,String pwd,String yqm,Model model){
        model.addAttribute("tel",tel);
        model.addAttribute("pwd",pwd);
        model.addAttribute("invitationCode",yqm);
        return "account/sendcorde";
    }
    @RequestMapping("setting")
  public String   setting(@RequestParam(defaultValue = "/") String redirect)
  {
      boolean isLogin = LoginSessionUtil.isLogin(request, response);
      if (!isLogin) {
          return "redirect:"+redirect;
      }
      return "account/setting";
  }
    @RequestMapping("logout")
    public String logout() {
        LoginSessionUtil.logout(request,response);
        return "redirect:/";
    }
    @RequestMapping("forgot")
    public String forgot(){
        return "account/forgot";
    }
    @RequestMapping("funds")
    public String funds() {
        boolean isLogin = LoginSessionUtil.isLogin(request, response);
        if (!isLogin) {
            return "account/login";
        }
        return "account/funds";
    }
    @RequestMapping("investrecord")
    public String invest() {
        boolean isLogin = LoginSessionUtil.isLogin(request, response);
        if (!isLogin) {
            return "account/login";
        }
        return "account/investrecord";
    }
    @RequestMapping(value = "recharge")
    public String recharge() {
        return "account/recharge";
    }
    @RequestMapping(value = "withdraw")
    public String withdraw() {
        return "account/withdraw";
    }

    @RequestMapping("voucher")
    public String voucher(){
        return "account/voucher";
    }
}
