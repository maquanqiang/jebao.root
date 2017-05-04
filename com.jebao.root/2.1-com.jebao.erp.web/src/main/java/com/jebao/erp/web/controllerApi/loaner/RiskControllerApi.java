package com.jebao.erp.web.controllerApi.loaner;

import com.jebao.erp.service.inf.loaner.ILoanerServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.requestModel.loaner.RcpMaterialsTempAF;
import com.jebao.erp.web.requestModel.loaner.RiskCtlPrjAF;
import com.jebao.erp.web.responseModel.base.*;
import com.jebao.erp.web.responseModel.loaner.RcpMaterialsTempVM;
import com.jebao.erp.web.responseModel.loaner.RiskCtlPrjMTempVM;
import com.jebao.erp.web.responseModel.loaner.RiskCtlPrjTempListVM;
import com.jebao.erp.web.responseModel.loaner.RiskCtlPrjTempVM;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
@RestController
@RequestMapping("api/risk/")
public class RiskControllerApi extends _BaseController {
    @Autowired
    private ILoanerServiceInf loanerService;

    //region 风控api
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(Long loanerId){
        if(loanerId == null || loanerId == 0){
            return new JsonResultList<>(null);
        }
        List<TbRiskCtlPrjTemp> riskctlprjList = loanerService.selectRiskCtlPrjTempByLoanerIdForPage(loanerId,null);
        List<RiskCtlPrjTempListVM> viewModelList = new ArrayList<>();
        riskctlprjList.forEach(o -> viewModelList.add(new RiskCtlPrjTempListVM(o)));
        return new JsonResultList<>(viewModelList);
    }

    @RequestMapping("delete")
    public @ResponseBody JsonResult delete(Long id){
        if(id == null || id == 0){
            return new JsonResultData<>(null);
        }
        int result = loanerService.deleteRiskCtlPrjTempById(id);
        if (result > 0) {
            return new JsonResultOk("删除成功");
        } else {
            return new JsonResultError("删除失败");
        }
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult details(Long rcptId){
        if(rcptId == null || rcptId == 0){
            return new JsonResultData<>(null);
        }
        TbRiskCtlPrjTemp rcpt = loanerService.findRiskCtlPrjTempById(rcptId);
        List<TbRcpMaterialsTemp> rcpmtList = loanerService.selectRcpMaterialsTempByPrjIdForPage(rcptId,null);
        RiskCtlPrjMTempVM viewModel = new RiskCtlPrjMTempVM();
        viewModel.setRcpmtList(rcpmtList);
        viewModel.setBorrowDesc(rcpt.getRcptBorrowDesc());
        viewModel.setCreateTime(rcpt.getRcptCreateTime());
        viewModel.setDesc(rcpt.getRcptDesc());
        viewModel.setFundsPurpose(rcpt.getRcptFundsPurpose());
        viewModel.setId(rcpt.getRcptId());
        viewModel.setIsDel(rcpt.getRcptIsDel());
        viewModel.setLoanerId(rcpt.getRcptLoanerId());
        viewModel.setMortgageInfo(rcpt.getRcptMortgageInfo());
        viewModel.setName(rcpt.getRcptName());
        viewModel.setOpinion(rcpt.getRcptOpinion());
        viewModel.setPersonalCredit(rcpt.getRcptPersonalCredit());
        viewModel.setRepayingSource(rcpt.getRcptRepayingSource());
        viewModel.setUpdateTime(rcpt.getRcptUpdateTime());
        return new JsonResultData<>(viewModel);
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult info(Long rcptId){
        if(rcptId == null || rcptId == 0){
            return new JsonResultData<>(null);
        }
        TbRiskCtlPrjTemp rcpt = loanerService.findRiskCtlPrjTempById(rcptId);
        RiskCtlPrjTempVM viewModel = new RiskCtlPrjTempVM(rcpt);
        return new JsonResultData<>(viewModel);
    }

    @RequestMapping(value = "post",method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody JsonResult post(RiskCtlPrjAF form) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg());
        }

        //todo 编辑风控项目逻辑实现
        //todo 实际的业务逻辑
        TbRiskCtlPrjTemp entity = new TbRiskCtlPrjTemp();
        entity.setRcptLoanerId(form.getLoanerId());
        entity.setRcptBorrowDesc(form.getBorrowDesc());
        entity.setRcptDesc(form.getDesc());
        entity.setRcptFundsPurpose(form.getFundsPurpose());
        entity.setRcptMortgageInfo(form.getMortgageInfo());
        entity.setRcptOpinion(form.getOpinion());
        entity.setRcptName(form.getName());
        entity.setRcptPersonalCredit(form.getPersonalCredit());
        entity.setRcptRepayingSource(form.getRepayingSource());

        int result;
        if(form.getId() == 0) {
            entity.setRcptCreateTime(new Date());
            entity.setRcptUpdateTime(new Date());
            entity.setRcptIsDel(EnumModel.IsDel.有效.getValue());
            result = loanerService.addRiskCtlPrjTemp(entity);
        }else{
            entity.setRcptId(form.getId());
            entity.setRcptUpdateTime(new Date());
            result = loanerService.updateRiskCtlPrjTemp(entity);
        }
        //
        if (result > 0) {
            return new JsonResultOk("保存成功");
        } else {
            return new JsonResultError("保存失败");
        }
    }
    //endregion

