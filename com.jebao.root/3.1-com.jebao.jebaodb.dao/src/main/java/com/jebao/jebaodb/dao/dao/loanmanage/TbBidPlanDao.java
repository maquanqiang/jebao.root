package com.jebao.jebaodb.dao.dao.loanmanage;

import com.jebao.jebaodb.dao.mapper.loanmanage.TbBidPlanMapper;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanExtSM;
import com.jebao.jebaodb.entity.product.ProductSM;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.LoanTotal;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/20.
 */
@Repository
public class TbBidPlanDao {
    @Autowired
    private TbBidPlanMapper tbBidPlanMapper;

    public int insert(TbBidPlan record) {
        return tbBidPlanMapper.insert(record);
    }
    public int insertSelective(TbBidPlan record) {
        return tbBidPlanMapper.insertSelective(record);
    }
    public TbBidPlan selectByPrimaryKey(Long bpId)
    {
        return tbBidPlanMapper.selectByPrimaryKey(bpId);
    }
    public int updateByPrimaryKeySelective(TbBidPlan record)
    {
        return tbBidPlanMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbBidPlan record)
    {
        return tbBidPlanMapper.updateByPrimaryKey(record);
    }
    public List<TbBidPlan> selectForPage(PageWhere pageWhere)
    {
        return tbBidPlanMapper.selectForPage(pageWhere);
    }

    /**
     * 系统条件分页排序查询
     * @param record
     * @param pageWhere
     * @return
     */
    public List<TbBidPlan> selectByConditionForPage(@Param("record")TbBidPlan record, @Param("pageWhere")PageWhere pageWhere){
        return tbBidPlanMapper.selectByConditionForPage(record, pageWhere);
    }

    /**
     * 系统条件查询统计
     * @param record
     * @return
     */
    public int selectByConditionCount(@Param("record")TbBidPlan record){
        return tbBidPlanMapper.selectByConditionCount(record);
    }

    /**
     * 自定义条件
     * @param record
     * @param pageWhere
     * @return
     */
    public List<TbBidPlan> selectBySelfConditionForPage(@Param("record")BidPlanSM record, @Param("pageWhere")PageWhere pageWhere){
        return tbBidPlanMapper.selectBySelfConditionForPage(record, pageWhere);
    }

    /**
     * 自定义条件查询统计
     * @param record
     * @return
     */
    public int selectBySelfConditionCount(@Param("record")BidPlanSM record){
        return tbBidPlanMapper.selectBySelfConditionCount(record);
    }

    /**
     * p2p条件查询列表
     * @param record
     * @param pageWhere
     * @return
     */
    public List<TbBidPlan> selectP2PList(@Param("record") ProductSM record, @Param("pageWhere") PageWhere pageWhere){
        return tbBidPlanMapper.selectP2PList(record, pageWhere);
    }

    /**
     * p2p条件查询统计
     * @param record
     * @return
     */
    public int selectP2PListCount(@Param("record") ProductSM record){
        return tbBidPlanMapper.selectP2PListCount(record);
    }

    /**
     * 投标改变剩余金额
     * @param map
     * @return
     */
    public int investBid(Map<String, Object> map){

        return tbBidPlanMapper.investBid(map);
    }

    /**
     * 修改满标状态
     * @return
     */
    public int fullBid(Map<String, Object> map){
        return tbBidPlanMapper.fullBid(map);
    }

    /**
     * 增加剩余金额
     * @param map
     * @return
     */
    public int addSurplus(Map<String, Object> map)
    {
        return tbBidPlanMapper.addSurplus(map);
    }

    /**
     * 改变标的过期状态
     * @return
     */
    public int timeoutBid(Date nowDate){
        return tbBidPlanMapper.timeoutBid(nowDate);
    }
    @Transactional
    public int insertForTransactional(TbBidPlan record) {
        return tbBidPlanMapper.insert(record);
    }

    public List<TbBidPlan> selectBpNumberList(String bpNumberStr){
        return tbBidPlanMapper.selectBpNumberList(bpNumberStr);
    }

    /* ==================================================借款人相关借款统计查询==================================================*/
    /**
     * 统计借款总金额，数量
     * @param loanerId
     * @return
     */
    public LoanTotal statisticsByLoanerId(Long loanerId){
        return tbBidPlanMapper.statisticsByLoanerId(loanerId);
    }

    /**
     * 批量查询统计借款人借款金额，数量
     * @param loanerIds
     * @return
     */
    public List<LoanTotal> selectLoanTotalByLoanerIds(@Param("ids")List<Long> loanerIds){
        return tbBidPlanMapper.selectLoanTotalByLoanerIds(loanerIds);
    }

    /**
     * 借款人相关借款记录列表
     * @param model
     * @return
     */
    public List<TbBidPlan> selectByLoanerIdForPage(BidPlanExtSM model){
        return tbBidPlanMapper.selectByLoanerIdForPage(model);
    }

    public int selectByLoanerIdForPageCount(BidPlanExtSM model){
        return tbBidPlanMapper.selectByLoanerIdForPageCount(model);
    }

    public List<TbBidPlan> selectCacheData(List ids){
        return tbBidPlanMapper.selectCacheData(ids);
    }

    /**
     * 查询未还金额
     * @param dateSearType     1:到期金额   2 应还金额
     * @return
     */
    public BigDecimal selectIncomeCount(Integer dateSearType){return tbBidPlanMapper.selectIncomeCount(dateSearType); }
}
