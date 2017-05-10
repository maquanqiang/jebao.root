package com.jebao.jebaodb.dao.mapper.investment;


import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbLoanerRepaymentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbLoanerRepaymentDetailMapper {
    int insert(TbLoanerRepaymentDetail record);

    int insertSelective(TbLoanerRepaymentDetail record);

    TbLoanerRepaymentDetail selectByPrimaryKey(Long lrdId);

    int updateByPrimaryKeySelective(TbLoanerRepaymentDetail record);

    int updateByPrimaryKey(TbLoanerRepaymentDetail record);

    List<TbLoanerRepaymentDetail> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbLoanerRepaymentDetail> selectByConditionForPage(@Param("record") TbLoanerRepaymentDetail record, @Param("pageWhere") PageWhere pageWhere);

    int selectByConditionCount(@Param("record") TbLoanerRepaymentDetail record);
}