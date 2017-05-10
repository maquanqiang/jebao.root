package com.jebao.jebaodb.dao.dao.loaner;

import com.jebao.jebaodb.dao.mapper.loaner.TbRcpMaterialsTempMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */
@Repository
public class TbRcpMaterialsTempDao {
    @Autowired
    private TbRcpMaterialsTempMapper tbRcpMaterialsTempMapper;

    public int insert(TbRcpMaterialsTemp record) {
        return tbRcpMaterialsTempMapper.insert(record);
    }
    public int insertSelective(TbRcpMaterialsTemp record) {
        return tbRcpMaterialsTempMapper.insertSelective(record);
    }
    public TbRcpMaterialsTemp selectByPrimaryKey(Long rcpmtId)
    {
        return tbRcpMaterialsTempMapper.selectByPrimaryKey(rcpmtId);
    }
    public int updateByPrimaryKeySelective(TbRcpMaterialsTemp record)
    {
        return tbRcpMaterialsTempMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbRcpMaterialsTemp record)
    {
        return tbRcpMaterialsTempMapper.updateByPrimaryKey(record);
    }
    public int deleteByPrimaryKey(Long rcpmtId)
    {
        return tbRcpMaterialsTempMapper.deleteByPrimaryKey(rcpmtId);
    }
    public int deleteByProjectId(Long projectId)
    {
        return tbRcpMaterialsTempMapper.deleteByProjectId(projectId);
    }
    public List<TbRcpMaterialsTemp> selectForPage(PageWhere pageWhere)
    {
        return tbRcpMaterialsTempMapper.selectForPage(pageWhere);
    }
    public List<TbRcpMaterialsTemp> selectByProjectIdForPage(TbRcpMaterialsTemp record,PageWhere pageWhere)
    {
        if (record == null){
            record = new TbRcpMaterialsTemp();
        }
/*        if(pageWhere == null){
            pageWhere = new PageWhere(0,10);
        }*/
        return tbRcpMaterialsTempMapper.selectByProjectIdForPage(record, pageWhere);
    }
    public int selectByProjectIdForPageCount(TbRcpMaterialsTemp record)
    {
        if (record == null){
            record = new TbRcpMaterialsTemp();
        }
        return tbRcpMaterialsTempMapper.selectByProjectIdForPageCount(record);
    }
    @Transactional
    public int insertForTransactional(TbRcpMaterialsTemp record) {
        return tbRcpMaterialsTempMapper.insert(record);
    }
}
