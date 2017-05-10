package com.jebao.erp.web.controllerApi.bidplan;

import com.jebao.erp.service.inf.loanmanage.ITbBidRiskDataServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.requestModel.bidplan.RiskDataForm;
import com.jebao.erp.web.requestModel.loaner.RcpMaterialsTempAF;
import com.jebao.erp.web.responseModel.base.*;
import com.jebao.erp.web.responseModel.bidplan.RiskDataVM;
import com.jebao.erp.web.responseModel.loaner.RcpMaterialsTempVM;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2016/11/26.
 */
@Controller
@RequestMapping("api/bidRiskData")
public class BidRiskDataControllerApi extends _BaseController {

    @Autowired
    private ITbBidRiskDataServiceInf bidRiskDataService;

    @RequestMapping("addRiskData")
    @ResponseBody
    public JsonResult addRiskData(RiskDataForm form) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg());
        }

        TbBidRiskData tbBidRiskData = RiskDataForm.toEntity(form);
        tbBidRiskData.setBrdIsDel(1);
        Date date = new Date();
        tbBidRiskData.setBrdCreateTime(date);
        tbBidRiskData.setBrdUpdateTime(date);
        int result =bidRiskDataService.add(tbBidRiskData);

        if (result > 0) {
            return new JsonResultOk("添加成功");
        } else {
            return new JsonResultError("添加失败");
        }
    }

    @RequestMapping("removeRiskData")
    @ResponseBody
    public JsonResult removeRiskData(Long id){
        TbBidRiskData tbBidRiskData = new TbBidRiskData();
        tbBidRiskData.setBrdId(id);
        tbBidRiskData.setBrdIsDel(2);
        int result = bidRiskDataService.updateByBidIdSelective(tbBidRiskData);
        if(result>0){
            return new JsonResultOk("删除成功");
        }else {
            return new JsonResultError("删除失败");
        }
    }

    @RequestMapping("getRiskDataListForPage")
    @ResponseBody
    public JsonResult getRiskDataListForPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "rows", defaultValue = "10") Integer rows, Long bpId){
        TbBidRiskData riskData = new TbBidRiskData();
        riskData.setBrdBpId(bpId);
        PageWhere pw = new PageWhere(page, rows);
        List<TbBidRiskData> riskDatas = bidRiskDataService.selectByConditionForPage(riskData, pw);
        int count = bidRiskDataService.selectByConditionCount(riskData);

        List<RiskDataVM> dataVMs = new ArrayList<RiskDataVM>();
        riskDatas.forEach(o -> dataVMs.add(new RiskDataVM(o)));
        return new JsonResultList<>(dataVMs, count);
    }


    @RequestMapping(value = "getMaterials", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getMaterials(Long bpId){
        if(bpId == null || bpId == 0){
            return new JsonResultData<>(null);
        }
        TbBidRiskData tbBidRiskData = bidRiskDataService.selectByBpId(bpId);
        RiskDataVM viewModel = new RiskDataVM(tbBidRiskData);
        return new JsonResultData<>(viewModel);
    }
}
