package com.jebao.jebaodb.entity.loanmanage.Contract;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lee on 2016/12/26.
 */
public class ContractParamForm {

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

    private Long investId;      //投资id
    private String bidNumber;   //标的编号
    private String contractName;//合同名字

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

    public Long getInvestId() {
        return investId;
    }

    public void setInvestId(Long investId) {
        this.investId = investId;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return  "bpId=" + bpId +
                "&bpLoanMoney=" + bpLoanMoney +
                "&bpInterestSt=" + sdf.format(bpInterestSt) +
                "&bpRepayTime=" + sdf.format(bpRepayTime) +
                "&investId=" + investId +
                "&bidNumber=" + bidNumber +
                "&contractName=" + contractName;
    }
}
