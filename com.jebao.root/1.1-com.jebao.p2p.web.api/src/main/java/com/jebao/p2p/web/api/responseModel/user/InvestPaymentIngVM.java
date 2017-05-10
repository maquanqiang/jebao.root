package com.jebao.p2p.web.api.responseModel.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.investment.InvestBase;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款中的项目
 * Created by Administrator on 2016/12/12.
 */
public class InvestPaymentIngVM extends ViewModel {
    public InvestPaymentIngVM(InvestBase entity){
        this.iiId = entity.getIiId();
        this.bpId = entity.getBpId();
        this.bpName = entity.getBpName();
        this.createTime = entity.getCreateTime();
        this.money = entity.getMoney();
        this.periods = String.valueOf(entity.getPeriods()) + getCycleTypeName(entity.getCycleType());
        this.bpRate = entity.getBpRate();
        this.contractUrl = entity.getContractUrl();
    }

    private String getCycleTypeName(int cycleType){
        String name = "其他";
        switch (cycleType){
            case 1:name="日";break;
            case 2:name="个月";break;
            case 3:name="季";break;
            case 4:name="年";break;
            default:break;
        }
        return name;
    }

    //投资记录ID
    private Long iiId;

    //标的ID
    private Long bpId;

    //标的名称
    private String bpName;

    //投资时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    //投资金额
    private BigDecimal money;

    //投资期限 (标的信息表中 标的期数*周期大小 + 周期类型)
    private String periods;

    //年化利率%
    private BigDecimal bpRate;

    //下个还款日
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date nextDueDate;

    //还款日应还金额
    private BigDecimal dueMoney;

    //合同地址
    private String contractUrl;

    public Long getIiId() {
        return iiId;
    }

    public void setIiId(Long iiId) {
        this.iiId = iiId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public BigDecimal getBpRate() {
        return bpRate;
    }

    public void setBpRate(BigDecimal bpRate) {
        this.bpRate = bpRate;
    }

    public Date getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(Date nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public BigDecimal getDueMoney() {
        return dueMoney;
    }

    public void setDueMoney(BigDecimal dueMoney) {
        this.dueMoney = dueMoney;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }
}
