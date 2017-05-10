package com.jebao.p2p.web.controller;

import com.jebao.p2p.web.utils.session.CurrentUser;
import com.jebao.p2p.web.utils.session.CurrentUserContextHolder;
import com.jebao.p2p.web.utils.session.LoginSessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/9/21.
 */
@Controller
@RequestMapping("/user/")
public class UserController extends _BaseController {
    /**
     * 用户中心首页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "user/index";
    }

    /**
     * 收支明细
     *
     * @return
     */
    @RequestMapping("funds")
    public String funds() {
        return "user/funds";
    }

    /**
     * 投资记录
     *
     * @return
     */
    @RequestMapping("investrecord")
    public String invest() {
        return "user/investrecord";
    }

    /**
     * 邀请好友
     *
     * @return
     */
    @RequestMapping("invite")
    public String invite(Model model) {
        StringBuffer url = request.getRequestURL();
        String domainUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        CurrentUser user = CurrentUserContextHolder.get();
        String inviteUrl = domainUrl + "account/register?code=" + user.getName();
        model.addAttribute("inviteUrl", inviteUrl);
        return "user/invite";
    }

    /**
     * 账户设置
     *
     * @return
     */
    @RequestMapping("setting")
    public String setting(Model model) {
        CurrentUser user = LoginSessionUtil.User(request, response);
        String mobile = user.getName().replaceFirst("(?<=\\d{3})\\d+(?=\\d{4})", "****");
        model.addAttribute("mobile", mobile);
        return "user/setting";
    }

    /**
     * 我的银行卡
     *
     * @return
     */
    @RequestMapping("bankcard")
    public String mybankcard() {
        return "user/bankcard";
    }

    /**
     * 充值提现
     *
     * @return
     */
    @RequestMapping(value = "chargewithdraw", method = RequestMethod.GET)
    public String chargewithdraw(@RequestParam("typeId") int typeId, Model model) {
        model.addAttribute("typeId", typeId);
        return "user/chargewithdraw";
    }

    /**
     * 借款管理
     *
     * @return
     */
    @RequestMapping("loanmanage")
    public String loanmanage() {
        return "user/loanmanage";
    }


    /**
     * 我的红包
     * @return
     */
    @RequestMapping("voucher")
    public String voucher() {
        return "user/voucher";
    }
}
