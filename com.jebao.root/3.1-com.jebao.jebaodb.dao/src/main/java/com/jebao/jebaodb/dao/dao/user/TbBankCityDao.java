package com.jebao.jebaodb.dao.dao.user;

import com.jebao.jebaodb.dao.mapper.user.TbBankCityMapper;
import com.jebao.jebaodb.entity.user.TbBankCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/11/15.
 */
@Repository
public class TbBankCityDao {
    @Autowired
    private TbBankCityMapper tbBankCityMapper;

    public int insert(TbBankCity record) {
        return tbBankCityMapper.insert(record);
    }
    public int insertSelective(TbBankCity record) {
        return tbBankCityMapper.insertSelective(record);
    }
    public TbBankCity selectByPrimaryKey(Integer bcCityId)
    {
        return tbBankCityMapper.selectByPrimaryKey(bcCityId);
    }
    public int updateByPrimaryKeySelective(TbBankCity record)
    {
        return tbBankCityMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbBankCity record)
    {
        return tbBankCityMapper.updateByPrimaryKey(record);
    }
}
