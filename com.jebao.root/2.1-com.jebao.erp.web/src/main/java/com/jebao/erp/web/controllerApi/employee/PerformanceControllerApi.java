package com.jebao.erp.web.controllerApi.employee;

import com.jebao.erp.service.inf.employee.IEmployeeServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.employee.PerformanceVM;
import com.jebao.jebaodb.entity.employee.EmpPerformanceInfo;
import com.jebao.jebaodb.entity.employee.search.EmployeeSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017/1/4.
 */
@RestController
@RequestMapping("api/performance/")
public class PerformanceControllerApi extends _BaseController {
    @Autowired
    private IEmployeeServiceInf employeeService;

    @RequestMapping("list")
    public JsonResult list(EmployeeSM model) {
        if (model==null){return new JsonResultList<>(null);}

        List<EmpPerformanceInfo> entityList = employeeService.getEmpPerformance(model);
        List<PerformanceVM> viewModelList = new ArrayList<>();
        entityList.forEach(o -> viewModelList.add(new PerformanceVM(o)));

        int count=0;
        if (model.getPageIndex()==0){
            count = employeeService.getEmpPerformanceCount(model);
        }

        return new JsonResultList<>(viewModelList,count);
    }

}
