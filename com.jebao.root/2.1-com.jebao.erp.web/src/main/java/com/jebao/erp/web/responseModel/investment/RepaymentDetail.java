package com.jebao.erp.web.responseModel.investment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbLoanerRepaymentDetail;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/12/3.
 */
public class RepaymentDetail extends ViewModel {


    public static TbLoanerRepaymentDetail toRepayDetail(RepaymentDetail repaymentDetail){
        Date today = new Date();
        TbLoanerRepaymentDetail detail = new TbLoanerRepaymentDetail();
        detail.setLrdStatus(1);
        detail.setLrdPeriods(repaymentDetail.getIntentPeriod());
        detail.setLrdUpdateTime(today);
        detail.setLrdCreateTime(today);
//        detail.setLrdBpId(form.getBpId());
//        detail.setLrdBpName(tbBidPlan.getBpName());
//        detail.setLrdBpNumber(tbBidPlan.getBpNumber());
        detail.setLrdDateTime(repaymentDetail.getRepayDate());
        detail.setLrdFundType(repaymentDetail.getFundType());
        detail.setLrdInterestEt(repaymentDetail.getInterestEt());
        detail.setLrdInterestSt(repaymentDetail.getInterestSt());
        detail.setLrdIsDel(1);
        detail.setLrdMoney(repaymentDetail.getMoney());
        detail.setLrdOverdueDays(0);
        detail.setLrdOverdueMoney(BigDecimal.ZERO);
        return detail;
    }


    public static TbIncomeDetail toIncomeDetail(RepaymentDetail repaymentDetail){
        Date today = new Date();
        TbIncomeDetail detail = new TbIncomeDetail();
        detail.setIndCreateTime(today);
        detail.setIndUpdateTime(today);
        detail.setIndDateTime(repaymentDetail.getRepayDate());
        detail.setIndFundType(repaymentDetail.getFundType());
        detail.setIndInterestSt(repaymentDetail.getInterestSt());
        detail.setIndInterestEt(repaymentDetail.getInterestEt());
        detail.setIndIsDel(1);                          //初始 未删除 有效
        detail.setIndMoney(repaymentDetail.getMoney());
        detail.setIndOverdueDays(0);                    //初始 逾期天数为0
        detail.setIndOverdueMoney(BigDecimal.ZERO);     //逾期金额0
        detail.setIndPeriods(repaymentDetail.getIntentPeriod());
        detail.setIndStatus(1);                         //未还款
//        detail.setIndBpName();
//        detail.setIndBpNumber();
//        detail.setIndIiId();
//        detail.setIndLoginId();
//        detail.setIndThirdAccount();
//        detail.setIndTrueName();
        return detail;
    }

    private Integer intentPeriod;        //当前还款期数
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date repayDate;              //还款日期
    private Integer fundType;        //资金类型   1本金  2 利息
    private BigDecimal money;
    private Date interestSt;
    private Date interestEt;


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

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getInterestSt() {
        return interestSt;
    }

    public void setInterestSt(Date interestSt) {
        this.interestSt = interestSt;
    }

    public Date getInterestEt() {
        return interestEt;
    }

    public void setInterestEt(Date interestEt) {
        this.interestEt = interestEt;
    }
}
