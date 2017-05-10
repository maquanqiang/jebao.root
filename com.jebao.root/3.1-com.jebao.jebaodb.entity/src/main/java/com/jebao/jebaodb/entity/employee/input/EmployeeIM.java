package com.jebao.jebaodb.entity.employee.input;

import com.jebao.jebaodb.entity.extEntity.InputModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * Created by Jack on 2016/11/23.
 */
public class EmployeeIM extends InputModel {
    private int empId;
    @NotBlank(message="员工姓名不能为空")
    private String name;
    @NotBlank(message="手机号不能为空")
    @Pattern(regexp = "^1[3-8]\\d{9}$")
    private String mobile;
    @NotBlank(message="身份证不能为空")
    private String cardNo;
    /**
     * 员工级别
     */
    @Min(value = 1,message = "请选择职级")
    private int rankId;
    /**
     * 部门id
     */
    @Min(value = 1,message = "请选择部门")
    private int departmentId;
    /**
     * 所属团队
     */
    private Integer teamId;
    /**
     * 在职状态
     */
    private int status;
    /**
     * 登录状态
     */
    private int loginStatus;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }
}
