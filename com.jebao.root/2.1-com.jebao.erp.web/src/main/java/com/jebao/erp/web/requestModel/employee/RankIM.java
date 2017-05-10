package com.jebao.erp.web.requestModel.employee;

import com.jebao.jebaodb.entity.employee.TbRank;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

/**
 * Created by Jack on 2016/12/20.
 */
public class RankIM {
    public TbRank toEntity(){
        TbRank entity = new TbRank();
        entity.setRankId(this.id);
        entity.setRankName(this.name);
        entity.setRankParentId(this.parentId);
        entity.setRankBrokeragePercent(this.brokerage);
        return entity;
    }
    private int id;
    @NotBlank(message = "名称不能为空")
    private String name;
    private Integer parentId;
    /**
     * 0-1之间的小数，对应前台的百分比数
     */
    @Range(min = 0L,max = 1L,message = "业绩提成比例错误，必须为0-100%之间")
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }
}
