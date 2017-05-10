package com.jebao.jebaodb.entity.loaner;

import java.util.Date;

public class TbRiskCtlPrjTemp {
    private Long rcptId;

    private Long rcptLoanerId;

    private String rcptName;

    private String rcptBorrowDesc;

    private String rcptFundsPurpose;

    private String rcptRepayingSource;

    private String rcptMortgageInfo;

    private String rcptPersonalCredit;

    private String rcptOpinion;

    private Date rcptCreateTime;

    private Date rcptUpdateTime;

    private Integer rcptIsDel;

    private String rcptDesc;

    public Long getRcptId() {
        return rcptId;
    }

    public void setRcptId(Long rcptId) {
        this.rcptId = rcptId;
    }

    public Long getRcptLoanerId() {
        return rcptLoanerId;
    }

    public void setRcptLoanerId(Long rcptLoanerId) {
        this.rcptLoanerId = rcptLoanerId;
    }

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName == null ? null : rcptName.trim();
    }

    public String getRcptBorrowDesc() {
        return rcptBorrowDesc;
    }

    public void setRcptBorrowDesc(String rcptBorrowDesc) {
        this.rcptBorrowDesc = rcptBorrowDesc == null ? null : rcptBorrowDesc.trim();
    }

    public String getRcptFundsPurpose() {
        return rcptFundsPurpose;
    }

    public void setRcptFundsPurpose(String rcptFundsPurpose) {
        this.rcptFundsPurpose = rcptFundsPurpose == null ? null : rcptFundsPurpose.trim();
    }

    public String getRcptRepayingSource() {
        return rcptRepayingSource;
    }

    public void setRcptRepayingSource(String rcptRepayingSource) {
        this.rcptRepayingSource = rcptRepayingSource == null ? null : rcptRepayingSource.trim();
    }

    public String getRcptMortgageInfo() {
        return rcptMortgageInfo;
    }

    public void setRcptMortgageInfo(String rcptMortgageInfo) {
        this.rcptMortgageInfo = rcptMortgageInfo == null ? null : rcptMortgageInfo.trim();
    }

    public String getRcptPersonalCredit() {
        return rcptPersonalCredit;
    }

    public void setRcptPersonalCredit(String rcptPersonalCredit) {
        this.rcptPersonalCredit = rcptPersonalCredit == null ? null : rcptPersonalCredit.trim();
    }

    public String getRcptOpinion() {
        return rcptOpinion;
    }

    public void setRcptOpinion(String rcptOpinion) {
        this.rcptOpinion = rcptOpinion == null ? null : rcptOpinion.trim();
    }

    public Date getRcptCreateTime() {
        return rcptCreateTime;
    }

    public void setRcptCreateTime(Date rcptCreateTime) {
        this.rcptCreateTime = rcptCreateTime;
    }

    public Date getRcptUpdateTime() {
        return rcptUpdateTime;
    }

    public void setRcptUpdateTime(Date rcptUpdateTime) {
        this.rcptUpdateTime = rcptUpdateTime;
    }

    public Integer getRcptIsDel() {
        return rcptIsDel;
    }

    public void setRcptIsDel(Integer rcptIsDel) {
        this.rcptIsDel = rcptIsDel;
    }

    public String getRcptDesc() {
        return rcptDesc;
    }

    public void setRcptDesc(String rcptDesc) {
        this.rcptDesc = rcptDesc == null ? null : rcptDesc.trim();
    }
}