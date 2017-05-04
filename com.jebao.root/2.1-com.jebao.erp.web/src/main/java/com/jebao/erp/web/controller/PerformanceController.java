package com.jebao.erp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jack on 2017/1/4.
 */
@Controller
@RequestMapping("/performance/")
public class PerformanceController extends _BaseController  {
    @RequestMapping("index")
    public String index(Model model) {
        return "performance/index";
    }
}
