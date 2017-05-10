package com.jebao.jebaodb.dao.dao.employee;

import com.jebao.jebaodb.dao.mapper.employee.TbRankMapper;
import com.jebao.jebaodb.entity.employee.TbRank;
import com.jebao.jebaodb.entity.employee.search.RankSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TbRankDao {
    @Autowired
    private TbRankMapper mapper;

    public int insert(TbRank record){return mapper.insert(record);}

    public TbRank selectByPrimaryKey(Integer rankId){return mapper.selectByPrimaryKey(rankId);}

    public int updateByPrimaryKeySelective(TbRank record){return mapper.updateByPrimaryKeySelective(record);}

    public int updateByPrimaryKey(TbRank record){return mapper.updateByPrimaryKey(record);}

    public List<TbRank> selectList(RankSM model){return mapper.selectList(model);}
    public int selectListCount(RankSM model){return mapper.selectListCount(model);}
    public int delete(int id){return mapper.delete(id);}
}