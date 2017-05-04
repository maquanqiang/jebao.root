package com.jebao.jebaodb.entity.loanmanage.search;

import com.jebao.jebaodb.entity.extEntity.PageWhere;

/**
 * Created by Administrator on 2016/12/19.
 */
public class BidPlanExtSM extends PageWhere {
    private Long loanerId;

    private Integer bpStatus;


    public Integer getBpStatus() {
        return bpStatus;
    }

    public void setBpStatus(Integer bpStatus) {
        this.bpStatus = bpStatus;
    }

    public Long getLoanerId() {
        return loanerId;
    }

    public void setLoanerId(Long loanerId) {
        this.loanerId = loanerId;
    }
}
