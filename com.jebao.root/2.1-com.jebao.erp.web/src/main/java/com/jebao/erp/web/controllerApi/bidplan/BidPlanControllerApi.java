package com.jebao.erp.web.controllerApi.bidplan;

import com.jebao.erp.service.inf.investment.IIncomeDetailServiceInf;
import com.jebao.erp.service.inf.investment.IInvestInfoServiceInf;
import com.jebao.erp.service.inf.loaner.ILoanerServiceInf;
import com.jebao.erp.service.inf.loanmanage.ITbBidPlanServiceInf;
import com.jebao.erp.service.inf.loanmanage.ITbBidRiskDataServiceInf;
import com.jebao.erp.service.inf.user.IUserDetailsServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.requestModel.bidplan.AddPlanForm;
import com.jebao.erp.web.requestModel.bidplan.BidPlanForm;
import com.jebao.erp.web.requestModel.bidplan.PlanMaterialForm;
import com.jebao.erp.web.requestModel.bidplan.UpdatePlanForm;
import com.jebao.erp.web.requestModel.investment.RepaymentForm;
import com.jebao.erp.web.responseModel.base.*;
import com.jebao.erp.web.responseModel.bidplan.BidPlanVM;
import com.jebao.erp.web.responseModel.bidplan.LoanIntentVM;
import com.jebao.erp.web.responseModel.bidplan.ProjTempNameVM;
import com.jebao.erp.web.responseModel.bidplan.ProjectTempVM;
import com.jebao.erp.web.responseModel.postLoan.IncomeDetailsVM;
import com.jebao.erp.web.utils.contract.UpCaseRMB;
import com.jebao.erp.web.utils.excel.ExcelUtil;
import com.jebao.erp.web.utils.toolbox.BetweenDays;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;
import com.jebao.jebaodb.entity.loanmanage.Contract.ContractCommData;
import com.jebao.jebaodb.entity.loanmanage.Contract.InvesterData;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.thirdPay.fuiou.impl.TransferBuServiceImpl;
import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuRequest;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Lee on 2016/11/26.
 */
@Controller
@RequestMapping("api/bidPlan")
public class BidPlanControllerApi extends _BaseController {

    @Autowired
    private ITbBidPlanServiceInf bidPlanService;
    @Autowired
    private ILoanerServiceInf loanerService;
    @Autowired
    private ITbBidRiskDataServiceInf riskDataService;
    @Autowired
    private IInvestInfoServiceInf investInfoService;
    @Autowired
    private IUserDetailsServiceInf userDetailsService;
    @Autowired
    private IIncomeDetailServiceInf incomeDetailService;

    /**
     * 删除标的
     * @param bpId
     * @return
     */
    @RequestMapping("removeBidPlan")
    @ResponseBody
    public JsonResult removeBidPlan(Long bpId) {
        TbBidPlan tbBidPlan = new TbBidPlan();
        tbBidPlan.setBpId(bpId);
        tbBidPlan.setBpIsDel(2);
        tbBidPlan.setBpUpdateTime(new Date());
        int count = bidPlanService.updateByBidIdSelective(tbBidPlan);
        if (count > 0) {
            return new JsonResultOk("删除成功");
        } else {
            return new JsonResultError("删除失败");
        }

    }

    @RequestMapping("getProjList")
    @ResponseBody
    public JsonResult getProjList(PageWhere page, Long bpLoanerId) {
        page.setOrderBy(" rcptId desc");
        List<TbRiskCtlPrjTemp> projectTemps = loanerService.selectRiskCtlPrjTempByLoanerIdForPage(bpLoanerId, page);
        List<ProjTempNameVM> tempVMs = new ArrayList<>();
        projectTemps.forEach(o -> tempVMs.add(new ProjTempNameVM(o)));
        return new JsonResultList<>(tempVMs);
    }

    /**
     * 标的详情
     * @param bpId
     * @return
     */
    @RequestMapping("getOne/{bpId}")
    @ResponseBody
    public JsonResult getOne(Long bpId) {
        TbBidPlan bidPlan = bidPlanService.selectByBpId(bpId);
        if(bidPlan==null){
            return new JsonResultError("无此记录");
        }
        BidPlanVM viewModel = new BidPlanVM(bidPlan);
        return new JsonResultData<>(viewModel);
    }

