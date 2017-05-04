package com.jebao.p2p.h5.controller;

import com.jebao.p2p.h5.utils.session.CurrentUser;
import com.jebao.p2p.h5.utils.session.CurrentUserContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wenyq on 2017/2/26.
 */
@Controller
@RequestMapping("/userfund/")
public class UserfundController extends _BaseController {
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(Model model){
        CurrentUser user = CurrentUserContextHolder.get();
        if (!StringUtils.isBlank(user.getFundAccount())){
            //已开通第三方资金账户
            model.addAttribute("title","您已开通第三方资金账户，请勿重复操作");
            model.addAttribute("backUrl","/user/index");

            return "userfund/index";
        }
        return "userfund/register";
    }
    @RequestMapping(value = "registers/{truename}/{idcard}")
    public String registers(@PathVariable("truename") String truename,@PathVariable("idcard") String idcard, Model model){
        CurrentUser user = CurrentUserContextHolder.get();
        if (!StringUtils.isBlank(user.getFundAccount())){
            //已开通第三方资金账户
            model.addAttribute("title","您已开通第三方资金账户，请勿重复操作");
            model.addAttribute("backUrl","/user/index");

            return "userfund/index";
        }
        model.addAttribute("truename",truename);
        model.addAttribute("idcard",idcard);
        return "userfund/registers";
    }
    @RequestMapping("registerSuccess")
    public String registerSuccess(){
        return "userfund/registerSuccess";
    }
}
