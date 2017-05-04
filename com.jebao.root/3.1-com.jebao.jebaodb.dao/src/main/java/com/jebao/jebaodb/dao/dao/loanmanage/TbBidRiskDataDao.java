package com.jebao.jebaodb.dao.dao.loanmanage;

import com.jebao.jebaodb.dao.mapper.loanmanage.TbBidRiskDataMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
@Repository
public class TbBidRiskDataDao {
    @Autowired
    private TbBidRiskDataMapper mapper;

    public int insert(TbBidRiskData record) {
        return mapper.insert(record);
    }
    public int insertSelective(TbBidRiskData record) {
        return mapper.insertSelective(record);
    }
    public TbBidRiskData selectByPrimaryKey(Long bpId)
    {
        return mapper.selectByPrimaryKey(bpId);
    }
    public int updateByPrimaryKeySelective(TbBidRiskData record)
    {
        return mapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbBidRiskData record)
    {
        return mapper.updateByPrimaryKey(record);
    }
    public List<TbBidRiskData> selectForPage(PageWhere pageWhere)
    {
        return mapper.selectForPage(pageWhere);
    }

    /**
     * 按条件分页排序查询
     * @param record
     * @param pageWhere
     * @return
     */
    public List<TbBidRiskData> selectByConditionForPage(@Param("record")TbBidRiskData record, @Param("pageWhere")PageWhere pageWhere){
        return mapper.selectByConditionForPage(record, pageWhere);
    }

    /**
     * 带条件查询统计
     * @param record
     * @return
     */
    public int selectByConditionCount(@Param("record")TbBidRiskData record){
        return mapper.selectByConditionCount(record);
    }

    @Transactional
    public int insertForTransactional(TbBidRiskData record) {
        return mapper.insert(record);
    }
}
