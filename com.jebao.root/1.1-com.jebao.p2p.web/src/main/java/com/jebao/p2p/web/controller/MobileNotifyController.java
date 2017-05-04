package com.jebao.p2p.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/17.
 */
@Controller
@RequestMapping("/mobileNotify/")
public class MobileNotifyController {
    /**
     * 公共失败展示页面
     */
    @RequestMapping("failed")
    public String failed(String title, String content,Model model){
        model.addAttribute("title",title);
        model.addAttribute("content",content);
        return "mobileNotify/failed";
    }

    /**
     * 成功页面
     */
    @RequestMapping("success")
    public String success(String title, String content, Model model){
        model.addAttribute("title",title);
        model.addAttribute("content",content);
        return "mobileNotify/success";
    }

    @RequestMapping("test")
    public String test(){
        return "mobileNotify/test";
    }
}
