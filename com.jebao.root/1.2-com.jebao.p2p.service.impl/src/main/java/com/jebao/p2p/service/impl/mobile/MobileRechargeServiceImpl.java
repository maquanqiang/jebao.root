package com.jebao.p2p.service.impl.mobile;

import com.jebao.common.utils.fastjson.FastJsonUtil;
import com.jebao.jebaodb.dao.dao.loanmanage.TbThirdInterfaceLogDao;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.p2p.service.inf.mobile.IMobileRechargeServiceInf;
import com.jebao.p2p.service.inf.user.IAccountsFundsServiceInf;
import com.jebao.p2p.service.inf.user.IFundsDetailsServiceInf;
import com.jebao.p2p.service.inf.user.IUserServiceInf;
import com.jebao.thirdPay.fuiou.app.impl.AppFastRechargeServiceImpl;
import com.jebao.thirdPay.fuiou.app.impl.AppPersonQuickPayServiceImpl;
import com.jebao.thirdPay.fuiou.app.model.fastRecharge.FastRechargeRequest;
import com.jebao.thirdPay.fuiou.app.model.fastRecharge.FastRechargeResponse;
import com.jebao.thirdPay.fuiou.app.model.personQuickPay.PersonQuickPayRequest;
import com.jebao.thirdPay.fuiou.app.model.personQuickPay.PersonQuickPayResponse;
import com.jebao.thirdPay.fuiou.constants.FuiouConfig;
import com.jebao.thirdPay.fuiou.util.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class MobileRechargeServiceImpl implements IMobileRechargeServiceInf {
    @Autowired
    private IUserServiceInf userService;
    @Autowired
    private IAccountsFundsServiceInf accountsFundsService;
    @Autowired
    private IFundsDetailsServiceInf fundsDetailsService;
    @Autowired
    private TbThirdInterfaceLogDao thirdInterfaceLogDao;
    @Autowired
    private AppPersonQuickPayServiceImpl appPersonQuickPayService;
    @Autowired
    private AppFastRechargeServiceImpl appFastRechargeService;

    /**
     * 快捷充值 form表单提交 跳转
     */
    @Override
    public ResultInfo personQuickPayByWeb(Long loginId, BigDecimal money) {
        TbUserDetails userDetails = userService.getUserDetailsInfo(loginId);
        if (userDetails == null || StringUtils.isBlank(userDetails.getUdThirdAccount())) {
            return new ResultInfo(false, "您尚未开通第三方资金账户");
        }
        String amt = money.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString();
        PersonQuickPayRequest reqData = new PersonQuickPayRequest();
        reqData.setLogin_id(userDetails.getUdThirdAccount());
        reqData.setAmt(amt);
        reqData.setBack_notify_url("");

        String html = appPersonQuickPayService.post(reqData);
        if (html != null && html.length() > 0) {
            //region 提交到富有，记录接口日志
            TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
            thirdInterfaceLog.setTilType(29); // 接口编号
            thirdInterfaceLog.setTilSerialNumber(reqData.getMchnt_txn_ssn());
            String jsonText = FastJsonUtil.serialize(reqData);
            thirdInterfaceLog.setTilReqText(jsonText);
            thirdInterfaceLog.setTilRespText("form跳转请求，接口响应内容查看后续一条记录");
            thirdInterfaceLog.setTilCreateTime(new Date());
            thirdInterfaceLogDao.insert(thirdInterfaceLog);
            //endregion

            return new ResultData<String>(true, html, "提交第三方");
        }
        return new ResultInfo(false, "form提交失败");
    }

    /**
     * 快捷充值 返回结果处理
     *
     * @param loginId
     * @param model
     * @return
     */
    @Override
    public ResultInfo personQuickPayByWebComplete(Long loginId, PersonQuickPayResponse model, EnumModel.Platform platform, EnumModel.PlatformType platformType) {
        if (model != null) {
            int count = fundsDetailsService.selectBySerialNumberForPageCount(loginId, model.getMchnt_txn_ssn());
            if (count > 0) {
                if (FuiouConfig.Success_Code.equals(model.getResp_code())) {
                    return new ResultInfo(true, "充值成功");
                } else {
                    return new ResultInfo(false, "充值失败");
                }
            }
        }

        //获取变更前账户资金信息
        TbAccountsFunds afEntity = userService.getAccountsFundsInfo(loginId);
        BigDecimal balance = afEntity.getAfBalance();

        //todo 添加资金收支明细
        TbFundsDetails fundsDetails = new TbFundsDetails();
        fundsDetails.setFdBalanceBefore(balance);
        fundsDetails.setFdBalanceAfter(balance);
        fundsDetails.setFdLoginId(loginId);
        fundsDetails.setFdBalanceStatus(EnumModel.FdBalanceStatus.收入.getValue());
        fundsDetails.setFdCommissionCharge(new BigDecimal(0));//手续费
        fundsDetails.setFdCreateTime(new Date());
        fundsDetails.setFdSerialTypeId(EnumModel.SerialType.充值.getValue());
        fundsDetails.setFdSerialTypeName(EnumModel.SerialType.充值.name());
        fundsDetails.setFdThirdAccount(afEntity.getAfThirdAccount());
        fundsDetails.setFdIsDel(EnumModel.IsDel.有效.getValue());
        fundsDetails.setFdPlatform(platform.getValue());
        fundsDetails.setFdPlatformType(platformType.getValue());
        fundsDetails.setFdChannel(0);
        fundsDetails.setFdChannelType(0);
        if (model == null) {
            //更新资金收支明细状态为失败
            fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.失败.getValue());
            fundsDetails.setFdSerialTime(new Date());
            fundsDetailsService.insert(fundsDetails);
            return new ResultInfo(false, "第三方返回异常");
        }

        //region 富有返回成功，记录接口日志
        TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
        thirdInterfaceLog.setTilType(29); // 接口编号
        thirdInterfaceLog.setTilSerialNumber(model.getMchnt_txn_ssn());
        thirdInterfaceLog.setTilReturnCode(model.getResp_code());
        String jsonText = FastJsonUtil.serialize(model);
        thirdInterfaceLog.setTilReqText("form跳转请求，接口请求内容查看上一条记录");
        thirdInterfaceLog.setTilRespText(jsonText);
        thirdInterfaceLog.setTilCreateTime(new Date());
        thirdInterfaceLogDao.insert(thirdInterfaceLog);
        //endregion

        BigDecimal money = new BigDecimal(model.getAmt()).divide(new BigDecimal(100));
        fundsDetails.setFdSerialAmount(money);
        fundsDetails.setFdSerialNumber(model.getMchnt_txn_ssn());//流水号
        fundsDetails.setFdThirdAccount(model.getLogin_id());

        if (!FuiouConfig.Success_Code.equals(model.getResp_code())) {
            String responseMessage = model.getResp_desc();
            if (StringUtils.isBlank(responseMessage)) {
                responseMessage = "第三方返回异常";
            }
            fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.失败.getValue());
            fundsDetails.setFdSerialTime(new Date());
            fundsDetailsService.insert(fundsDetails);
            return new ResultInfo(false, responseMessage);
        }

        String signature = model.requestSignPlain();
        boolean isValid = SecurityUtils.verifySign(signature, model.getSignature());
        if (!isValid) {
            //更新资金收支明细状态为失败
            fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.失败.getValue());
            fundsDetails.setFdSerialTime(new Date());
            fundsDetailsService.insert(fundsDetails);
            return new ResultInfo(false, "操作异常，校验失败");
        }

        BigDecimal balance_new = balance.add(new BigDecimal(model.getAmt()).divide(new BigDecimal(100)));

        //更新资金收支明细状态为成功
        fundsDetails.setFdBalanceAfter(balance_new);
        fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.成功.getValue());
        fundsDetails.setFdSerialTime(new Date());
        fundsDetailsService.insert(fundsDetails);

        //todo 修改账户资金信息
        TbAccountsFunds accountsFunds = new TbAccountsFunds();
        accountsFunds.setAfThirdAccount(model.getLogin_id());
        accountsFunds.setAfBalance(balance_new);
        accountsFunds.setAfUpdateTime(new Date());
        accountsFundsService.update(accountsFunds);

        return new ResultInfo(true, "充值成功");
    }

    /**
     * 快速充值 form表单提交 跳转
     */
    @Override
    public ResultInfo fastRechargeByWeb(Long loginId, BigDecimal money) {
        TbUserDetails userDetails = userService.getUserDetailsInfo(loginId);
        if (userDetails == null || StringUtils.isBlank(userDetails.getUdThirdAccount())) {
            return new ResultInfo(false, "您尚未开通第三方资金账户");
        }
        String amt = money.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString();
        FastRechargeRequest reqData = new FastRechargeRequest();
        reqData.setLogin_id(userDetails.getUdThirdAccount());
        reqData.setAmt(amt);
        reqData.setBack_notify_url("");
        String html = appFastRechargeService.post(reqData);
        if (html != null && html.length() > 0) {
            //region 提交到富有，记录接口日志
            TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
            thirdInterfaceLog.setTilType(28); // 接口编号
            thirdInterfaceLog.setTilSerialNumber(reqData.getMchnt_txn_ssn());
            String jsonText = FastJsonUtil.serialize(reqData);
            thirdInterfaceLog.setTilReqText(jsonText);
            thirdInterfaceLog.setTilRespText("form跳转请求，接口响应内容查看后续一条记录");
            thirdInterfaceLog.setTilCreateTime(new Date());
            thirdInterfaceLogDao.insert(thirdInterfaceLog);
            //endregion

            return new ResultData<String>(true, html, "提交第三方");
        }
        return new ResultInfo(false, "form提交失败");
    }

    /**
     * 快速充值 返回结果处理
     *
     * @param loginId
     * @param model
     * @return
     */
    @Override
    public ResultInfo fastRechargeByWebComplete(Long loginId, FastRechargeResponse model, EnumModel.Platform platform, EnumModel.PlatformType platformType) {
        if (model != null) {
            int count = fundsDetailsService.selectBySerialNumberForPageCount(loginId, model.getMchnt_txn_ssn());
            if (count > 0) {
                if (FuiouConfig.Success_Code.equals(model.getResp_code())) {
                    return new ResultInfo(true, "充值成功");
                } else {
                    return new ResultInfo(false, "充值失败");
                }
            }
        }

        //获取变更前账户资金信息
        TbAccountsFunds afEntity = userService.getAccountsFundsInfo(loginId);
        BigDecimal balance = afEntity.getAfBalance();

        //todo 添加资金收支明细
        TbFundsDetails fundsDetails = new TbFundsDetails();
        fundsDetails.setFdBalanceBefore(balance);
        fundsDetails.setFdBalanceAfter(balance);
        fundsDetails.setFdLoginId(loginId);
        fundsDetails.setFdBalanceStatus(EnumModel.FdBalanceStatus.收入.getValue());
        fundsDetails.setFdCommissionCharge(new BigDecimal(0));//手续费
        fundsDetails.setFdCreateTime(new Date());
        fundsDetails.setFdSerialTypeId(EnumModel.SerialType.充值.getValue());
        fundsDetails.setFdSerialTypeName(EnumModel.SerialType.充值.name());
        fundsDetails.setFdThirdAccount(afEntity.getAfThirdAccount());
        fundsDetails.setFdIsDel(EnumModel.IsDel.有效.getValue());
        fundsDetails.setFdPlatform(platform.getValue());
        fundsDetails.setFdPlatformType(platformType.getValue());
        fundsDetails.setFdChannel(0);
        fundsDetails.setFdChannelType(0);
        if (model == null) {
            //更新资金收支明细状态为失败
            fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.失败.getValue());
            fundsDetails.setFdSerialTime(new Date());
            fundsDetailsService.insert(fundsDetails);
            return new ResultInfo(false, "第三方返回异常");
        }

        //region 富有返回数据，记录接口日志
        TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
        thirdInterfaceLog.setTilType(28); // 接口编号
        thirdInterfaceLog.setTilSerialNumber(model.getMchnt_txn_ssn());
        thirdInterfaceLog.setTilReturnCode(model.getResp_code());
        String jsonText = FastJsonUtil.serialize(model);
        thirdInterfaceLog.setTilReqText("form跳转请求，接口请求内容查看上一条记录");
        thirdInterfaceLog.setTilRespText(jsonText);
        thirdInterfaceLog.setTilCreateTime(new Date());
        thirdInterfaceLogDao.insert(thirdInterfaceLog);
        //endregion

        BigDecimal money = new BigDecimal(model.getAmt()).divide(new BigDecimal(100));
        fundsDetails.setFdSerialAmount(money);
        fundsDetails.setFdSerialNumber(model.getMchnt_txn_ssn());//流水号
        fundsDetails.setFdThirdAccount(model.getLogin_id());

        if (!FuiouConfig.Success_Code.equals(model.getResp_code())) {
            String responseMessage = model.getResp_desc();
            if (StringUtils.isBlank(responseMessage)) {
                responseMessage = "第三方返回异常";
            }
            fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.失败.getValue());
            fundsDetails.setFdSerialTime(new Date());
            fundsDetailsService.insert(fundsDetails);
            return new ResultInfo(false, responseMessage);
        }

        String signature = model.requestSignPlain();
        boolean isValid = SecurityUtils.verifySign(signature, model.getSignature());
        if (!isValid) {
            //更新资金收支明细状态为失败
            fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.失败.getValue());
            fundsDetails.setFdSerialTime(new Date());
            fundsDetailsService.insert(fundsDetails);
            return new ResultInfo(false, "操作异常，校验失败");
        }

        BigDecimal balance_new = balance.add(new BigDecimal(model.getAmt()).divide(new BigDecimal(100)));

        //更新资金收支明细状态为成功
        fundsDetails.setFdBalanceAfter(balance_new);
        fundsDetails.setFdSerialStatus(EnumModel.FdSerialStatus.成功.getValue());
        fundsDetails.setFdSerialTime(new Date());
        fundsDetailsService.insert(fundsDetails);

        //todo 修改账户资金信息
        TbAccountsFunds accountsFunds = new TbAccountsFunds();
        accountsFunds.setAfThirdAccount(model.getLogin_id());
        accountsFunds.setAfBalance(balance_new);
        accountsFunds.setAfUpdateTime(new Date());
        accountsFundsService.update(accountsFunds);

        return new ResultInfo(true, "充值成功");
    }
}