package com.jebao.erp.service.impl.employee;

import com.jebao.common.utils.date.DateUtil;
import com.jebao.common.utils.encrypt.EncryptUtil;
import com.jebao.common.utils.idcard.IdCardUtil;
import com.jebao.erp.service.inf.employee.IEmployeeServiceInf;
import com.jebao.jebaodb.dao.dao.employee.*;
import com.jebao.jebaodb.entity.employee.*;
import com.jebao.jebaodb.entity.employee.input.EmployeeIM;
import com.jebao.jebaodb.entity.employee.search.EmployeeSM;
import com.jebao.jebaodb.entity.extEntity.ResultData;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 2016/11/16.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeServiceInf {

    @Autowired
    private TbEmployeeDao employeeDao;
    @Autowired
    private TbEmpDepRelationshipDao empDepRelationshipDao;
    @Autowired
    private TbEmpRankRelationshipDao empRankRelationshipDao;
    @Autowired
    private TbEmployeeLoginDao loginDao;
    @Autowired
    private TbEmployeeLogDao logDao;

    /**
     * 获取员工信息
     *
     * @return
     */
    @Override
    public List<EmployeeInfo> getEmployeeInfoList(EmployeeSM model) {
        return employeeDao.selectEmployeeDetailsInfo(model);
    }

    @Override
    public int getEmployeeInfoListCount(EmployeeSM model) {
        return employeeDao.selectEmployeeDetailsInfoCount(model);
    }

    @Override
    public ResultInfo saveEmployeeInfo(EmployeeIM model) {
        ResultInfo resultInfo = new ResultInfo(false);

        if (model.getTeamId()==null || model.getTeamId() == 0) {
            model.setTeamId(model.getDepartmentId());
        }
        if (model.getLoginStatus()==0){
            model.setLoginStatus(2);
        }
        //endregion
        int empId = model.getEmpId();

        if (empId == 0) {
            resultInfo = addEmployeeInfo(model);//新增
        } else {
            resultInfo = updateEmployeeInfo(model);//修改
        }

        return resultInfo;
    }

    @Override
    public ResultInfo deleteEmployeeInfo(int empId, int userId) {
        ResultInfo resultInfo = new ResultInfo(false);

        TbEmployee employeeEntity = employeeDao.selectByPrimaryKey(empId);
        if (employeeEntity == null) {
            resultInfo.setMsg("不存在此员工");
            return resultInfo;
        }
        boolean success = employeeDao.delete(empId);
        if (success) {
            loginDao.deleteEmployeeLogin(empId);
            resultInfo.setSuccess_is_ok(true);
            resultInfo.setMsg("删除成功");

            //region 记录日志
            TbEmployeeLog logEntity = new TbEmployeeLog();
            logEntity.setElEmpId(empId);
            logEntity.setElContent("删除员工");//操作内容
            logEntity.setElOperateTime(new Date());//操作时间
            logEntity.setElOperator(userId);//操作人
            logDao.insert(logEntity);//插入日志记录

            //endregion
        } else {
            resultInfo.setMsg("删除失败");
        }
        return resultInfo;
    }

    /**
     * 新增员工信息
     */
    private ResultInfo addEmployeeInfo(EmployeeIM model) {
        //region 转换实体
        Date today = new Date();
        //员工基本信息
        TbEmployee employee = new TbEmployee();
        employee.setEmpName(model.getName());//员工姓名
        employee.setEmpMobilephone(model.getMobile());//手机号
        employee.setEmpCardNo(model.getCardNo());//身份证号
        employee.setEmpSex(IdCardUtil.getGenderByIdCard(model.getCardNo()));//性别
        String birthdayStr = IdCardUtil.getBirthdayByIdCard(model.getCardNo()); //根据身份证提取生日字符串
        employee.setEmpBirthday(DateUtil.stringToDate(birthdayStr));// 生日
        employee.setEmpStatus(1);//在职
        employee.setEmpEntryDate(today);//入职日期
        employee.setEmpIsDeleted(false);
        employee.setEmpCreateTime(today);//创建时间
        employee.setEmpCreateUser(model.getUserId());//创建人
        int empId = 0;
        try {
            //插入员工基本信息
            int reval = employeeDao.insert(employee);
            empId = employee.getEmpId();//员工id，插入之后有内容
            if (reval == 0) {
                return new ResultInfo(false, "添加员工基本信息失败");
            }
        } catch (Exception e) {
            if (e.getMessage().contains("for key 'i_emp_card_no'")){
                return new ResultInfo(false, "身份证号码为唯一性");
            }
            return new ResultInfo(false, "手机号为唯一性");
        }

        //员工所属部门
        if (model.getTeamId() > 0) {
            TbEmpDepRelationship empDepRelationship = new TbEmpDepRelationship();
            empDepRelationship.setEdrEmpId(empId);
            empDepRelationship.setEdrDepId(model.getTeamId());//部门/团队 id
            empDepRelationship.setEdrEffectDate(today);//生效时间
            empDepRelationship.setEdrCreateTime(today);//创建时间
            empDepRelationship.setEdrCreateUser(model.getUserId());//创建人
            empDepRelationshipDao.insert(empDepRelationship); //插入员工部门信息
        }
        //员工职级
        if (model.getRankId() > 0) {
            TbEmpRankRelationship empRankRelationship = new TbEmpRankRelationship();
            empRankRelationship.setErrEmpId(empId);
            empRankRelationship.setErrRankId(model.getRankId());//职级id
            empRankRelationship.setErrEffectDate(today);//生效时间
            empRankRelationship.setErrCreateTime(today);//创建时间
            empRankRelationship.setErrCreateUser(model.getUserId());//创建人
            empRankRelationshipDao.insert(empRankRelationship);//插入员工职级信息
        }
        //员工登录信息
        TbEmployeeLogin loginEntity = new TbEmployeeLogin();
        loginEntity.setLgEmpId(empId);
        String mobile = model.getMobile();
        loginEntity.setLgUsername(mobile);//手机号作为登录名
        String loginPassword = mobile.substring(mobile.length() - 6);//密码默认手机号码后6位
        String md5Password = new EncryptUtil().encryptToMD5(loginPassword);
        loginEntity.setLgPassword(md5Password);
        loginEntity.setLgStatus(model.getLoginStatus());//登录状态，是否禁用
        loginDao.insert(loginEntity);//插入员工登录信息

        //endregion

        //region 记录日志
        TbEmployeeLog logEntity = new TbEmployeeLog();
        logEntity.setElEmpId(empId);
        String content = String.format("新增员工，所属团队：%s，职级：%s", model.getTeamId(), model.getRankId());
        logEntity.setElContent(content);//操作内容
        logEntity.setElOperateTime(today);//操作时间
        logEntity.setElOperator(model.getUserId());//操作人
        logDao.insert(logEntity);//插入日志记录

        //endregion

        return new ResultData<Integer>(true,empId, "保存成功");
    }

    /**
     * 更新员工信息
     */
    private ResultInfo updateEmployeeInfo(EmployeeIM model) {

        EmployeeSM searchModel = new EmployeeSM();
        searchModel.setEmpId(model.getEmpId());
        searchModel.setPageIndex(0);
        searchModel.setPageSize(1);
        List<EmployeeInfo> employeeInfoList = employeeDao.selectEmployeeDetailsInfo(searchModel);
        if (employeeInfoList == null || employeeInfoList.size() == 0) {
            return new ResultInfo(false, "更新失败，不存在此员工");
        }
        EmployeeInfo employeeInfo = employeeInfoList.get(0); //已存在的员工信息
        int empId = model.getEmpId();//员工id
        //region 转换实体
        Date today = new Date();
        //员工基本信息
        TbEmployee employeeEntity = new TbEmployee();
        employeeEntity.setEmpId(empId);//员工id
        employeeEntity.setEmpStatus(model.getStatus());//在职状态
        try {
            //更新员工基本信息
            employeeDao.updateByPrimaryKeySelective(employeeEntity);
        } catch (Exception e) {
            if (e.getMessage().contains("for key 'i_emp_card_no'")){
                return new ResultInfo(false, "身份证号码为唯一性");
            }
            return new ResultInfo(false, "手机号为唯一性");
        }

        //region员工所属部门
        if (model.getTeamId() > 0) {
            TbDepartment departmentInfo = employeeInfo.getDepartment();
            if (!(departmentInfo != null && departmentInfo.getDepId().equals(model.getTeamId()))) { //团队关系变动

                TbEmpDepRelationship empDepRelationshipEntity = empDepRelationshipDao.selectCurrentEmpRelation(empId);//当前存在的部门关系，更新失效时间
                if (empDepRelationshipEntity != null) {
                    Date currentTime = new Date();
                    //获取昨天时间
                    Date yesterday = DateUtils.addDays(currentTime, -1);
                    empDepRelationshipEntity.setEdrExpiryDate(yesterday);//失效时间,昨日
                    empDepRelationshipDao.updateByPrimaryKey(empDepRelationshipEntity);
                }
                //插入新的部门关系
                TbEmpDepRelationship newEmpDepRelationshipEntity = new TbEmpDepRelationship();
                newEmpDepRelationshipEntity.setEdrEmpId(empId);
                newEmpDepRelationshipEntity.setEdrDepId(model.getTeamId());//部门/团队 id
                newEmpDepRelationshipEntity.setEdrEffectDate(today);//生效时间
                newEmpDepRelationshipEntity.setEdrCreateTime(today);//创建时间
                newEmpDepRelationshipEntity.setEdrCreateUser(model.getUserId());//创建人
                empDepRelationshipDao.insert(newEmpDepRelationshipEntity); //插入员工部门信息
            }
        }
        //endregion

        //region员工职级
        if (model.getRankId() > 0) {
            TbRank rankInfo = employeeInfo.getRank();
            if (!(rankInfo != null && rankInfo.getRankId() == model.getRankId())) { //职级关系变动
                TbEmpRankRelationship empRankRelationshipEntity = empRankRelationshipDao.selectCurrentEmpRelation(empId);//当前存在的职级关系，更新失效时间
                if (empRankRelationshipEntity != null) {
                    Date currentTime = new Date();
                    //获取昨天时间
                    Date yesterday = DateUtils.addDays(currentTime, -1);
                    empRankRelationshipEntity.setErrExpiryDate(yesterday);//失效时间,昨日
                    empRankRelationshipDao.updateByPrimaryKey(empRankRelationshipEntity);
                }
                TbEmpRankRelationship newEmpRankRelationshipEntity = new TbEmpRankRelationship();
                newEmpRankRelationshipEntity.setErrEmpId(empId);
                newEmpRankRelationshipEntity.setErrRankId(model.getRankId());//职级id
                newEmpRankRelationshipEntity.setErrEffectDate(today);//生效时间
                newEmpRankRelationshipEntity.setErrCreateTime(today);//创建时间
                newEmpRankRelationshipEntity.setErrCreateUser(model.getUserId());//创建人
                empRankRelationshipDao.insert(newEmpRankRelationshipEntity);//插入员工职级信息
            }
        }
        //endregion

        //region员工登录状态
        if (model.getLoginStatus() > 0) {
            TbEmployeeLogin loginEntity = loginDao.selectByUsername(model.getMobile());
            if (loginEntity.getLgStatus()!=null && loginEntity.getLgStatus() != model.getLoginStatus()) {
                loginEntity.setLgStatus(model.getLoginStatus());
                loginDao.updateByPrimaryKey(loginEntity);
            }
        }
        //endregion

        //endregion

        //region 记录日志
        TbEmployeeLog logEntity = new TbEmployeeLog();
        logEntity.setElEmpId(empId);
        String content = String.format("修改员工，所属团队：%s，职级：%s", model.getTeamId(), model.getRankId());
        logEntity.setElContent(content);//操作内容
        logEntity.setElOperateTime(today);//操作时间
        logEntity.setElOperator(model.getUserId());//操作人
        logDao.insert(logEntity);//插入日志记录

        //endregion

        return new ResultInfo(true, "修改成功");
    }

    @Override
    public List<EmpPerformanceInfo> getEmpPerformance(EmployeeSM model){
        return employeeDao.selectEmpPerformance(model);
    }
    @Override
    public int getEmpPerformanceCount(EmployeeSM model){
        return employeeDao.selectEmpPerformanceCount(model);
    }

}
