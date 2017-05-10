package com.jebao.p2p.web.api.responseModel.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2016/12/23.
 */
public class RecentInvestVM extends ViewModel {


    public RecentInvestVM(TbInvestInfo entity){
        String trueName = entity.getIiTrueName().substring(0, 1);

        this.trueName = trueName+"***";
        this.investMoney = entity.getIiMoney();
        this.investDate = entity.getIiCreateTime();
    }


    private String trueName;
    private BigDecimal investMoney;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date investDate;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public Date getInvestDate() {
        return investDate;
    }

    public void setInvestDate(Date investDate) {
        this.investDate = investDate;
    }
}
