package com.jebao.erp.web.controller;

import com.jebao.erp.web.utils.captcha.CaptchaImageUtil;
import com.jebao.erp.web.utils.captcha.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/2.
 */
@Controller
@RequestMapping("/captcha/")
public class CaptchaController {
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = CaptchaImageUtil.generateVerifyCode(4);
        CaptchaUtil.setCaptchaToken(verifyCode, request, response);
        //生成图片
        int w = 200, h = 60;
        CaptchaImageUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
    @RequestMapping("doVerify")
    public void doVerify(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        String verifyCode=CaptchaUtil.getCaptchaToken(request,response);
        System.out.println(verifyCode);
    }
}