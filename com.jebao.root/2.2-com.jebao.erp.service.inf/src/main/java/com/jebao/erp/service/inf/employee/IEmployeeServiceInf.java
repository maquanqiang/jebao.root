package com.jebao.erp.service.inf.employee;

import com.jebao.jebaodb.entity.employee.EmpPerformanceInfo;
import com.jebao.jebaodb.entity.employee.EmployeeInfo;
import com.jebao.jebaodb.entity.employee.input.EmployeeIM;
import com.jebao.jebaodb.entity.employee.search.EmployeeSM;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;

import java.util.List;

/**
 * Created by Jack on 2016/11/16.
 */
public interface IEmployeeServiceInf {

    /**
     * 获取员工信息
     * @return
     */
    List<EmployeeInfo> getEmployeeInfoList(EmployeeSM model);
    int getEmployeeInfoListCount(EmployeeSM model);

    /**
     * 保存员工信息（新增或修改）
     * @param model 输入员工信息
     * @return 执行结果
     */
    ResultInfo saveEmployeeInfo(EmployeeIM model);

    /**
     * 删除员工信息
     * @param empId 员工id
     * @param userId 操作用户id
     * @return 执行结果
     */
    ResultInfo deleteEmployeeInfo(int empId,int userId);

    /**
     * 获取员工绩效信息
     */
    List<EmpPerformanceInfo> getEmpPerformance(EmployeeSM model);
    int getEmpPerformanceCount(EmployeeSM model);

}
