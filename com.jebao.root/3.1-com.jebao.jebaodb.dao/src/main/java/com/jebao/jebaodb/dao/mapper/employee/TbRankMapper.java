package com.jebao.jebaodb.dao.mapper.employee;

import com.jebao.jebaodb.entity.employee.TbRank;
import com.jebao.jebaodb.entity.employee.search.RankSM;

import java.util.List;

public interface TbRankMapper {
    int insert(TbRank record);

    int insertSelective(TbRank record);

    TbRank selectByPrimaryKey(Integer rankId);

    int updateByPrimaryKeySelective(TbRank record);

    int updateByPrimaryKey(TbRank record);

    List<TbRank> selectList(RankSM model);
    int selectListCount(RankSM model);
    int delete(int id);
}