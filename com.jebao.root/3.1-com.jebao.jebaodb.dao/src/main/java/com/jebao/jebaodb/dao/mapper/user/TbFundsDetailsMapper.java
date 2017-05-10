package com.jebao.jebaodb.dao.mapper.user;

import com.jebao.jebaodb.entity.user.FundsStatistics;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbFundsDetailsMapper {
    int insert(TbFundsDetails record);

    int insertSelective(TbFundsDetails record);

    TbFundsDetails selectByPrimaryKey(Long fdId);

    int updateByPrimaryKeySelective(TbFundsDetails record);

    int updateByPrimaryKey(TbFundsDetails record);

    /* ==================================================华丽分割线==================================================*/
    int updateBySsn(TbFundsDetails record);

    List<FundsStatistics> statisticsByLoginId(Long loginId);

    List<TbFundsDetails> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbFundsDetails> selectByParamsForPage(@Param("record") TbFundsDetails record, @Param("pageWhere") PageWhere pageWhere);

    int selectByParamsForPageCount(@Param("record") TbFundsDetails record);
    TbFundsDetails selectByParamsForModel(@Param("record") TbFundsDetails record);
}