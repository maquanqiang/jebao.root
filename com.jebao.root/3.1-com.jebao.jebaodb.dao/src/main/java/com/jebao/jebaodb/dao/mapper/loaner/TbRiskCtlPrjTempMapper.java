package com.jebao.jebaodb.dao.mapper.loaner;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbRiskCtlPrjTempMapper {
    int insert(TbRiskCtlPrjTemp record);

    int insertSelective(TbRiskCtlPrjTemp record);

    TbRiskCtlPrjTemp selectByPrimaryKey(Long rcptId);

    int updateByPrimaryKeySelective(TbRiskCtlPrjTemp record);

    int updateByPrimaryKeyWithBLOBs(TbRiskCtlPrjTemp record);

    int updateByPrimaryKey(TbRiskCtlPrjTemp record);

    /* ==================================================华丽分割线==================================================*/
    int deleteByPrimaryKey(Long rcptId);

    int deleteByLoanerId(Long loanerId);

    List<TbRiskCtlPrjTemp> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbRiskCtlPrjTemp> selectByLoanerIdForPage(@Param("record") TbRiskCtlPrjTemp record, @Param("pageWhere") PageWhere pageWhere);

    int selectByLoanerIdForPageCount(@Param("record") TbRiskCtlPrjTemp record);
}