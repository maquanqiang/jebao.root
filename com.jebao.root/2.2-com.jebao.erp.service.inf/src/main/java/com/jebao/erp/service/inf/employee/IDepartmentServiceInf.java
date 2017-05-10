package com.jebao.erp.service.inf.employee;

import com.jebao.jebaodb.entity.employee.TbDepartment;
import com.jebao.jebaodb.entity.employee.search.DepartmentSM;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;

import java.util.List;

/**
 * Created by Jack on 2016/11/21.
 */
public interface IDepartmentServiceInf {
    List<TbDepartment> getDepartmentList(DepartmentSM model);
    int getDepartmentListCount(DepartmentSM model);

    ResultInfo save(TbDepartment model);
    ResultInfo delete(int id);
}
