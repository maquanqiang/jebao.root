package com.jebao.p2p.web.api.requestModel.product;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Lee on 2016/12/8.
 */
public class InvestInfoForm {
    @NotNull(message="标的不能为空！")
    private Long bpId;

    @DecimalMin(value="1",message="投资金额最小值是1")
    @NotNull(message = "投资金额不能为空")
    private BigDecimal investMoney;


    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

}
