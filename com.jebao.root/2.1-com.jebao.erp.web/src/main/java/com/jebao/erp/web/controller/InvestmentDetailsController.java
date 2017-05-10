package com.jebao.erp.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/4/10.
 */
@Controller
@RequestMapping("/report/")
public class InvestmentDetailsController {
    /**
     * 查询全部的数据
     */
    @RequestMapping("getAllDetails")
    public String getAll(){
        return "report/investment";
    }
    /**
     * 实现分页查询
     *
     */
    @RequestMapping("pagingSelectDetails")
    public String pagingSelectDetails(){
        return "report/investment";
    }
//    @RequestMapping("getDate")
//    public String getDate(){
//        System.out.println("hehe");
//        return "report/investment";
//    }
}
