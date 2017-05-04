package com.jebao.erp.service.inf.user;

import com.jebao.jebaodb.entity.user.TbAccountsFunds;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface IAccountsFundsServiceInf {
    TbAccountsFunds findAccountsFundsByloginId(Long loginId);
}
