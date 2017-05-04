package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;

import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/16.
 */
public interface ILoanManageServiceInf {

    public ResultInfo repay(TbBidPlan bidPlan, TbAccountsFunds accountsFunds, Integer period, BigDecimal repayMoney);
}