    //region 材料api
    @RequestMapping(value = "materialsList", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult materialsList(Long rcptId){
        if(rcptId == null || rcptId == 0){
            return new JsonResultList<>(null);
        }
        List<TbRcpMaterialsTemp> rcpmtList = loanerService.selectRcpMaterialsTempByPrjIdForPage(rcptId,null);
        List<RcpMaterialsTempVM> viewModelList = new ArrayList<>();
        rcpmtList.forEach(o -> viewModelList.add(new RcpMaterialsTempVM(o)));
        return new JsonResultList<>(viewModelList);
    }

    @RequestMapping(value = "materials", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult materials(Long id){
        if(id == null || id == 0){
            return new JsonResultData<>(null);
        }
        TbRcpMaterialsTemp model = loanerService.findRcpMaterialsTempById(id);
        RcpMaterialsTempVM viewModel = new RcpMaterialsTempVM(model);
        return new JsonResultData<>(viewModel);
    }

    @RequestMapping("deleteMaterials")
    public @ResponseBody JsonResult deleteMaterials(Long id){
        if(id == null || id == 0){
            return new JsonResultData<>(null);
        }
        int result = loanerService.deleteRcpMaterialsTempById(id);
        if (result > 0) {
            return new JsonResultOk("删除成功");
        } else {
            return new JsonResultError("删除失败");
        }
    }

    @RequestMapping(value = "addMaterials", produces = "application/json")
    public @ResponseBody JsonResult addMaterials(RcpMaterialsTempAF form) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg());
        }

        //todo 添加风控项目材料逻辑实现
        //todo 实际的业务逻辑
        TbRcpMaterialsTemp entity = new TbRcpMaterialsTemp();
        entity.setRcpmtProjectId(form.getProjectId());
        entity.setRcpmtName(form.getName());
        entity.setRcpmtNo(form.getNo());
        entity.setRcpmtPath(form.getPath());
        entity.setRcpmtRemark(form.getRemark());
        entity.setRcpmtUrl(form.getUrl());
        entity.setRcpmtIsDel(EnumModel.IsDel.有效.getValue());
        entity.setRcpmtCreateTime(new Date());
        int result = loanerService.addRcpMaterialsTemp(entity);
        //
        if (result > 0) {
            return new JsonResultOk("添加成功");
        } else {
            return new JsonResultError("添加失败");
        }
    }
    //endregion
}
