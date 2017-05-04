package com.jebao.erp.web.responseModel.loaner;

import com.jebao.erp.web.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/2.
 */
public class FundsSumVM extends ViewModel {
    private int czCount;
    private BigDecimal czAmounts;
    private int txCount;
    private BigDecimal txAmounts;

    public int getCzCount() {
        return czCount;
    }

    public void setCzCount(int czCount) {
        this.czCount = czCount;
    }

    public BigDecimal getCzAmounts() {
        return czAmounts;
    }

    public void setCzAmounts(BigDecimal czAmounts) {
        this.czAmounts = czAmounts;
    }

    public int getTxCount() {
        return txCount;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }

    public BigDecimal getTxAmounts() {
        return txAmounts;
    }

    public void setTxAmounts(BigDecimal txAmounts) {
        this.txAmounts = txAmounts;
    }
}
