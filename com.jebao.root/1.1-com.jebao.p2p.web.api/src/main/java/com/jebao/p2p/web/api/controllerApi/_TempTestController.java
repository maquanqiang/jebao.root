package com.jebao.p2p.web.api.controllerApi;

import com.jebao.common.utils.encrypt.EncryptDESUtil;
import com.jebao.common.utils.fastjson.FastJsonUtil;
import com.jebao.jebaodb.entity.TbTempTest;
import com.jebao.p2p.service.inf.ITempTestServiceInf;
import com.jebao.p2p.web.api.requestModel.tempTest.TempTestForm;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultError;
import com.jebao.p2p.web.api.responseModel.base.JsonResultList;
import com.jebao.p2p.web.api.responseModel.base.JsonResultOk;
import com.jebao.p2p.web.api.responseModel.tempTest.TempTestData;
import com.jebao.p2p.web.api.utils.constants.ProjectSetting;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.LoginSessionUtil;
import com.jebao.p2p.web.api.utils.session.LoginToken;
import com.jebao.p2p.web.api.utils.session.LoginTokenUtil;
import com.jebao.p2p.web.api.utils.validation.ValidationResult;
import com.jebao.p2p.web.api.utils.validation.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 架构初始下的测试方法
 * 所有的API的控制器必须以api开头
 * Created by Administrator on 2016/10/20.
 */
@RestController
@RequestMapping("/api/tempTest/")
public class _TempTestController extends _BaseController{

    @Autowired
    ITempTestServiceInf tempTestServiceInf;

    //
    @RequestMapping("doWork")
    public String doWork() {
        return "doWork";
    }

    @RequestMapping("jsonResultTest")
    public JsonResult jsonResultTest() {
        List<Integer> listData = new ArrayList<>();
        listData.add(1);
        //JsonResultList<Integer> data = new JsonResultList(listData);
        //JsonResultData<Integer> data = new JsonResultData(1);
        //JsonResultOk data=new JsonResultOk();
        //JsonResultOk data=new JsonResultOk("描述信息");
        //JsonResultError data=new JsonResultError("错误描述信息");
        //JsonResultError data=new JsonResultError("错误描述信息",1);
        JsonResultError data=new JsonResultError(ValidationUtil.validateEntity(new TempTestForm()).getErrorMsg());
        return data;
    }

    //测试登录
    @RequestMapping("doLogin")
    public JsonResult doLogin(TempTestForm tempTestForm) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(tempTestForm);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.toString());
        }
        TbTempTest data = TempTestForm.toEntity(tempTestForm);
        int result = tempTestServiceInf.add(data);
        //todo 实际的业务逻辑
        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(50);
        currentUser.setName(tempTestForm.getName());
        String loginTokenUUID= LoginTokenUtil.setLoginToken(currentUser);
        LoginToken loginToken=new LoginToken(loginTokenUUID,tempTestForm.getRedirectUrl());
        String loginTokenJson=FastJsonUtil.serialize(loginToken);
        String encryptLoginToken=EncryptDESUtil.toEncrypt(loginTokenJson);
        return new JsonResultOk(encryptLoginToken);
    }
    //测试登录认证
    @RequestMapping("doLoginToken")
    public void doLoginToken(String token, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(StringUtils.isBlank(token)||token.equals("undefined"))
        {
            throw new Exception("登录认证失败-内容不能为空");
        }
        if(token.length()<20)
        {
            throw new Exception("登录认证失败-内容不正确");
        }
        LoginToken loginToken;
        try {
            String loginTokenJson= EncryptDESUtil.toDecrypt(token);
            loginToken= FastJsonUtil.deserialize(loginTokenJson, LoginToken.class);
        }catch (Exception ex)
        {
            //ex.printStackTrace();
            throw new Exception("登录认证失败-内容验证无效");
        }
        String redirectURL=loginToken.getRedirectUrl();
        String loginTokenUUID=loginToken.getUuid();
        //String defaultRedirectLoginURL="http://localhost:9088/account/login";
        //String defaultRedirectURL="http://localhost:9088/user/index";
        String defaultRedirectLoginURL=getDefaultRedirectLoginURL();
        String defaultRedirectURL=getDefaultRedirectURL();
        //todo 实际的业务逻辑
        //http://localhost:9089/api/tempTest/redirect?loginToken=938acb3e-7c81-4aeb-bc7e-8165bba77005&redirectURL=http://localhost:9088/html/mediaNews
        CurrentUser currentUser= LoginTokenUtil.getLoginToken(loginTokenUUID);
        if(currentUser==null)
        {
            response.sendRedirect(defaultRedirectLoginURL);
            return;
        }
        //设置登录信息
        LoginSessionUtil.setLogin(currentUser, request, response);
        if(!StringUtils.isBlank(redirectURL)){defaultRedirectURL=redirectURL;}
        response.sendRedirect(defaultRedirectURL);
    }

    @RequestMapping("doLogout")
    public JsonResult doLogout() {
        return new JsonResultOk("退出成功");
    }

    @RequestMapping("getTempTestDataList")
    public JsonResult getTempTestDataList() {
        List<TbTempTest> entityList = tempTestServiceInf.getList(0, 10);
        List<TempTestData> dataList = TempTestData.toViewDataList(entityList);
        return new JsonResultList<>(dataList);
    }

    @RequestMapping("version")
    @ResponseBody
    public String version() {
        //当前发布程序的版本号
        return "2016102101";
    }

    private String getDefaultRedirectLoginURL()
    {
        return ProjectSetting.getConfigProperty("project.login.session.token.defaultRedirectLoginURL");
    }
    private String getDefaultRedirectURL()
    {
        return ProjectSetting.getConfigProperty("project.login.session.token.defaultRedirectURL");
    }

}
