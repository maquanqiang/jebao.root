package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.investment.TbIncomeDetailDao;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidRiskDataDao;
import com.jebao.jebaodb.dao.dao.report.ReportInvestmentDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.FundDetailSM;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import com.jebao.jebaodb.entity.product.ProductSM;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.internal.constraintvalidators.bv.past.PastValidatorForReadableInstant;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerCustomDateFormat;

/**
 * Created by Lee on 2016/11/15.
 */
public class TbBidPlanDao_UnitTest extends _BaseUnitTest {

    @Autowired
    private TbBidPlanDao tbBidPlanDao;
    @Autowired
    private TbBidRiskDataDao riskDataDao;
    @Autowired
    private TbIncomeDetailDao incomeDetailDao;
    @Autowired
    private TbInvestInfoDao investInfoDao;
    @Autowired
    private ReportInvestmentDao dao;
    @Test
    public void show(){
        List<ReportInvestment> list = dao.getAll();
        System.out.print(list.size()+"----------------------------++++++++++++++++");
    }

    @Test
    public void selectList()
    {
        ProductSM productSM = new ProductSM();
        productSM.setBpInterestPayType(2);
        productSM.setBpCycleType(2);
        productSM.setBpPeriods(4);
        productSM.setBpStatus(7);
        productSM.setSearchMoneySt(new BigDecimal(20000));
        PageWhere pageWhere = new PageWhere(0, 1000);
        pageWhere.setOrderBy("bp_update_time desc");
        List<TbBidPlan> tbBidPlans = tbBidPlanDao.selectP2PList(productSM, pageWhere);
        System.out.println();
    }

    @Test
    public void overdueMoneyOther(){
        Map<String, Object> map = new HashMap<>();
        map.put("bpId", 10);
        map.put("nowTime", new Date());

        int i = tbBidPlanDao.fullBid(map);
        System.out.println(i);

    }


    @Test
    public void select(){
        TbInvestInfo tbInvestInfo = new TbInvestInfo();
        tbInvestInfo.setIiId(37l);
        tbInvestInfo.setIiContractUrl("http://");
        investInfoDao.updateByPrimaryKeySelective(tbInvestInfo);
        System.out.println();
    }


    @Test
    public void insertExample()
    {
        TbBidPlan record = new TbBidPlan();
        record.setBpCreateTime(new Date());
        record.setBpBidMoney(BigDecimal.ONE);
        record.setBpBorrowDesc("非常好");
        record.setBpCycleSize(1);
        record.setBpRiskOpinion("可控");
        int result= tbBidPlanDao.insert(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getBpId());
    }

    @Test
    public void selectByConditionForPage(){
        TbBidPlan record = new TbBidPlan();
        record.setBpBorrowDesc("非常好");
        String orderByCondition = "bp_id desc";
        PageWhere pageWhere = new PageWhere(0,1);
        List<TbBidPlan> planList = tbBidPlanDao.selectByConditionForPage(record, pageWhere);
        System.out.println(planList);
    }

    @Test
    public void selectByConditionCount(){
        TbBidPlan plan = new TbBidPlan();
        plan.setBpBorrowDesc("非常好");
        System.out.println(tbBidPlanDao.selectByConditionCount(plan));
    }

    @Test
    public void addBidPlan(){
        TbBidPlan bidPlan = new TbBidPlan();
        bidPlan.setBpBorrowDesc("测试联合插入数据");
        bidPlan.setBpCreateTime(new Date());
        tbBidPlanDao.insert(bidPlan);

        TbBidRiskData riskData = new TbBidRiskData();
        riskData.setBrdBpId(bidPlan.getBpId());
        riskData.setBrdRemark("测试");
        riskData.setBrdNo("99999");
        riskData.setBrdUrl("http://baidu.com");
        riskDataDao.insert(riskData);
    }

    @Test
    public void updatePlan(){
        TbBidPlan plan = new TbBidPlan();
        plan.setBpId(1L);
        plan.setBpBorrowDesc("还可以");

        tbBidPlanDao.updateByPrimaryKeySelective(plan);


    }

    @Test
    public void surpriseMoney(){
        List ids = new ArrayList<>();
        ids.add(22);
        ids.add(23);
        ids.add(24);
        List<TbBidPlan> tbBidPlans = tbBidPlanDao.selectCacheData(ids);
        for(TbBidPlan plan : tbBidPlans){

            System.out.println(plan.getBpId());
        }
    }
    @Test
    public void timeout(){
        tbBidPlanDao.timeoutBid(new Date());
        System.out.println("22222222222");
    }

    @Test
    public void test_selectIncomeCount(){

        List<TbBidPlan> tbBidPlans = tbBidPlanDao.selectForPage(new PageWhere(0, 1000));
        for(TbBidPlan plan:tbBidPlans){
            if(plan.getBpCycleType()==1){
                plan.setBpMonthTerm(plan.getBpPeriods()%30==0?plan.getBpPeriods()/30:plan.getBpPeriods()/30+1);
            }else if(plan.getBpCycleType()==2){
                plan.setBpMonthTerm(plan.getBpPeriods());
            }else if(plan.getBpCycleType()==3){
                plan.setBpMonthTerm(plan.getBpPeriods()*3);
            }else if(plan.getBpCycleType()==4){
                plan.setBpMonthTerm(plan.getBpPeriods()*12);
            }
            tbBidPlanDao.updateByPrimaryKeySelective(plan);
        }

        System.out.println("2222222");


    }


}
