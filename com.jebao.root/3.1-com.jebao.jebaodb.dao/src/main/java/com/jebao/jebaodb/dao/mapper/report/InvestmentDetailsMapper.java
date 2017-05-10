package com.jebao.jebaodb.dao.mapper.report;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20 0020.
 * 投资明细
 *
 */
public interface InvestmentDetailsMapper {
    //查询全部投资人到期收益明细的信息
    List<InvestmentDetails> getAll();
    //根据分页查询并显示投资人的信息
    List<InvestmentDetails> selectByConditionForPage(@Param("investment") InvestmentDetails investment, @Param("pageWhere") PageWhere pageWhere, @Param("searchDateSt") String searchDateSt, @Param("searchDateEnd") String searchDateEnd);
    //查询当前的数量
    int selectPostLoanDetailCount(InvestmentDetails investment);
    //根据时间区段查询当前的数据,利用对象来调用相应的字段
    List<InvestmentDetails> getDate(@Param("searchDateSt") String searchDateSt, @Param("searchDateEnd") String searchDateEnd);
    //根据标的标号查询相关的数据
    List<InvestmentDetails> getId(@Param("indBpNumber")String indBpNumber);
}

