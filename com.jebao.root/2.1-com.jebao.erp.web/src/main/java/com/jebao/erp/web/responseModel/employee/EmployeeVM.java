package com.jebao.erp.web.responseModel.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.employee.EmployeeInfo;
import com.jebao.jebaodb.entity.employee.TbDepartment;
import com.jebao.jebaodb.entity.employee.TbEmployee;
import com.jebao.jebaodb.entity.employee.TbRank;

import java.util.Date;

/**
 * Created by Jack on 2016/11/18.
 */
public class EmployeeVM extends ViewModel {
    public EmployeeVM(EmployeeInfo entity) {
        TbEmployee employee = entity.getEmployee();
        if (employee != null) {
            this.id = employee.getEmpId();
            this.name = employee.getEmpName();
            this.mobile = employee.getEmpMobilephone();
            this.cardNo = employee.getEmpCardNo();
            this.sex = employee.getEmpSex();
            this.birthday = employee.getEmpBirthday();
            this.status = employee.getEmpStatus();
            this.entryDate = employee.getEmpEntryDate();
            this.dimissionDate = employee.getEmpDimissionDate();
        }
        TbDepartment department = entity.getDepartment();
        if (department != null) {
            this.teamId = department.getDepId();
            this.teamName = department.getDepName();
        }
        TbRank rank = entity.getRank();
        if (rank != null) {
            this.rankId = rank.getRankId();
            this.rankName = rank.getRankName();
        }
        this.loginStatus =entity.getLgStatus()==null?2:entity.getLgStatus();
    }

    private int id;
    @JsonPropertyDescription("员工姓名")
    private String name;
    @JsonPropertyDescription("手机号")
    private String mobile;

    private String cardNo;

    private int sex;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    private int status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date entryDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date dimissionDate;
    private int rankId;
    @JsonPropertyDescription("员工级别")
    private String rankName;
    private int teamId;
    @JsonPropertyDescription("所属团队")
    private String teamName;
    private int loginStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }
}
