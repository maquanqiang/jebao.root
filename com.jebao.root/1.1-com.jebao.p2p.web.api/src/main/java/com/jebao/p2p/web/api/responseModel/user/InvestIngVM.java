package com.jebao.p2p.web.api.responseModel.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.investment.InvestBase;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
public class InvestIngVM extends ViewModel {
    public InvestIngVM(InvestBase entity){
        this.bpId = entity.getBpId();
        this.bpName = entity.getBpName();
        this.createTime = entity.getCreateTime();
        this.money = entity.getMoney();
        this.periods = String.valueOf(entity.getPeriods()) + getCycleTypeName(entity.getCycleType());
        this.bpRate = entity.getBpRate();
        this.progress = getProgressValue(entity.getProgress());
    }

    private BigDecimal getProgressValue(BigDecimal pro){
        if(pro.compareTo(new BigDecimal(0)) == 1 && pro.compareTo(new BigDecimal(1)) == -1){
            return new BigDecimal(1);
        }else if(pro.compareTo(new BigDecimal(99)) == 1 && pro.compareTo(new BigDecimal(100)) == -1){
            return new BigDecimal(99);
        }else{
            return pro.setScale(0, BigDecimal.ROUND_HALF_UP);
        }
    }

    private String getCycleTypeName(int cycleType){
        String name = "其他";
        switch (cycleType){
            case 1:name="天";break;
            case 2:name="个月";break;
            case 3:name="季";break;
            case 4:name="年";break;
            default:break;
        }
        return name;
    }

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

    //投资进度%(1-(剩余金额/标的总额))*100
    private BigDecimal progress;

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

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }
}
