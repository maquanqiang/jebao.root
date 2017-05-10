package com.jebao.p2p.web.api.mobileControllerApi;

import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.p2p.service.inf.mobile.IMobileRechargeServiceInf;
import com.jebao.p2p.service.inf.mobile.IMobileWithdrawServiceInf;
import com.jebao.p2p.web.api.controllerApi._BaseController;
import com.jebao.p2p.web.api.requestModel.user.RechargeSM;
import com.jebao.p2p.web.api.utils.constants.Constants;
import com.jebao.p2p.web.api.utils.http.HttpUtil;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.CurrentUserContextHolder;
import com.jebao.p2p.web.api.utils.validation.ValidationResult;
import com.jebao.p2p.web.api.utils.validation.ValidationUtil;
import com.jebao.thirdPay.fuiou.app.model.fastRecharge.FastRechargeResponse;
import com.jebao.thirdPay.fuiou.app.model.personQuickPay.PersonQuickPayResponse;
import com.jebao.thirdPay.fuiou.app.model.withdrawDeposit.WithdrawDepositResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URLEncoder;

/**
 * 手机端 充值提现API
 * Created by Administrator on 2017/1/16.
 */
@RestController
@RequestMapping("/mobile/api/")
public class MobileChargeWithdrawController extends _BaseController {
    @Autowired
    private IMobileWithdrawServiceInf withdrawService;
    @Autowired
    private IMobileRechargeServiceInf rechargeService;

    //region 快捷充值
    /**
     * 快捷充值
     * @param form
     * @return
     */
    //非ajax请求
    @RequestMapping(value = "quickPay", method = RequestMethod.POST)
    public String quickPay(RechargeSM form) throws Exception {
        //拒绝ajax请求 //如果是ajax请求响应头会有x-requested-with
        String requestType = request.getHeader("x-requested-with");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return null;
        }

