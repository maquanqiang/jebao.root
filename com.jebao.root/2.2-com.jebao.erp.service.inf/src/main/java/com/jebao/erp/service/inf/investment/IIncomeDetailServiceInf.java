package com.jebao.erp.service.inf.investment;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.search.IncomeDetailSM;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.postLoan.search.RepaymentDetailSM;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Lee on 2016/11/17.
 */
public interface IIncomeDetailServiceInf {

    int save(TbIncomeDetail record);

    TbIncomeDetail selectById(Long id);

    List<TbIncomeDetail> selectByConditionForPage(TbIncomeDetail record, PageWhere pageWhere);

    int selectByConditionCount(TbIncomeDetail record);

    List<TbIncomeDetail> selectGroupByConditionForPage(RepaymentDetailSM record, PageWhere pageWhere);

    int selectGroupByConditionCount(RepaymentDetailSM record);

    int updateByConditionSelective(TbIncomeDetail record);

    BigDecimal investerTotalMoney(TbIncomeDetail record);

    List<TbIncomeDetail> selectPostLoanDetail(BidPlanSM record, PageWhere pageWhere);

    int selectPostLoanDetailCount(BidPlanSM record);


    /*==================================================借款人相关信息==================================================*/

    /**
     * 统计借款人待还(已还)金额（本金、利息）
     * @param loanerId 借款人ID
     * @param fundType 资金类型 1:本金 2 : 利息
     * @param status 还款状态 1:未还 2:已还
     * @return
     */
    BigDecimal totalMoneyByloanerId(Long loanerId, int fundType, int status);
}
