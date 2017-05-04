package com.jebao.p2p.service.impl.user;

import com.jebao.common.utils.encrypt.EncryptUtil;
import com.jebao.common.utils.regex.RegexUtil;
import com.jebao.jebaodb.dao.dao.employee.TbEmployeeDao;
import com.jebao.jebaodb.dao.dao.user.TbLoginInfoDao;
import com.jebao.jebaodb.dao.dao.user.TbUserDetailsDao;
import com.jebao.jebaodb.dao.dao.user.TbUserLogDao;
import com.jebao.jebaodb.entity.employee.EmployeeInfo;
import com.jebao.jebaodb.entity.employee.TbEmployee;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.user.TbUserLog;
import com.jebao.p2p.service.inf.user.IAccountServiceInf;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Jack on 2016/12/6.
 */
@Service
public class AccountServiceImpl implements IAccountServiceInf {
    @Autowired
    private TbLoginInfoDao loginInfoDao;
    @Autowired
    private TbUserDetailsDao userDetailsDao;
    @Autowired
    private TbUserLogDao userLogDao;
    @Autowired
    private TbEmployeeDao employeeDao;
    @Override
    public ResultData<Long> login(String username, String password, String ip) {
        TbLoginInfo loginEntity = loginInfoDao.selectByLoginName(username);
        if (loginEntity==null || (loginEntity.getLiIsDel()!=null && loginEntity.getLiIsDel()!=1) ){
            return new ResultData(false,null,"不存在此用户名");
        }
        String md5Password = new EncryptUtil().encryptToMD5(password);
        if(!loginEntity.getLiPassword().equalsIgnoreCase(md5Password)){
            return new ResultData(false,null,"登录密码错误");
        }

        //登录成功，更新最近登录时间
        loginEntity.setLiLastLoginTime(new Date());
        loginInfoDao.updateByPrimaryKey(loginEntity);

        return new ResultData(true,loginEntity.getLiId(),"登录成功");
    }
    @Override
    @Transactional
    public ResultData<Long> register(String username, String password, String invitationCode,String ip, EnumModel.Platform platform, EnumModel.PlatformType platformType){

        TbLoginInfo existsLoginEntity = loginInfoDao.selectByLoginName(username);
        if (existsLoginEntity != null){
            return new ResultData(false,null,"该手机号码已注册");
        }

        TbLoginInfo loginModel = new TbLoginInfo();
        loginModel.setLiLoginName(username);
        loginModel.setLiPassword(new EncryptUtil().encryptToMD5(password));
        loginModel.setLiCreateTime(new Date());
        loginModel.setLiIsDel(1);//是否有效,1有效 2无效
        int insertCount = loginInfoDao.insert(loginModel);
        if (insertCount>0){
            long userId = loginModel.getLiId();
            //region 创建用户详情信息
            TbUserDetails detailsModel = new TbUserDetails();
            detailsModel.setUdLoginId(userId);//用户id，登录表id
            detailsModel.setUdPhone(username);//手机号码
            detailsModel.setUdPlatform(platform.getValue());//注册(来源)平台
            detailsModel.setUdPlatformType(platformType.getValue());//注册(来源)平台分类
            detailsModel.setUdChannel(0);//渠道
            detailsModel.setUdChannelType(0);//渠道分类
            detailsModel.setUdCreateTime(new Date());//创建时间
            detailsModel.setUdIsDel(1);//是否有效，1有效
            detailsModel.setUdPosStatus(0);//POS机签约状态 0未签约  1签约
            //region 检查邀请码
            if (RegexUtil.matches(invitationCode,"^1(3|4|5|7|8)\\d{9}$")){
                EmployeeInfo employeeInfoEntity = employeeDao.selectEmployeeByMobile(invitationCode);
                if (employeeInfoEntity!=null ){
                    TbEmployee employeeEntity = employeeInfoEntity.getEmployee();
                    if (employeeEntity!=null && (employeeEntity.getEmpIsDeleted()!=null && !employeeEntity.getEmpIsDeleted()) ){
                        detailsModel.setUdInvitationCode(employeeEntity.getEmpMobilephone());//销售人员的手机号
                        detailsModel.setUdCustomerManagerId(employeeEntity.getEmpId());//专属客户经理id,员工id
                    }
                }
                if(StringUtils.isBlank(detailsModel.getUdInvitationCode())){
                    //不是销售人员的邀请码，查询普通员工
                    TbLoginInfo existsUserInfo = loginInfoDao.selectByLoginName(invitationCode);
                    if (existsUserInfo!=null && (existsUserInfo.getLiIsDel()!=null && existsUserInfo.getLiIsDel() == 1) ){
                        detailsModel.setUdInvitationCode(username);
                    }
                }
            }
//            if(StringUtils.isBlank(detailsModel.getUdInvitationCode())){
//                //无效的邀请码，设置金额宝本部门的邀请码
//                detailsModel.setUdInvitationCode(username);
//            }

            //endregion

            userDetailsDao.insert(detailsModel);
           //endregion

            //region 记录日志
            TbUserLog logModel = new TbUserLog();
            logModel.setUlUserId(userId);//用户id
            String logContent = "用户注册，注册手机号："+username;
            if (!StringUtils.isBlank(detailsModel.getUdInvitationCode())){
                logContent += "，邀请码："+detailsModel.getUdInvitationCode();
            }
            logModel.setUlContent(logContent);
            logModel.setUlCreateUserId(userId);
            logModel.setUlCreateUserTime(new Date());
            userLogDao.insert(logModel);
            //endregion

            return new ResultData(true,userId,"注册成功");
        }
        return new ResultData(false,0l,"注册失败，请稍后再试");
    }
    @Override
    public ResultInfo setPassword(String mobile, String password){
        TbLoginInfo existsLoginEntity = loginInfoDao.selectByLoginName(mobile);
        if (existsLoginEntity == null){
            return new ResultInfo(false,"用户不存在");
        }
        String newPassword =new EncryptUtil().encryptToMD5(password);
        existsLoginEntity.setLiPassword(newPassword);
        int reval = loginInfoDao.updateByPrimaryKey(existsLoginEntity);
        if (reval>0){
            //记录日志
            TbUserLog logModel = new TbUserLog();
            logModel.setUlUserId(existsLoginEntity.getLiId());
            logModel.setUlContent("用户重设密码");
            logModel.setUlCreateUserId(existsLoginEntity.getLiId());
            logModel.setUlCreateUserTime(new Date());
            userLogDao.insert(logModel);

            return new ResultData<Long>(true,existsLoginEntity.getLiId(),"设置成功");
        }
        return new ResultInfo(false,"服务器异常，操作失败");
    }
}
