package com.jebao.jebaodb.dao.dao;

import com.jebao.jebaodb.dao.conf.mybatis.annotation.ReadOnlyDB;
import com.jebao.jebaodb.dao.mapper.TbTempTestMapper;
import com.jebao.jebaodb.entity.TbTempTest;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
@Repository
public class TbTempTestDao {
    @Autowired
    private TbTempTestMapper tbTempTestMapper;

    public int insert(TbTempTest record) {
        return tbTempTestMapper.insert(record);
    }
    public int insertSelective(TbTempTest record) {
        return tbTempTestMapper.insertSelective(record);
    }
    public TbTempTest selectByPrimaryKey(Integer id)
    {
        return tbTempTestMapper.selectByPrimaryKey(id);
    }
    public int updateByPrimaryKeySelective(TbTempTest record)
    {
        return tbTempTestMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbTempTest record)
    {
        return tbTempTestMapper.updateByPrimaryKey(record);
    }
    public int deleteByPrimaryKey(Integer id)
    {
        return tbTempTestMapper.deleteByPrimaryKey(id);
    }
    public List<TbTempTest> selectForPage(PageWhere pageWhere)
    {
        return tbTempTestMapper.selectForPage(pageWhere);
    }
    public List<TbTempTest> selectByUserNameForPage(TbTempTest record,PageWhere pageWhere)
    {
        return tbTempTestMapper.selectByUserNameForPage(record, pageWhere);
    }
    public int selectByUserNameForPageCount(TbTempTest record)
    {
        return tbTempTestMapper.selectByUserNameForPageCount(record);
    }
    @Transactional
    public int insertForTransactional(TbTempTest record) {
        return tbTempTestMapper.insert(record);
    }
}
