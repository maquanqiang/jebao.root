package com.jebao.jebaodb.dao.dao.user;

import com.jebao.jebaodb.dao.mapper.user.TbUserDetailsMapper;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.user.search.UserSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
@Repository
public class TbUserDetailsDao {
    @Autowired
    private TbUserDetailsMapper tbUserDetailsMapper;

    public int insert(TbUserDetails record) {
        return tbUserDetailsMapper.insert(record);
    }

    public int insertSelective(TbUserDetails record) {
        return tbUserDetailsMapper.insertSelective(record);
    }

    public TbUserDetails selectByPrimaryKey(Long udId) {
        return tbUserDetailsMapper.selectByPrimaryKey(udId);
    }

    public int updateByPrimaryKeySelective(TbUserDetails record) {
        return tbUserDetailsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TbUserDetails record) {
        return tbUserDetailsMapper.updateByPrimaryKey(record);
    }

    public int deleteByPrimaryKey(Long udId) {
        return tbUserDetailsMapper.deleteByPrimaryKey(udId);
    }

    public List<TbUserDetails> selectForPage(PageWhere pageWhere) {
        return tbUserDetailsMapper.selectForPage(pageWhere);
    }

    public TbUserDetails selectByLoginId(Long udLoginId) {
        return tbUserDetailsMapper.selectByLoginId(udLoginId);
    }

    @Transactional
    public long insertForTransactional(TbUserDetails record) {
        return tbUserDetailsMapper.insert(record);
    }

    public List<TbUserDetails> selectList(UserSM model) {
        return tbUserDetailsMapper.selectList(model);
    }

    public int selectListCount(UserSM model) {
        return tbUserDetailsMapper.selectListCount(model);
    }

    public TbUserDetails selectByMobile(String mobile) {
        if (mobile == null || mobile.length() == 0) {
            return null;
        }
        UserSM searchModel = new UserSM();
        searchModel.setMobile(mobile);
        List<TbUserDetails> list = tbUserDetailsMapper.selectList(searchModel);
        return list.size() == 0 ? null : list.get(0);
    }

    public List<TbUserDetails> selectByConditionForPage(UserSM userSM){
        List<TbUserDetails> tbUserDetailses = tbUserDetailsMapper.selectByConditionForPage(userSM);
        return tbUserDetailses;
    }
}
