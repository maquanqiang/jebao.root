package com.jebao.p2p.web.api.controllerApi.user;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.*;
import com.jebao.p2p.service.inf.user.IInvestServiceInf;
import com.jebao.p2p.web.api.controllerApi._BaseController;
import com.jebao.p2p.web.api.requestModel.user.InvestSM;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultData;
import com.jebao.p2p.web.api.responseModel.base.JsonResultList;
import com.jebao.p2p.web.api.responseModel.user.InvestIngVM;
import com.jebao.p2p.web.api.responseModel.user.InvestPaymentIngVM;
import com.jebao.p2p.web.api.responseModel.user.InvestPaymentedVM;
import com.jebao.p2p.web.api.responseModel.user.InvestStatisticsVM;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.CurrentUserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
@RestController
@RequestMapping("/api/invest/")
public class InvestController extends _BaseController {
    @Autowired
    private IInvestServiceInf investService;

    /**
     * 账户总览-资金统计
     *
     * @return
     */
    @RequestMapping(value = "statistics", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult statistics() {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return new JsonResultData<>(null);
        }
        Map<String, BigDecimal> map = investService.getInvestStatisticsByLoginId(currentUser.getId());
        InvestStatisticsVM viewModel = new InvestStatisticsVM(map);
        return new JsonResultData<>(viewModel);
    }

