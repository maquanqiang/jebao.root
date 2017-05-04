package com.jebao.erp.web.controllerApi.loaner;

import com.jebao.erp.service.inf.investment.IIncomeDetailServiceInf;
import com.jebao.erp.service.inf.loanmanage.ITbBidPlanServiceInf;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultData;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.loaner.LoanRecordSumVM;
import com.jebao.erp.web.responseModel.loaner.LoanRecordVM;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.loaner.LoanTotal;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanExtSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
@RestController
@RequestMapping("api/loanrecord/")
public class LoanRecordControllerApi {
    @Autowired
    private ITbBidPlanServiceInf tbBidPlanService;
    @Autowired
    private IIncomeDetailServiceInf incomeDetailService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(BidPlanExtSM model) {
        if (model == null) {
            return new JsonResultList<>(null);
        }
        model.setBpStatus(EnumModel.BidStatus.还款中.getValue());
        List<TbBidPlan> planList = tbBidPlanService.selectLoanRecordByLoanerIdForPage(model);
        if(planList == null || planList.size() == 0){
            return new JsonResultList<>(null);
        }

        List<LoanRecordVM> viewModelList = new ArrayList<>();
        planList.forEach(o -> viewModelList.add(new LoanRecordVM(o)));

        int count = 0;
        if (model.getPageIndex() == 0) {
            count = tbBidPlanService.selectLoanRecordByLoanerIdForPageCount(model);
        }
        return new JsonResultList<>(viewModelList, count);
    }

    @RequestMapping(value = "statistics", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult statistics(Long loanerId) {
        if (loanerId == null || loanerId == 0) {
            return new JsonResultData<>(null);
        }

        LoanTotal loanTotal = tbBidPlanService.totalLoanByLoanerId(loanerId);
        if (loanTotal == null) {
            return new JsonResultData<>(null);
        }
        LoanRecordSumVM viewModel = new LoanRecordSumVM();
        viewModel.setJkCount(loanTotal.getTotalTrades());
        viewModel.setJkAmounts(loanTotal.getTotalAmounts().setScale(2, BigDecimal.ROUND_HALF_UP));
        viewModel.setDhAmounts(incomeDetailService.totalMoneyByloanerId(loanerId, EnumModel.FundType.全部.getValue(), EnumModel.IncomeStatus.未还.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
        viewModel.setYhAmounts(incomeDetailService.totalMoneyByloanerId(loanerId, EnumModel.FundType.全部.getValue(), EnumModel.IncomeStatus.已还.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
        return new JsonResultData<>(viewModel);
    }
}
