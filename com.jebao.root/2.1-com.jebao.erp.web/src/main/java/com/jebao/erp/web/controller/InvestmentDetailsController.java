package com.jebao.erp.web.controller;
import com.jebao.erp.service.inf.report.InvestmentDetailsServiceInf;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.report.InvestmenDetailsVM;
import com.jebao.erp.web.utils.excel.ExcelUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/4/10.
 */
@Controller
@RequestMapping("/report/")
public class InvestmentDetailsController  {
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
    public String pagingSelect(){
    System.out.println("我在这里了.");
        return "report/investment";
    }
    @RequestMapping("getDate")
    public String getDate(){
        System.out.println("hehe");
        return "report/investment";
    }
}
