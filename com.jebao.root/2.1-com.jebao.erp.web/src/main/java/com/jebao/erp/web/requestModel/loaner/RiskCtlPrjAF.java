package com.jebao.erp.web.requestModel.loaner;

import com.jebao.erp.web.requestModel.base._BaseForm;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2016/11/25.
 */
public class RiskCtlPrjAF extends _BaseForm {
    private Long id;

    private Long loanerId;

    @NotBlank(message="项目名称不能为空")
    private String name;

    @NotBlank(message="借款描述不能为空")
    private String borrowDesc;

    @NotBlank(message="资金用途不能为空")
    private String fundsPurpose;

    @NotBlank(message="还款来源不能为空")
    private String repayingSource;

    @NotBlank(message="抵押信息不能为空")
    private String mortgageInfo;

    @NotBlank(message="个人征信不能为空")
    private String personalCredit;

    @NotBlank(message="风控意见不能为空")
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
