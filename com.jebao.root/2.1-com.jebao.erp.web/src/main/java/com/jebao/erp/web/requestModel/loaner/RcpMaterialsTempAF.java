package com.jebao.erp.web.requestModel.loaner;

import com.jebao.erp.web.requestModel.base._BaseForm;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2016/11/25.
 */
public class RcpMaterialsTempAF extends _BaseForm {
    private Long projectId;

    /*@NotBlank(message="材料编号不能为空")*/
    private String no;

    @NotBlank(message="材料名称不能为空")
    private String name;

    @NotBlank(message="备注不能为空")
    private String remark;

    private String path;

    private String url;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
