package com.jebao.p2p.service.impl.user;

import com.jebao.jebaodb.dao.dao.user.TbAccountsFundsDao;
import com.jebao.jebaodb.dao.dao.user.TbLoginInfoDao;
import com.jebao.jebaodb.dao.dao.user.TbUserDetailsDao;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.p2p.service.inf.user.IUserServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jack on 2016/12/12.
 */
@Service
public class UserServiceImpl implements IUserServiceInf {
    @Autowired
    private TbLoginInfoDao loginInfoDao;
    @Autowired
    private TbUserDetailsDao userDetailsDao;
    @Autowired
    private TbAccountsFundsDao tbAccountsFundsDao;

    @Override
    public TbLoginInfo getUserLoginInfo(String mobile) {
        return loginInfoDao.selectByLoginName(mobile);
    }
    @Override
    public TbUserDetails getUserDetailsInfo(String mobile) {
        return userDetailsDao.selectByMobile(mobile);
    }
    @Override
    public TbUserDetails getUserDetailsInfo(long userId) {
        return userDetailsDao.selectByLoginId(userId);
    }

    @Override
    public TbAccountsFunds getAccountsFundsInfo(long userId) {
        return tbAccountsFundsDao.selectByLoginId(userId);
    }
}
