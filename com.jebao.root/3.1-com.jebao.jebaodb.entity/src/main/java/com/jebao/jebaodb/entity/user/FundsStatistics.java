package com.jebao.jebaodb.entity.user;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/6.
 */
public class FundsStatistics {
    //交易次数
    private int totalTrades;

    //交易金额
    private BigDecimal totalAmounts;

    //交易类型
    private int serialTypeId;

    public int getTotalTrades() {
        return totalTrades;
    }

    public void setTotalTrades(int totalTrades) {
        this.totalTrades = totalTrades;
    }

    public BigDecimal getTotalAmounts() {
        return totalAmounts;
    }

    public void setTotalAmounts(BigDecimal totalAmounts) {
        this.totalAmounts = totalAmounts;
    }

    public int getSerialTypeId() {
        return serialTypeId;
    }

    public void setSerialTypeId(int serialTypeId) {
        this.serialTypeId = serialTypeId;
    }
}
