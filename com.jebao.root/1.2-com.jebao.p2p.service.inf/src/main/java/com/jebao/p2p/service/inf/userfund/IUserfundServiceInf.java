package com.jebao.p2p.service.inf.userfund;

import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.thirdPay.fuiou.model.changeCard.ChangeCardResponse;
import com.jebao.thirdPay.fuiou.model.reg.RegRequest;
import com.jebao.thirdPay.fuiou.model.webReg.WebRegResponse;

/**
 * Created by Jack on 2016/12/14.
 */
public interface IUserfundServiceInf {

    /**
     * 第三方资金账户开通，http 直连
     */
    ResultInfo register(RegRequest reqData,long userId);
    /**
     * 第三方资金账户开户 form跳转
     * @param userId 金额宝用户id
     * @param realName 真实姓名
     * @param idCard 身份证号码
     * @return 开户结果
     */
    ResultInfo registerByWeb(String realName, String idCard,long userId);

    /**
     * 第三方资金账户开户完成
     */
    ResultInfo registerByWebComplete(WebRegResponse model,long userId);

    /**
     * 用户更换银行卡
     */
    ResultInfo changeCardByWeb(long userId);
    /**
     * 用户更换银行卡完成
     */
    ResultInfo changeCardByWebComplete(ChangeCardResponse model,long userId);

    /**
     * 查询更换银行卡请求的审核结果
     */
    ResultInfo queryChangeCardResult(long userId);

    /**
     * 同步（富有）用户信息
     * @param userId
     * @return
     */
    ResultInfo queryUserInfs(long userId);

    /**
     * 同步（富有）用户可用余额
     * @param userId
     * @return
     */
    ResultInfo queryUserBalance(long userId);
}
