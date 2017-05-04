package com.jebao.jebaodb.dao.dao.user;

import com.jebao.jebaodb.dao.mapper.user.TbLoginInfoMapper;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
@Repository
public class TbLoginInfoDao {
    @Autowired
    private TbLoginInfoMapper tbLoginInfoMapper;

    public int insert(TbLoginInfo record) {
        return tbLoginInfoMapper.insert(record);
    }
    public int insertSelective(TbLoginInfo record) {
        return tbLoginInfoMapper.insertSelective(record);
    }
    public TbLoginInfo selectByPrimaryKey(Long liId)
    {
        return tbLoginInfoMapper.selectByPrimaryKey(liId);
    }
    public int updateByPrimaryKeySelective(TbLoginInfo record)
    {
        return tbLoginInfoMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbLoginInfo record)
    {
        return tbLoginInfoMapper.updateByPrimaryKey(record);
    }
    public int deleteByPrimaryKey(Long liId)
    {
        return tbLoginInfoMapper.deleteByPrimaryKey(liId);
    }
    public List<TbLoginInfo> selectForPage(PageWhere pageWhere)
    {
        return tbLoginInfoMapper.selectForPage(pageWhere);
    }
    public TbLoginInfo selectByLoginName(String liLoginName)
    {
        return tbLoginInfoMapper.selectByLoginName(liLoginName);
    }
    @Transactional
    public long insertForTransactional(TbLoginInfo record) {
        return tbLoginInfoMapper.insert(record);
    }
}
