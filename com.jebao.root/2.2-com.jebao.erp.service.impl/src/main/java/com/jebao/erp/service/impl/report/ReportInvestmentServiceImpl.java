package com.jebao.erp.service.impl.report;

import com.jebao.erp.service.inf.report.ReportInvestmentServiceInf;
import com.jebao.jebaodb.dao.dao.report.ReportInvestmentDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 * 服务层
 */
@Service
public class ReportInvestmentServiceImpl implements ReportInvestmentServiceInf {
    //自动装配
    @Autowired
    private ReportInvestmentDao reportInvestmentDao;

    /**
     * 查询全部的数据
     * @return
     */
    @Override
    public List<ReportInvestment> getAll() {
        return reportInvestmentDao.getAll();
    }

    /**
     * 实现分页查询数据
     * @param
     * @return
     */
    @Override
    public List<ReportInvestment> selectByConditionForPage(ReportInvestment reportInvestment, PageWhere pageWhere, String searchDateSt, String searchDateEnd) {
      List<ReportInvestment> list = reportInvestmentDao.selectByConditionForPage(reportInvestment,pageWhere,searchDateSt,searchDateEnd);
        return list;
    }

    /**
     * 查询投资人明细的总人数
     * @param reportInvestment
     * @return
     */
    public int selectPostLoanDetailCount(ReportInvestment reportInvestment){
        return reportInvestmentDao.selectPostLoanDetailCount(reportInvestment);
    }

    /**
     * 根据时间的区间查询当前的数据
     *
     */
    @Override
    public List<ReportInvestment> betweenDate(String searchDateSt, String searchDateEnd) {
        return reportInvestmentDao.betweenDate(searchDateSt,searchDateEnd);
    }

    @Override
    public List<ReportInvestment> getId(String indBpNumber) {
        return reportInvestmentDao.getId(indBpNumber);
    }

}
