package com.jebao.erp.service.inf.report;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.ReportInvestment;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public interface ReportInvestmentServiceInf {
    //查询全部的数据
    List<ReportInvestment> getAll();
    List<ReportInvestment> selectByConditionForPage(ReportInvestment reportInvestment, PageWhere pageWhere, String searchDateSt, String searchDateEnd);
    //查询当前的数量
    int selectPostLoanDetailCount(ReportInvestment reportInvestment);
    //根据实现查询当前阶段的数据
    List<ReportInvestment> betweenDate(String searchDateSt, String searchDateEnd);
    //根据标的标号查询相关的数据
    List<ReportInvestment> getId(String indBpNumber);
}


