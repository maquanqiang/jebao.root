package com.jebao.erp.service.inf.report;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface InvestmentDetailsServiceInf {
    //查询全部的数据
    List<InvestmentDetails> getAll();
    List<InvestmentDetails> selectByConditionForPage(InvestmentDetails reportInvestment, PageWhere pageWhere, String searchDateSt, String searchDateEnd);
    //查询当前的数量
    int selectPostLoanDetailCount(InvestmentDetails reportInvestment);
    //根据时间查询当前的数据
    List<InvestmentDetails> getDate(String liCreateTime, String bpRepayTime);
    //根据标的标号查询数据
    List<InvestmentDetails> getId(String indBpNumber);
}
