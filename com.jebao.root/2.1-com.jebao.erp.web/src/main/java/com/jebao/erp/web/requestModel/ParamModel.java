package com.jebao.erp.web.requestModel;

import com.jebao.jebaodb.entity.employee.TbDepartment;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jack on 2016/11/22.
 */
public class ParamModel {

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

}