        String title = "充值失败！";
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            goFailedPage(title, resultValidation.getErrorMsg().toString());
            return null;
        }
        //endregion

        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }

        ResultInfo resultInfo = rechargeService.personQuickPayByWeb(currentUser.getId(), form.getMoney());
        if (!resultInfo.getSuccess_is_ok()) {
            String content = resultInfo.getMsg();
            goFailedPage(title, content);
            return null;
        }
        try {
            ResultData<String> resultData = (ResultData<String>) resultInfo;
            String responseHtml = resultData.getData();
            response.getWriter().write(responseHtml);
            return null;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 第三方快捷充值回调地址
     *
     * @param model
     * @return
     */
    @RequestMapping("quickPayNotify")
    public String quickPayNotify(PersonQuickPayResponse model) {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }
        HttpUtil httpUtil = new HttpUtil();
        ResultInfo resultInfo = rechargeService.personQuickPayByWebComplete(currentUser.getId(), model, httpUtil.getPlatform(request), httpUtil.getPlatformType(request));
        if (!resultInfo.getSuccess_is_ok()) {
            String title = "充值失败！";
            String content = resultInfo.getMsg();
            goFailedPage(title, content);
        } else {
            goSuccessPage("充值成功！", "您的本次充值金额为" + new BigDecimal(model.getAmt()).divide(new BigDecimal(100)) + "元");
        }
        return null;
    }
    //endregion

    //region 快速充值

    /**
     * 快速充值
     *
     * @param form
     * @return
     */
    //非ajax请求
    @RequestMapping(value = "fastRecharge", method = RequestMethod.POST)
    public String fastRecharge(RechargeSM form) throws Exception {
        //拒绝ajax请求 //如果是ajax请求响应头会有x-requested-with
        String requestType = request.getHeader("x-requested-with");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return null;
        }

        String title = "充值失败！";
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            goFailedPage(title, resultValidation.getErrorMsg().toString());
            return null;
        }
        //endregion

        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }

        ResultInfo resultInfo = rechargeService.fastRechargeByWeb(currentUser.getId(), form.getMoney());
        if (!resultInfo.getSuccess_is_ok()) {
            String content = resultInfo.getMsg();
            goFailedPage(title, content);
            return null;
        }
        try {
            ResultData<String> resultData = (ResultData<String>) resultInfo;
            String responseHtml = resultData.getData();
            response.getWriter().write(responseHtml);
            return null;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 第三方快速充值回调地址
     *
     * @param model
     * @return
     */
    @RequestMapping("fastRechargeNotify")
    public String fastRechargeNotify(FastRechargeResponse model) {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }
        HttpUtil httpUtil = new HttpUtil();
        ResultInfo resultInfo = rechargeService.fastRechargeByWebComplete(currentUser.getId(), model, httpUtil.getPlatform(request), httpUtil.getPlatformType(request));
        if (!resultInfo.getSuccess_is_ok()) {
            String title = "充值失败！";
            String content = resultInfo.getMsg();
            goFailedPage(title, content);
        } else {
            goSuccessPage("充值成功！", "您的本次充值金额为" + new BigDecimal(model.getAmt()).divide(new BigDecimal(100)) + "元");
        }
        return null;
    }
    //endregion

    //region 提现

    /**
     * 提现
     *
     * @param form
     * @return
     */
    //非ajax请求
    @RequestMapping(value = "withdrawDeposit", method = RequestMethod.POST)
    public String withdrawDeposit(RechargeSM form) throws Exception {
        //拒绝ajax请求 //如果是ajax请求响应头会有x-requested-with
        String requestType = request.getHeader("x-requested-with");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return null;
        }

        String title = "提现失败！";
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            goFailedPage(title, resultValidation.getErrorMsg().toString());
            return null;
        }
        //endregion

        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }

        BigDecimal fee = new BigDecimal(Constants.COMMISSION_CHARGE);
        ResultInfo resultInfo = withdrawService.withdrawDepositByWeb(currentUser.getId(), form.getMoney(), fee);
        if (!resultInfo.getSuccess_is_ok()) {
            String content = resultInfo.getMsg();
            goFailedPage(title, content);
            return null;
        }
        try {
            ResultData<String> resultData = (ResultData<String>) resultInfo;
            String responseHtml = resultData.getData();
            response.getWriter().write(responseHtml);
            return null;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 第三方提现回调地址
     *
     * @param model
     * @return
     */
    @RequestMapping("withdrawDepositNotify")
    public String withdrawDepositNotify(WithdrawDepositResponse model) {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }

        BigDecimal fee = new BigDecimal(Constants.COMMISSION_CHARGE);
        HttpUtil httpUtil = new HttpUtil();
        ResultInfo resultInfo = withdrawService.withdrawDepositByWebComplete(currentUser.getId(), model, fee, httpUtil.getPlatform(request), httpUtil.getPlatformType(request));
        if (!resultInfo.getSuccess_is_ok()) {
            String title = "提现失败！";
            String content = resultInfo.getMsg();
            goFailedPage(title, content);
        } else {
            goSuccessPage("提现成功！", "预计资金于提现申请日的下个工作日到账");
        }
        return null;
    }
    //endregion

    /**
     * 跳转到失败页面
     *
     * @param title   标题
     * @param content 失败信息
     */
    private void goFailedPage(String title, String content) {
        String webOrigin = Constants.JEBAO_WEB_ORIGIN;
        String errorUrl = webOrigin + "mobileNotify/failed";
        String charset = "UTF-8";
        try {
            String queryString = "title=" + URLEncoder.encode(title, charset) + "&content=" + URLEncoder.encode(content, charset);
            String redirectUrl = errorUrl + "?" + queryString;
            response.sendRedirect(redirectUrl);
        } catch (Exception e) {

        }
    }

    /**
     * 跳转到成功页面
     *
     * @param title   标题
     * @param content
     */
    private void goSuccessPage(String title, String content) {
        String webOrigin = Constants.JEBAO_WEB_ORIGIN;
        String errorUrl = webOrigin + "mobileNotify/success";
        String charset = "UTF-8";
        try {
            String queryString = "title=" + URLEncoder.encode(title, charset) + "&content=" + URLEncoder.encode(content, charset);
            String redirectUrl = errorUrl + "?" + queryString;
            response.sendRedirect(redirectUrl);
        } catch (Exception e) {

        }
    }
}