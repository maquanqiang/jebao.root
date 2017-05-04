package com.jebao.jebaodb.dao.mapper.user;

import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.user.search.UserSM;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbUserDetailsMapper {
    int insert(TbUserDetails record);

    int insertSelective(TbUserDetails record);

    TbUserDetails selectByPrimaryKey(Long udId);

    int updateByPrimaryKeySelective(TbUserDetails record);

    int updateByPrimaryKey(TbUserDetails record);

    /* ==================================================华丽分割线==================================================*/
    int deleteByPrimaryKey(Long udId);

    List<TbUserDetails> selectForPage(@Param("pageWhere") PageWhere pageWhere);

    TbUserDetails selectByLoginId(Long udLoginId);

    List<TbUserDetails> selectList(UserSM model);
    int selectListCount(UserSM model);

    List<TbUserDetails> selectByConditionForPage(UserSM model);
}