package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.report.ReportInvestmentDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentReportSM;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.sql.RowSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class ReportTest extends _BaseUnitTest {
    @Autowired
    private ReportInvestmentDao dao;
    private RowSet preparedStatement;

    @Test
    public void show(){
        ReportInvestment investment = new ReportInvestment();

//        preparedStatement.setTimestamp(Timestamp.valueOf());

       investment.setBpRepayTime(new Date());
        investment.setLiCreateTime(new Date());
        String s=investment.getLiCreateTime().toString();
        String b=investment.getBpRepayTime().toString();
//        List<ReportInvestment> list = dao.betweenDate(investment);
//        System.out.println(list.size());
    }
    @Test
    public void select(){
        List<ReportInvestment> list=dao.getId("ZE1216120001");
        System.out.println(list.size());

    }
}
