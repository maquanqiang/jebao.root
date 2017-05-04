package com.jebao.jebaodb.dao.mapper.user;

import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbLoginInfoMapper {
    int insert(TbLoginInfo record);

    int insertSelective(TbLoginInfo record);

    TbLoginInfo selectByPrimaryKey(Long liId);

    int updateByPrimaryKeySelective(TbLoginInfo record);

    int updateByPrimaryKey(TbLoginInfo record);

    /* ==================================================华丽分割线==================================================*/
    int deleteByPrimaryKey(Long liId);

    List<TbLoginInfo> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    TbLoginInfo selectByLoginName(String liLoginName);
}