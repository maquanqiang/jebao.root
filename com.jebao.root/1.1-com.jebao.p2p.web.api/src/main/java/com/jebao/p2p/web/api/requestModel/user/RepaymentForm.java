package com.jebao.p2p.web.api.requestModel.user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/17.
 */
public class RepaymentForm {

    @NotNull(message = "期数为空")
    private Integer period;             //当前期数
    @NotNull(message = "标号为空")
    private Long bpId;                  //标id
    @NotNull(message = "还款金额为空")
    @Min(value = 0, message = "金额小于0")
    private BigDecimal repayMoney;      //还款金额

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public BigDecimal getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }
}
