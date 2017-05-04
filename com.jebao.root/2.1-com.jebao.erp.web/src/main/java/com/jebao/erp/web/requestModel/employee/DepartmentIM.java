package com.jebao.erp.web.requestModel.employee;

import com.jebao.jebaodb.entity.employee.TbDepartment;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jack on 2016/12/1.
 */
public class DepartmentIM {

    public TbDepartment toEntity(){
        TbDepartment entity = new TbDepartment();
        entity.setDepId(this.id);
        entity.setDepName(this.name);
        entity.setDepParentId(this.parentId);
        entity.setDepIsDepartment(this.isDepartment);
        return entity;
    }

    private int id;
    @NotBlank(message = "名称不能为空")
    private String name;
    private Integer parentId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean getIsDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(boolean department) {
        isDepartment = department;
    }
}
