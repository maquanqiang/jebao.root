package com.jebao.erp.web.responseModel.loaner;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
public class RiskCtlPrjMTempVM extends ViewModel {
    private Long id;

    private Long loanerId;

    private String name;

    private String borrowDesc;

    private String fundsPurpose;

    private String repayingSource;

    private String mortgageInfo;

    private String personalCredit;

    private String opinion;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;

    private String desc;

    private List<TbRcpMaterialsTemp> rcpmtList;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<TbRcpMaterialsTemp> getRcpmtList() {
        return rcpmtList;
    }

    public void setRcpmtList(List<TbRcpMaterialsTemp> rcpmtList) {
        this.rcpmtList = rcpmtList;
    }
}
