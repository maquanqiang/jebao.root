package com.jebao.jebaodb.entity.loaner;

import java.util.Date;

public class TbRcpMaterialsTemp {
    private Long rcpmtId;

    private Long rcpmtProjectId;

    private String rcpmtNo;

    private String rcpmtName;

    private String rcpmtRemark;

    private String rcpmtPath;

    private String rcpmtUrl;

    private Date rcpmtCreateTime;

    private Integer rcpmtIsDel;

    public Long getRcpmtId() {
        return rcpmtId;
    }

    public void setRcpmtId(Long rcpmtId) {
        this.rcpmtId = rcpmtId;
    }

    public Long getRcpmtProjectId() {
        return rcpmtProjectId;
    }

    public void setRcpmtProjectId(Long rcpmtProjectId) {
        this.rcpmtProjectId = rcpmtProjectId;
    }

    public String getRcpmtNo() {
        return rcpmtNo;
    }

    public void setRcpmtNo(String rcpmtNo) {
        this.rcpmtNo = rcpmtNo == null ? null : rcpmtNo.trim();
    }

    public String getRcpmtName() {
        return rcpmtName;
    }

    public void setRcpmtName(String rcpmtName) {
        this.rcpmtName = rcpmtName == null ? null : rcpmtName.trim();
    }

    public String getRcpmtRemark() {
        return rcpmtRemark;
    }

    public void setRcpmtRemark(String rcpmtRemark) {
        this.rcpmtRemark = rcpmtRemark == null ? null : rcpmtRemark.trim();
    }

    public String getRcpmtPath() {
        return rcpmtPath;
    }

    public void setRcpmtPath(String rcpmtPath) {
        this.rcpmtPath = rcpmtPath == null ? null : rcpmtPath.trim();
    }

    public String getRcpmtUrl() {
        return rcpmtUrl;
    }

    public void setRcpmtUrl(String rcpmtUrl) {
        this.rcpmtUrl = rcpmtUrl == null ? null : rcpmtUrl.trim();
    }

    public Date getRcpmtCreateTime() {
        return rcpmtCreateTime;
    }

    public void setRcpmtCreateTime(Date rcpmtCreateTime) {
        this.rcpmtCreateTime = rcpmtCreateTime;
    }

    public Integer getRcpmtIsDel() {
        return rcpmtIsDel;
    }

    public void setRcpmtIsDel(Integer rcpmtIsDel) {
        this.rcpmtIsDel = rcpmtIsDel;
    }
}