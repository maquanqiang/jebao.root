package com.jebao.jebaodb.dao.mapper.loaner;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TbLoanerMapper {
    int insert(TbLoaner record);

    int insertSelective(TbLoaner record);

    TbLoaner selectByPrimaryKey(Long lId);

    int updateByPrimaryKeySelective(TbLoaner record);

    int updateByPrimaryKey(TbLoaner record);

    /* ==================================================华丽分割线==================================================*/
    int deleteByPrimaryKey(Long lId);

    TbLoaner selectByLoginId(Long lLoginId);

    List<TbLoaner> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbLoaner> selectByParamsForPage(@Param("record") TbLoaner record, @Param("pageWhere") PageWhere pageWhere);

    int selectByParamsForPageCount(@Param("record") TbLoaner record);



    //=======================首页统计
    int selectCreateCount(@Param("paramDate")String paramDate);

    int selectLoginCount(@Param("paramDate")String paramDate);

    int selectNonCheckCount();

    int selectPlanCount(@Param("paramDate")String paramDate);

    BigDecimal selectLoanCount(@Param("paramDate")String paramDate);

    int selectWithdrawCount(@Param("paramDate")String paramDate);

    BigDecimal selectInvestCount();

    BigDecimal selectCurrentIncome();

}