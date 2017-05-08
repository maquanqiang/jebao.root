package com.jebao.erp.web.controllerApi.report;

import com.alibaba.fastjson.JSON;
import com.jebao.erp.service.inf.report.InvestmentDetailsServiceInf;
import com.jebao.erp.service.inf.report.ReportInvestmentServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.report.InvestmenDetailsVM;
import com.jebao.erp.web.responseModel.report.InvestmentReportVM;
import com.jebao.erp.web.utils.excel.ExcelUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;
import com.jebao.jebaodb.entity.report.InvestmentDetailsSM;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
@Controller
@RequestMapping("api/report")
public class InvestmentDetailsControllerApi extends _BaseController {
    @Autowired
    private InvestmentDetailsServiceInf investmentDetailsServiceInf;

    /**
     * 获取当前的数据
     * @return
     */
    @RequestMapping("getAllDetails")
    @ResponseBody
    public JsonResult getAll(){
        List<InvestmentDetails>  relist = investmentDetailsServiceInf.getAll();
        List<InvestmenDetailsVM> inlist=new ArrayList<>();

        relist.forEach(o -> inlist.add(new InvestmenDetailsVM(o)));
        return new JsonResultList<>(inlist);
    }
    /**
     * 分页查询
     * pageinxex：当前的页数
     * pageSize；每页显示的数据
     */
    @RequestMapping("pagingSelectDetails")
    @ResponseBody
    public JsonResult pagingSelect(InvestmentDetails reportsm, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        /**
         *
         * @param pageIndex 页号-开始页为0页
         * @param pageSize 页大小-显示的行数
         **/
        System.out.println(reportsm.toString());


        List<InvestmenDetailsVM> investmentReportVMs = new ArrayList<>();
        PageWhere pageWhere = new PageWhere(pageIndex,pageSize);
        List<InvestmentDetails> reportInvestmentList = investmentDetailsServiceInf.selectByConditionForPage(reportsm, pageWhere);
        System.out.println(reportInvestmentList.size());

        reportInvestmentList.forEach(o -> investmentReportVMs.add(new InvestmenDetailsVM(o)));
        int count=investmentDetailsServiceInf.selectPostLoanDetailCount(reportsm);
        System.out.println(count+"===========");
        return new JsonResultList<>(investmentReportVMs,count);
    }
    /**
     * 实现文件导出导出的功能
     *
     */

    @RequestMapping("excelDetails")
    @ResponseBody
    public void getExcel(InvestmentDetails report) throws Exception{
        PageWhere pageWhere= new PageWhere(0,10000);
        List<InvestmentDetails> reportInvestments = investmentDetailsServiceInf.selectByConditionForPage(report, pageWhere);
        List<InvestmenDetailsVM> investmentReportVMs = new ArrayList<>();
        for(InvestmentDetails o : reportInvestments){
            investmentReportVMs.add(new InvestmenDetailsVM(o));
        }
//        reportInvestments.forEach(o -> investmentReportVMs.add(new InvestmentReportVM(o)));
        new ExcelUtil().outputFile(response, "投资人明细列表.xlsx",investmentReportVMs);
        }
    /**
     * 根据日期查询相关的数据
     */
    @RequestMapping("getDate")
    @ResponseBody
    public JsonResult getDate( String liCreateTime, String bpRepayTime ){

        List<InvestmentDetails> investmentDetailsList=investmentDetailsServiceInf.getDate(liCreateTime,bpRepayTime);
        List<InvestmenDetailsVM> investmenDetailsVMs=new ArrayList<>();
        for(InvestmentDetails o :investmentDetailsList ){
            investmenDetailsVMs.add(new InvestmenDetailsVM(o));

        }

        return new JsonResultList<>(investmenDetailsVMs);
    }
}
