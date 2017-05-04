package com.jebao.p2p.web.api.responseModel.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收支明细
 * Created by Administrator on 2016/12/3.
 */
public class FundsDetailsVM extends ViewModel {
    public FundsDetailsVM(TbFundsDetails entity) {
        this.serialNumber = entity.getFdSerialNumber();
        this.serialTime = entity.getFdSerialTime();
        this.serialTypeName = entity.getFdSerialTypeName();
        if (entity.getFdBalanceStatus() == 1) {
            this.incAmount = "+" + entity.getFdSerialAmount().toString();
            this.expAmount = "";
        } else {
            this.incAmount = "";
            this.expAmount = "-" + entity.getFdSerialAmount().toString();
        }
        this.serialStatus = getStatusName(entity.getFdSerialTypeName(), entity.getFdSerialStatus());
    }

    private String getStatusName(String typeName, int status) {
        StringBuilder name = new StringBuilder();
        name.append(typeName);
        switch (status) {
            case -1:
                name.append("失败");
                break;
            case 0:
                name.append("处理中");
                break;
            case 1:
                name.append("成功");
                break;
            default:
                name.append("失败");
                break;
        }
        return name.toString();
    }

    //交易流水号
    private String serialNumber;

    //交易时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date serialTime;

    //交易类型
    private String serialTypeName;

    //收入金额
    private String incAmount;

    //支出金额
    private String expAmount;

    //交易状态
    private String serialStatus;

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

    public String getIncAmount() {
        return incAmount;
    }

    public void setIncAmount(String incAmount) {
        this.incAmount = incAmount;
    }

    public String getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(String expAmount) {
        this.expAmount = expAmount;
    }

    public String getSerialStatus() {
        return serialStatus;
    }

    public void setSerialStatus(String serialStatus) {
        this.serialStatus = serialStatus;
    }
}
