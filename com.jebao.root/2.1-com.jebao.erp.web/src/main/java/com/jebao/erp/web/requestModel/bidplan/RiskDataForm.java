package com.jebao.erp.web.requestModel.bidplan;

import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;

/**
 * Created by Lee on 2016/11/17.
 */
public class RiskDataForm {

    public static TbBidRiskData toEntity(RiskDataForm form){
        TbBidRiskData tbBidRiskData = new TbBidRiskData();
        tbBidRiskData.setBrdBpId(form.getBrdBpId());
        tbBidRiskData.setBrdName(form.getName());
        tbBidRiskData.setBrdNo(form.getBrdNo());
        tbBidRiskData.setBrdUrl(form.getBrdUrl());
        tbBidRiskData.setBrdPath(form.getPath());
        tbBidRiskData.setBrdRemark(form.getRemark());
        return tbBidRiskData;
    }

    private Long brdId;

    private Long brdBpId;

    private String name;

    private String brdNo;

    private String path;

    private String brdUrl;

    private String remark;



    public String getBrdUrl() {
        return brdUrl;
    }

    public void setBrdUrl(String brdUrl) {
        this.brdUrl = brdUrl;
    }

    public Long getBrdId() {
        return brdId;
    }

    public void setBrdId(Long brdId) {
        this.brdId = brdId;
    }

    public Long getBrdBpId() {
        return brdBpId;
    }

    public void setBrdBpId(Long brdBpId) {
        this.brdBpId = brdBpId;
    }

    public String getBrdNo() {
        return brdNo;
    }

    public void setBrdNo(String brdNo) {
        this.brdNo = brdNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

