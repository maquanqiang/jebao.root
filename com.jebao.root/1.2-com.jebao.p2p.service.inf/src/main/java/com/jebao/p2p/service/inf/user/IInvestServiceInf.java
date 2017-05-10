package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 投资记录和账户总览接口
 * Created by Administrator on 2016/12/10.
 */
public interface IInvestServiceInf {
    /**
     * 账户资金汇总
     *
     * @param loginId
     * @return
     */
    Map<String, BigDecimal> getInvestStatisticsByLoginId(Long loginId);

    /**
     * 投资项目
     *
     * @param record
     * @param page
     * @return
     */
    List<InvestBase> selectInvestBaseByLoginId(TbInvestInfo record, PageWhere page);

    /**
     * 投资中项目总数
     *
     * @param record
     * @return
     */
    int selectInvestBaseByLoginIdForPageCount(TbInvestInfo record);

    /**
     * 投资人还款项目列表
     *
     * @param iiIds     投资记录IDs
     * @param indStatus 还款状态 1:未还 2:已还
     * @param fundType  资金类型 1:本金 2 : 利息
     * @return
     */
    List<InvestPayment> selectPaymentByIds(List<Long> iiIds, int indStatus, int fundType);

    List<TbInvestInfo> selectInvestmentTop();
}
