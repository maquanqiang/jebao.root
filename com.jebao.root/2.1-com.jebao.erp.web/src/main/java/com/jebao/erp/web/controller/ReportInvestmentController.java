package com.jebao.erp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/4/10.
 */
@Controller
@RequestMapping("/report/")
public class ReportInvestmentController {
    /**
     * 查询全部的数据
     */
    @RequestMapping("getAll")
    public String getAll(){
        return "report/index";
    }
    /**
     * 实现分页查询
     *
     */
    @RequestMapping("pagingSelect")
    public String pagingSelect(){
        return "report/index";

    }

    /**
     * 实现文件的导出
     * @return
     */
//    @RequestMapping("excel")
//    public String excel(){
//        return "report/index";
//
//    }
    /**
     * 根据时间查询当前的数据
     * @PathVariable:
     */
//    @RequestMapping("betweenDate")
//    public String betweenDate(@PathVariable("liCreateTime")String liCreateTime, @PathVariable("bpRepayTime") String bpRepayTime, Model model){
//
//        System.out.println("呵呵");
//        model.addAttribute("liCreateTime",liCreateTime);
//        model.addAttribute("bpRepayTime",bpRepayTime);
//        return "report/index";
//
//    }


}
