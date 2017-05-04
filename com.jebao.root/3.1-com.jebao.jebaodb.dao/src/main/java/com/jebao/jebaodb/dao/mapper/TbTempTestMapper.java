package com.jebao.jebaodb.dao.mapper;

import com.jebao.jebaodb.entity.TbTempTest;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbTempTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbTempTest record);

    int insertSelective(TbTempTest record);

    TbTempTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbTempTest record);

    int updateByPrimaryKey(TbTempTest record);

    List<TbTempTest> selectForPage(@Param("pageWhere")PageWhere pageWhere);

    List<TbTempTest> selectByUserNameForPage(@Param("record")TbTempTest record,@Param("pageWhere")PageWhere pageWhere);

    int selectByUserNameForPageCount(@Param("record")TbTempTest record);
}