package com.jebao.p2p.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zd.yao on 2016/9/20.
 */
@Controller
@RequestMapping(value = {"/","/home"})
public class HomeController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = {"/","/index"})
    public String index() {
        return "home/index";
    }
}
