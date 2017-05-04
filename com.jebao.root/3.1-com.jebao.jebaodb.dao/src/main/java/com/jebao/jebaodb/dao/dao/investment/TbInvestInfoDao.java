package com.jebao.jebaodb.dao.dao.investment;

import com.jebao.jebaodb.dao.mapper.investment.TbInvestInfoMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.InvestBase;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
@Repository
public class TbInvestInfoDao {
    @Autowired
    private TbInvestInfoMapper mapper;

    public int insert(TbInvestInfo record) {
        return mapper.insert(record);
    }

    public int insertSelective(TbInvestInfo record) {
        return mapper.insertSelective(record);
    }

    public TbInvestInfo selectByPrimaryKey(Long bpId) {
        return mapper.selectByPrimaryKey(bpId);
    }

    public int updateByPrimaryKeySelective(TbInvestInfo record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TbInvestInfo record) {
        return mapper.updateByPrimaryKey(record);
    }

    public List<TbInvestInfo> selectForPage(PageWhere pageWhere) {
        return mapper.selectForPage(pageWhere);
    }

    /**
     * 系统条件分页排序查询
     *
     * @param record
     * @param pageWhere
     * @return
     */
    public List<TbInvestInfo> selectByConditionForPage(@Param("record") TbInvestInfo record, @Param("pageWhere") PageWhere pageWhere) {
        return mapper.selectByConditionForPage(record, pageWhere);
    }

    /**
     * 系统条件查询统计
     *
     * @param record
     * @return
     */
    public int selectByConditionCount(@Param("record") TbInvestInfo record) {
        return mapper.selectByConditionCount(record);
    }


    public List<TbInvestInfo> selectBybpId(@Param("record") TbInvestInfo record, @Param("pageWhere") PageWhere pageWhere) {
        return mapper.selectByBpId(record, pageWhere);
    }

    public BigDecimal investTotal(Long bpId){
        return mapper.investTotal(bpId);
    }

    @Transactional
    public int insertForTransactional(TbInvestInfo record) {
        return mapper.insert(record);
    }

    /**==================================================投资记录==================================================**/

    /**
     * 投资中项目
     *
     * @param record
     * @param pageWhere
     * @return
     */
    public List<InvestBase> selectInvestBaseByLoginId(@Param("record") TbInvestInfo record, @Param("pageWhere") PageWhere pageWhere) {
        return mapper.selectInvestBaseByLoginId(record, pageWhere);
    }

    /**
     * 投资中项目总数
     *
     * @param record
     * @return
     */
    public int selectInvestBaseByLoginIdForPageCount(@Param("record") TbInvestInfo record) {
        return mapper.selectInvestBaseByLoginIdForPageCount(record);
    }

    /**
     * 统计账户冻结金额
     *
     * @param iiLoginId
     * @return
     */
    public BigDecimal totalFreezeMoneyByLoginId(Long iiLoginId) {
        return mapper.totalFreezeMoneyByLoginId(iiLoginId);
    }
}
