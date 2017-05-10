package com.jebao.erp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Lee on 2016/11/17.
 */
@Controller
@RequestMapping("/bidplan/")
public class BidPlanController {


    @RequestMapping("index")
    public String index() {
        return "bidplan/index";
    }


    @RequestMapping("reviewedPlanList")
    public String reviewedPlanList() {
        return "bidplan/reviewedplanlist";
    }

    @RequestMapping("notPassList")
    public String notPassList() {
        return "bidplan/notpasslist";
    }

    @RequestMapping("addPlan/{bpLoanerId}")
    public String addPlan(@PathVariable Long bpLoanerId, Model model) {
        model.addAttribute("bpLoanerId", bpLoanerId);
        return "bidplan/addplan";
    }

    @RequestMapping("updatePlanDetail/{bpId}")
    public String updatePlanDetail(@PathVariable("bpId") Long bpId, Model model) {
        model.addAttribute("bpId", bpId);
        return "bidplan/updateplandetail";
    }

    @RequestMapping("bidRiskDataList/{bpId}")
    public String bidRiskDataList(@PathVariable("bpId") Long bpId, Model model) {
        model.addAttribute("bpId", bpId);
        return "bidplan/bidRiskDataList";
    }

    @RequestMapping("alreadyLoanList")
    public String alreadyLoanList(){
        return "bidplan/alreadyloanlist";
    }


    @RequestMapping("reviewedPlanDetail/{bpId}")
    public String reviewedPlanDetail(@PathVariable("bpId") Long bpId, Model model) {
        model.addAttribute("bpId", bpId);
        return "bidplan/reviewedPlanDetail";
    }

    @RequestMapping("nolendinglist")
    public String nolendinglist(){
        return "bidplan/nolendinglist";
    }

    @RequestMapping("alreadyLoanDetail/{bpId}")
    public String alreadyLoanDetail(@PathVariable("bpId") Long bpId, Model model) {
        model.addAttribute("bpId", bpId);
        return "bidplan/alreadyloandetail";
    }

    @RequestMapping("noLendingDetail/{bpId}")
    public String noLendingDetail(@PathVariable("bpId") Long bpId, Model model) {
        model.addAttribute("bpId", bpId);
        return "bidplan/nolendingdetail";
    }

    @RequestMapping("updatePlanMaterial/{bpId}/{bpLoanerId}")
    public String updatePlanMaterial(@PathVariable("bpId") Long bpId, @PathVariable("bpLoanerId") Long bpLoanerId, Model model) {
        model.addAttribute("bpId", bpId);
        model.addAttribute("bpLoanerId", bpLoanerId);
        return "bidplan/updatePlanMaterial";
    }
}
