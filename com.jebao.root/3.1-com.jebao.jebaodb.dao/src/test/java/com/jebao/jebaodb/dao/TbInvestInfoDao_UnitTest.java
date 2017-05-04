package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidRiskDataDao;
import com.jebao.jebaodb.dao.dao.report.ReportInvestmentDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee on 2016/11/15.
 */
public class TbInvestInfoDao_UnitTest extends _BaseUnitTest {

    @Autowired
    private TbInvestInfoDao investInfoDao;
    private ReportInvestmentDao dao;

    @Test
    public void selectByConditionForPage(){
        TbInvestInfo record = new TbInvestInfo();
        record.setIiBpId(1l);
        PageWhere pageWhere = new PageWhere(0,1000);
        List<TbInvestInfo> planList = investInfoDao.selectBybpId(record, pageWhere);
        System.out.println(planList);
    }
    @Test
    public void show(){

    }



}
