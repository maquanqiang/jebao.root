package com.jebao.jebaodb.dao.dao.loaner;

import com.jebao.jebaodb.dao.mapper.loaner.TbLoanerMapper;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
@Repository
public class TbLoanerDao {
    @Autowired
    private TbLoanerMapper tbLoanerMapper;

    public int insert(TbLoaner record) {
        return tbLoanerMapper.insert(record);
    }
    public int insertSelective(TbLoaner record) {
        return tbLoanerMapper.insertSelective(record);
    }
    public TbLoaner selectByPrimaryKey(Long lId)
    {
        return tbLoanerMapper.selectByPrimaryKey(lId);
    }
    public int updateByPrimaryKeySelective(TbLoaner record)
    {
        return tbLoanerMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbLoaner record)
    {
        return tbLoanerMapper.updateByPrimaryKey(record);
    }

    /* ==================================================华丽分割线==================================================*/
    public int deleteByPrimaryKey(Long lId)
    {
        return tbLoanerMapper.deleteByPrimaryKey(lId);
    }
    public TbLoaner selectByLoginId(Long lLoginId){
        return tbLoanerMapper.selectByLoginId(lLoginId);
    }
    public List<TbLoaner> selectForPage(PageWhere pageWhere)
    {
        return tbLoanerMapper.selectForPage(pageWhere);
    }
    public List<TbLoaner> selectByParamsForPage(TbLoaner record,PageWhere pageWhere)
    {
        if (record == null){
            record = new TbLoaner();
        }
        if(pageWhere == null){
            pageWhere = new PageWhere(0,10);
        }
        return tbLoanerMapper.selectByParamsForPage(record, pageWhere);
    }
    public int selectByParamsForPageCount(TbLoaner record)
    {
        if (record == null){
            record = new TbLoaner();
        }
        return tbLoanerMapper.selectByParamsForPageCount(record);
    }


    /*===================首页统计*/
    public int selectCreateCount(String paramDate){return tbLoanerMapper.selectCreateCount(paramDate);}

    public int selectLoginCount(String paramDate){return tbLoanerMapper.selectLoginCount(paramDate);}

    public int selectNonCheckCount(){return tbLoanerMapper.selectNonCheckCount();}

    public int selectPlanCount(String paramDate){return tbLoanerMapper.selectPlanCount(paramDate);}

    public BigDecimal selectLoanCount(String paramDate){

        BigDecimal count = tbLoanerMapper.selectLoanCount(paramDate);
        return count==null?BigDecimal.ZERO:count;
    }

    public int selectWithdrawCount(String paramDate){return tbLoanerMapper.selectWithdrawCount(paramDate);}

    public BigDecimal selectInvestCount(){

        BigDecimal count = tbLoanerMapper.selectInvestCount();
        return count==null?BigDecimal.ZERO:count;
    }

    public BigDecimal selectCurrentIncome(){
        BigDecimal count = tbLoanerMapper.selectCurrentIncome();
        return count==null?BigDecimal.ZERO:count;
    }
}
