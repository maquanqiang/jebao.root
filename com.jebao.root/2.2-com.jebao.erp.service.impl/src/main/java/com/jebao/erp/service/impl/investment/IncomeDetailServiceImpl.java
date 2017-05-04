package com.jebao.erp.service.impl.investment;

import com.jebao.erp.service.inf.investment.IIncomeDetailServiceInf;
import com.jebao.erp.service.inf.investment.ILoanerRepaymentDetailServiceInf;
import com.jebao.jebaodb.dao.dao.investment.TbIncomeDetailDao;
import com.jebao.jebaodb.dao.dao.investment.TbIncomeDetailDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.search.IncomeDetailSM;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.postLoan.search.RepaymentDetailSM;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Lee on 2016/12/2.
 */
@Service
public class IncomeDetailServiceImpl implements IIncomeDetailServiceInf {

    @Autowired
    private TbIncomeDetailDao incomeDetailDao;

    @Override
    public int save(TbIncomeDetail record) {


        //endregion
        Long id = record.getIndId();
        int result = 0;
        if (id == null) {
            result = addInvestInfo(record);//新增
        } else {
            result = updateByBidIdSelective(record);//修改
        }

        return result;
    }

    public int addInvestInfo(TbIncomeDetail record) {
        int result = incomeDetailDao.insert(record);
        return result;
    }

    @Override
    public TbIncomeDetail selectById(Long id) {
        TbIncomeDetail tbInvestInfo = incomeDetailDao.selectByPrimaryKey(id);
        return tbInvestInfo;
    }

    @Override
    public List<TbIncomeDetail> selectByConditionForPage(TbIncomeDetail record, PageWhere pageWhere) {
        List<TbIncomeDetail> tbInvestInfos = incomeDetailDao.selectByConditionForPage(record, pageWhere);
        return tbInvestInfos;
    }

    @Override
    public int selectByConditionCount(TbIncomeDetail record) {
        int count = incomeDetailDao.selectByConditionCount(record);
        return count;
    }

    @Override
    public List<TbIncomeDetail> selectGroupByConditionForPage(RepaymentDetailSM record, PageWhere pageWhere) {
        return incomeDetailDao.selectGroupByConditionForPage(record, pageWhere);
    }

    @Override
    public int selectGroupByConditionCount(RepaymentDetailSM record) {
        return incomeDetailDao.selectGroupByConditionCount(record);
    }

    @Override
    public int updateByConditionSelective(TbIncomeDetail record) {
        return incomeDetailDao.updateByConditionSelective(record);
    }

    @Override
    public BigDecimal investerTotalMoney(TbIncomeDetail record) {
        return incomeDetailDao.investerTotalMoney(record);
    }

    @Override
    public List<TbIncomeDetail> selectPostLoanDetail(BidPlanSM record, PageWhere pageWhere) {
        return incomeDetailDao.selectPostLoanDetail(record, pageWhere);
    }

    @Override
    public int selectPostLoanDetailCount(BidPlanSM record) {
        return incomeDetailDao.selectPostLoanDetailCount(record);
    }

    public int updateByBidIdSelective(TbIncomeDetail record) {
        int result = incomeDetailDao.updateByPrimaryKeySelective(record);
        return result;
    }

    /*==================================================借款人相关信息==================================================*/

    /**
     * 统计借款人待还(已还)金额（本金、利息）
     *
     * @param loanerId 借款人ID
     * @param fundType 资金类型 1:本金 2 : 利息
     * @param status   还款状态 1:未还 2:已还
     * @return
     */
    @Override
    public BigDecimal totalMoneyByloanerId(Long loanerId, int fundType, int status) {
        IncomeDetailSM model = new IncomeDetailSM();
        model.setFundType(fundType);
        model.setLoanerId(loanerId);
        model.setStatus(status);
        return incomeDetailDao.totalMoneyByloanerId(model);
    }
}
