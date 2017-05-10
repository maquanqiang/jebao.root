package com.jebao.erp.service.inf.investment;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;

import java.util.List;

/**
 * Created by Lee on 2016/11/17.
 */
public interface IInvestInfoServiceInf {

    int save(TbInvestInfo record);

    TbInvestInfo selectById(Long id);

    List<TbInvestInfo> selectByConditionForPage(TbInvestInfo record, PageWhere pageWhere);

    int selectByConditionCount(TbInvestInfo record);

    List<TbInvestInfo> selectByBpId(TbInvestInfo record, PageWhere pageWhere);

}
