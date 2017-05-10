package com.jebao.jebaodb.dao.mapper.user;

import com.jebao.jebaodb.entity.user.TbAccountsFunds;

public interface TbAccountsFundsMapper {
    int insert(TbAccountsFunds record);

    int insertSelective(TbAccountsFunds record);

    TbAccountsFunds selectByPrimaryKey(Long afId);

    int updateByPrimaryKeySelective(TbAccountsFunds record);

    int updateByPrimaryKey(TbAccountsFunds record);

    /* ==================================================华丽分割线==================================================*/
    int updateByLoginId(TbAccountsFunds record);

    int deleteByLoginId(Long afLoginId);

    TbAccountsFunds selectByLoginId(Long afLoginId);
}