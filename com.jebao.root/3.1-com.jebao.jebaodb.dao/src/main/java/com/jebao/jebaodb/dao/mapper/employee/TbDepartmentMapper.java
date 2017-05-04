package com.jebao.jebaodb.dao.mapper.employee;

import com.jebao.jebaodb.entity.employee.TbDepartment;
import com.jebao.jebaodb.entity.employee.search.DepartmentSM;

import java.util.List;

public interface TbDepartmentMapper {
    int insert(TbDepartment record);

    int insertSelective(TbDepartment record);

    TbDepartment selectByPrimaryKey(Integer depId);

    int updateByPrimaryKeySelective(TbDepartment record);

    int updateByPrimaryKey(TbDepartment record);

    List<TbDepartment> selectList(DepartmentSM model);
    int selectListCount(DepartmentSM model);
    int delete(int depId);

}