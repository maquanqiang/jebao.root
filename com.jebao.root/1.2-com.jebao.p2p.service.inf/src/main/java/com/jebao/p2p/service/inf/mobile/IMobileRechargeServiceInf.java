package com.jebao.p2p.service.inf.mobile;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.thirdPay.fuiou.app.model.fastRecharge.FastRechargeResponse;
import com.jebao.thirdPay.fuiou.app.model.personQuickPay.PersonQuickPayResponse;

import java.math.BigDecimal;

/**
 * APP 手机端充值接口
 * Created by Administrator on 2017/1/17.
 */
public interface IMobileRechargeServiceInf {
    /**
     * 快捷充值 form表单提交 跳转
     *
     * @param loginId
     * @param money
     * @return
     */
    ResultInfo personQuickPayByWeb(Long loginId, BigDecimal money);

    /**
     * 快捷充值 返回结果处理
     *
     * @param loginId
     * @param model
     * @return
     */
    ResultInfo personQuickPayByWebComplete(Long loginId, PersonQuickPayResponse model, EnumModel.Platform platform, EnumModel.PlatformType platformType);


    /**
     * 快速充值 form表单提交 跳转
     *
     * @param loginId
     * @param money
     * @return
     */
    ResultInfo fastRechargeByWeb(Long loginId, BigDecimal money);

    /**
     * 快速充值 返回结果处理
     *
     * @param loginId
     * @param model
     * @return
     */
    ResultInfo fastRechargeByWebComplete(Long loginId, FastRechargeResponse model, EnumModel.Platform platform, EnumModel.PlatformType platformType);
}
