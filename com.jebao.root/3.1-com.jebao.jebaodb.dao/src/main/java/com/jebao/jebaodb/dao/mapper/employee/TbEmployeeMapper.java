package com.jebao.jebaodb.dao.mapper.employee;

import com.jebao.jebaodb.entity.employee.EmpPerformanceInfo;
import com.jebao.jebaodb.entity.employee.EmployeeInfo;
import com.jebao.jebaodb.entity.employee.TbEmployee;
import com.jebao.jebaodb.entity.employee.search.EmployeeSM;

import java.util.List;

public interface TbEmployeeMapper {
    int insert(TbEmployee record);

    int insertSelective(TbEmployee record);

    TbEmployee selectByPrimaryKey(Integer empId);

    int updateByPrimaryKeySelective(TbEmployee record);

    int updateByPrimaryKey(TbEmployee record);
    //==================================================华丽分割线==================================================
    /**
     * 获取员工详细信息
     */
    List<EmployeeInfo> selectEmployeeDetailsInfo(EmployeeSM model);
    Integer selectEmployeeDetailsInfoCount(EmployeeSM model);

    /**
     * 查询员工业绩信息
     * @param model
     * @return
     */
    List<EmpPerformanceInfo> selectEmpPerformance(EmployeeSM model);
    int selectEmpPerformanceCount(EmployeeSM model);
}