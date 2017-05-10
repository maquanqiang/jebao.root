package com.jebao.jebaodb.entity.employee.search;

import com.jebao.jebaodb.entity.extEntity.PageWhere;

/**
 * Created by Jack on 2016/11/30.
 */
public class DepartmentSM extends PageWhere{
    public DepartmentSM(){
        super(0,1000);
    }
    private String name;
    private Integer parentId;
    /**
     * 根据parentId查询时，是否包含parent自身
     */
    private boolean parentAndSelf;
    /**
     * 是否只查看部级，1是2否
     */
    private Boolean isDepartment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isParentAndSelf() {
        return parentAndSelf;
    }

    public void setParentAndSelf(boolean parentAndSelf) {
        this.parentAndSelf = parentAndSelf;
    }

    public Boolean getIsDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(Boolean isDepartment) {
        this.isDepartment = isDepartment;
    }
}
