package com.jebao.erp.web.controllerApi.report;

import com.jebao.erp.service.inf.report.ReportInvestmentServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.requestModel.bidplan.BidPlanForm;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.bidplan.BidPlanVM;
import com.jebao.erp.web.responseModel.postLoan.IncomeDetailsVM;
import com.jebao.erp.web.responseModel.report.InvestmentReportVM;
import com.jebao.erp.web.utils.excel.ExcelUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.report.InvestmentReportSM;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.apache.catalina.startup.ClassLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.narayana.NarayanaBeanFactoryPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.PAEncTSEnc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
@Controller
@RequestMapping("api/report")
public class ReportInvestmentControllerApi extends _BaseController {
    @Autowired
    private ReportInvestmentServiceInf reportInvestmentServiceInf;

    /**
     * 获取当前的数据
     * @return
     */
    @RequestMapping("getAll")
    @ResponseBody
    public JsonResult getAll(){
        List<ReportInvestment>  relist = reportInvestmentServiceInf.getAll();
        List<InvestmentReportVM> inlist=new ArrayList<>();

        relist.forEach(o -> inlist.add(new InvestmentReportVM(o)));
        return new JsonResultList<>(inlist);
    }
    /**
     * 分页查询
     * pageinxex：当前的页数
     * pageSize；每页显示的数据
     */
    @RequestMapping("pagingSelect")
    @ResponseBody
    public JsonResult pagingSelect(ReportInvestment reportsm, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        /**
         *
         * @param pageIndex 页号-开始页为0页
         * @param pageSize 页大小-显示的行数
         **/
        System.out.println(reportsm.toString());


        List<InvestmentReportVM> investmentReportVMs = new ArrayList<>();
        PageWhere pageWhere = new PageWhere(pageIndex,pageSize);
        List<ReportInvestment> reportInvestmentList = reportInvestmentServiceInf.selectByConditionForPage(reportsm, pageWhere);
        System.out.println(reportInvestmentList.size());

        reportInvestmentList.forEach(o -> investmentReportVMs.add(new InvestmentReportVM(o)));
        int count=reportInvestmentServiceInf.selectPostLoanDetailCount(reportsm);
        System.out.println(count+"===========");
        return new JsonResultList<>(investmentReportVMs,count);
    }
    /**
     * 实现文件导出导出的功能
     *
     */

    @RequestMapping("excel")
    @ResponseBody
    public void getExcel(ReportInvestment report) throws Exception{
        PageWhere pageWhere= new PageWhere(0,10000);
        List<ReportInvestment> reportInvestments = reportInvestmentServiceInf.selectByConditionForPage(report, pageWhere);
        List<InvestmentReportVM> investmentReportVMs = new ArrayList<>();
        System.out.println(reportInvestments+"-----");
        for(ReportInvestment o : reportInvestments){
            investmentReportVMs.add(new InvestmentReportVM(o));
        }
//        reportInvestments.forEach(o -> investmentReportVMs.add(new InvestmentReportVM(o)));
        new ExcelUtil().outputFile(response, "投资人明细列表.xlsx",investmentReportVMs);
    }

    /**
     * 根据时间查询
     */
    @RequestMapping("betweenDate")
    @ResponseBody
    public JsonResult betweenDate( String liCreateTime, String bpRepayTime){
        //获取全部的数据
        List<ReportInvestment> reportInvestmentList  = reportInvestmentServiceInf.betweenDate(liCreateTime,bpRepayTime);
        List<InvestmentReportVM> investmentReportVMs= new ArrayList<>();
        System.out.println(investmentReportVMs+"+++++");
        for(ReportInvestment r:reportInvestmentList){
            investmentReportVMs.add(new InvestmentReportVM(r));


        }
        return new JsonResultList<>(investmentReportVMs);
    }


}
