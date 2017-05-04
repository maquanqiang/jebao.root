package com.jebao.file.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/16.
 */
@Controller
@RequestMapping("/test/")
public class TestController {
   /* @RequestMapping("t1")
    public String T1()
    {
        return "test/t1";
    }
    @RequestMapping("t2")
    public String T2()
    {
        return "test/t2";
    }
    @RequestMapping("t3")
    public String T3()
    {
        return "test/t3";
    }
    @RequestMapping("t4")
    public String T4()
    {
        return "test/t4";
    }*/
    @RequestMapping("t5")
    public String T5(String key)
    {
        if(StringUtils.isBlank(key)||!key.equals("www.jebao.net"))
        {
            return "test/t0";
        }
        return "test/t5";
    }
}