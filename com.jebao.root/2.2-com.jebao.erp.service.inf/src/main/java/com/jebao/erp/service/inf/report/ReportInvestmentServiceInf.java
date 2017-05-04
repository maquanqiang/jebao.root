package com.jebao.erp.service.inf.report;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentReportSM;
import com.jebao.jebaodb.entity.report.ReportInvestment;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public interface ReportInvestmentServiceInf {
    //查询全部的数据
    List<ReportInvestment> getAll();
    List<ReportInvestment> selectByConditionForPage(ReportInvestment reportInvestment , PageWhere pageWhere);
    //查询当前的数量
    int selectPostLoanDetailCount(ReportInvestment reportInvestment);
    //根据实现查询当前阶段的数据
    List<ReportInvestment> betweenDate(String liCreateTime,String bpRepayTime);
}


