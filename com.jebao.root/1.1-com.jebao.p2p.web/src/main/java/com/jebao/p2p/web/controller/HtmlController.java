package com.jebao.p2p.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/9/21.
 */
@Controller
@RequestMapping("/html/")
public class HtmlController {
    /**
     * 产品
     *
     * @return
     */
    @RequestMapping("product")
    public String product() {
        return "html/product";
    }

    /**
     * 安全保障
     *
     * @return
     */
    @RequestMapping("secure")
    public String secure() {
        return "html/secure";
    }

    /**
     * 优惠活动
     * 公司动态
     *
     * @return
     */
    @RequestMapping("activity")
    public String activity() {
        return "html/activity";
    }

    /**
     * 公司动态详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/activity/details/{id}", method = RequestMethod.GET)
    public String activityDetails(@PathVariable Long id, Model model) {
        model.addAttribute("aId", id);
        return "html/activityDetails";
    }

    /**
     * 关于我们/关于平台
     *
     * @return
     */
    @RequestMapping("aboutMe")
    public String aboutMe() {
        return "html/aboutMe";
    }

    /**
     * 公司介绍
     *
     * @return
     */
    @RequestMapping("company")
    public String company() {
        return "html/company";
    }

    /**
     * 平台公告
     *
     * @return
     */
    @RequestMapping("officeNews")
    public String officeNews() {
        return "html/officeNews";
    }

    /**
     * 平台公告详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/officeNews/details/{id}", method = RequestMethod.GET)
    public String officeNewsDetails(@PathVariable Long id, Model model) {
        model.addAttribute("aId", id);
        return "html/officeNewsDetails";
    }

    /**
     * 媒体报道
     *
     * @return
     */
    @RequestMapping("mediaNews")
    public String mediaNews() {
        return "html/mediaNews";
    }

    /**
     * 媒体报道详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/mediaNews/details/{id}", method = RequestMethod.GET)
    public String mediaNewsDetails(@PathVariable Long id, Model model) {
        model.addAttribute("aId", id);
        return "html/mediaNewsDetails";
    }

    /**
     * 联系我们
     *
     * @return
     */
    @RequestMapping("contactUs")
    public String contactUs() {
        return "html/contactUs";
    }

    /**
     * 帮助中心
     *
     * @return
     */
    @RequestMapping("helpArticle")
    public String helpArticle() {
        return "html/helpArticle";
    }

    /**
     * 团队介绍
     * @return
     */
    @RequestMapping("game")
    public String game() {
        return "html/game";
    }

}
