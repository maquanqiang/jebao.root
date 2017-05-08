package com.jebao.jebaodb.dao.mapper.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
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
    List<InvestmentDetails> selectByConditionForPage(@Param("investment") InvestmentDetails investment, @Param("pageWhere") PageWhere pageWhere);
    //查询当前的数量
    int selectPostLoanDetailCount(InvestmentDetails investment);
    //根据时间区段查询当前的数据,利用对象来调用相应的字段
    List<InvestmentDetails> getDate(@Param("liCreateTime") String liCreateTime, @Param("bpRepayTime") String bpRepayTime);

}

