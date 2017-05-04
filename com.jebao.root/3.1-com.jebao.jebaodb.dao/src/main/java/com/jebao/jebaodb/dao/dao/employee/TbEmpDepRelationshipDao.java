package com.jebao.jebaodb.dao.dao.employee;

import com.jebao.jebaodb.dao.mapper.employee.TbEmpDepRelationshipMapper;
import com.jebao.jebaodb.entity.employee.TbEmpDepRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TbEmpDepRelationshipDao {
    @Autowired
    private TbEmpDepRelationshipMapper mapper;

    public int insert(TbEmpDepRelationship record){return mapper.insert(record);}

    public TbEmpDepRelationship selectByPrimaryKey(Integer edrId){return mapper.selectByPrimaryKey(edrId);}

    public int updateByPrimaryKeySelective(TbEmpDepRelationship record){return mapper.updateByPrimaryKeySelective(record);}

    public int updateByPrimaryKey(TbEmpDepRelationship record){return mapper.updateByPrimaryKey(record);}

    /**
     * 查询员工的当前部门关系
     * @param empId 员工id
     * @return 当前员工所属部门关系
     */
    public TbEmpDepRelationship selectCurrentEmpRelation(Integer empId){return mapper.selectCurrentEmpRelation(empId);}

}