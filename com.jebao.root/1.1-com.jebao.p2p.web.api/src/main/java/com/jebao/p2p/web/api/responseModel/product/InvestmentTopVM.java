package com.jebao.p2p.web.api.responseModel.product;

import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/24.
 */
public class InvestmentTopVM extends ViewModel {

    public InvestmentTopVM(TbInvestInfo info) {
        this.name = info.getIiTrueName().substring(0,1)+"***";
        this.money = info.getIiMoney().setScale(0).toString();
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
