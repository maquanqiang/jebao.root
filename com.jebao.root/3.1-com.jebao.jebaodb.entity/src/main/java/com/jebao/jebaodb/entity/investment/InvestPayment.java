package com.jebao.jebaodb.entity.investment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投资记录-还款项目实体类
 * Created by Administrator on 2016/12/20.
 */
public class InvestPayment {
    private Long iiId;

    private Date dateTime;

    private BigDecimal totalMoney;

    public Long getIiId() {
        return iiId;
    }

    public void setIiId(Long iiId) {
        this.iiId = iiId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
