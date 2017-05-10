package com.jebao.erp.web.controller;

import com.jebao.erp.web.requestModel.home.IndexForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/11/2.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String indexHome(@ModelAttribute("form") IndexForm form) {

        return "redirect:/home/index";
    }
    @RequestMapping("/home/index")
    public String index(@ModelAttribute("form") IndexForm form) {

        return "home/index";
    }
    @RequestMapping("/home/test")
    public String test() {


        return "home/test";
    }

}
