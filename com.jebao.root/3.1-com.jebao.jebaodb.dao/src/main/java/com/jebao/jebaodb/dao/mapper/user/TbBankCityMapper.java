package com.jebao.jebaodb.dao.mapper.user;

import com.jebao.jebaodb.entity.user.TbBankCity;

public interface TbBankCityMapper {
    int insert(TbBankCity record);

    int insertSelective(TbBankCity record);

    TbBankCity selectByPrimaryKey(Integer bcCityId);

    int updateByPrimaryKeySelective(TbBankCity record);

    int updateByPrimaryKey(TbBankCity record);
}