    /**
     * 添加标的
     * @param form
     * @return
     */
    @RequestMapping("doAddPlan")
    @ResponseBody
    public JsonResult doAddPlan(AddPlanForm form) {
        //校验
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg().toString());
        }

        TbBidPlan plan = new TbBidPlan();
        plan.setBpNumber(form.getBpNumber());
        //判断标的编号是否重复
        List<TbBidPlan> tbBidPlans = bidPlanService.selectByConditionForPage(plan, new PageWhere(0, 100));
        if (tbBidPlans != null && tbBidPlans.size() > 0) {
            return new JsonResultError("标的编号重复");
        }

        //保存标的信息
        TbBidPlan bidPlan = AddPlanForm.toEntity(form);
        TbLoaner loaner = loanerService.findLoanerById(form.getBpLoanerId());
        bidPlan.setBpSurplusMoney(bidPlan.getBpBidMoney());
        bidPlan.setBpStatus(TbBidPlan.STATUS_UNAUDITED);                     //默认未审核
        Date date = new Date();
        bidPlan.setBpCreateTime(date);
        bidPlan.setBpUpdateTime(date);
        bidPlan.setBpTrueName(loaner.getlTrueName());
        bidPlan.setBpLoginId(loaner.getlLoginId());
        bidPlan.setBpLoanerType(1);                 //默认个人
        bidPlan.setBpRepayedPeriods(0);             //初始为0
        bidPlan.setBpIsDel(1);                      //有效
        bidPlanService.add(bidPlan);

        Long rcptId = form.getRcptId();
        //保存风控信息
        if (rcptId != null) {
            List<TbRcpMaterialsTemp> materialsTemps = loanerService.selectRcpMaterialsTempByPrjIdForPage(rcptId, null);
            if (materialsTemps != null && materialsTemps.size() > 0) {
                for (TbRcpMaterialsTemp temp : materialsTemps) {
                    Date cDate = new Date();
                    TbBidRiskData riskData = TbBidRiskData.toEntity(temp);
                    riskData.setBrdCreateTime(cDate);
                    riskData.setBrdUpdateTime(cDate);
                    riskData.setBrdIsDel(1);
                    riskData.setBrdBpId(bidPlan.getBpId());
                    riskDataService.add(riskData);
                }
            }
        }
        return new JsonResultOk("ok");
    }


    @RequestMapping("getBidPlanById")
    @ResponseBody
    public JsonResult getBidPlanById(Long bpId) {
        TbBidPlan bidPlan = bidPlanService.selectByBpId(bpId);
        if(bidPlan==null){
            return new JsonResultError("无此记录");
        }
        BidPlanVM viewModel = new BidPlanVM(bidPlan);
        return new JsonResultData<>(viewModel);
    }


    @RequestMapping("getPlanListForPage")
    @ResponseBody
    public JsonResult getPlanListForPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "rows", defaultValue = "10") Integer rows, Integer bpStatus) {
        TbBidPlan plan = new TbBidPlan();
        plan.setBpStatus(bpStatus);
        PageWhere pw = new PageWhere(page, rows);
        List<TbBidPlan> tbBidPlans = bidPlanService.selectByConditionForPage(plan, pw);
        int count = bidPlanService.selectByConditionCount(plan);
        List<BidPlanVM> viewModelList = new ArrayList<BidPlanVM>();
        tbBidPlans.forEach(o -> viewModelList.add(new BidPlanVM(o)));

        return new JsonResultList<>(viewModelList, count);
    }


    @RequestMapping("updatePlan")
    @ResponseBody
    public JsonResult updatePlan(UpdatePlanForm form) {
        TbBidPlan tbBidPlan = bidPlanService.selectByBpId(form.getBpId());
        if(tbBidPlan.getBpStatus()>1){
            if(form.getBpRate().compareTo(tbBidPlan.getBpRate())!=0 ||
                    form.getBpPeriods()!=tbBidPlan.getBpPeriods() ||
                    form.getBpBidMoney().compareTo(tbBidPlan.getBpBidMoney())!=0){
                return new JsonResultError("修改失败");
            }
        }

        TbBidPlan bidPlan = UpdatePlanForm.toEntity(form);
        bidPlan.setBpStatus(TbBidPlan.STATUS_UNAUDITED);                             //改为待审核状态
        bidPlan.setBpUpdateTime(new Date());
        int count = bidPlanService.updateByBidIdSelective(bidPlan);
        if (count > 0) {
            return new JsonResultOk("信息修改成功");
        } else {
            return new JsonResultError("信息修改失败");
        }
    }

    /**
     * 获取风控模板
     * @param rcptId
     * @return
     */
    @RequestMapping("getProjectTempById")
    @ResponseBody
    public JsonResult getProjectTempById(Long rcptId) {
        TbRiskCtlPrjTemp tbRiskCtlPrjTemp = loanerService.findRiskCtlPrjTempById(rcptId);
        ProjectTempVM projectTemp = new ProjectTempVM(tbRiskCtlPrjTemp);
        return new JsonResultData<>(projectTemp);
    }

    @RequestMapping("getLoanFundIntents")
    @ResponseBody
    public JsonResult getLoanFundIntents(AddPlanForm form) {

        List<LoanIntentVM> loanFundIntents = new ArrayList<>();
        BigDecimal principal = form.getBpBidMoney();
        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(form.getBpExpectLoanDate());

        if (form.getBpInterestPayType() == 1) {     //一次性还本付息
            LoanIntentVM loanIntentVM = new LoanIntentVM();
            Date loanDate = form.getBpExpectLoanDate();
            Date repayDate = form.getBpExpectRepayDate();
            int days = BetweenDays.differentDays(loanDate, repayDate);
            BigDecimal interest = principal.multiply(form.getBpRate()).multiply(new BigDecimal(days))
                    .divide(new BigDecimal(100 * 365), 2, BigDecimal.ROUND_HALF_UP);

            loanIntentVM.setIntentPeriod(1);
            loanIntentVM.setRepayDate(form.getBpExpectRepayDate());
            loanIntentVM.setPrincipal(principal);
            loanIntentVM.setInterest(interest);
            loanIntentVM.setDays(days);
            loanIntentVM.setTotal(principal.add(interest));
            loanFundIntents.add(loanIntentVM);
            System.out.println(days);
        } else if (form.getBpInterestPayType() == 2) {//按期付息
            Date loanDate = form.getBpExpectLoanDate();
            Date nextRepayDate = form.getBpExpectLoanDate();
            Integer bpCycleType = form.getBpCycleType();
            calendar.setTime(loanDate);
            int dd = calendar.get(GregorianCalendar.DAY_OF_MONTH);

            for (int i = 1; i <= form.getBpPeriodsDisplay(); i++) {
                LoanIntentVM loanIntent = new LoanIntentVM();
                now.setTime(loanDate);
                loanIntent.setIntentPeriod(i);
                if (bpCycleType == 1) {         //日
                    now.add(Calendar.DATE, i);
                } else if (bpCycleType == 2) {   //月
                    now.add(Calendar.MONTH, i);
                } else if (bpCycleType == 3) {   //季
                    now.add(Calendar.MONTH, i * 3);
                } else if (bpCycleType == 4) {   //年
                    now.add(Calendar.YEAR, i);
                }
                if (now.get(GregorianCalendar.DAY_OF_MONTH) != dd) {
                    now.add(Calendar.DATE, 1);
                }
                int days = BetweenDays.differentDays(nextRepayDate, now.getTime());

                System.out.println("实际时间"+days);
                nextRepayDate = now.getTime();
                BigDecimal interest = principal.multiply(form.getBpRate()).multiply(new BigDecimal(days))
                        .divide(new BigDecimal(100 * 365), 2, BigDecimal.ROUND_HALF_UP);
                loanIntent.setRepayDate(nextRepayDate);
                loanIntent.setInterest(interest);
                loanIntent.setDays(days);
                if (i == form.getBpPeriodsDisplay()) {
                    loanIntent.setPrincipal(form.getBpBidMoney());
                    loanIntent.setTotal(interest.add(form.getBpBidMoney()));
                    loanIntent.setRepayDate(form.getBpExpectRepayDate());
                } else {
                    loanIntent.setPrincipal(BigDecimal.ZERO);
                    loanIntent.setTotal(interest);
                }

                loanFundIntents.add(loanIntent);
            }
        }

        return new JsonResultList<>(loanFundIntents);
    }

    /**
     * 标的审核
     * @param bpId
     * @param status
     * @param remark
     * @return
     */
    @RequestMapping("reviewedPlan")
    @ResponseBody
    public JsonResult reviewedPlan(Long bpId, Integer status, String remark) {
        TbBidPlan bidPlan = bidPlanService.selectByBpId(bpId);
        //设置借款利率上限 20% 超过则报异常
        if(status==2){
            if(bidPlan.getBpRate().compareTo(new BigDecimal(20))>0){
                return new JsonResultError("审核不通过-利率20");
            }
        }
        if(bidPlan.getBpStatus()!=0){
            return new JsonResultError("标的不是待审核阶段");
        }
        TbBidPlan tbBidPlan = new TbBidPlan();
        tbBidPlan.setBpStatus(status);
        tbBidPlan.setBpId(bpId);
        tbBidPlan.setBpRemark(remark);
        tbBidPlan.setBpUpdateTime(new Date());
        if (status.equals(TbBidPlan.STATUS_AUDITE_FAIL)) {
            tbBidPlan.setBpCreateTime(tbBidPlan.getBpUpdateTime());
        }
        int result = bidPlanService.updateByBidIdSelective(tbBidPlan);
        if (result > 0) {
            return new JsonResultOk();
        } else {
            return new JsonResultError("操作异常");
        }
    }

    /**
     * 条件查询标的列表
     * @param form
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("getPlanListBySearchCondition")
    @ResponseBody
    public JsonResult getPlanListBySearchCondition(BidPlanForm form, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageWhere pageWhere = new PageWhere(pageIndex, pageSize);
        pageWhere.setOrderBy(" bp_id asc");
        BidPlanSM bidPlanSM = BidPlanForm.toEntity(form);
        List<TbBidPlan> tbBidPlans = bidPlanService.selectBySelfConditionForPage(bidPlanSM, pageWhere);
        List<BidPlanVM> viewModelList = new ArrayList<BidPlanVM>();
        tbBidPlans.forEach(o -> viewModelList.add(new BidPlanVM(o)));
        int count = bidPlanService.selectBySelfConditionCount(bidPlanSM);
        return new JsonResultList<>(viewModelList, count);
    }

    /**
     * 放款
     *
     * @param form
     * @return
     */
    @RequestMapping("doLoan")
    @ResponseBody
    public JsonResult doLoan(RepaymentForm form) {
        //校验
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg().toString());
        }

        TbBidPlan tbBidPlan = bidPlanService.selectByBpId(form.getBpId());
        if (tbBidPlan.getBpStatus() != TbBidPlan.STATUS_BID_FULL && tbBidPlan.getBpStatus() != TbBidPlan.STATUS_BID_LOSE) {
            return new JsonResultError("不能操作还款,标的状态为:" + tbBidPlan.getBpStatus());
        }

        tbBidPlan.setBpLoanMoney(form.getBpLoanMoney());
        tbBidPlan.setBpInterestSt(form.getBpInterestSt());
        tbBidPlan.setBpRepayTime(form.getBpRepayTime());


        boolean flag = bidPlanService.doLoan(tbBidPlan);
        if (flag) {
            //修改标的信息
            tbBidPlan.setBpLoanTime(new Date());
            tbBidPlan.setBpUpdateTime(tbBidPlan.getBpLoanTime());
            tbBidPlan.setBpStatus(TbBidPlan.STATUS_REPAYING);
            bidPlanService.updateByBidIdSelective(tbBidPlan);


            return new JsonResultOk("放款成功");
        } else {
            return new JsonResultError("放款失败-联系管理员");
        }

    }

    /**
     * 关闭当前标的  (只针对不投资已过期的标的)
     * 设为无效数据
     * @param bpId
     * @return
     */
    @RequestMapping("close")
    @ResponseBody
    public JsonResult close(Long bpId) {

        TbBidPlan tbBidPlan = new TbBidPlan();
        tbBidPlan.setBpId(bpId);
        tbBidPlan.setBpIsDel(2);

        int count = bidPlanService.updateByBidIdSelective(tbBidPlan);
        if (count > 0) {
            return new JsonResultOk("关闭成功");
        } else {
            return new JsonResultError("关闭失败");
        }
    }


    /**
     *
     * @return
     */
    @RequestMapping("repay")
    @ResponseBody
    public JsonResult repay(TbIncomeDetail detail){
        TbIncomeDetail tbIncomeDetail = new TbIncomeDetail();
        tbIncomeDetail.setIndIsDel(1);
        tbIncomeDetail.setIndPeriods(detail.getIndPeriods());
        tbIncomeDetail.setIndBpId(detail.getIndBpId());

        ResultInfo resultInfo = bidPlanService.repayFreeze(tbIncomeDetail);
        if(resultInfo.getSuccess_is_ok()){
            return new JsonResultOk(resultInfo.getMsg());
        }else {
            return new JsonResultError(resultInfo.getMsg());
        }
    }

    @RequestMapping("updatePlanMaterial")
    @ResponseBody
    public JsonResult updatePlanMaterial(PlanMaterialForm form) {

        TbBidPlan bidPlan = PlanMaterialForm.toEntity(form);
        bidPlan.setBpUpdateTime(new Date());
        int count = bidPlanService.updateByBidIdSelective(bidPlan);

        if (count > 0) {
            //查询到材料 标识为删除
            TbBidRiskData riskData = new TbBidRiskData();
            riskData.setBrdBpId(bidPlan.getBpId());
            List<TbBidRiskData> tbBidRiskDatas = riskDataService.selectByConditionForPage(riskData, new PageWhere(0, 100));

            if(tbBidRiskDatas!=null && tbBidRiskDatas.size()>0){
                for(TbBidRiskData data:tbBidRiskDatas){
                    data.setBrdUpdateTime(new Date());
                    data.setBrdIsDel(2);
                    riskDataService.updateByBidIdSelective(data);
                }
            }
            Long rcptId = form.getRcptId();
            //保存风控信息
            if (rcptId != null) {
                List<TbRcpMaterialsTemp> materialsTemps = loanerService.selectRcpMaterialsTempByPrjIdForPage(rcptId, null);
                if (materialsTemps != null && materialsTemps.size() > 0) {
                    for (TbRcpMaterialsTemp temp : materialsTemps) {
                        Date cDate = new Date();
                        TbBidRiskData nRiskData = TbBidRiskData.toEntity(temp);
                        nRiskData.setBrdCreateTime(cDate);
                        nRiskData.setBrdUpdateTime(cDate);
                        nRiskData.setBrdIsDel(1);
                        nRiskData.setBrdBpId(bidPlan.getBpId());
                        riskDataService.add(nRiskData);
                    }
                }
            }
            return new JsonResultOk("信息修改成功");
        } else {
            return new JsonResultError("信息修改失败");
        }

    }

    @RequestMapping("selectBpNumberList")
    @ResponseBody
    public JsonResult selectBpNumberList(String bpNumberStr){

        List<TbBidPlan> tbBidPlans = bidPlanService.selectBpNumberList("'"+bpNumberStr+"%'");
        List<BidPlanVM> viewModelList = new ArrayList<BidPlanVM>();
        tbBidPlans.forEach(o -> viewModelList.add(new BidPlanVM(o)));
        return new JsonResultList<>(viewModelList);
    }

