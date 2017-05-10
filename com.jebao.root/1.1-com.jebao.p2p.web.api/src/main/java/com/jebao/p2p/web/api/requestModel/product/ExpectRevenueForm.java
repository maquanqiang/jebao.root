package com.jebao.p2p.web.api.requestModel.product;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2017/2/20.
 */
public class ExpectRevenueForm {

    @NotNull(message = "参数有误")
    private BigDecimal bpRate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bpExpectLoanDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bpExpectRepayDate;

    @DecimalMin(value="1",message="投资金额有误")
    @NotNull(message = "投资金额不能为空")
    private BigDecimal investMoney;

    public BigDecimal getBpRate() {
        return bpRate;
    }

    public void setBpRate(BigDecimal bpRate) {
        this.bpRate = bpRate;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public Date getBpExpectLoanDate() {
        return bpExpectLoanDate;
    }

    public void setBpExpectLoanDate(Date bpExpectLoanDate) {
        this.bpExpectLoanDate = bpExpectLoanDate;
    }

    public Date getBpExpectRepayDate() {
        return bpExpectRepayDate;
    }

    public void setBpExpectRepayDate(Date bpExpectRepayDate) {
        this.bpExpectRepayDate = bpExpectRepayDate;
    }
}
