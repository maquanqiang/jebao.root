package com.jebao.erp.web.controllerApi.employee;

import com.jebao.erp.service.inf.employee.IDepartmentServiceInf;
import com.jebao.erp.web.requestModel.employee.DepartmentIM;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.employee.DepartmentVM;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import com.jebao.jebaodb.entity.employee.TbDepartment;
import com.jebao.jebaodb.entity.employee.search.DepartmentSM;
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
@RequestMapping("api/department")
public class DepartmentControllerApi {

    @Autowired
    private IDepartmentServiceInf departmentService;

    @RequestMapping("list")
    public JsonResult list(DepartmentSM model) {
        List<TbDepartment> departmentList = departmentService.getDepartmentList(model);
        int count=0;
        if (model.getPageIndex()==0){
            count = departmentService.getDepartmentListCount(model);
        }
        List<DepartmentVM> vmList = new ArrayList<>();
        departmentList.forEach(o->vmList.add(new DepartmentVM(o)));
        return new JsonResultList<>(vmList,count);
    }
    @RequestMapping("post")
    public ResultInfo post(DepartmentIM model){
        //region 校验
        ValidationResult resultValidation = ValidationUtil.validateEntity(model);
        if (resultValidation.isHasErrors()) {
            return new ResultInfo(false,resultValidation.getErrorMsg().toString());
        }
        //endregion
        TbDepartment entityModel = model.toEntity();
        ResultInfo resultInfo = departmentService.save(entityModel);
        return  resultInfo;
    }
    @RequestMapping("delete")
    public ResultInfo delete(int id){
        ResultInfo resultInfo = departmentService.delete(id);
        return resultInfo;
    }
}
