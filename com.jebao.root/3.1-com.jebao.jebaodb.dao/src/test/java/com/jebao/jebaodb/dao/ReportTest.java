package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.report.InvestmentDetailsDao;
import com.jebao.jebaodb.dao.dao.report.ReportInvestmentDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;
import com.jebao.jebaodb.entity.report.InvestmentReportSM;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class ReportTest extends _BaseUnitTest {
    @Autowired
    private ReportInvestmentDao dao;
    @Test
    public void show(){
        ReportInvestment investment = new ReportInvestment();
        investment.getBpCycleType();
        int count=dao.selectPostLoanDetailCount(investment);
        System.out.print(count+"------------");

    }
    @Test
    public void select(){
        List<InvestmentReportSM> investmentReportSMs= new ArrayList<>();

        InvestmentDetailsDao dao = new InvestmentDetailsDao();
        ReportInvestmentDao reportInvestmentDao = new ReportInvestmentDao();
        ReportInvestment r = new ReportInvestment();
        PageWhere page = new PageWhere(0,10);
        List<ReportInvestment> list =reportInvestmentDao.selectByConditionForPage(r,page,null,null);
        System.out.println(list.size());

//
//        ReportInvestment re= new ReportInvestment();
////        re.setIndBpNumber("UP121701002");
//
//
//        PageWhere pageWhere= new PageWhere(0,5);
//        List<ReportInvestment> list = dao.selectByConditionForPage(re,pageWhere);
//        System.out.println(list.size()+"+++++++++++++++++++++++++");
//        for(ReportInvestment r:list){
//            System.out.println(r.getBpCycleType()+"--"+r.getIiMoney()+"========="+r.getIndBpNumber()+"----"+r.getBpPeriods());
//        }
    }
}
