package com.jebao.p2p.web.api.controllerApi;

import com.jebao.common.utils.validation.ValidatorUtil;
import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.p2p.service.inf.user.IAccountServiceInf;
import com.jebao.p2p.service.inf.user.IUserServiceInf;
import com.jebao.p2p.service.inf.userfund.IUserfundServiceInf;
import com.jebao.p2p.service.inf.voucher.ITbVoucherServiceInf;
import com.jebao.p2p.web.api.requestModel.account.LoginForm;
import com.jebao.p2p.web.api.requestModel.account.RegisterModel;
import com.jebao.p2p.web.api.responseModel.base.ErrorEnum;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultError;
import com.jebao.p2p.web.api.responseModel.base.JsonResultOk;
import com.jebao.p2p.web.api.utils.captcha.CaptchaUtil;
import com.jebao.p2p.web.api.utils.captcha.MessageUtil;
import com.jebao.p2p.web.api.utils.http.HttpUtil;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.CurrentUserContextHolder;
import com.jebao.p2p.web.api.utils.session.LoginSessionUtil;
import com.jebao.p2p.web.api.utils.validation.ValidationResult;
import com.jebao.p2p.web.api.utils.validation.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/20.
 */
@RestController
@RequestMapping("/api/account/")
public class AccountController extends _BaseController {

    @Autowired
    private IAccountServiceInf accountService;
    @Autowired
    private IUserServiceInf userService;
    @Autowired
    private ITbVoucherServiceInf voucherService;
    //登录
    @RequestMapping("doLogin")
    public JsonResult doLogin(LoginForm loginForm) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(loginForm);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.toString());
        }
