package com.jebao.jebaodb.dao.mapper.investment;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.InvestBase;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TbInvestInfoMapper {
    int insert(TbInvestInfo record);

    int insertSelective(TbInvestInfo record);

    TbInvestInfo selectByPrimaryKey(Long iiId);

    int updateByPrimaryKeySelective(TbInvestInfo record);

    int updateByPrimaryKey(TbInvestInfo record);

    List<TbInvestInfo> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbInvestInfo> selectByConditionForPage(@Param("record") TbInvestInfo record, @Param("pageWhere") PageWhere pageWhere);

    int selectByConditionCount(@Param("record") TbInvestInfo record);

    List<TbInvestInfo> selectByBpId(@Param("record") TbInvestInfo record, @Param("pageWhere") PageWhere pageWhere);

    BigDecimal investTotal(Long iiBpId);

    /**==================================================投资记录==================================================**/

    List<InvestBase> selectInvestBaseByLoginId(@Param("record") TbInvestInfo record, @Param("pageWhere") PageWhere pageWhere);

    int selectInvestBaseByLoginIdForPageCount(@Param("record") TbInvestInfo record);

    BigDecimal totalFreezeMoneyByLoginId(Long iiLoginId);

    List<TbInvestInfo> selectInvestmentTop();
}