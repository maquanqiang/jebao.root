package com.jebao.erp.web.responseModel.bidplan;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;

/**
 * Created by Lee on 2016/11/25.
 */
public class ProjectTempVM extends ViewModel {


    public ProjectTempVM(TbRiskCtlPrjTemp entity) {
        this.rcptId = entity.getRcptId();
        this.bpBorrowDesc = entity.getRcptBorrowDesc();
        this.bpFundsPurpose = entity.getRcptFundsPurpose();
        this.bpMortgageInfo = entity.getRcptMortgageInfo();
        this.bpPersonalCredit = entity.getRcptPersonalCredit();
        this.bpRiskOpinion = entity.getRcptOpinion();
        this.bpRepayingSource = entity.getRcptRepayingSource();
        this.bpRcptDesc = entity.getRcptDesc();
    }

    private Long rcptId;

    private String bpBorrowDesc;

    private String bpFundsPurpose;

    private String bpRepayingSource;

    private String bpPersonalCredit;

    private String bpMortgageInfo;

    private String bpRiskOpinion;

    private String bpRcptDesc;

    public Long getRcptId() {
        return rcptId;
    }

    public void setRcptId(Long rcptId) {
        this.rcptId = rcptId;
    }

    public String getBpBorrowDesc() {
        return bpBorrowDesc;
    }

    public void setBpBorrowDesc(String bpBorrowDesc) {
        this.bpBorrowDesc = bpBorrowDesc;
    }

    public String getBpFundsPurpose() {
        return bpFundsPurpose;
    }

    public void setBpFundsPurpose(String bpFundsPurpose) {
        this.bpFundsPurpose = bpFundsPurpose;
    }

    public String getBpRepayingSource() {
        return bpRepayingSource;
    }

    public void setBpRepayingSource(String bpRepayingSource) {
        this.bpRepayingSource = bpRepayingSource;
    }

    public String getBpPersonalCredit() {
        return bpPersonalCredit;
    }

    public void setBpPersonalCredit(String bpPersonalCredit) {
        this.bpPersonalCredit = bpPersonalCredit;
    }

    public String getBpMortgageInfo() {
        return bpMortgageInfo;
    }

    public void setBpMortgageInfo(String bpMortgageInfo) {
        this.bpMortgageInfo = bpMortgageInfo;
    }

    public String getBpRiskOpinion() {
        return bpRiskOpinion;
    }

    public void setBpRiskOpinion(String bpRiskOpinion) {
        this.bpRiskOpinion = bpRiskOpinion;
    }

    public String getBpRcptDesc() {
        return bpRcptDesc;
    }

    public void setBpRcptDesc(String bpRcptDesc) {
        this.bpRcptDesc = bpRcptDesc;
    }
}
