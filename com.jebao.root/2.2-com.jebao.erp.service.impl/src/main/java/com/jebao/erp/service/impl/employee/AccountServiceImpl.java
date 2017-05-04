package com.jebao.erp.service.impl.employee;

import com.jebao.common.utils.encrypt.EncryptUtil;
import com.jebao.erp.service.inf.employee.IAccountServiceInf;
import com.jebao.jebaodb.dao.dao.employee.TbEmployeeDao;
import com.jebao.jebaodb.dao.dao.employee.TbEmployeeLoginDao;
import com.jebao.jebaodb.entity.employee.TbEmployee;
import com.jebao.jebaodb.entity.employee.TbEmployeeLogin;
import com.jebao.jebaodb.entity.employee.input.LoginIM;
import com.jebao.jebaodb.entity.employee.input.PasswordIM;
import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jack on 2016/11/25.
 */
@Service
public class AccountServiceImpl implements IAccountServiceInf {

    @Autowired
    private TbEmployeeDao employeeDao;
    @Autowired
    private TbEmployeeLoginDao loginDao;

    @Override
    public ResultInfo Login(LoginIM model){
        TbEmployeeLogin loginEntity = loginDao.selectByUsername(model.getUsername());
        if (loginEntity==null){
            return new ResultInfo(false,"不存在此用户");
        }

        if (loginEntity.getLgStatus()==null || loginEntity.getLgStatus()!=1){//账户登录状态禁用
            return new ResultInfo(false,"登录已禁用");
        }
        String password = new EncryptUtil().encryptToMD5(model.getPassword());
        if(!loginEntity.getLgPassword().equalsIgnoreCase(password)){
            return new ResultInfo(false,"登录密码错误");
        }
        //登录成功
        if (loginEntity.getLgFirstLoginTime() == null){
            loginEntity.setLgFirstLoginTime(new Date());
        }
        loginEntity.setLgLastLoginTime(new Date());
        //更新登录时间
        loginDao.updateByPrimaryKey(loginEntity);

        return new ResultData<Integer>(true,loginEntity.getLgEmpId(),"登录成功");
    }

    @Override
    public ResultInfo ModifyPassword(PasswordIM model){
        TbEmployee employeeEntity = employeeDao.selectByPrimaryKey(model.getUserId());
        if (employeeEntity==null || employeeEntity.getEmpIsDeleted() || employeeEntity.getEmpStatus()==2){
            return new ResultInfo(false,"操作异常，不存在此员工信息");
        }
        TbEmployeeLogin loginEntity = loginDao.selectByUsername(employeeEntity.getEmpMobilephone());
        if (loginEntity==null){
            return new ResultInfo(false,"帐号信息异常，登录信息丢失，请联系管理员");
        }
        EncryptUtil encryptUtil = new EncryptUtil();
        if (!loginEntity.getLgPassword().equalsIgnoreCase(encryptUtil.encryptToMD5(model.getCurrentPassword()))){
            return new ResultInfo(false,"当前密码输入错误");
        }
        String md5Password = new EncryptUtil().encryptToMD5(model.getNewPassword());
        loginEntity.setLgPassword(md5Password);

        int reval = loginDao.updateByPrimaryKey(loginEntity);
        if (reval>0){
            return new ResultInfo(true,"保存成功");
        }
        return new ResultInfo(false,"操作失败，请稍后再试");
    }
}
