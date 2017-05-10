package com.jebao.erp.service.inf.investment;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbLoanerRepaymentDetail;

import java.util.List;

/**
 * Created by Lee on 2016/12/5.
 */
public interface ILoanerRepaymentDetailServiceInf {

    int save(TbLoanerRepaymentDetail record);

    TbLoanerRepaymentDetail selectById(Long id);

    List<TbLoanerRepaymentDetail> selectByConditionForPage(TbLoanerRepaymentDetail record, PageWhere pageWhere);

    int selectByConditionCount(TbLoanerRepaymentDetail record);

}
