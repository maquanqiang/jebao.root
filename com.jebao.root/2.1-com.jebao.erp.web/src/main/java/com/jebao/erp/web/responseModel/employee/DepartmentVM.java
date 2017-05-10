package com.jebao.erp.web.responseModel.employee;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.employee.TbDepartment;

/**
 * Created by Jack on 2016/11/21.
 */
public class DepartmentVM extends ViewModel {

    public DepartmentVM(TbDepartment entity) {
        this.id = entity.getDepId();
        this.name = entity.getDepName();
        this.parentId = entity.getDepParentId();
        this.isDepartment=entity.getDepIsDepartment();
    }

    private int id;
    private String name;
    private int parentId;
    private boolean isDepartment;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean getIsDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(boolean department) {
        isDepartment = department;
    }
}
