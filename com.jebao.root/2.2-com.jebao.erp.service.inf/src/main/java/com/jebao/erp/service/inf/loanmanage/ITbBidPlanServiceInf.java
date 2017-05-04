package com.jebao.erp.service.inf.loanmanage;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.loaner.LoanTotal;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanExtSM;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Lee on 2016/11/17.
 */
public interface ITbBidPlanServiceInf {

    int add(TbBidPlan plan);

    TbBidPlan selectByBpId(Long bpId);

    List<TbBidPlan> selectByConditionForPage(TbBidPlan bidPlan, PageWhere pageWhere);

    int selectByConditionCount(TbBidPlan record);

    int updateByBidIdSelective(TbBidPlan record);

    List<TbBidPlan> selectBySelfConditionForPage(BidPlanSM record, PageWhere pageWhere);

    int selectBySelfConditionCount(BidPlanSM record);

    boolean doLoan(TbBidPlan record);

    public ResultInfo repayFreeze(TbIncomeDetail tbIncomeDetail);

    public List<TbBidPlan> selectBpNumberList(String bpNumberStr);

    /* ==================================================借款人相关借款统计查询==================================================*/
    /**
     * 借款资金统计
     * @param loanerId
     * @return
     */
    LoanTotal totalLoanByLoanerId(Long loanerId);

    /**
     * 批量查询统计借款人借款金额，数量
     * @param loanerIds
     * @return
     */
    List<LoanTotal> selectLoanTotalByLoanerIds(List<Long> loanerIds);

    /**
     * 借款人相关借款记录列表
     * @param model
     * @return
     */
    List<TbBidPlan> selectLoanRecordByLoanerIdForPage(BidPlanExtSM model);

    int selectLoanRecordByLoanerIdForPageCount(BidPlanExtSM model);

    BigDecimal selectIncomeCount(int dateSearType);
}
