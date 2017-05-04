package com.jebao.jebaodb.dao.mapper.employee;

import com.jebao.jebaodb.entity.employee.TbEmployeeLog;

public interface TbEmployeeLogMapper {
    int insert(TbEmployeeLog record);

    int insertSelective(TbEmployeeLog record);

    TbEmployeeLog selectByPrimaryKey(Integer elId);

    int updateByPrimaryKeySelective(TbEmployeeLog record);

    int updateByPrimaryKey(TbEmployeeLog record);
}