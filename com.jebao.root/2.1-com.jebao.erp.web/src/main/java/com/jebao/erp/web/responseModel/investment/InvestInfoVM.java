package com.jebao.erp.web.responseModel.investment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jack on 2016/11/21.
 */
public class InvestInfoVM extends ViewModel {

    public InvestInfoVM(TbInvestInfo entity) {
        this.setId(entity.getIiId());
        this.setLoginId(entity.getIiLoginId());
        this.setTrueName(entity.getIiTrueName());
        this.setThirdAccount(entity.getIiThirdAccount());
        this.setSsn(entity.getIiSsn());
        this.setBpId(entity.getIiBpId());
        this.setBpName(entity.getIiBpName());
        this.setMoney(entity.getIiMoney());
        this.setFreezeStatus(entity.getIiFreezeStatus());
        this.setContractUrl(entity.getIiContractUrl());
        this.setBpRepayedPeriods(entity.getIiBpRepayedPeriods());
        this.setCreateTime(entity.getIiCreateTime());
        this.setLoginName(entity.getIiLoginName());
        this.setBpBidMoney(entity.getBpBidMoney());
    }


    private Long id;

    private Long loginId;

    private String loginName;

    private String trueName;

    private String thirdAccount;

    private String ssn;

    private Long bpId;

    private String bpName;

    private BigDecimal money;

    private Integer freezeStatus;

    private String contractUrl;

    private Integer bpRepayedPeriods;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    private BigDecimal bpBidMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getThirdAccount() {
        return thirdAccount;
    }

    public void setThirdAccount(String thirdAccount) {
        this.thirdAccount = thirdAccount;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public String getBpName() {
        return bpName;
    }

    public void setBpName(String bpName) {
        this.bpName = bpName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getBpBidMoney() {
        return bpBidMoney;
    }

    public void setBpBidMoney(BigDecimal bpBidMoney) {
        this.bpBidMoney = bpBidMoney;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Integer getBpRepayedPeriods() {
        return bpRepayedPeriods;
    }

    public void setBpRepayedPeriods(Integer bpRepayedPeriods) {
        this.bpRepayedPeriods = bpRepayedPeriods;
    }
}
