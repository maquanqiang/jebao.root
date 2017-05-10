package com.jebao.jebaodb.dao.dao.employee;

import com.jebao.jebaodb.dao.mapper.employee.TbEmployeeLoginMapper;
import com.jebao.jebaodb.entity.employee.TbEmployeeLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TbEmployeeLoginDao {
    @Autowired
    private TbEmployeeLoginMapper mapper;
    public int insert(TbEmployeeLogin record){return mapper.insert(record);}


    public TbEmployeeLogin selectByPrimaryKey(Integer lgId){return mapper.selectByPrimaryKey(lgId);}

    public int updateByPrimaryKeySelective(TbEmployeeLogin record){return mapper.updateByPrimaryKeySelective(record);}

    public int updateByPrimaryKey(TbEmployeeLogin record){return mapper.updateByPrimaryKey(record);}


    public int deleteEmployeeLogin(int empId){
        return mapper.deleteEmployeeLogin(empId);
    }

    public TbEmployeeLogin selectByUsername(String username)
    {
        return mapper.selectByUsername(username);
    }

}