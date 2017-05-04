package com.jebao.jebaodb.dao.dao.employee;

import com.jebao.jebaodb.dao.mapper.employee.TbEmpRankRelationshipMapper;
import com.jebao.jebaodb.entity.employee.TbEmpRankRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TbEmpRankRelationshipDao {
    @Autowired
    private TbEmpRankRelationshipMapper mapper;

    public int insert(TbEmpRankRelationship record){return mapper.insert(record);}

    public TbEmpRankRelationship selectByPrimaryKey(Integer errId){return mapper.selectByPrimaryKey(errId);}

    public int updateByPrimaryKeySelective(TbEmpRankRelationship record){return mapper.updateByPrimaryKeySelective(record);}

    public int updateByPrimaryKey(TbEmpRankRelationship record){return mapper.updateByPrimaryKey(record);}
    /**
     * 查询员工的当前职级关系
     * @param empId 员工id
     * @return 当前员工职级关系
     */
    public TbEmpRankRelationship selectCurrentEmpRelation(Integer empId){return mapper.selectCurrentEmpRelation(empId);}

}