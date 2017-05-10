package com.jebao.p2p.web.api.requestModel.user;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/14.
 */
public class RechargeSM {
    /*@DecimalMin(value="1",message="充值金额最小值是1")*/
    @NotNull(message = "充值金额不能为空")
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
