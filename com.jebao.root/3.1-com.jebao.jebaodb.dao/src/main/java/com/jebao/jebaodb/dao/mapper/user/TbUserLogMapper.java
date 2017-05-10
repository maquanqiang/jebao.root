package com.jebao.jebaodb.dao.mapper.user;

import com.jebao.jebaodb.entity.user.TbUserLog;

public interface TbUserLogMapper {
    long insert(TbUserLog record);

    int insertSelective(TbUserLog record);

    TbUserLog selectByPrimaryKey(Long ulId);

    int updateByPrimaryKeySelective(TbUserLog record);

    int updateByPrimaryKey(TbUserLog record);
}