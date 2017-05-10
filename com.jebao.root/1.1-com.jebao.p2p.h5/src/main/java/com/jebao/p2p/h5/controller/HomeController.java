package com.jebao.p2p.h5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wenyq on 2017/2/17.
 */
@Controller
@RequestMapping(value = {"/", "/templates/home"})
public class HomeController {
    @RequestMapping(value = {"/","/index"})
    public String index() {
        return "templates/home/index";
    }
}
