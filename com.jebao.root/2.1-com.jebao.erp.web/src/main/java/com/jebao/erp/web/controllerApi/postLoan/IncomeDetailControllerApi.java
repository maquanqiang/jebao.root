package com.jebao.erp.web.controllerApi.postLoan;

import com.jebao.erp.service.inf.investment.IIncomeDetailServiceInf;
import com.jebao.erp.service.inf.investment.IInvestInfoServiceInf;
import com.jebao.erp.service.inf.investment.ILoanerRepaymentDetailServiceInf;
import com.jebao.erp.service.inf.user.ILoginInfoServiceInf;
import com.jebao.erp.web.requestModel.bidplan.BidPlanForm;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultError;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.postLoan.IncomeDetailsVM;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.postLoan.search.RepaymentDetailSM;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * Created by Lee on 2016/12/5.
 */
@Controller
@RequestMapping("api/incomeDetail")
public class IncomeDetailControllerApi {

    @Autowired
    private IIncomeDetailServiceInf incomeDetailService;
    @Autowired
    private ILoginInfoServiceInf loginInfoService;


    /**
     * 投资人台账明细
     * @param form
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("repaymentList")
    @ResponseBody
    public JsonResult repaymentList(RepaymentDetailSM form, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){

        List<IncomeDetailsVM> incomeDetailsVM = new ArrayList<>();
        PageWhere pageWhere = new PageWhere(pageIndex, pageSize);
        List<TbIncomeDetail> incomeDetails = incomeDetailService.selectGroupByConditionForPage(form, pageWhere);
        incomeDetails.forEach(o -> incomeDetailsVM.add(new IncomeDetailsVM(o)));
        int count = incomeDetailService.selectGroupByConditionCount(form);

        return new JsonResultList<>(incomeDetailsVM, count);
    }

    /**
     * 标的每期的还款明细
     * @param bpId
     * @param period
     * @param fundType
     * @return
     */
    @RequestMapping("incomeListCurrPeriod")
    @ResponseBody
    public JsonResult incomeListCurrPeriod(Long bpId, Integer period, Integer fundType){

        List<IncomeDetailsVM> detailsVMs = new ArrayList<>();
        TbIncomeDetail tbIncomeDetail = new TbIncomeDetail();
        tbIncomeDetail.setIndBpId(bpId);
        tbIncomeDetail.setIndPeriods(period);
        tbIncomeDetail.setIndFundType(fundType);
        PageWhere pageWhere = new PageWhere(0, 10000);
        List<TbIncomeDetail> incomeDetails = incomeDetailService.selectByConditionForPage(tbIncomeDetail, pageWhere);
        if(incomeDetails!=null && incomeDetails.size()>0){
            for(TbIncomeDetail detail : incomeDetails){
                TbLoginInfo tbLoginInfo = loginInfoService.selectByLiId(detail.getIndLoginId());
                IncomeDetailsVM detailsVM = new IncomeDetailsVM(detail);
                detailsVM.setBpTrueName(tbLoginInfo.getLiLoginName());
                detailsVMs.add(detailsVM);
            }
        }

        return new JsonResultList<>(detailsVMs);
    }

    /**
     * 贷后管理  还款明细
     * @param form
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("postLoanIncomeDetail")
    @ResponseBody
    public JsonResult postLoanIncomeDetail(BidPlanForm form, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){

        List<IncomeDetailsVM> incomeDetailsVM = new ArrayList<>();
        BidPlanSM bidPlanSM = BidPlanForm.toEntity(form);
        PageWhere pageWhere = new PageWhere(pageIndex, pageSize);
        List<TbIncomeDetail> incomeDetails = incomeDetailService.selectPostLoanDetail(bidPlanSM, pageWhere);
        incomeDetails.forEach(o -> incomeDetailsVM.add(new IncomeDetailsVM(o)));
        int count = incomeDetailService.selectPostLoanDetailCount(bidPlanSM);

        return new JsonResultList<>(incomeDetailsVM, count);
    }


}
