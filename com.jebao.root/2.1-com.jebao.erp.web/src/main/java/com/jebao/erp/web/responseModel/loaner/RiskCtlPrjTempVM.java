package com.jebao.erp.web.responseModel.loaner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/24.
 */
public class RiskCtlPrjTempVM extends ViewModel {
    public RiskCtlPrjTempVM(TbRiskCtlPrjTemp entity){
        this.id = entity.getRcptId();
        this.loanerId = entity.getRcptLoanerId();
        this.name = entity.getRcptName();
        this.updateTime = entity.getRcptUpdateTime();
        this.borrowDesc = entity.getRcptBorrowDesc();
        this.fundsPurpose = entity.getRcptFundsPurpose();
        this.repayingSource = entity.getRcptRepayingSource();
        this.mortgageInfo = entity.getRcptMortgageInfo();
        this.personalCredit = entity.getRcptPersonalCredit();
        this.opinion = entity.getRcptOpinion();
        this.desc = entity.getRcptDesc();
    }

    private Long id;

    private Long loanerId;

    private String name;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private String borrowDesc;

    private String fundsPurpose;

    private String repayingSource;

    private String mortgageInfo;

    private String personalCredit;

    private String opinion;

    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanerId() {
        return loanerId;
    }

    public void setLoanerId(Long loanerId) {
        this.loanerId = loanerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBorrowDesc() {
        return borrowDesc;
    }

    public void setBorrowDesc(String borrowDesc) {
        this.borrowDesc = borrowDesc;
    }

    public String getFundsPurpose() {
        return fundsPurpose;
    }

    public void setFundsPurpose(String fundsPurpose) {
        this.fundsPurpose = fundsPurpose;
    }

    public String getRepayingSource() {
        return repayingSource;
    }

    public void setRepayingSource(String repayingSource) {
        this.repayingSource = repayingSource;
    }

    public String getMortgageInfo() {
        return mortgageInfo;
    }

    public void setMortgageInfo(String mortgageInfo) {
        this.mortgageInfo = mortgageInfo;
    }

    public String getPersonalCredit() {
        return personalCredit;
    }

    public void setPersonalCredit(String personalCredit) {
        this.personalCredit = personalCredit;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
