package com.jebao.erp.service.inf.employee;

import com.jebao.jebaodb.entity.employee.input.LoginIM;
import com.jebao.jebaodb.entity.employee.input.PasswordIM;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;

/**
 * Created by Jack on 2016/11/25.
 */
public interface IAccountServiceInf {
    /**
     * 员工登录
     */
    ResultInfo Login(LoginIM model);

    /**
     * 修改密码
     */
    ResultInfo ModifyPassword(PasswordIM model);
}
