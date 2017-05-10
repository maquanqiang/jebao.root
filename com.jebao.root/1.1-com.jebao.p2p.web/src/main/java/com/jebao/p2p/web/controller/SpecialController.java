package com.jebao.p2p.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/6.
 * 首页banner专题
 */
@Controller
@RequestMapping("/special/")
public class SpecialController {
    @RequestMapping("banner1")
    public String banner1() {
        return "special/banner1";
    }

    @RequestMapping("banner2")
    public String banner2() {
        return "special/banner2";
    }

    @RequestMapping("banner3")
    public String banner3() {
        return "special/banner3";
    }

    @RequestMapping("banner4")
    public String banner4() {
        return "special/banner4";
    }

    @RequestMapping("banner6")
    public String banner6() {
        return "special/banner6";
    }
}
