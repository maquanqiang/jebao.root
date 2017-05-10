package com.jebao.jebaodb.dao.dao.user;

import com.jebao.jebaodb.dao.mapper.user.TbAccountsFundsMapper;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/10.
 */
@Repository
public class TbAccountsFundsDao {
    @Autowired
    private TbAccountsFundsMapper tbAccountsFundsMapper;

    public int insert(TbAccountsFunds record) {
        return tbAccountsFundsMapper.insert(record);
    }

    public int insertSelective(TbAccountsFunds record) {
        return tbAccountsFundsMapper.insertSelective(record);
    }

    public TbAccountsFunds selectByPrimaryKey(Long afId) {
        return tbAccountsFundsMapper.selectByPrimaryKey(afId);
    }

    public int updateByPrimaryKeySelective(TbAccountsFunds record) {
        return tbAccountsFundsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TbAccountsFunds record) {
        return tbAccountsFundsMapper.updateByPrimaryKey(record);
    }

    /* ==================================================华丽分割线==================================================*/
    public int updateByLoginId(TbAccountsFunds record){
        return tbAccountsFundsMapper.updateByLoginId(record);
    }

    public int deleteByLoginId(Long afLoginId) {
        return tbAccountsFundsMapper.deleteByLoginId(afLoginId);
    }

    public TbAccountsFunds selectByLoginId(Long afLoginId) {
        return tbAccountsFundsMapper.selectByLoginId(afLoginId);
    }
}
