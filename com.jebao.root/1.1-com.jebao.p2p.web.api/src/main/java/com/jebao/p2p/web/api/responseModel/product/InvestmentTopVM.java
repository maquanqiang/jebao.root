package com.jebao.p2p.web.api.responseModel.product;

import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/24.
 */
public class InvestmentTopVM extends ViewModel {

    public InvestmentTopVM(String name, String money) {
        this.name = name;
        this.money = money;
    }

    private String name;
    private String money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
