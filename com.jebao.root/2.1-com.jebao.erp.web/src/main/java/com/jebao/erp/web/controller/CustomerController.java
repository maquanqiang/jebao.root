package com.jebao.erp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lee on 2017/4/17.
 */

@Controller
@RequestMapping("customer")
public class CustomerController {

    @RequestMapping("index")
    public String index() {
        return "customer/index";
    }
}
