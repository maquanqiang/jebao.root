package com.jebao.erp.web.controllerApi.employee;

import com.jebao.erp.service.inf.employee.IRankServiceInf;
import com.jebao.erp.web.requestModel.employee.RankIM;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultError;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.base.JsonResultOk;
import com.jebao.erp.web.responseModel.employee.RankVM;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import com.jebao.jebaodb.entity.employee.TbRank;
import com.jebao.jebaodb.entity.employee.search.RankSM;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2016/11/21.
 */
@RestController
@RequestMapping("api/rank")
public class RankControllerApi {
    @Autowired
    private IRankServiceInf rankService;

    @RequestMapping("list")
    public JsonResult list(RankSM model)
    {
        List<TbRank> rankList = rankService.getRankList(model);
        List<RankVM> vmList = new ArrayList<>();
        rankList.forEach(o->vmList.add(new RankVM(o)));
        return new JsonResultList<>(vmList);
    }

    @RequestMapping("post")
    public JsonResult post(RankIM model){
        //region 校验
        ValidationResult resultValidation = ValidationUtil.validateEntity(model);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg().toString());
        }
        //endregion
        TbRank entityModel = model.toEntity();
        ResultInfo resultInfo = rankService.save(entityModel);
        if (resultInfo.getSuccess_is_ok()){
            return new JsonResultOk(resultInfo.getMsg());
        }
        return new JsonResultError(resultInfo.getMsg());
    }
    @RequestMapping("delete")
    public JsonResult delete(int id){
        ResultInfo resultInfo = rankService.delete(id);
        if (resultInfo.getSuccess_is_ok()){
            return new JsonResultOk(resultInfo.getMsg());
        }
        return new JsonResultError(resultInfo.getMsg());
    }

}
