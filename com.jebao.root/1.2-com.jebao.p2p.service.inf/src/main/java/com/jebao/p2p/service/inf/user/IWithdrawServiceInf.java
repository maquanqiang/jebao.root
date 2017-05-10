package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.thirdPay.fuiou.model.withdrawDeposit.WithdrawDepositResponse;

import java.math.BigDecimal;

/**
 * 提现接口
 * Created by Administrator on 2016/12/15.
 */
public interface IWithdrawServiceInf {
    /**
     * 提现接口 form表单提交 跳转
     *
     * @param loginId
     * @param money
     * @return
     */
    ResultInfo withdrawDepositByWeb(Long loginId, BigDecimal money, BigDecimal fee);

    /**
     * 提现接口 返回结果处理
     *
     * @param loginId
     * @param model
     * @return
     */
    ResultInfo withdrawDepositByWebComplete(Long loginId, WithdrawDepositResponse model, BigDecimal fee, EnumModel.Platform platform, EnumModel.PlatformType platformType);
}
