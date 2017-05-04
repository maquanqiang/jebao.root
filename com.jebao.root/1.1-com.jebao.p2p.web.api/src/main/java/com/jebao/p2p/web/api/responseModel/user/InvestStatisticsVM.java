package com.jebao.p2p.web.api.responseModel.user;

import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 投资人账户收益总览数据展示类
 * Created by Administrator on 2016/12/12.
 */
public class InvestStatisticsVM extends ViewModel {
    public InvestStatisticsVM(Map<String, BigDecimal> map) {
        this.incomeAmount = map.get("incomeAmount").setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalAssets = map.get("totalAssets").setScale(2, BigDecimal.ROUND_HALF_UP);
        this.balance = map.get("balance");
        this.freezeAmount = map.get("freezeAmount").setScale(2, BigDecimal.ROUND_HALF_UP);
        this.dueInPrincipal = map.get("dueInPrincipal").setScale(2, BigDecimal.ROUND_HALF_UP);
        this.dueInIncome = map.get("dueInIncome").setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    //累计收益
    private BigDecimal incomeAmount;

    //总资产
    private BigDecimal totalAssets;

    //可用金额
    private BigDecimal balance;

    //冻结金额
    private BigDecimal freezeAmount;

    //待收本金
    private BigDecimal dueInPrincipal;

    //待收收益
    private BigDecimal dueInIncome;

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getDueInPrincipal() {
        return dueInPrincipal;
    }

    public void setDueInPrincipal(BigDecimal dueInPrincipal) {
        this.dueInPrincipal = dueInPrincipal;
    }

    public BigDecimal getDueInIncome() {
        return dueInIncome;
    }

    public void setDueInIncome(BigDecimal dueInIncome) {
        this.dueInIncome = dueInIncome;
    }
}
