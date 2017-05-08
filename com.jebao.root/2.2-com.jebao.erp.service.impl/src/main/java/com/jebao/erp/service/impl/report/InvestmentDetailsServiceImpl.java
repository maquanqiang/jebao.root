package com.jebao.erp.service.impl.report;

import com.jebao.erp.service.inf.report.InvestmentDetailsServiceInf;
import com.jebao.jebaodb.dao.dao.report.InvestmentDetailsDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
@Service
public class InvestmentDetailsServiceImpl implements InvestmentDetailsServiceInf{
    @Autowired
    private InvestmentDetailsDao investmentDetailsDao;
    @Override
    public List<InvestmentDetails> getAll() {
        return investmentDetailsDao.getAll();
    }
    @Override
    public List<InvestmentDetails> selectByConditionForPage(InvestmentDetails reportInvestment, PageWhere pageWhere) {
        return investmentDetailsDao.selectByConditionForPage(reportInvestment,pageWhere);
    }
    @Override
    public int selectPostLoanDetailCount(InvestmentDetails reportInvestment) {
        return investmentDetailsDao.selectPostLoanDetailCount(reportInvestment);
    }

    @Override
    public List<InvestmentDetails> getDate(String liCreateTime,String bpRepayTime) {
        return investmentDetailsDao.getDate(liCreateTime,bpRepayTime);
    }
}
