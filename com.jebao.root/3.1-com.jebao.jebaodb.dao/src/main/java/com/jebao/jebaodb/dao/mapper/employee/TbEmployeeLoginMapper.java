package com.jebao.jebaodb.dao.mapper.employee;

import com.jebao.jebaodb.entity.employee.TbEmployeeLogin;

public interface TbEmployeeLoginMapper {
    int insert(TbEmployeeLogin record);

    int insertSelective(TbEmployeeLogin record);

    TbEmployeeLogin selectByPrimaryKey(Integer lgId);

    int updateByPrimaryKeySelective(TbEmployeeLogin record);

    int updateByPrimaryKey(TbEmployeeLogin record);


    int deleteEmployeeLogin(int empId);
    TbEmployeeLogin selectByUsername(String username);

}