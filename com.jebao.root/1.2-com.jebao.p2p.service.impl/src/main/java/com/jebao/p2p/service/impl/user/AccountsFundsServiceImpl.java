package com.jebao.p2p.service.impl.user;

import com.jebao.jebaodb.dao.dao.user.TbAccountsFundsDao;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.p2p.service.inf.user.IAccountsFundsServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/14.
 */
@Service
public class AccountsFundsServiceImpl implements IAccountsFundsServiceInf {
    @Autowired
    private TbAccountsFundsDao tbAccountsFundsDao;

    @Override
    public int insert(TbAccountsFunds record) {
        return tbAccountsFundsDao.insertSelective(record);
    }

    @Override
    public int update(TbAccountsFunds record) {
        return tbAccountsFundsDao.updateByLoginId(record);
    }

    @Override
    public TbAccountsFunds selectByLoginId(Long loginId){
        return tbAccountsFundsDao.selectByLoginId(loginId);
    }

}
