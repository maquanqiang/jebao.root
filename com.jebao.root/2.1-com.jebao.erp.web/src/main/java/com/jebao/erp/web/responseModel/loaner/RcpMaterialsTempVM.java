package com.jebao.erp.web.responseModel.loaner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/25.
 */
public class RcpMaterialsTempVM extends ViewModel {
    public RcpMaterialsTempVM(TbRcpMaterialsTemp entity){
        this.id = entity.getRcpmtId();
        this.projectId = entity.getRcpmtProjectId();
        this.no = entity.getRcpmtNo();
        this.name = entity.getRcpmtName();
        this.remark = entity.getRcpmtRemark();
        this.path = entity.getRcpmtPath();
        this.url = entity.getRcpmtUrl();
        this.createTime = entity.getRcpmtCreateTime();
    }

    private Long id;

    private Long projectId;

    private String no;

    private String name;

    private String remark;

    private String path;

    private String url;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
