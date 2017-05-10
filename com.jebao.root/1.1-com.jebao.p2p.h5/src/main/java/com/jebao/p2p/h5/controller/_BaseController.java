package com.jebao.p2p.h5.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wenyq on 2017/2/17.
 */
public class _BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
}
