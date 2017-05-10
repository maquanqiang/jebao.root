package com.jebao.jebaodb.dao.dao.report;

import com.jebao.jebaodb.dao.mapper.report.InvestmentDetailsMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.report.InvestmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20 0020.
 * 投资明细
 *
 */
@Repository
public class InvestmentDetailsDao {
    //自动装配
    @Autowired
    private InvestmentDetailsMapper investmentDetailsMapper;
    //查询全部投资人到期收益明细的信息
   public List<InvestmentDetails> getAll(){
        return investmentDetailsMapper.getAll();
   }
    public List<InvestmentDetails> selectByConditionForPage(InvestmentDetails investment, PageWhere pageWhere, String searchDateSt, String searchDateEnd){
    return investmentDetailsMapper.selectByConditionForPage(investment,pageWhere,searchDateSt,searchDateEnd);
}
    public int selectPostLoanDetailCount(InvestmentDetails investmentDetails){
        return investmentDetailsMapper.selectPostLoanDetailCount(investmentDetails);
    }
    /**
     * 根据日期查询区间查询当前的信息
     */
    //根据时间区段查询当前的数据,利用对象来调用相应的字段
   public List<InvestmentDetails> getDate(String liCreateTime, String bpRepayTime){
       return investmentDetailsMapper.getDate(liCreateTime,bpRepayTime);
   }
    //根据标的标号查询相关的数据
  public  List<InvestmentDetails> getId(String indBpNumber){
      return investmentDetailsMapper.getId(indBpNumber);
  }
}

