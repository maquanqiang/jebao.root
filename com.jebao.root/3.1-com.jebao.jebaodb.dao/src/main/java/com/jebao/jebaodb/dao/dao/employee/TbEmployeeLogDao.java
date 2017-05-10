package com.jebao.jebaodb.dao.dao.employee;

import com.jebao.jebaodb.dao.mapper.employee.TbEmployeeLogMapper;
import com.jebao.jebaodb.entity.employee.TbEmployeeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TbEmployeeLogDao {
    @Autowired
    private TbEmployeeLogMapper mapper;

    public int insert(TbEmployeeLog record){return mapper.insert(record);}

    public TbEmployeeLog selectByPrimaryKey(Integer elId){return mapper.selectByPrimaryKey(elId);}

    public int updateByPrimaryKeySelective(TbEmployeeLog record){return mapper.updateByPrimaryKeySelective(record);}

    public int updateByPrimaryKey(TbEmployeeLog record){return mapper.updateByPrimaryKey(record);}
}