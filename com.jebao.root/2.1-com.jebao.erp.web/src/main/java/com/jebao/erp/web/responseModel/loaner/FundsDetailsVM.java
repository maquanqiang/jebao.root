package com.jebao.erp.web.responseModel.loaner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.user.TbFundsDetails;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/2.
 */
public class FundsDetailsVM extends ViewModel {
    public FundsDetailsVM(TbFundsDetails entity) {
        this.id = entity.getFdId();
        this.loginId = entity.getFdLoginId();
        this.serialNumber = entity.getFdSerialNumber();
        this.serialTime = entity.getFdSerialTime();
        this.serialTypeName = entity.getFdSerialTypeName();
        this.serialAmount = entity.getFdSerialAmount();
        this.commissionCharge = entity.getFdCommissionCharge();
    }

    private Long id;

    private Long loginId;

    private String serialNumber;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date serialTime;

    private String serialTypeName;

    private BigDecimal serialAmount;

    private BigDecimal commissionCharge;

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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getSerialTime() {
        return serialTime;
    }

    public void setSerialTime(Date serialTime) {
        this.serialTime = serialTime;
    }

    public String getSerialTypeName() {
        return serialTypeName;
    }

    public void setSerialTypeName(String serialTypeName) {
        this.serialTypeName = serialTypeName;
    }

    public BigDecimal getSerialAmount() {
        return serialAmount;
    }

    public void setSerialAmount(BigDecimal serialAmount) {
        this.serialAmount = serialAmount;
    }

    public BigDecimal getCommissionCharge() {
        return commissionCharge;
    }

    public void setCommissionCharge(BigDecimal commissionCharge) {
        this.commissionCharge = commissionCharge;
    }
}
