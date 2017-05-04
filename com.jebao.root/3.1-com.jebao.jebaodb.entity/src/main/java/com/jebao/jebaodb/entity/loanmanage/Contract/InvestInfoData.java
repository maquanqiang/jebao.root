package com.jebao.jebaodb.entity.loanmanage.Contract;

import com.jebao.jebaodb.entity.investment.TbInvestInfo;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Lee on 2016/12/26.
 */
public class InvestInfoData {

    DecimalFormat d1 = new DecimalFormat("#,##0.##");

    public InvestInfoData(TbInvestInfo entity) {
        this.name = entity.getIiTrueName();
        this.investMoney = d1.format(entity.getIiMoney());
    }

    private String name;
    private String investMoney;
    private String bidNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(String investMoney) {
        this.investMoney = investMoney;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }
}
