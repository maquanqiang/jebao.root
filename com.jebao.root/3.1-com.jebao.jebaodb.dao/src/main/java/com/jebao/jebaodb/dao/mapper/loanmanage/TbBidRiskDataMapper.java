package com.jebao.jebaodb.dao.mapper.loanmanage;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBidRiskDataMapper {
    int insert(TbBidRiskData record);

    int insertSelective(TbBidRiskData record);

    TbBidRiskData selectByPrimaryKey(Long brdId);

    int updateByPrimaryKeySelective(TbBidRiskData record);

    int updateByPrimaryKey(TbBidRiskData record);

    List<TbBidRiskData> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbBidRiskData> selectByConditionForPage(@Param("record") TbBidRiskData record, @Param("pageWhere") PageWhere pageWhere);

    int selectByConditionCount(@Param("record") TbBidRiskData record);
}