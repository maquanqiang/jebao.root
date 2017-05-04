package com.jebao.erp.web.controllerApi.loaner;

import com.jebao.erp.service.inf.loaner.ILoanerServiceInf;
import com.jebao.erp.service.inf.loanmanage.ITbBidPlanServiceInf;
import com.jebao.erp.service.inf.user.IUserDetailsServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.requestModel.loaner.LoanerIM;
import com.jebao.erp.web.requestModel.loaner.LoanerSM;
import com.jebao.erp.web.responseModel.base.*;
import com.jebao.erp.web.responseModel.loaner.LoanerVM;
import com.jebao.erp.web.responseModel.loaner.UserInfoVM;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.LoanTotal;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwei on 2016/11/22.
 */
@RestController
@RequestMapping("api/loaner/")
public class LoanerControllerApi extends _BaseController {
    @Autowired
    private ILoanerServiceInf loanerService;
    @Autowired
    private ITbBidPlanServiceInf bidPlanService;

    @RequestMapping(value = "post", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    JsonResult post(LoanerIM model) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(model);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg());
        }

        //todo 编辑借款人逻辑实现
        //todo 实际的业务逻辑
        TbLoaner entity = new TbLoaner();
        entity.setlPhone(model.getPhone());
        entity.setlHomeAdd(model.getHomeAdd());
        entity.setlHkadr(model.getHkadr());
        entity.setlMaritalStatus(model.getMaritalStatus());
        entity.setlIshaveHouse(model.getIshaveHouse());
        entity.setlIshaveCar(model.getIshaveCar());
        entity.setlPoliticsStatus(model.getPoliticsStatus());
        entity.setlCreditStatus(model.getCreditStatus());
        entity.setlMonthlySalary(model.getMonthlySalary());
        entity.setlEducation(model.getEducation());
        entity.setlWorkCity(model.getWorkCity());
        entity.setlId(model.getId());
        int result = loanerService.saveLoaner(entity);
        if (result > 0) {
            return new JsonResultOk("保存成功");
        } else {
            return new JsonResultError("保存失败");
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult delete(Long id) {
        if (id == null || id == 0) {
            return new JsonResultData<>(null);
        }
        int result = loanerService.deleteLoanerById(id);
        if (result > 0) {
            return new JsonResultOk("删除成功");
        } else {
            return new JsonResultError("删除失败");
        }
    }

    @RequestMapping("list")
    public JsonResult list(LoanerSM model) {
        if (model == null) {
            return new JsonResultList<>(null);
        }

        TbLoaner record = new TbLoaner();
        record.setlNickName(model.getNickName());
        record.setlPhone(model.getPhone());
        record.setlTrueName(model.getTrueName());
        PageWhere page = new PageWhere(model.getPageIndex(), model.getPageSize());
        List<TbLoaner> loanerList = loanerService.selectLoanerByParamsForPage(record, page);

        if (loanerList == null || loanerList.size() == 0) {
            return new JsonResultList<>(null);
        }

        List<LoanerVM> viewModelList = new ArrayList<>();

        List<Long> loanerIds = new ArrayList<>();
        loanerList.forEach(o -> loanerIds.add(o.getlId()));
        List<LoanTotal> loanTotalList = bidPlanService.selectLoanTotalByLoanerIds(loanerIds);

        for(int i = 0; i < loanerList.size(); i++){
            LoanerVM vm = new LoanerVM(loanerList.get(i));
            for (int j=0;j<loanTotalList.size();j++){
                if(loanTotalList.get(j).getLoanerId().equals(vm.getId())){
                    vm.setBorrowCount(loanTotalList.get(j).getTotalTrades());
                    vm.setBorrowAmount(loanTotalList.get(j).getTotalAmounts());
                    break;
                }
            }
            viewModelList.add(vm);
        }

        int count = 0;
        if (model.getPageIndex() == 0) {
            count = loanerService.selectLoanerByParamsForPageCount(record);
        }

        return new JsonResultList<>(viewModelList, count);
    }

    @RequestMapping("doImport")
    public JsonResult doImport(String phone) {
        if (StringUtils.isBlank(phone)) {
            return new JsonResultError("手机号不能为空");
        }
        phone = StringUtils.trim(phone);
        TbLoaner loaner = loanerService.getLoanerByPhone(phone);
        if (loaner == null) {
            return new JsonResultError("未开通第三方账户或该借款人信息已存在");
        }
        UserInfoVM viewModel = new UserInfoVM(loaner);
        return new JsonResultData<>(viewModel);
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult details(Long loanerId) {
        if (loanerId == null || loanerId == 0) {
            return new JsonResultData<>(null);
        }
        TbLoaner loaner = loanerService.findLoanerById(loanerId);
        if (loaner == null) {
            return new JsonResultData<>(null);
        }
        LoanerVM viewModel = new LoanerVM(loaner);
        return new JsonResultData<>(viewModel);
    }
}