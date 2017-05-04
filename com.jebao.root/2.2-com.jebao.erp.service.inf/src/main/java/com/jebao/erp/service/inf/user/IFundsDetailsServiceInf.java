package com.jebao.erp.service.inf.user;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.user.FundsStatistics;
import com.jebao.jebaodb.entity.user.TbFundsDetails;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
public interface IFundsDetailsServiceInf {
    /**
     * 资金明细
     * @param record
     * @param pageWhere
     * @return
     */
    List<TbFundsDetails> selectByParamsForPage(TbFundsDetails record,PageWhere pageWhere);

    /**
     * 资金明细总数
     * @param record
     * @return
     */
    int selectByParamsForPageCount(TbFundsDetails record);

    /**
     * 资金汇总
     * @param loginId 用户ID
     * @return
     */
    List<FundsStatistics> statisticsByLoginId(Long loginId);
}
