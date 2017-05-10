package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.user.TbAccountsFunds;

/**
 * 账户资金信息接口
 * Created by Administrator on 2016/12/14.
 */
public interface IAccountsFundsServiceInf {
    int insert(TbAccountsFunds record);

    int update(TbAccountsFunds record);

    TbAccountsFunds selectByLoginId(Long loginId);
}
