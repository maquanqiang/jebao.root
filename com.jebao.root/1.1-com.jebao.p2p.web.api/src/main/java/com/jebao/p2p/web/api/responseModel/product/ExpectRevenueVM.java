package com.jebao.p2p.web.api.responseModel.product;

import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Lee on 2017/2/20.
 */
public class ExpectRevenueVM extends ViewModel {

    private BigDecimal investMoney;         //投资金额
    private BigDecimal expectRevenue;       //收益

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public BigDecimal getExpectRevenue() {
        return expectRevenue;
    }

    public void setExpectRevenue(BigDecimal expectRevenue) {
        this.expectRevenue = expectRevenue;
    }
}
