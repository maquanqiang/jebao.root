package com.jebao.jebaodb.dao.mapper.loaner;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbRcpMaterialsTempMapper {
    int insert(TbRcpMaterialsTemp record);

    int insertSelective(TbRcpMaterialsTemp record);

    TbRcpMaterialsTemp selectByPrimaryKey(Long rcpmtId);

    int updateByPrimaryKeySelective(TbRcpMaterialsTemp record);

    int updateByPrimaryKey(TbRcpMaterialsTemp record);

    /* ==================================================华丽分割线==================================================*/
    int deleteByPrimaryKey(Long rcpmtId);

    int deleteByProjectId(Long projectId);

    List<TbRcpMaterialsTemp> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    List<TbRcpMaterialsTemp> selectByProjectIdForPage(@Param("record") TbRcpMaterialsTemp record, @Param("pageWhere") PageWhere pageWhere);

    int selectByProjectIdForPageCount(@Param("record") TbRcpMaterialsTemp record);
}