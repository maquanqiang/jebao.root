package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.FundDetailSM;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.user.TbFundsDetails;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 资金明细接口
 * Created by Administrator on 2016/12/2.
 */
public interface IFundsDetailsServiceInf {
    /**
     * 根据用户ID获取资金明细列表
     * @param loginId
     * @param page
     * @return
     */
    List<TbFundsDetails> selectFundsDetailsByLoginIdForPage(Long loginId, PageWhere page);

    /**
     * 根据用户ID获取资金明细总数
     * @param loginId
     * @return
     */
    int selectFundsDetailsByLoginIdForPageCount(Long loginId);

    /**
     * 还款清单
     * @param record
     * @return
     */
    List<TbIncomeDetail> selectFundList(FundDetailSM record, PageWhere pageWhere);

    int selectFundCount(FundDetailSM record);

    /**
     * 借款管理统计
     * @param loginId
     * @return
     */
    Map<String, BigDecimal> loanManageInfo(Long loginId);

     /** 新增资金收支明细
     * @param record
     * @return
     */
    int insert(TbFundsDetails record);

    /**
     * 修改资金收支明细
     * @param record
     * @return
     */
    int update(TbFundsDetails record);

    /**
     * 根据流水号获取资金收支明细详情
     * @param serialNumber
     * @return
     */
    int selectBySerialNumberForPageCount(Long loginId, String serialNumber);
    /*
    *  根据流水号获取资金收支明细详情 返回model
    *
    */
    TbFundsDetails selectByParamsForModel(Long loginId, String serialNumber);

    List<TbFundsDetails> selectByParamsForPage(TbFundsDetails record, PageWhere pageWhere);
}
