package com.jebao.jebaodb.dao.mapper.report;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 * mapper.xml中需要指定的名称
 */
public interface ReportInvestmentMapper {
    //查询全部投资人到期收益明细的信息
    List<ReportInvestment> getAll();
    //根据分页查询并显示投资人的信息
    //,  String searchDateSt,  String searchDateEnd
    List<ReportInvestment> selectByConditionForPage(@Param("report") ReportInvestment report, @Param("pageWhere") PageWhere pageWhere,@Param("searchDateSt")String searchDateSt,@Param("searchDateEnd") String searchDateEnd);
    //查询当前的数量
    int selectPostLoanDetailCount(ReportInvestment report);

//    ReportInvestment getDate(ReportInvestment reportInvestment);
//    @Param("liCreateTime")String liCreateTime,@Param("bpRepayTime")String bpRepayTime
    //根据实现查询当前阶段的数据
    List<ReportInvestment> betweenDate(@Param("searchDateSt") String searchDateSt, @Param("searchDateEnd") String searchDateEnd);
    //根据标的标号查询相关的数据
    List<ReportInvestment> getId(@Param("indBpNumber") String indBpNumber);
}
