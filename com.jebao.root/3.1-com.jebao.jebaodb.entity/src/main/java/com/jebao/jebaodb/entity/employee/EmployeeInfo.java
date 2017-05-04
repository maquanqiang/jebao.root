package com.jebao.jebaodb.entity.employee;

import java.util.Date;

/**
 * Created by Jack on 2016/11/14.
 */
public class EmployeeInfo {
    //基础员工信息
    private TbEmployee employee;
    //员工部门信息
    private TbDepartment department;
    //部门生效时间
    private Date edrEffectDate;
    //部门失效时间
    private Date edrExpiryDate;
    //员工销售职级信息
    private TbRank rank;
    //职级生效时间
    private Date errEffectDate;
    //职级失效时间
    private Date errExpiryDate;
    private Integer lgStatus;

    public TbEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(TbEmployee employee) {
        this.employee = employee;
    }

    public TbDepartment getDepartment() {
        return department;
    }

    public void setDepartment(TbDepartment department) {
        this.department = department;
    }

    public Date getEdrEffectDate() {
        return edrEffectDate;
    }

    public void setEdrEffectDate(Date edrEffectDate) {
        this.edrEffectDate = edrEffectDate;
    }

    public Date getEdrExpiryDate() {
        return edrExpiryDate;
    }

    public void setEdrExpiryDate(Date edrExpiryDate) {
        this.edrExpiryDate = edrExpiryDate;
    }

    public TbRank getRank() {
        return rank;
    }

    public void setRank(TbRank rank) {
        this.rank = rank;
    }

    public Date getErrEffectDate() {
        return errEffectDate;
    }

    public void setErrEffectDate(Date errEffectDate) {
        this.errEffectDate = errEffectDate;
    }

    public Date getErrExpiryDate() {
        return errExpiryDate;
    }

    public void setErrExpiryDate(Date errExpiryDate) {
        this.errExpiryDate = errExpiryDate;
    }

    public Integer getLgStatus() {
        return lgStatus;
    }

    public void setLgStatus(Integer lgStatus) {
        this.lgStatus = lgStatus;
    }
}
