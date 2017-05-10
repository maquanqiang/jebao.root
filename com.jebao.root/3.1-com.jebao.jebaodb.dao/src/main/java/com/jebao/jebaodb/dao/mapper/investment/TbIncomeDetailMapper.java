package com.jebao.jebaodb.dao.mapper.investment;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.FundDetailSM;
import com.jebao.jebaodb.entity.investment.InvestPayment;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.search.IncomeDetailSM;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.postLoan.search.RepaymentDetailSM;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TbIncomeDetailMapper {
    int insert(TbIncomeDetail record);

    int insertSelective(TbIncomeDetail record);

    TbIncomeDetail selectByPrimaryKey(Long indId);

    int updateByPrimaryKeySelective(TbIncomeDetail record);

    int updateByPrimaryKey(TbIncomeDetail record);

    List<TbIncomeDetail> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbIncomeDetail> selectByConditionForPage(@Param("record") TbIncomeDetail record, @Param("pageWhere") PageWhere pageWhere);

    int selectByConditionCount(@Param("record") TbIncomeDetail record);

    List<TbIncomeDetail> selectGroupByConditionForPage(@Param("record") RepaymentDetailSM record, @Param("pageWhere") PageWhere pageWhere);

    int selectGroupByConditionCount(@Param("record") RepaymentDetailSM record);

    List<TbIncomeDetail> selectFundList(@Param("record") FundDetailSM record, @Param("pageWhere") PageWhere pageWhere);

    BigDecimal loanMoneyTotal(@Param("loginId") Long loginId);

    TbIncomeDetail overdueMoneyOther(@Param("bpLoginId")Long loginId, @Param("dateTime") Date dateTime);

    int selectFundCount(@Param("record") FundDetailSM record);

    int selectFundListForPageCount(@Param("record") FundDetailSM record);

    List<TbIncomeDetail> repaymentList(@Param("record") TbIncomeDetail record);

    BigDecimal repaymoneyTotal(@Param("record") TbIncomeDetail record);

    int updateByConditionSelective(TbIncomeDetail record);

    BigDecimal investerTotalMoney(TbIncomeDetail record);

    List<TbIncomeDetail> selectPostLoanDetail(@Param("record")BidPlanSM record, @Param("pageWhere") PageWhere pageWhere);

    int selectPostLoanDetailCount(@Param("record") BidPlanSM record);

    /*==================================================借款人相关统计==================================================*/
    BigDecimal totalMoneyByloanerId(IncomeDetailSM model);

    /*==================================================投资人相关统计==================================================*/
    BigDecimal totalMoneyByLoginId(@Param("record") TbIncomeDetail record);

    List<InvestPayment> selectPaymentByIds(@Param("ids") List<Long> iiIds, @Param("record") TbIncomeDetail record);
}