package com.jebao.jebaodb.entity.loaner;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/6.
 */
public class LoanMovementTotal {
    //金额统计
    private BigDecimal totalAmounts;

    //统计类型(待还、已还)
    private int typeId;

    public BigDecimal getTotalAmounts() {
        return totalAmounts;
    }

    public void setTotalAmounts(BigDecimal totalAmounts) {
        this.totalAmounts = totalAmounts;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
