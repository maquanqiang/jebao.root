package com.jebao.jebaodb.dao.mapper.employee;

import com.jebao.jebaodb.entity.employee.TbEmpDepRelationship;

public interface TbEmpDepRelationshipMapper {
    int insert(TbEmpDepRelationship record);

    int insertSelective(TbEmpDepRelationship record);

    TbEmpDepRelationship selectByPrimaryKey(Integer edrId);

    int updateByPrimaryKeySelective(TbEmpDepRelationship record);

    int updateByPrimaryKey(TbEmpDepRelationship record);


    //-----------------------------------------------------

    /**
     * 查询员工的当前部门关系
     * @param empId 员工id
     * @return 当前员工所属部门关系
     */
    TbEmpDepRelationship selectCurrentEmpRelation(Integer empId);

}