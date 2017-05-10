package com.jebao.jebaodb.entity.employee;

import java.math.BigDecimal;

public class TbRank {
    private Integer rankId;

    private String rankName;

    private Integer rankParentId;

    private BigDecimal rankBrokeragePercent;

    private Boolean rankIsDel;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName == null ? null : rankName.trim();
    }

    public Integer getRankParentId() {
        return rankParentId;
    }

    public void setRankParentId(Integer rankParentId) {
        this.rankParentId = rankParentId;
    }

    public BigDecimal getRankBrokeragePercent() {
        return rankBrokeragePercent;
    }

    public void setRankBrokeragePercent(BigDecimal rankBrokeragePercent) {
        this.rankBrokeragePercent = rankBrokeragePercent;
    }

    public Boolean getRankIsDel() {
        return rankIsDel;
    }

    public void setRankIsDel(Boolean rankIsDel) {
        this.rankIsDel = rankIsDel;
    }
}