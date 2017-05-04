package com.jebao.p2p.web.api.responseModel.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/12/12.
 */
public class IncomeDetailsVM extends ViewModel {

    public IncomeDetailsVM(TbIncomeDetail entity) {
        this.setIndBpId(entity.getIndBpId());
        this.setIndBpName(entity.getIndBpName());
        this.setBpTrueName(entity.getBpTrueName());
        this.setIndBpNumber(entity.getIndBpNumber());
        this.setIndDateTime(entity.getIndDateTime());
        this.setIndFactDateTime(entity.getIndFactDateTime());
        this.setIndFundType(entity.getIndFundType());
        this.setIndMoney(entity.getIndMoney());
        this.setIndOverdueDays(entity.getIndOverdueDays());
        this.setIndOverdueMoney(entity.getIndOverdueMoney());
        this.setIndStatus(entity.getIndStatus());
        this.setIndPeriods(entity.getIndPeriods());
        this.setIndFactMoeny(entity.getIndFactMoeny());
        this.setIndTrueName(entity.getIndTrueName());
    }


    private Long indBpId;

    private String bpTrueName;

    private String indBpNumber;

    private String indBpName;

    private Integer indPeriods;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date indDateTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date indFactDateTime;

    private Integer indOverdueDays;

    private BigDecimal indOverdueMoney;

    private Integer indFundType;

    private BigDecimal indMoney;

    private BigDecimal indFactMoeny;

    private Integer indStatus;

    private String indTrueName;


    public Long getIndBpId() {
        return indBpId;
    }

    public void setIndBpId(Long indBpId) {
        this.indBpId = indBpId;
    }

    public String getIndBpNumber() {
        return indBpNumber;
    }

    public void setIndBpNumber(String indBpNumber) {
        this.indBpNumber = indBpNumber;
    }

    public String getIndBpName() {
        return indBpName;
    }

    public void setIndBpName(String indBpName) {
        this.indBpName = indBpName;
    }

    public Integer getIndPeriods() {
        return indPeriods;
    }

    public void setIndPeriods(Integer indPeriods) {
        this.indPeriods = indPeriods;
    }

    public Date getIndDateTime() {
        return indDateTime;
    }

    public void setIndDateTime(Date indDateTime) {
        this.indDateTime = indDateTime;
    }

    public Date getIndFactDateTime() {
        return indFactDateTime;
    }

    public void setIndFactDateTime(Date indFactDateTime) {
        this.indFactDateTime = indFactDateTime;
    }

    public Integer getIndOverdueDays() {
        return indOverdueDays;
    }

    public void setIndOverdueDays(Integer indOverdueDays) {
        this.indOverdueDays = indOverdueDays;
    }

    public BigDecimal getIndOverdueMoney() {
        return indOverdueMoney;
    }

    public void setIndOverdueMoney(BigDecimal indOverdueMoney) {
        this.indOverdueMoney = indOverdueMoney;
    }

    public Integer getIndFundType() {
        return indFundType;
    }

    public void setIndFundType(Integer indFundType) {
        this.indFundType = indFundType;
    }

    public BigDecimal getIndMoney() {
        return indMoney;
    }

    public void setIndMoney(BigDecimal indMoney) {
        this.indMoney = indMoney;
    }

    public BigDecimal getIndFactMoeny() {
        return indFactMoeny;
    }

    public void setIndFactMoeny(BigDecimal indFactMoeny) {
        this.indFactMoeny = indFactMoeny;
    }

    public Integer getIndStatus() {
        return indStatus;
    }

    public void setIndStatus(Integer indStatus) {
        this.indStatus = indStatus;
    }

    public String getBpTrueName() {
        return bpTrueName;
    }

    public void setBpTrueName(String bpTrueName) {
        this.bpTrueName = bpTrueName;
    }

    public String getIndTrueName() {
        return indTrueName;
    }

    public void setIndTrueName(String indTrueName) {
        this.indTrueName = indTrueName;
    }
}
