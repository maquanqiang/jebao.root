package com.jebao.erp.service.impl.investment;

import com.jebao.erp.service.inf.investment.ILoanerRepaymentDetailServiceInf;
import com.jebao.jebaodb.dao.dao.investment.TbLoanerRepaymentDetailDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbLoanerRepaymentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2016/12/2.
 */
@Service
public class LoanerRepaymentDetailServiceImpl implements ILoanerRepaymentDetailServiceInf {

    @Autowired
    private TbLoanerRepaymentDetailDao loanerRepaymentDetailDao;

    @Override
    public int save(TbLoanerRepaymentDetail record) {


        //endregion
        Long id = record.getLrdId();
        int result = 0;
        if (id == null) {
            result = addInvestInfo(record);//新增
        } else {
            result = updateByBidIdSelective(record);//修改
        }

        return result;
    }

    public int addInvestInfo(TbLoanerRepaymentDetail record){
        int result = loanerRepaymentDetailDao.insert(record);
        return result;
    }

    @Override
    public TbLoanerRepaymentDetail selectById(Long id) {
        TbLoanerRepaymentDetail tbInvestInfo = loanerRepaymentDetailDao.selectByPrimaryKey(id);
        return tbInvestInfo;
    }

    @Override
    public List<TbLoanerRepaymentDetail> selectByConditionForPage(TbLoanerRepaymentDetail record, PageWhere pageWhere) {
        List<TbLoanerRepaymentDetail> tbInvestInfos = loanerRepaymentDetailDao.selectByConditionForPage(record, pageWhere);
        return tbInvestInfos;
    }

    @Override
    public int selectByConditionCount(TbLoanerRepaymentDetail record) {
        int count = loanerRepaymentDetailDao.selectByConditionCount(record);
        return count;
    }


    public int updateByBidIdSelective(TbLoanerRepaymentDetail record) {
        int result = loanerRepaymentDetailDao.updateByPrimaryKeySelective(record);
        return result;
    }
}
