package com.jebao.p2p.web.api.controllerApi.user;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.p2p.service.inf.user.*;
import com.jebao.p2p.service.inf.userfund.IUserfundServiceInf;
import com.jebao.p2p.web.api.controllerApi._BaseController;
import com.jebao.p2p.web.api.requestModel.user.FuiouRechargeForm;
import com.jebao.p2p.web.api.requestModel.user.RechargeSM;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultData;
import com.jebao.p2p.web.api.responseModel.base.JsonResultError;
import com.jebao.p2p.web.api.responseModel.base.JsonResultOk;
import com.jebao.p2p.web.api.responseModel.user.UserDetailsVM;
import com.jebao.p2p.web.api.responseModel.user.UserVM;
import com.jebao.p2p.web.api.utils.constants.Constants;
import com.jebao.p2p.web.api.utils.http.HttpUtil;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.CurrentUserContextHolder;
import com.jebao.p2p.web.api.utils.session.LoginSessionUtil;
import com.jebao.p2p.web.api.utils.validation.ValidationResult;
import com.jebao.p2p.web.api.utils.validation.ValidationUtil;
import com.jebao.thirdPay.fuiou.impl.FuiouRechargeServiceImpl;
import com.jebao.thirdPay.fuiou.model.FuiouRecharge.FuiouRechargeRequest;
import com.jebao.thirdPay.fuiou.model.FuiouRecharge.RechargeResult;
import com.jebao.thirdPay.fuiou.model.fastRecharge.FastRechargeResponse;
import com.jebao.thirdPay.fuiou.model.onlineBankRecharge.OnlineBankRechargeResponse;
import com.jebao.thirdPay.fuiou.model.personQuickPay.PersonQuickPayResponse;
import com.jebao.thirdPay.fuiou.model.withdrawDeposit.WithdrawDepositResponse;
//import com.sun.tools.corba.se.idl.InterfaceGen;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
@RestController
@RequestMapping("/api/user/")
public class UserController extends _BaseController {
    @Autowired
    private IUserServiceInf userService;

    @Autowired
    private IWithdrawServiceInf withdrawService;

    @Autowired
    private IRechargeServiceInf rechargeService;

    @Autowired
    private IUserfundServiceInf userfundService;
    @Autowired
    private FuiouRechargeServiceImpl fuiouRechargeService;
    @Autowired
    private IFundsDetailsServiceInf fundsDetailsService;

    @RequestMapping("getUser")
    public JsonResult getUser() {
        CurrentUser user = CurrentUserContextHolder.get();
        if (user == null) {
            return new JsonResultError("用户未登录");
        }
        TbUserDetails userDetailsEntity = userService.getUserDetailsInfo(user.getId());

        String newBankName = null; //更换中的银行卡
        String newBankCardNo = null;
        if (userDetailsEntity.getUdBankCardNoChangeStatus() != null && userDetailsEntity.getUdBankCardNoChangeStatus().equals(EnumModel.BankCardChangeStatus.更换审核中.getValue())) {
            //去富友查询银行卡更换结果
            ResultInfo resultInfo = userfundService.queryChangeCardResult(user.getId());
            if (resultInfo.getSuccess_is_ok()) {
                ResultData<TbUserDetails> resultData = (ResultData<TbUserDetails>) resultInfo;
                userDetailsEntity = resultData.getData();
            } else if (resultInfo.getCode() == 1) {
                //更换的卡在审核中..
                String[] newBankArrays = resultInfo.getMsg().split(",");
                newBankName = newBankArrays[0];
                newBankCardNo = newBankArrays.length > 0 ? newBankArrays[1] : "";
            }
        }
        UserVM userVM = new UserVM(userDetailsEntity, newBankName, newBankCardNo);
        //region 账户余额
        if (userVM.getHasFundAccount()) {
            TbAccountsFunds accountsFunds = userService.getAccountsFundsInfo(user.getId());
            if (accountsFunds == null) {
                userVM.setBalance(new BigDecimal(0));
            } else {
                userVM.setBalance(accountsFunds.getAfBalance());
            }
        }
        //endregion
        return new JsonResultData<>(userVM);
    }

