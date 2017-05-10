package com.jebao.erp.service.impl.user;

import com.jebao.erp.service.inf.user.IAccountsFundsServiceInf;
import com.jebao.jebaodb.dao.dao.user.TbAccountsFundsDao;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/19.
 */
@Service
public class AccountsFundsServiceImpl implements IAccountsFundsServiceInf {
    @Autowired
    private TbAccountsFundsDao tbAccountsFundsDao;

    @Override
    public TbAccountsFunds findAccountsFundsByloginId(Long loginId) {
        return tbAccountsFundsDao.selectByLoginId(loginId);
    }
}
