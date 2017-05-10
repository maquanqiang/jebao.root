package com.jebao.erp.web.responseModel.bidplan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import org.apache.el.lang.ELArithmetic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lee on 2016/11/23.
 */
public class LoanIntentVM extends ViewModel {



    private Integer intentPeriod;        //当前还款期数
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date repayDate;              //还款日期
    private BigDecimal principal;        //本金
    private BigDecimal interest;         //利息
    private BigDecimal total;            //总计
    private Integer days;                //周期天数
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date interestEt;             //到期日期

    public Date getInterestEt() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(repayDate);
        cal.add(Calendar.DATE, -1);

        return cal.getTime();
    }

    public void setInterestEt(Date interestEt) {
        this.interestEt = interestEt;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getIntentPeriod() {
        return intentPeriod;
    }

    public void setIntentPeriod(Integer intentPeriod) {
        this.intentPeriod = intentPeriod;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