    /**
     * 同步第三方资金帐号信息
     * @return
     */
    @RequestMapping("syncThirdAccount")
    public JsonResult syncThirdAccount(HttpServletRequest request, HttpServletResponse response) {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return new JsonResultError();
        }
        //如果第三方账号存在，则直接返回OK
        if (!StringUtils.isBlank(currentUser.getFundAccount())) {
            return new JsonResultOk();
        }
        ResultInfo result = userfundService.queryUserInfs(currentUser.getId());
        if (result.getSuccess_is_ok()) {//更新用户缓存
            TbUserDetails userDetails = userService.getUserDetailsInfo(currentUser.getId());
            if (userDetails != null && StringUtils.isNotBlank(userDetails.getUdThirdAccount())) {
                currentUser.setFundAccount(userDetails.getUdThirdAccount());
                CurrentUserContextHolder.set(currentUser); // 设置第三方账户
                LoginSessionUtil.Refresh(currentUser, request, response);//刷新
            }
        }
        return new JsonResultOk();
    }

    /**
     * 同步第三方账户线下POS签约状态
     * @return
     */
    @RequestMapping("syncThirdPosStatus")
    public JsonResult syncThirdPosStatus() {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return new JsonResultError();
        }
        userfundService.queryUserInfs(currentUser.getId());
        return new JsonResultOk();
    }

    /**
     * 同步第三方账户余额
     * @return
     */
    @RequestMapping("syncUserBalance")
    public JsonResult syncUserBalance() {
        CurrentUser user = CurrentUserContextHolder.get();
        if (user == null) {
            return new JsonResultError("用户未登录");
        }
        /*TbAccountsFunds accountsFunds = userService.getAccountsFundsInfo(user.getId());
        ResultInfo resultInfo = userfundService.queryUserBalance(user.getId());
        if (resultInfo.getSuccess_is_ok()) {
            ResultData<TbAccountsFunds> resultData = (ResultData<TbAccountsFunds>) resultInfo;
            accountsFunds = resultData.getData();
        }*/
        userfundService.queryUserBalance(user.getId());
        return new JsonResultOk();
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult details() {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return new JsonResultData<>(null);
        }

        TbUserDetails userDetails = userService.getUserDetailsInfo(currentUser.getId());
        if (userDetails == null) {
            return new JsonResultData<>(null);
        }
        TbAccountsFunds accountsFunds = userService.getAccountsFundsInfo(currentUser.getId());
        if (accountsFunds == null) {
            return new JsonResultData<>(null);
        }
        UserDetailsVM viewModel = new UserDetailsVM();
        viewModel.setBalance(accountsFunds.getAfBalance());
        viewModel.setBankCardNo(userDetails.getUdBankCardNo());
        viewModel.setBankParentBankName(userDetails.getUdBankParentBankName());
        viewModel.setInvitationCode(userDetails.getUdInvitationCode());
        viewModel.setNickName(userDetails.getUdNickName());
        viewModel.setThirdAccount(userDetails.getUdThirdAccount());
        viewModel.setTrueName(userDetails.getUdTrueName());
        return new JsonResultData<>(viewModel);
    }

    //region 快捷充值

    /**
     * 快捷充值
     *
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
        String backUrl = "/user/chargewithdraw?typeId=1"; // web页面内的回跳地址，可以是相对路径
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            goFailedPage(title, resultValidation.getErrorMsg().toString(), backUrl);
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
            goFailedPage(title, content, backUrl);
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
            String backUrl = "/user/chargewithdraw?typeId=1"; // web页面内的回跳地址，可以是相对路径
            goFailedPage(title, content, backUrl);
        } else {
            goSuccessPage("充值成功！", "您的本次充值金额为" + new BigDecimal(model.getAmt()).divide(new BigDecimal(100)) + "元", "/user/index", "查看我的账户", "/html/product", "我要投资");
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
        String backUrl = "/user/chargewithdraw?typeId=1"; // web页面内的回跳地址，可以是相对路径
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            goFailedPage(title, resultValidation.getErrorMsg().toString(), backUrl);
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
            goFailedPage(title, content, backUrl);
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
            String backUrl = "/user/chargewithdraw?typeId=1"; // web页面内的回跳地址，可以是相对路径
            goFailedPage(title, content, backUrl);
        } else {
            goSuccessPage("充值成功！", "您的本次充值金额为" + new BigDecimal(model.getAmt()).divide(new BigDecimal(100)) + "元", "/user/index", "查看我的账户", "/html/product", "我要投资");
        }
        return null;
    }
    //endregion

    //region 网银充值

    /**
     * 网银充值
     *
     * @param form
     * @return
     */
    //非ajax请求
    @RequestMapping(value = "onlineBankRecharge", method = RequestMethod.POST)
    public String onlineBankRecharge(RechargeSM form) throws Exception {
        //拒绝ajax请求 //如果是ajax请求响应头会有x-requested-with
        String requestType = request.getHeader("x-requested-with");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return null;
        }

        String title = "充值失败！";
        String backUrl = "/user/chargewithdraw?typeId=1"; // web页面内的回跳地址，可以是相对路径
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            goFailedPage(title, resultValidation.getErrorMsg().toString(), backUrl);
            return null;
        }
        //endregion

        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }

        ResultInfo resultInfo = rechargeService.onlineBankRechargeByWeb(currentUser.getId(), form.getMoney());
        if (!resultInfo.getSuccess_is_ok()) {
            String content = resultInfo.getMsg();
            goFailedPage(title, content, backUrl);
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
     * 第三方网银充值回调地址
     *
     * @param model
     * @return
     */
    @RequestMapping("onlineBankRechargeNotify")
    public String onlineBankRechargeNotify(OnlineBankRechargeResponse model) {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return null;
        }
        HttpUtil httpUtil = new HttpUtil();
        ResultInfo resultInfo = rechargeService.onlineBankRechargeByWebComplete(currentUser.getId(), model, httpUtil.getPlatform(request), httpUtil.getPlatformType(request));
        if (!resultInfo.getSuccess_is_ok()) {
            String title = "充值失败！";
            String content = resultInfo.getMsg();
            String backUrl = "/user/chargewithdraw?typeId=1"; // web页面内的回跳地址，可以是相对路径
            goFailedPage(title, content, backUrl);
        } else {
            goSuccessPage("充值成功！", "您的本次充值金额为" + new BigDecimal(model.getAmt()).divide(new BigDecimal(100)) + "元", "/user/index", "查看我的账户", "/html/product", "我要投资");
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
        String backUrl = "/user/chargewithdraw?typeId=2"; // web页面内的回跳地址，可以是相对路径
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            goFailedPage(title, resultValidation.getErrorMsg().toString(), backUrl);
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
            goFailedPage(title, content, backUrl);
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
            String backUrl = "/user/chargewithdraw?typeId=2"; // web页面内的回跳地址，可以是相对路径
            goFailedPage(title, content, backUrl);
        } else {
            goSuccessPage("提现成功！", "预计资金于提现申请日的下个工作日到账", "/user/index", "查看我的账户", "/html/product", "我要投资");
        }
        return null;
    }
    //endregion

    /**
     * 跳转到失败页面
     *
     * @param title   标题
     * @param content 失败信息
     * @param backUrl 返回按钮链接
     */
    private void goFailedPage(String title, String content, String backUrl) {
        String webOrigin = Constants.JEBAO_WEB_ORIGIN;
        String errorUrl = webOrigin + "notify/failed";
        String charset = "UTF-8";
        try {
            String queryString = "title=" + URLEncoder.encode(title, charset) + "&content=" + URLEncoder.encode(content, charset) + "&backUrl=" + URLEncoder.encode(backUrl, charset);
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
     * @param backUrl
     * @param btnText
     */
    private void goSuccessPage(String title, String content, String backUrl, String btnText, String investUrl, String btnInvestText) {
        String webOrigin = Constants.JEBAO_WEB_ORIGIN;
        String errorUrl = webOrigin + "notify/success";
        String charset = "UTF-8";
        try {
            String queryString = "title=" + URLEncoder.encode(title, charset) + "&content=" + URLEncoder.encode(content, charset) + "&backUrl=" + URLEncoder.encode(backUrl, charset) + "&btnText=" + URLEncoder.encode(btnText, charset);
            if(StringUtils.isNotBlank(investUrl) && StringUtils.isNotBlank(btnInvestText)){
                queryString += "&investUrl=" + URLEncoder.encode(investUrl, charset) + "&btnInvestText=" + URLEncoder.encode(btnInvestText, charset);
            }
            String redirectUrl = errorUrl + "?" + queryString;
            response.sendRedirect(redirectUrl);
        } catch (Exception e) {

        }
    }


    @RequestMapping("fuiouRechargeNotify")
    private void fuiouRechargeNotify(FuiouRechargeForm form) {
        FuiouRechargeRequest reqData = form.toEntity(form);
        System.out.println(reqData.requestSignPlain());
        RechargeResult rechargeResult = fuiouRechargeService.responseRecharge(reqData);
        if(rechargeResult.isFlag()){

            TbLoginInfo userLoginInfo = userService.getUserLoginInfo(form.getMobile_no());
            TbFundsDetails details = new TbFundsDetails();
            details.setFdLoginId(userLoginInfo.getLiId());
            details.setFdSerialNumber(form.getMchnt_txn_ssn());
            //查询台账记录 并查看是否需要更新
            List<TbFundsDetails> tbFundsDetailses = fundsDetailsService.selectByParamsForPage(details, null);

            if(tbFundsDetailses==null||tbFundsDetailses.size()==0){
                rechargeService.fuiouRechargeNotify(userLoginInfo.getLiId(),reqData,rechargeResult.getXmlResp());
            }else{
                TbFundsDetails tbFundsDetails = tbFundsDetailses.get(0);
                Integer status = tbFundsDetails.getFdSerialStatus();
                if(status==0){
                    tbFundsDetails.setFdSerialStatus(1);
                    fundsDetailsService.update(tbFundsDetails);
                }
            }
        }

        try {
            response.getWriter().write(rechargeResult.getXmlResp());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
