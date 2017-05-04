package com.jebao.jebaodb.dao.dao.investment;

import com.jebao.jebaodb.dao.mapper.investment.TbLoanerRepaymentDetailMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbLoanerRepaymentDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
@Repository
public class TbLoanerRepaymentDetailDao {
    @Autowired
    private TbLoanerRepaymentDetailMapper mapper;

    public int insert(TbLoanerRepaymentDetail record) {
        return mapper.insert(record);
    }
    public int insertSelective(TbLoanerRepaymentDetail record) {
        return mapper.insertSelective(record);
    }
    public TbLoanerRepaymentDetail selectByPrimaryKey(Long bpId)
    {
        return mapper.selectByPrimaryKey(bpId);
    }
    public int updateByPrimaryKeySelective(TbLoanerRepaymentDetail record)
    {
        return mapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbLoanerRepaymentDetail record)
    {
        return mapper.updateByPrimaryKey(record);
    }
    public List<TbLoanerRepaymentDetail> selectForPage(PageWhere pageWhere)
    {
        return mapper.selectForPage(pageWhere);
    }

    /**
     * 系统条件分页排序查询
     * @param record
     * @param pageWhere
     * @return
     */
    public List<TbLoanerRepaymentDetail> selectByConditionForPage(@Param("record")TbLoanerRepaymentDetail record, @Param("pageWhere")PageWhere pageWhere){
        return mapper.selectByConditionForPage(record, pageWhere);
    }

    /**
     * 系统条件查询统计
     * @param record
     * @return
     */
    public int selectByConditionCount(@Param("record")TbLoanerRepaymentDetail record){
        return mapper.selectByConditionCount(record);
    }


    @Transactional
    public int insertForTransactional(TbLoanerRepaymentDetail record) {
        return mapper.insert(record);
    }
}
