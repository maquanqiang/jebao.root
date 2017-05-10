package com.jebao.jebaodb.dao.mapper.employee;

import com.jebao.jebaodb.entity.employee.TbEmpRankRelationship;

public interface TbEmpRankRelationshipMapper {
    int insert(TbEmpRankRelationship record);

    int insertSelective(TbEmpRankRelationship record);

    TbEmpRankRelationship selectByPrimaryKey(Integer errId);

    int updateByPrimaryKeySelective(TbEmpRankRelationship record);

    int updateByPrimaryKey(TbEmpRankRelationship record);
    //-----------------------------------------------------

    /**
     * 查询员工的当前职级关系
     * @param empId 员工id
     * @return 当前员工职级关系
     */
    TbEmpRankRelationship selectCurrentEmpRelation(Integer empId);
}