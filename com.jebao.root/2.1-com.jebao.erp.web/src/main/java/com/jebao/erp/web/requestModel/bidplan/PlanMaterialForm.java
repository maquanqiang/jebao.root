package com.jebao.erp.web.requestModel.bidplan;

import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/11/24.
 */
public class PlanMaterialForm {

    public static TbBidPlan toEntity(PlanMaterialForm planForm) {
        TbBidPlan bidPlan = new TbBidPlan();
        bidPlan.setBpId(planForm.getBpId());
        bidPlan.setBpBorrowDesc(planForm.getBpBorrowDesc());
        bidPlan.setBpFundsPurpose(planForm.getBpFundsPurpose());
        bidPlan.setBpRepayingSource(planForm.getBpRepayingSource());
        bidPlan.setBpPersonalCredit(planForm.getBpPersonalCredit());
        bidPlan.setBpMortgageInfo(planForm.getBpMortgageInfo());
        bidPlan.setBpRiskOpinion(planForm.getBpRiskOpinion());
        bidPlan.setBpDesc(planForm.getBpDesc());
        return bidPlan;
    }
    private Long bpId;

    private Long rcptId;    //项目模板id

    private String bpBorrowDesc;

    private String bpFundsPurpose;

    private String bpRepayingSource;

    private String bpPersonalCredit;

    private String bpMortgageInfo;

    private String bpRiskOpinion;

    private String bpDesc;


    public Long getRcptId() {
        return rcptId;
    }

    public void setRcptId(Long rcptId) {
        this.rcptId = rcptId;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
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

    public String getBpDesc() {
        return bpDesc;
    }

    public void setBpDesc(String bpDesc) {
        this.bpDesc = bpDesc;
    }

}
