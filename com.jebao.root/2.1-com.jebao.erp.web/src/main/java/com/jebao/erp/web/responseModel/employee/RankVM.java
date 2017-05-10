package com.jebao.erp.web.responseModel.employee;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.employee.TbRank;

import java.math.BigDecimal;

/**
 * Created by Jack on 2016/11/21.
 */
public class RankVM extends ViewModel {
    public RankVM(TbRank entity) {
        this.id = entity.getRankId();
        this.name = entity.getRankName();
        this.parentId = entity.getRankParentId();
        this.brokerage = entity.getRankBrokeragePercent();
    }

    private int id;
    private String name;
    private int parentId;
    private BigDecimal brokerage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }
}
