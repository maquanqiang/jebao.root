package com.jebao.erp.web.responseModel.bidplan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jack on 2016/11/21.
 */
public class RiskDataVM extends ViewModel {
    public RiskDataVM(TbBidRiskData entity) {

        this.brdId = entity.getBrdId();
        this.brdName = entity.getBrdName();
        this.brdNo = entity.getBrdNo();
        this.brdPath = entity.getBrdPath();
        this.brdUrl = entity.getBrdUrl();
        this.brdRemark = entity.getBrdRemark();
    }

    private Long brdId;
    private String brdName;
    private String brdNo;
    private String brdPath;
    private String brdUrl;
    private String brdRemark;


    public Long getBrdId() {
        return brdId;
    }

    public void setBrdId(Long brdId) {
        this.brdId = brdId;
    }

    public String getBrdName() {
        return brdName;
    }

    public void setBrdName(String brdName) {
        this.brdName = brdName;
    }

    public String getBrdNo() {
        return brdNo;
    }

    public void setBrdNo(String brdNo) {
        this.brdNo = brdNo;
    }

    public String getBrdPath() {
        return brdPath;
    }

    public void setBrdPath(String brdPath) {
        this.brdPath = brdPath;
    }

    public String getBrdUrl() {
        return brdUrl;
    }

    public void setBrdUrl(String brdUrl) {
        this.brdUrl = brdUrl;
    }

    public String getBrdRemark() {
        return brdRemark;
    }

    public void setBrdRemark(String brdRemark) {
        this.brdRemark = brdRemark;
    }
}
