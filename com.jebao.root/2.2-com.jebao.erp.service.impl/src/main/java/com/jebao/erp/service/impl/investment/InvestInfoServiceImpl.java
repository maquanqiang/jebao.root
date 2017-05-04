package com.jebao.erp.service.impl.investment;

import com.jebao.erp.service.inf.investment.IInvestInfoServiceInf;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2016/12/2.
 */
@Service
public class InvestInfoServiceImpl implements IInvestInfoServiceInf {

    @Autowired
    private TbInvestInfoDao investInfoDao;

    @Override
    public int save(TbInvestInfo record) {


        //endregion
        Long id = record.getIiId();
        int result = 0;
        if (id == null) {
            result = addInvestInfo(record);//新增
        } else {
            result = updateByBidIdSelective(record);//修改
        }

        return result;
    }

    public int addInvestInfo(TbInvestInfo record){
        int result = investInfoDao.insert(record);
        return result;
    }

    @Override
    public TbInvestInfo selectById(Long id) {
        TbInvestInfo tbInvestInfo = investInfoDao.selectByPrimaryKey(id);
        return tbInvestInfo;
    }

    @Override
    public List<TbInvestInfo> selectByConditionForPage(TbInvestInfo record, PageWhere pageWhere) {
        List<TbInvestInfo> tbInvestInfos = investInfoDao.selectByConditionForPage(record, pageWhere);
        return tbInvestInfos;
    }

    @Override
    public int selectByConditionCount(TbInvestInfo record) {
        int count = investInfoDao.selectByConditionCount(record);
        return count;
    }

    @Override
    public List<TbInvestInfo> selectByBpId(TbInvestInfo record, PageWhere pageWhere) {
        List<TbInvestInfo> tbInvestInfos = investInfoDao.selectBybpId(record, pageWhere);
        return tbInvestInfos;
    }

    public int updateByBidIdSelective(TbInvestInfo record) {
        int result = investInfoDao.updateByPrimaryKeySelective(record);
        return result;
    }
}
