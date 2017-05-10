package com.jebao.erp.web.controllerApi.report;

import com.jebao.erp.service.inf.report.ReportInvestmentServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.report.InvestmentReportVM;
import com.jebao.erp.web.utils.excel.ExcelUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
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
        List<InvestmentReportVM> investmentReportVMs = new ArrayList<>();
        PageWhere pageWhere = new PageWhere(pageIndex,pageSize);
        List<ReportInvestment> reportInvestmentList = reportInvestmentServiceInf.selectByConditionForPage(reportsm, pageWhere,null,null);
        reportInvestmentList.forEach(o -> investmentReportVMs.add(new InvestmentReportVM(o)));
        int count=reportInvestmentServiceInf.selectPostLoanDetailCount(reportsm);
        return new JsonResultList<>(investmentReportVMs,count);
    }
    /**
     * 实现文件导出的功能
     *
     */

    @RequestMapping("excelAll")
    @ResponseBody
    public void getExcel(String searchDateSt, String searchDateEnd) throws Exception{

                ReportInvestment report = new ReportInvestment();
                //导出查询出来的数据
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(searchDateSt!=null && searchDateEnd!=null){
                    PageWhere pageWhere= new PageWhere(0,100);
                    report.setSearchDateSt(searchDateSt);
//                   String a= simpleDateFormat.format(report.getSearchDateSt());
                    report.setSearchDateEnd(searchDateEnd);
//                    String b=simpleDateFormat.format(report.getSearchDateEnd());
                    List<ReportInvestment> reportInvestments = reportInvestmentServiceInf.selectByConditionForPage(report,pageWhere,searchDateSt,searchDateEnd);
                    List<InvestmentReportVM> investmentReportVMs = new ArrayList<>();
                    for(ReportInvestment o : reportInvestments){
                        investmentReportVMs.add(new InvestmentReportVM(o));
                    }
                    new ExcelUtil().outputFile(response, "投资人明细列表.xlsx",investmentReportVMs);
                }else{
                    PageWhere pageWhere= new PageWhere(0,10000);
                    List<ReportInvestment> reportInvestments = reportInvestmentServiceInf.selectByConditionForPage(report, pageWhere,null,null);
                    List<InvestmentReportVM> investmentReportVMs = new ArrayList<>();
                    for(ReportInvestment o : reportInvestments){

                        investmentReportVMs.add(new InvestmentReportVM(o));
                    }
                    new ExcelUtil().outputFile(response, "投资人明细列表.xlsx",investmentReportVMs);
                }

            }
    /**
     * 根据时间查询
     */
    @RequestMapping("betweenDate")
    @ResponseBody
    public JsonResult betweenDate(String searchDateSt, String searchDateEnd){
        List<ReportInvestment> reportInvestmentList  = reportInvestmentServiceInf.betweenDate(searchDateSt,searchDateEnd);
        List<InvestmentReportVM> investmentReportVMs= new ArrayList<>();
        for(ReportInvestment r:reportInvestmentList){
            investmentReportVMs.add(new InvestmentReportVM(r));
        }
        return new JsonResultList<>(investmentReportVMs);
    }
    /**
     *根据标的编号查询当前的数据
     *   @RequestScope；表示变量的作用域，一共4种。pageScope: 表示变量只能在本页面使用。
     *   requestScope:表示变量能在本次请求中使用。
     *   sessionScope:表示变量能在本次会话中使用。
     *   applicationScope:表示变量能在整个应用程序中使用。
     *    @ResponseStatus:用于修饰一个类或者一个方法
     *    @Override:用在方法之上，用来告诉别人这一个方法是改写父类的
     @Deprecated:建议别人不要使用旧的API的时候用的,编译的时候会用产生警告信息,可以设定在程序里的所有的元素上.
     @SuppressWarnings:暂时把一些警告信息消息关闭
     @Entity:表示该类是可持久化的类
     *
     *
     */
    @RequestMapping("getId")
    @ResponseBody
    public JsonResult getId(String indBpNumber){
        //根据字段查询唯一的标的编号
        List<ReportInvestment> reportInvestmentList = reportInvestmentServiceInf.getId(indBpNumber);
        List<InvestmentReportVM> investmentReportVMs = new ArrayList<>();
        for(ReportInvestment r:reportInvestmentList){
            //添加到封装好的类型中,以便程序解析
            investmentReportVMs.add(new InvestmentReportVM(r));
        }
        return new JsonResultList<>(investmentReportVMs);
    }
}












