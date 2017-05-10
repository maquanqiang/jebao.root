package com.jebao.p2p.web.controller;

import com.jebao.p2p.web.utils.session.CurrentUser;
import com.jebao.p2p.web.utils.session.CurrentUserContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jack on 2016/12/13.
 */
@Controller
@RequestMapping("/userfund/")
public class UserfundController extends _BaseController {
    /**
     * 开通第三方资金账户
     */
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(Model model){
        CurrentUser user = CurrentUserContextHolder.get();
        if (!StringUtils.isBlank(user.getFundAccount())){
            //已开通第三方资金账户
            model.addAttribute("title","您已开通第三方资金账户，请勿重复操作");
            model.addAttribute("backUrl","/user/index");

            return "notify/failed";
        }
        return "userfund/register";
    }
    @RequestMapping("registerSuccess")
    public String registerSuccess(){
        return "userfund/registerSuccess";
    }


}
