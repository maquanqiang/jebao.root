package com.jebao.jebaodb.dao.dao.user;

import com.jebao.jebaodb.dao.mapper.user.TbUserLogMapper;
import com.jebao.jebaodb.entity.user.TbUserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Jack on 2016/12/10.
 */
@Repository
public class TbUserLogDao {
    @Autowired
    private TbUserLogMapper mapper;

    public long insert(TbUserLog record){
        return mapper.insert(record);
    }
}
