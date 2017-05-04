package com.jebao.erp.web.controller;

import com.jebao.erp.web.utils.session.CurrentUser;
import com.jebao.erp.web.utils.session.LoginSessionUtil;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/11/2.
 */
public class _BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected CurrentUser user;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.user = LoginSessionUtil.User(request,response);
    }


}
