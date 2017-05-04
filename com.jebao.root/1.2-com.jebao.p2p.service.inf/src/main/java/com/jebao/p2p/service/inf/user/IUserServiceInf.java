package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.user.TbUserDetails;

/**
 * Created by Jack on 2016/12/12.
 */
public interface IUserServiceInf {
    /**
     * 获取用户登录信息
     * @param mobile 手机号码
     * @return 用户登录信息
     */
    TbLoginInfo getUserLoginInfo(String mobile);

    /**
     * 获取用户详细信息
     * @param mobile 手机号码
     * @return 用户详细信息
     */
    TbUserDetails getUserDetailsInfo(String mobile);

    /**
     * 获取用户详细信息
     * @param userId 用户id，loginId
     * @return 用户详细信息
     */
    TbUserDetails getUserDetailsInfo(long userId);

    /**
     * 获取账户资金信息
     * @param userId
     * @return
     */
    TbAccountsFunds getAccountsFundsInfo(long userId);
}
