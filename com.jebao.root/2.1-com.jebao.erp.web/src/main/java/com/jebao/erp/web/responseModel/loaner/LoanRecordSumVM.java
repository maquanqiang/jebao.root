package com.jebao.erp.web.responseModel.loaner;

import com.jebao.erp.web.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/5.
 */
public class LoanRecordSumVM extends ViewModel {
    //实际借款笔数
    private int jkCount;

    //实际借款金额
    private BigDecimal jkAmounts;

    //已还金额
    private BigDecimal yhAmounts;

    //待还金额
    private BigDecimal dhAmounts;

    public int getJkCount() {
        return jkCount;
    }

    public void setJkCount(int jkCount) {
        this.jkCount = jkCount;
    }

    public BigDecimal getJkAmounts() {
        return jkAmounts;
    }

    public void setJkAmounts(BigDecimal jkAmounts) {
        this.jkAmounts = jkAmounts;
    }

    public BigDecimal getYhAmounts() {
        return yhAmounts;
    }

    public void setYhAmounts(BigDecimal yhAmounts) {
        this.yhAmounts = yhAmounts;
    }

    public BigDecimal getDhAmounts() {
        return dhAmounts;
    }

    public void setDhAmounts(BigDecimal dhAmounts) {
        this.dhAmounts = dhAmounts;
    }
}
