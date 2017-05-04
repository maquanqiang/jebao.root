package com.jebao.erp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/11/14.
 */
@Controller
@RequestMapping("/loaner/")
public class LoanerController extends _BaseController {
    //region 借款人
    @RequestMapping("index")
    public String index() {
        return "loaner/index";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("loanerId", id);
        return "loaner/details";
    }
    //endregion

    //region 风控
    @RequestMapping(value = "/risk/index/{lId}", method = RequestMethod.GET)
    public String risklist(@PathVariable Long lId, Model model) {
        model.addAttribute("loanerId", lId);
        return "loaner/risk/index";
    }

    @RequestMapping(value = "/risk/info/{lId}/rId/{rId}", method = RequestMethod.GET)
    public String riskedit(@PathVariable Long lId,@PathVariable Long rId, Model model) {
        model.addAttribute("loanerId", lId);
        model.addAttribute("rcptId", rId);
        return "loaner/risk/info";
    }

    @RequestMapping(value = "/risk/details/{rId}", method = RequestMethod.GET)
    public String riskdetails(@PathVariable Long rId, Model model) {
        model.addAttribute("rcptId", rId);
        return "loaner/risk/details";
    }

    @RequestMapping(value = "/risk/materials/{rId}", method = RequestMethod.GET)
    public String riskmaterials(@PathVariable Long rId, Model model) {
        model.addAttribute("rcptId", rId);
        return "loaner/risk/materials";
    }
    //endregion

    //region 资金信息
    @RequestMapping(value = "/funds/{id}", method = RequestMethod.GET)
    public String funds(@PathVariable Long id, Model model) {
        model.addAttribute("loginId", id);
        return "loaner/funds";
    }
    //endregion

    //region 借款信息
    @RequestMapping(value = "/loanrecord/{id}", method = RequestMethod.GET)
    public String loanrecord(@PathVariable Long id, Model model) {
        model.addAttribute("loanerId", id);
        return "loaner/loanrecord";
    }
    //endregion
}