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
import java.text.ParseException;
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
    public void show() throws ParseException {


        ReportInvestment investment = new ReportInvestment();

//        preparedStatement.setTimestamp(Timestamp.valueOf());
        String a= "2016-7-25";
        String c="2017-7-3";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(a);
        Date r=dateFormat.parse(c);
        investment.setBpRepayTime(date);
        ;
        investment.setLiCreateTime(r);
//        List<ReportInvestment> list = dao.betweenDate();
//        System.out.println(list.size());
    }
    //根据编号查询当前的数据

    @Test
    public void ss(){
        List<ReportInvestment> reportInvestmentList = dao.getId("ZE0317010004");
        System.out.println(reportInvestmentList.size());
    }
}
