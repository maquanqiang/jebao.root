package com.jebao.erp.web.requestModel.investment;

import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/12/3.
 */
public class RepaymentForm {


    public TbBidPlan toEntity(RepaymentForm form){
        TbBidPlan bidPlan = new TbBidPlan();
        bidPlan.setBpId(form.getBpId());
        bidPlan.setBpLoanMoney(form.getBpLoanMoney());
        bidPlan.setBpInterestSt(form.getBpInterestSt());
        bidPlan.setBpRepayTime(form.getBpRepayTime());
        return bidPlan;
    }

    @NotNull(message = "标的id为空")
    private Long bpId;
    @NotNull(message = "金额为空")
    private BigDecimal bpLoanMoney;
    @NotNull(message = "起息时间为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bpInterestSt;
    @NotNull(message = "还款时间为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bpRepayTime;



    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public BigDecimal getBpLoanMoney() {
        return bpLoanMoney;
    }

    public void setBpLoanMoney(BigDecimal bpLoanMoney) {
        this.bpLoanMoney = bpLoanMoney;
    }

    public Date getBpInterestSt() {
        return bpInterestSt;
    }

    public void setBpInterestSt(Date bpInterestSt) {
        this.bpInterestSt = bpInterestSt;
    }

    public Date getBpRepayTime() {
        return bpRepayTime;
    }

    public void setBpRepayTime(Date bpRepayTime) {
        this.bpRepayTime = bpRepayTime;
    }
}
