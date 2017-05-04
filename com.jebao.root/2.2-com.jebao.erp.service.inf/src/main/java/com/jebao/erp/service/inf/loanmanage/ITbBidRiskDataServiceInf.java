package com.jebao.erp.service.inf.loanmanage;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;

import java.util.List;

/**
 * Created by Lee on 2016/11/18.
 */
public interface ITbBidRiskDataServiceInf {

    int add(TbBidRiskData riskData);

    TbBidRiskData selectByBpId(Long bpId);

    List<TbBidRiskData> selectByConditionForPage(TbBidRiskData riskData, PageWhere pageWhere);

    int selectByConditionCount(TbBidRiskData record);

    int updateByBidIdSelective(TbBidRiskData record);
}
