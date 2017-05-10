package com.jebao.p2p.web.controller;

import com.jebao.p2p.web.utils.session.CurrentUser;
import com.jebao.p2p.web.utils.session.CurrentUserContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/9.
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @RequestMapping("detail/{bpId}")
    public String detail(@PathVariable("bpId") Long bpId, Model model){
        model.addAttribute("bpId", bpId);
        return "product/productDetail";
    }

    @RequestMapping("productFail/{msg}")
    public String productFail(@PathVariable("msg")String msg, Model model){
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if(currentUser == null){            //未登录 重定向登录页
            return "/";
        }
        model.addAttribute("msg",msg);
        return "product/productFail";
    }

    @RequestMapping("productSuccess/{investMoney}")
    public String productSuccess(@PathVariable("investMoney")BigDecimal investMoney, Model model){
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if(currentUser == null){            //未登录 重定向登录页
            return "/";
        }
        model.addAttribute("investMoney", investMoney);
        return "product/productSuccess";
    }
}
