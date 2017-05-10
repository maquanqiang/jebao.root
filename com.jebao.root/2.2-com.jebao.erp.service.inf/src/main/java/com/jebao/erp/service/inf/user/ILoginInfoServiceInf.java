package com.jebao.erp.service.inf.user;

import com.jebao.jebaodb.entity.user.TbLoginInfo;

/**
 * Created by Administrator on 2016/12/2.
 */
public interface ILoginInfoServiceInf {
    TbLoginInfo selectByLoginName(String loginName);

    TbLoginInfo selectByLiId(Long liId);
}
