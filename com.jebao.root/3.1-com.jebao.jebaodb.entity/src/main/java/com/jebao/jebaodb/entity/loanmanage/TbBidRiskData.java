package com.jebao.jebaodb.entity.loanmanage;

import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;

import java.util.Date;

public class TbBidRiskData {

    public static TbBidRiskData toEntity(TbRcpMaterialsTemp temp){
        TbBidRiskData riskData = new TbBidRiskData();
        riskData.setBrdNo(temp.getRcpmtNo());
        riskData.setBrdRemark(temp.getRcpmtRemark());
        riskData.setBrdName(temp.getRcpmtName());
        riskData.setBrdPath(temp.getRcpmtPath());
        riskData.setBrdUrl(temp.getRcpmtUrl());
        return riskData;
    }

    private Long brdId;

    private Long brdBpId;

    private String brdName;

    private String brdNo;

    private String brdPath;

    private String brdUrl;

    private Date brdCreateTime;

    private Date brdUpdateTime;

    private String brdRemark;

    private Integer brdIsDel;

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

    public String getBrdName() {
        return brdName;
    }

    public void setBrdName(String brdName) {
        this.brdName = brdName == null ? null : brdName.trim();
    }

    public String getBrdPath() {
        return brdPath;
    }

    public void setBrdPath(String brdPath) {
        this.brdPath = brdPath == null ? null : brdPath.trim();
    }

    public String getBrdUrl() {
        return brdUrl;
    }

    public void setBrdUrl(String brdUrl) {
        this.brdUrl = brdUrl == null ? null : brdUrl.trim();
    }

    public Date getBrdCreateTime() {
        return brdCreateTime;
    }

    public void setBrdCreateTime(Date brdCreateTime) {
        this.brdCreateTime = brdCreateTime;
    }

    public Date getBrdUpdateTime() {
        return brdUpdateTime;
    }

    public void setBrdUpdateTime(Date brdUpdateTime) {
        this.brdUpdateTime = brdUpdateTime;
    }

    public String getBrdRemark() {
        return brdRemark;
    }

    public void setBrdRemark(String brdRemark) {
        this.brdRemark = brdRemark == null ? null : brdRemark.trim();
    }

    public Integer getBrdIsDel() {
        return brdIsDel;
    }

    public void setBrdIsDel(Integer brdIsDel) {
        this.brdIsDel = brdIsDel;
    }

    public String getBrdNo() {
        return brdNo;
    }

    public void setBrdNo(String brdNo) {
        this.brdNo = brdNo;
    }
}