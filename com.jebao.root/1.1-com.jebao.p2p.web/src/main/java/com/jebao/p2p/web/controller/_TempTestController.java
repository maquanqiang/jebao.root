package com.jebao.p2p.web.controller;

import com.jebao.p2p.web.requestModel.account.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/21.
 */

@Controller
@RequestMapping("/tempTest/")
public class _TempTestController extends _BaseController {
    @RequestMapping("login")
    public String login(@ModelAttribute("form") LoginForm form) {
        return "tempTest/login";
    }
    @RequestMapping("version")
    @ResponseBody
    public String version() {
        //当前发布程序的版本号
        return "2016102101";
    }
    @RequestMapping("test")
    @ResponseBody
    public String test() throws  Exception{
        //response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<h1>你好</h1>");

        return null;
    }

}