//    @RequestMapping("createContract")
//    @ResponseBody
//    public JsonResult createContract(RepaymentForm form) {
//
//        boolean flag = true;
//
//        String[] cycleType = {"", "天", "个月", "季", "年"};
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        DecimalFormat d1 = new DecimalFormat("#,##0.##");
//        String loanMonayRMB = UpCaseRMB.number2CNMontrayUnit(form.getBpLoanMoney());
//
//        //common data
//        ContractCommData commData = new ContractCommData();
//        TbBidPlan tbBidPlan = bidPlanService.selectByBpId(form.getBpId());
//        TbLoaner loaner = loanerService.findLoanerById(tbBidPlan.getBpLoanerId());
//
//
//        commData.setLoanerTrueName(tbBidPlan.getBpTrueName());              //借款人姓名
//        commData.setLoanerNumber(loaner.getlIdNumber());                    //证件号码
//        commData.setInterestSt(sdf.format(form.getBpInterestSt()));         //受让日期
//        commData.setRepayTime(sdf.format(form.getBpRepayTime()));           //还款日期   到期日
//        commData.setLoanMoney(d1.format(form.getBpLoanMoney())); //标的金额--实际放款金额
//        commData.setLoanMoneyRMB(loanMonayRMB);                             //标的金额人民币大写
//        commData.setInterestPayType(tbBidPlan.getBpInterestPayType() == 1 ? "一次性还本付息" : "按期付息，到期还本");     //还款方式
//        commData.setBidPeriods(tbBidPlan.getBpPeriods() + cycleType[tbBidPlan.getBpCycleType()]);          //标的周期
//        commData.setLoanTime(sdf.format(form.getBpInterestSt()));            //放款日期  签字日期
//
//        TbInvestInfo tbInvestInfo = new TbInvestInfo();
//        tbInvestInfo.setIiBpId(form.getBpId());
//        tbInvestInfo.setIiIsDel(1);
//        PageWhere pageWhere = new PageWhere(0, 10000);
//
//        List<TbInvestInfo> tbInvestInfos = investInfoService.selectByBpId(tbInvestInfo, pageWhere);
//        commData.setInfos(tbInvestInfos);
//
//        //循环每个投资人
//        for (TbInvestInfo info : tbInvestInfos) {
//
//            TbUserDetails userDetails = userDetailsService.selectByLoginId(info.getIiLoginId());
//            String idNumber = userDetails.getUdIdNumber();
//
//            if (StringUtils.isNotBlank(idNumber)) {
//                String subs = idNumber.substring(4, 13);
//                idNumber = idNumber.replace(subs, "**********");
//            }
//            String investMoneyRMB = UpCaseRMB.number2CNMontrayUnit(info.getIiMoney());
//            //查询应收本息
//            TbIncomeDetail tbIncomeDetail = new TbIncomeDetail();
//            tbIncomeDetail.setIndIsDel(1);
//            tbIncomeDetail.setIndIiId(info.getIiId());
//            tbIncomeDetail.setIndLoginId(info.getIiLoginId());
//            BigDecimal totalMoney = incomeDetailService.investerTotalMoney(tbIncomeDetail);
//
//            String totalMoneyRMB = UpCaseRMB.number2CNMontrayUnit(totalMoney);
//
//            InvesterData data = new InvesterData();
//            data.setInvesterNumber(idNumber);
//            data.setInvestMoney(d1.format(info.getIiMoney()));
//            data.setInvestMoneyRMB(investMoneyRMB);
//            data.setName(info.getIiTrueName());
//            data.setTotalMoney(d1.format(totalMoney));
//            data.setTotalMoneyRMB(totalMoneyRMB);
//
//        }
//
//        if(flag){
//            return new JsonResultOk("合同生成成功");
//        }else {
//            return new JsonResultError("合同生成失败");
//        }
//    }

    /**
     * 导出excel
     * @param form
     */
    @RequestMapping("download")
    public void download(BidPlanForm form) throws Exception {
        PageWhere pageWhere = new PageWhere(0, 10000);
        pageWhere.setOrderBy(" bp_id desc");
        BidPlanSM bidPlanSM = BidPlanForm.toEntity(form);
        List<TbBidPlan> tbBidPlans = bidPlanService.selectBySelfConditionForPage(bidPlanSM, pageWhere);
        List<BidPlanVM> viewModelList = new ArrayList<>();
        tbBidPlans.forEach(o -> viewModelList.add(new BidPlanVM(o)));
        new ExcelUtil().outputFile(response, "标的列表.xlsx",viewModelList);
    }

    /**
     * 查询到期金额  应还款金额
     * @return
     */
    @RequestMapping("incomeCount/{type}")
    @ResponseBody
    public JsonResult incomeCount(@PathVariable("type")Integer type){
        BigDecimal dueTotal = bidPlanService.selectIncomeCount(type);

        return new JsonResultData<>(dueTotal);
    }
}

