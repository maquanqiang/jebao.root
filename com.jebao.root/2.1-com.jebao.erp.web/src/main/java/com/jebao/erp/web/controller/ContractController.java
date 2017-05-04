package com.jebao.erp.web.controller;

import com.jebao.erp.service.inf.investment.IIncomeDetailServiceInf;
import com.jebao.erp.service.inf.investment.IInvestInfoServiceInf;
import com.jebao.erp.service.inf.loaner.ILoanerServiceInf;
import com.jebao.erp.service.inf.loanmanage.ITbBidPlanServiceInf;
import com.jebao.erp.service.inf.user.IUserDetailsServiceInf;
import com.jebao.erp.web.conf.EmbeddedServletInstance;
import com.jebao.erp.web.requestModel.investment.RepaymentForm;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultError;
import com.jebao.erp.web.responseModel.base.JsonResultOk;
import com.jebao.erp.web.utils.contract.ContractUtil;
import com.jebao.erp.web.utils.contract.UpCaseRMB;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.jebaodb.entity.loanmanage.Contract.ContractCommData;
import com.jebao.jebaodb.entity.loanmanage.Contract.ContractParamForm;
import com.jebao.jebaodb.entity.loanmanage.Contract.InvestInfoData;
import com.jebao.jebaodb.entity.loanmanage.Contract.InvesterData;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
@Controller
@RequestMapping("/contract/")
public class ContractController {
    @Autowired
    private ITbBidPlanServiceInf bidPlanService;
    @Autowired
    private ILoanerServiceInf loanerService;
    @Autowired
    private IInvestInfoServiceInf investInfoService;
    @Autowired
    private IUserDetailsServiceInf userDetailsService;
    @Autowired
    private IIncomeDetailServiceInf incomeDetailService;

    //投资人合同模板
    @RequestMapping("template/t1")
    public String templateForT1(ContractParamForm form, Model model) {
        String[] cycleType = {"", "天", "个月", "季", "年"};

        Calendar cl = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat d1 = new DecimalFormat("#,##0.##");
        String loanMonayRMB = UpCaseRMB.number2CNMontrayUnit(form.getBpLoanMoney());
        //common data
        ContractCommData commData = new ContractCommData();
        TbBidPlan tbBidPlan = bidPlanService.selectByBpId(form.getBpId());
        TbLoaner loaner = loanerService.findLoanerById(tbBidPlan.getBpLoanerId());

        commData.setLoanerTrueName(tbBidPlan.getBpTrueName());              //借款人姓名
        commData.setLoanerNumber(loaner.getlIdNumber());                    //证件号码
        commData.setInterestSt(sdf.format(form.getBpInterestSt()));         //受让日期
        cl.setTime(form.getBpRepayTime());
        cl.add(Calendar.DATE, -1);
        commData.setInterestEt(sdf.format(cl.getTime()));                   //结束日期
        commData.setRepayTime(sdf.format(form.getBpRepayTime()));           //还款日期
        commData.setLoanMoney(d1.format(form.getBpLoanMoney()));            //标的金额--实际放款金额
        commData.setLoanMoneyRMB(loanMonayRMB);                             //标的金额人民币大写
        commData.setInterestPayType(tbBidPlan.getBpInterestPayType() == 1 ? "一次性还本付息" : "按期付息，到期还本");     //还款方式
        commData.setBidPeriods(tbBidPlan.getBpPeriods() + cycleType[tbBidPlan.getBpCycleType()]);          //标的周期
        commData.setLoanTime(sdf.format(form.getBpInterestSt()));            //放款日期  签字日期
        commData.setBidNumber(tbBidPlan.getBpNumber());

        TbInvestInfo tbInvestInfo = new TbInvestInfo();
        tbInvestInfo.setIiBpId(form.getBpId());
        tbInvestInfo.setIiIsDel(1);
        PageWhere pageWhere = new PageWhere(0, 10000);

        List<TbInvestInfo> tbInvestInfos = investInfoService.selectByBpId(tbInvestInfo, pageWhere);
        List<InvestInfoData> datas = new ArrayList<>();
        if (tbInvestInfos != null && tbInvestInfos.size() > 0) {
            for (TbInvestInfo info : tbInvestInfos) {
                InvestInfoData data = new InvestInfoData(info);
                data.setBidNumber(tbBidPlan.getBpNumber());
                datas.add(data);
            }
        }
        commData.setInfos(datas);

        TbInvestInfo investInfo = investInfoService.selectById(form.getInvestId());
        TbUserDetails userDetails = userDetailsService.selectByLoginId(investInfo.getIiLoginId());
        String idNumber = userDetails.getUdIdNumber();

        if (StringUtils.isNotBlank(idNumber)) {
            String subs = idNumber.substring(4, 14);
            idNumber = idNumber.replace(subs, "**********");
        }
        String investMoneyRMB = UpCaseRMB.number2CNMontrayUnit(investInfo.getIiMoney());
        //查询应收本息
        TbIncomeDetail tbIncomeDetail = new TbIncomeDetail();
        tbIncomeDetail.setIndIsDel(1);
        tbIncomeDetail.setIndIiId(investInfo.getIiId());
        tbIncomeDetail.setIndLoginId(investInfo.getIiLoginId());
        BigDecimal totalMoney = incomeDetailService.investerTotalMoney(tbIncomeDetail);

        String totalMoneyRMB = UpCaseRMB.number2CNMontrayUnit(totalMoney);

        InvesterData data = new InvesterData();
        data.setInvesterNumber(idNumber);
        data.setInvestMoney(d1.format(investInfo.getIiMoney()));
        data.setInvestMoneyRMB(investMoneyRMB);
        data.setName(investInfo.getIiTrueName());
        data.setTotalMoney(d1.format(totalMoney));
        data.setTotalMoneyRMB(totalMoneyRMB);

        model.addAttribute("commData", commData);
        model.addAttribute("data", data);
        return "contract/template/t1";
    }

    @RequestMapping("createDemo")
    @ResponseBody
    public JsonResult createDemo(ContractParamForm form) {

        boolean flag = true;
        String msg = "";

        TbInvestInfo tbInvestInfo = new TbInvestInfo();
        tbInvestInfo.setIiBpId(form.getBpId());
        tbInvestInfo.setIiIsDel(1);
        PageWhere pageWhere = new PageWhere(0, 10000);

        List<TbInvestInfo> tbInvestInfos = investInfoService.selectByBpId(tbInvestInfo, pageWhere);
        if(tbInvestInfos!=null && tbInvestInfos.size()>0){
            for(TbInvestInfo info : tbInvestInfos){
                form.setInvestId(info.getIiId());
                form.setContractName(form.getBidNumber()+"_"+info.getIiLoginName()+"_"+info.getIiId());
                String httpParam = form.toString();
                String templateUrl = String.format("http://localhost:%d/contract/template/t1?"+httpParam
                        , EmbeddedServletInstance.getServerPort());
                String fileName = String.format(form.getBidNumber()+"/"+form.getContractName()+".pdf", form.getBidNumber(), form.getContractName()+".pdf");
                try {
                    ContractUtil.create(templateUrl, fileName, info.getIiId());
                } catch (InterruptedException e) {
                    flag = false;
                    msg += info.getIiId()+";";
                    e.printStackTrace();
                }
            }
        }
        if(flag){
            return new JsonResultOk("合同制作成功");
        }else{
            return new JsonResultError("生成失败;失败编号为："+msg);
        }
    }
}