//        String verifyCode = CaptchaUtil.getCaptchaToken(request, response);
//        if (!StringUtils.isBlank(verifyCode)) {
//            if (!verifyCode.equalsIgnoreCase(loginForm.getVerifyCode())) {
//                return new JsonResultError("校验码错误");
//            }
//        }
        String ipAddress = new HttpUtil().getIpAddress(request);
        //todo 实际的业务逻辑
        ResultData<Long> resultInfo = accountService.login(loginForm.getJebUsername(), loginForm.getPassword(), ipAddress);
        if (resultInfo.getSuccess_is_ok()) {
            TbUserDetails userDetails = userService.getUserDetailsInfo(resultInfo.getData());

            CurrentUser currentUser = new CurrentUser();
            currentUser.setId(resultInfo.getData());
            currentUser.setName(loginForm.getJebUsername());

            if (userDetails == null) {
                currentUser.setFundAccount(null);
                currentUser.setLoanerId(0);
            } else {
                currentUser.setFundAccount(userDetails.getUdThirdAccount());
                currentUser.setLoanerId(userDetails.getUdLoanerId() == null ? 0 : userDetails.getUdLoanerId());
            }

            //String code = LoginSessionUtil.setAuthCode(currentUser);
            //return new JsonResultOk(code);
            LoginSessionUtil.setLogin(currentUser, request, response);
            //
            //同步富友第三方账号
            syncThirdAccount(currentUser);
            //
            return new JsonResultOk("登录成功");
        } else {
            String error = resultInfo.getMsg();
            return new JsonResultError(error);
        }

    }

    @Autowired
    private IUserfundServiceInf userfundService;

    /**
     * 核心业务是登录
     * 同步第三方账号调用的是富友接口吃掉异常是为了减少对富友接口的依赖
     *
     * @param currentUser
     */
    private void syncThirdAccount(CurrentUser currentUser) {
        try {
            if (currentUser == null) {
                return;
            }
            //如果第三方账号存在，则直接返回OK
            if (!StringUtils.isBlank(currentUser.getFundAccount())) {
                return;
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping("doLogout")
    public JsonResult doLogout() {
        LoginSessionUtil.logout(request, response);
        return new JsonResultOk("退出成功");
    }

    @RequestMapping("doRegister")
    public JsonResult doRegister(RegisterModel model) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(model);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.toString());
        }
        if (!model.getPassword().equalsIgnoreCase(model.getPasswordAgain())) {
            return new JsonResultError("俩次密码不一致");
        }
        //图形验证码校验
        String verifyCode = CaptchaUtil.getCaptchaToken(request, response);
        if (StringUtils.isBlank(verifyCode) || !verifyCode.equalsIgnoreCase(model.getImgCode())) {
            return new JsonResultError("图形验证码错误", ErrorEnum.imageVerifyCode.getValue());
        }
        //短信验证码校验
        String mobile = model.getMobile();
        MessageUtil messageUtil = new MessageUtil();
        if (!messageUtil.isValidCode(mobile, model.getMessageCode())) {
            return new JsonResultError("短信验证码错误", ErrorEnum.messageVefiryCode.getValue());
        }
        HttpUtil httpUtil = new HttpUtil();
        String ip = httpUtil.getIpAddress(request);
        //注册
        ResultData<Long> result = accountService.register(mobile, model.getPassword(), model.getInvitationCode(), ip, httpUtil.getPlatform(request), httpUtil.getPlatformType(request));
        if (result.getSuccess_is_ok()) {
            //登录成功，设置登录状态
            CurrentUser currentUser = new CurrentUser();
            currentUser.setId(result.getData());
            currentUser.setName(mobile);
            LoginSessionUtil.setLogin(currentUser, request, response);
//            赠送优惠券红包-------wenyq 17.03.16
            long flag=voucherService.insertByBatch(result.getData());
            return new JsonResultOk(result.getMsg());
        } else {
            return new JsonResultError(result.getMsg());
        }
    }

    @RequestMapping("sendMessage")
    public JsonResult sendMessage(String mobile, String imgCode) {
        //图形验证码校验
        String verifyCode = CaptchaUtil.getCaptchaToken(request, response);
        if (StringUtils.isBlank(verifyCode) || !verifyCode.equalsIgnoreCase(imgCode)) {
            return new JsonResultError("图形验证码错误", ErrorEnum.imageVerifyCode.getValue());
        }
        String ip = new HttpUtil().getIpAddress(request);
        JsonResult jsonResult = new MessageUtil().sendMessageVerifyCode(mobile, ip);
        return jsonResult;
    }

    @RequestMapping("validateMobile")
    public HashMap<String, Boolean> validateMobile(String mobile) {
        HashMap<String, Boolean> map = new HashMap<>();
        boolean valid = true;
        if (!StringUtils.isBlank(mobile) && new ValidatorUtil().isMobile(mobile)) {
            TbLoginInfo existsUserInfo = userService.getUserLoginInfo(mobile);
            if (existsUserInfo != null) {
                valid = false;
            }
        }
        map.put("valid", valid);
        //map.put("valid",true);
        return map;
    }

    @RequestMapping(value = "forgotStep1", method = RequestMethod.POST)
    public JsonResult forgotStep1(String mobile, String imgCode) {
        TbLoginInfo existsUserInfo = userService.getUserLoginInfo(mobile);
        if (existsUserInfo == null) {
            return new JsonResultError("该手机号未注册", ErrorEnum.userNotExists.getValue());
        }
        //图形验证码校验
        String verifyCode = CaptchaUtil.getCaptchaToken(request, response);
        if (StringUtils.isBlank(verifyCode) || !verifyCode.equalsIgnoreCase(imgCode)) {
            return new JsonResultError("图形验证码错误", ErrorEnum.imageVerifyCode.getValue());
        }
        return new JsonResultOk();
    }

    @RequestMapping(value = "forgotStep2", method = RequestMethod.POST)
    public JsonResult forgotStep2(RegisterModel model) {
        //region 验证
        ValidationResult resultValidation = ValidationUtil.validateEntity(model);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.toString());
        }
        if (!model.getPassword().equalsIgnoreCase(model.getPasswordAgain())) {
            return new JsonResultError("俩次密码不一致");
        }
        //图形验证码校验
        String verifyCode = CaptchaUtil.getCaptchaToken(request, response);
        if (StringUtils.isBlank(verifyCode) || !verifyCode.equalsIgnoreCase(model.getImgCode())) {
            return new JsonResultError("图形验证码错误", ErrorEnum.imageVerifyCode.getValue());
        }
        //短信验证码校验
        String mobile = model.getMobile();
        MessageUtil messageUtil = new MessageUtil();
        if (!messageUtil.isValidCode(mobile, model.getMessageCode())) {
            return new JsonResultError("短信验证码错误", ErrorEnum.messageVefiryCode.getValue());
        }
        //endregion

        // 找回密码
        ResultInfo resultInfo = accountService.setPassword(mobile, model.getPassword());
        if (resultInfo.getSuccess_is_ok()) {
            //成功，设置登录状态
//            ResultData<Long> resultData = (ResultData<Long>)resultInfo;
//            CurrentUser currentUser = new CurrentUser();
//            currentUser.setId(resultData.getData());
//            currentUser.setName(mobile);
//            LoginSessionUtil.setLogin(currentUser,request,response);
            return new JsonResultOk(resultInfo.getMsg());
        } else {
            return new JsonResultError(resultInfo.getMsg());
        }

    }

}
