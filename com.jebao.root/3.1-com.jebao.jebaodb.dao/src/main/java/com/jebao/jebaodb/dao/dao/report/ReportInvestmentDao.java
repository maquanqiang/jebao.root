package com.jebao.jebaodb.dao.dao.report;
import com.jebao.jebaodb.dao.mapper.report.ReportInvestmentMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.ReportInvestment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 * 投资人收益明细的接口
 */
@Repository
public class ReportInvestmentDao {

    //投资人详细列表
    @Autowired
    private ReportInvestmentMapper investmentMapper;
    public List<ReportInvestment> getAll(){
            return investmentMapper.getAll();
    }
    /**
     * 实现分页查询当前的数据
     * @param, String searchDateSt, String searchDateEnd
     */
   public List<ReportInvestment> selectByConditionForPage(ReportInvestment report, PageWhere pageWhere,String searchDateSt,String searchDateEnd){
        System.out.println("fe");
       return investmentMapper.selectByConditionForPage(report,pageWhere,searchDateSt,searchDateEnd);
       //,searchDateSt,searchDateEnd
   }
    /**
     * 查询当前的数量
     */
    public int selectPostLoanDetailCount(ReportInvestment reportInvestment){
        return investmentMapper.selectPostLoanDetailCount(reportInvestment);
    }
    /**
     * 根据日期查询当前的数据
     */
    public List<ReportInvestment> betweenDate(String searchDateSt, String searchDateEnd){
        return investmentMapper.betweenDate(searchDateSt,searchDateEnd);
    }
    //根据标的标号查询相关的数据
  public  List<ReportInvestment> getId(String indBpNumber){
        return investmentMapper.getId(indBpNumber);
  }
}
