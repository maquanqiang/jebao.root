package com.jebao.erp.service.impl.loanmanage;

import com.jebao.erp.service.inf.loanmanage.ITbBidRiskDataServiceInf;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidRiskDataDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2016/11/24.
 */
@Service
public class TbBidRiskDataServiceImpl implements ITbBidRiskDataServiceInf {
    @Autowired
    private TbBidRiskDataDao dao;

    @Override
    public int add(TbBidRiskData riskData) {
        int count = dao.insert(riskData);
        return count;
    }

    @Override
    public TbBidRiskData selectByBpId(Long brdId) {
        TbBidRiskData riskData = dao.selectByPrimaryKey(brdId);
        return riskData;
    }

    @Override
    public List<TbBidRiskData> selectByConditionForPage(TbBidRiskData riskData, PageWhere pageWhere) {
        List<TbBidRiskData> riskDatas = dao.selectByConditionForPage(riskData, pageWhere);
        return riskDatas;
    }

    @Override
    public int selectByConditionCount(TbBidRiskData record) {
        int count = dao.selectByConditionCount(record);
        return count;
    }

    @Override
    public int updateByBidIdSelective(TbBidRiskData record) {
        int count = dao.updateByPrimaryKeySelective(record);
        return count;
    }
}
