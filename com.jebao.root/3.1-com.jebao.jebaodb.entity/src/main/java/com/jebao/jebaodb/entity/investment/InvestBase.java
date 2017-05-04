package com.jebao.jebaodb.entity.investment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投资记录
 * Created by Administrator on 2016/12/9.
 */
public class InvestBase {
    //投资记录ID
    private Long iiId;

    //标的ID
    private Long bpId;

    //标的名称
    private String bpName;

    //投资时间
    private Date createTime;

    //投资金额
    private BigDecimal money;

    //投资期限 (标的信息表中 标的期数*周期大小)
    private int periods;

    //周期类型 1 : 按日 2 : 按月 3 : 按季 4 : 按年 5 : 其他
    private int cycleType;

    //年化利率%
    private BigDecimal bpRate;

    //投资进度%(1-(剩余金额/标的总额))*100
    private BigDecimal progress;

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

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getCycleType() {
        return cycleType;
    }

    public void setCycleType(int cycleType) {
        this.cycleType = cycleType;
    }

    public BigDecimal getBpRate() {
        return bpRate;
    }

    public void setBpRate(BigDecimal bpRate) {
        this.bpRate = bpRate;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }
}