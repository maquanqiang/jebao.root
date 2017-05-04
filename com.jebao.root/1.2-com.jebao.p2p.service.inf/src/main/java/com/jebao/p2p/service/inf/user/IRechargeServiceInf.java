package com.jebao.p2p.service.inf.user;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.thirdPay.fuiou.model.FuiouRecharge.FuiouRechargeRequest;
import com.jebao.thirdPay.fuiou.model.fastRecharge.FastRechargeResponse;
import com.jebao.thirdPay.fuiou.model.onlineBankRecharge.OnlineBankRechargeResponse;
import com.jebao.thirdPay.fuiou.model.personQuickPay.PersonQuickPayResponse;

import java.math.BigDecimal;

/**
 * 充值接口
 * Created by Administrator on 2016/12/15.
 */
public interface IRechargeServiceInf {
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

    /**
     * 网银充值 form表单提交 跳转
     * @param loginId
     * @param money
     * @return
     */
    ResultInfo onlineBankRechargeByWeb(Long loginId, BigDecimal money);

    /**
     * 网银充值 返回结果处理
     * @param loginId
     * @param model
     * @return
     */
    ResultInfo onlineBankRechargeByWebComplete(Long loginId, OnlineBankRechargeResponse model, EnumModel.Platform platform, EnumModel.PlatformType platformType);

    void fuiouRechargeNotify(Long loginId,FuiouRechargeRequest reqData,String respData);
}
