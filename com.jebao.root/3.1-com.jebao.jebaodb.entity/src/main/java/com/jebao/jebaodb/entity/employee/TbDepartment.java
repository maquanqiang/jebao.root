package com.jebao.jebaodb.entity.employee;

public class TbDepartment {
    private Integer depId;

    private String depName;

    private Integer depParentId;

    private Boolean depIsDepartment;

    private Boolean depIsDel;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    public Integer getDepParentId() {
        return depParentId;
    }

    public void setDepParentId(Integer depParentId) {
        this.depParentId = depParentId;
    }

    public Boolean getDepIsDepartment() {
        return depIsDepartment;
    }

    public void setDepIsDepartment(Boolean depIsDepartment) {
        this.depIsDepartment = depIsDepartment;
    }

    public Boolean getDepIsDel() {
        return depIsDel;
    }

    public void setDepIsDel(Boolean depIsDel) {
        this.depIsDel = depIsDel;
    }
}