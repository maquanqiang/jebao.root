package com.jebao.erp.service.inf.user;

import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.user.search.UserSM;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
public interface IUserDetailsServiceInf {
    TbUserDetails selectByLoginId(Long loginId);

    /**
     * 修改用户详细资料
     * @param entity
     * @return
     */
    int updateUserDetails(TbUserDetails entity);

    List<TbUserDetails> selectByConditionForPage(UserSM userSM);

    Integer selectListCount(UserSM userSM);

}