    /**
     * 账户总览-投资中,还款中的项目
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(int freezeStatus) {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return new JsonResultList<>(null);
        }

        PageWhere page = new PageWhere(0, 2);

        TbInvestInfo record = new TbInvestInfo();
        record.setIiLoginId(currentUser.getId());

        if (freezeStatus == EnumModel.FreezeStatus.冻结中.getValue()) {//投资中
            record.setIiFreezeStatus(EnumModel.FreezeStatus.冻结中.getValue());
            List<InvestBase> investIngList = investService.selectInvestBaseByLoginId(record, page);
            if (investIngList == null || investIngList.size() == 0) {
                return new JsonResultList<>(null);
            }

            List<InvestIngVM> viewModelList = new ArrayList<>();
            investIngList.forEach(o -> viewModelList.add(new InvestIngVM(o)));
            return new JsonResultList<>(viewModelList);
        } else {//还款中
            record.setIiFreezeStatus(EnumModel.FreezeStatus.还款中.getValue());
            List<InvestBase> baseList = investService.selectInvestBaseByLoginId(record, page);
            if (baseList == null || baseList.size() == 0) {
                return new JsonResultList<>(null);
            }
            List<Long> iiIds = new ArrayList<>();
            baseList.forEach(o -> iiIds.add(o.getIiId()));
            List<InvestPaymentIngVM> vmList = new ArrayList<>();
            List<InvestPayment> factList = investService.selectPaymentByIds(iiIds, EnumModel.IncomeStatus.未还.getValue(), EnumModel.FundType.本金.getValue());
            List<InvestPayment> makeList = investService.selectPaymentByIds(iiIds, EnumModel.IncomeStatus.未还.getValue(), EnumModel.FundType.利息.getValue());
            for (int i = 0; i < baseList.size(); i++) {
                InvestPaymentIngVM vm = new InvestPaymentIngVM(baseList.get(i));
                BigDecimal dueMoney = new BigDecimal(0);
                for (int j = 0; j < makeList.size(); j++) {
                    if (makeList.get(j).getIiId().equals(vm.getIiId())) {
                        dueMoney = makeList.get(j).getTotalMoney();
                        break;
                    }
                }
                for (int j = 0; j < factList.size(); j++) {
                    if (factList.get(j).getIiId().equals(vm.getIiId())) {
                        vm.setDueMoney(dueMoney.add(factList.get(j).getTotalMoney()));
                        vm.setNextDueDate(factList.get(j).getDateTime());
                        break;
                    }
                }
                vmList.add(vm);
            }
            return new JsonResultList<>(vmList);
        }
    }

    /**
     * 投资记录
     *
     * @return
     */
    @RequestMapping(value = "record", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult record(InvestSM model) {
        if (model == null) {
            return new JsonResultList<>(null);
        }
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {
            return new JsonResultList<>(null);
        }
        TbInvestInfo record = new TbInvestInfo();
        record.setIiLoginId(currentUser.getId());
        PageWhere page = new PageWhere(model.getPageIndex(), model.getPageSize());

        if (model.getFreezeStatus() == EnumModel.FreezeStatus.冻结中.getValue()) {//投资中
            record.setIiFreezeStatus(EnumModel.FreezeStatus.冻结中.getValue());
            List<InvestBase> investIngList = investService.selectInvestBaseByLoginId(record, page);
            if (investIngList == null || investIngList.size() == 0) {
                return new JsonResultList<>(null);
            }
            List<InvestIngVM> viewModelList = new ArrayList<>();
            investIngList.forEach(o -> viewModelList.add(new InvestIngVM(o)));

            int count = 0;
            if (model.getPageIndex() == 0) {
                count = investService.selectInvestBaseByLoginIdForPageCount(record);
            }
            return new JsonResultList<>(viewModelList, count);
        } else if (model.getFreezeStatus() == EnumModel.FreezeStatus.还款中.getValue()) {//还款中
            record.setIiFreezeStatus(EnumModel.FreezeStatus.还款中.getValue());
            List<InvestBase> baseList = investService.selectInvestBaseByLoginId(record, page);
            if (baseList == null || baseList.size() == 0) {
                return new JsonResultList<>(null);
            }
            List<Long> iiIds = new ArrayList<>();
            baseList.forEach(o -> iiIds.add(o.getIiId()));

            List<InvestPaymentIngVM> vmList = new ArrayList<>();

            List<InvestPayment> factList = investService.selectPaymentByIds(iiIds, EnumModel.IncomeStatus.未还.getValue(), EnumModel.FundType.本金.getValue());
            List<InvestPayment> makeList = investService.selectPaymentByIds(iiIds, EnumModel.IncomeStatus.未还.getValue(), EnumModel.FundType.利息.getValue());
            for (int i = 0; i < baseList.size(); i++) {
                InvestPaymentIngVM vm = new InvestPaymentIngVM(baseList.get(i));
                BigDecimal dueMoney = new BigDecimal(0);
                for (int j = 0; j < makeList.size(); j++) {
                    if (makeList.get(j).getIiId().equals(vm.getIiId())) {
                        dueMoney = makeList.get(j).getTotalMoney();
                        break;
                    }
                }
                for (int j = 0; j < factList.size(); j++) {
                    if (factList.get(j).getIiId().equals(vm.getIiId())) {
                        vm.setDueMoney(dueMoney.add(factList.get(j).getTotalMoney()));
                        vm.setNextDueDate(factList.get(j).getDateTime());
                        break;
                    }
                }
                vmList.add(vm);
            }

            int count = 0;
            if (model.getPageIndex() == 0) {
                count = investService.selectInvestBaseByLoginIdForPageCount(record);
            }
            return new JsonResultList<>(vmList, count);
        } else {//已还款
            record.setIiFreezeStatus(EnumModel.FreezeStatus.已还款.getValue());
            List<InvestBase> baseList = investService.selectInvestBaseByLoginId(record, page);
            if (baseList == null || baseList.size() == 0) {
                return new JsonResultList<>(null);
            }
            List<Long> iiIds = new ArrayList<>();
            baseList.forEach(o -> iiIds.add(o.getIiId()));

            List<InvestPaymentedVM> vmList = new ArrayList<>();

            List<InvestPayment> factList = investService.selectPaymentByIds(iiIds, EnumModel.IncomeStatus.已还.getValue(), EnumModel.FundType.本金.getValue());
            List<InvestPayment> makeList = investService.selectPaymentByIds(iiIds, EnumModel.IncomeStatus.已还.getValue(), EnumModel.FundType.利息.getValue());

            for (int i = 0; i < baseList.size(); i++) {
                InvestPaymentedVM vm = new InvestPaymentedVM(baseList.get(i));
                for (int j = 0; j < factList.size(); j++) {
                    if (factList.get(j).getIiId().equals(vm.getIiId())) {
                        vm.setFactMoeny(factList.get(j).getTotalMoney());
                        vm.setSettleDate(factList.get(j).getDateTime());
                        break;
                    }
                }
                for (int j = 0; j < makeList.size(); j++) {
                    if (makeList.get(j).getIiId().equals(vm.getIiId())) {
                        vm.setMakeMoney(makeList.get(j).getTotalMoney());
                        break;
                    }
                }
                vmList.add(vm);
            }

            int count = 0;
            if (model.getPageIndex() == 0) {
                count = investService.selectInvestBaseByLoginIdForPageCount(record);
            }
            return new JsonResultList<>(vmList, count);
        }
    }
}