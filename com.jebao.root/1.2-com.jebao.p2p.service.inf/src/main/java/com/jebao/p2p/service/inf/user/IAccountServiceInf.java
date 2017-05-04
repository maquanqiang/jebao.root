package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;

/**
 * Created by Jack on 2016/12/6.
 */
public interface IAccountServiceInf {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param ip 本次登录ip地址
     * @return 登录结果，用户id
     */
    ResultData<Long> login(String username, String password, String ip);

    /**
     * 用户注册
     * @param username 用户名(手机号码)
     * @param password 登录密码
     * @param invitationCode 邀请码
     * @param ip ip地址
     * @return 注册结果，用户id
     */
    ResultData<Long> register(String username, String password, String invitationCode,String ip, EnumModel.Platform platform, EnumModel.PlatformType platformType);

    /**
     *设置新密码
     * @param mobile 手机号码
     * @param password 新密码
     * @return 结果,用户id
     */
    ResultInfo setPassword(String mobile,String password);

}
