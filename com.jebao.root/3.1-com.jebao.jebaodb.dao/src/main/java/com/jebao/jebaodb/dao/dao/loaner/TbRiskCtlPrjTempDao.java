package com.jebao.jebaodb.dao.dao.loaner;

import com.jebao.jebaodb.dao.mapper.loaner.TbRiskCtlPrjTempMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */
@Repository
public class TbRiskCtlPrjTempDao {
    @Autowired
    private TbRiskCtlPrjTempMapper tbRiskCtlPrjTempMapper;

    public int insert(TbRiskCtlPrjTemp record) {
        return tbRiskCtlPrjTempMapper.insert(record);
    }
    public int insertSelective(TbRiskCtlPrjTemp record) {
        return tbRiskCtlPrjTempMapper.insertSelective(record);
    }
    public TbRiskCtlPrjTemp selectByPrimaryKey(Long rcptId)
    {
        return tbRiskCtlPrjTempMapper.selectByPrimaryKey(rcptId);
    }
    public int updateByPrimaryKeySelective(TbRiskCtlPrjTemp record)
    {
        return tbRiskCtlPrjTempMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbRiskCtlPrjTemp record)
    {
        return tbRiskCtlPrjTempMapper.updateByPrimaryKey(record);
    }
    public int deleteByPrimaryKey(Long rcptId)
    {
        return tbRiskCtlPrjTempMapper.deleteByPrimaryKey(rcptId);
    }
    public int deleteByLoanerId(Long loanerId)
    {
        return tbRiskCtlPrjTempMapper.deleteByLoanerId(loanerId);
    }
    public List<TbRiskCtlPrjTemp> selectForPage(PageWhere pageWhere)
    {
        return tbRiskCtlPrjTempMapper.selectForPage(pageWhere);
    }
    public List<TbRiskCtlPrjTemp> selectByLoanerIdForPage(TbRiskCtlPrjTemp record,PageWhere pageWhere)
    {
        if (record == null){
            record = new TbRiskCtlPrjTemp();
        }
/*        if(pageWhere == null){
            pageWhere = new PageWhere(0,10);
        }*/
        return tbRiskCtlPrjTempMapper.selectByLoanerIdForPage(record, pageWhere);
    }
    public int selectByLoanerIdForPageCount(TbRiskCtlPrjTemp record)
    {
        if (record == null){
            record = new TbRiskCtlPrjTemp();
        }
        return tbRiskCtlPrjTempMapper.selectByLoanerIdForPageCount(record);
    }
    @Transactional
    public int insertForTransactional(TbRiskCtlPrjTemp record) {
        return tbRiskCtlPrjTempMapper.insert(record);
    }
}
