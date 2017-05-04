package com.jebao.p2p.web.api.responseModel.product;

import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.util.Date;

/**
 * Created by Lee on 2016/12/12.
 */
public class BidRiskDataVM extends ViewModel {

    public BidRiskDataVM(TbBidRiskData entity) {
        this.brdId = entity.getBrdId();
        this.brdBpId = entity.getBrdBpId();
        this.brdName = entity.getBrdName();
        this.brdNo = entity.getBrdNo();
        this.brdPath = entity.getBrdPath();
        this.brdUrl = entity.getBrdUrl();
        this.brdRemark = entity.getBrdRemark();
    }

    private Long brdId;

    private Long brdBpId;

    private String brdName;

    private String brdNo;

    private String brdPath;

    private String brdUrl;

    private String brdRemark;

    public Long getBrdId() {
        return brdId;
    }

    public void setBrdId(Long brdId) {
        this.brdId = brdId;
    }

    public Long getBrdBpId() {
        return brdBpId;
    }

    public void setBrdBpId(Long brdBpId) {
        this.brdBpId = brdBpId;
    }

    public String getBrdName() {
        return brdName;
    }

    public void setBrdName(String brdName) {
        this.brdName = brdName;
    }

    public String getBrdNo() {
        return brdNo;
    }

    public void setBrdNo(String brdNo) {
        this.brdNo = brdNo;
    }

    public String getBrdPath() {
        return brdPath;
    }

    public void setBrdPath(String brdPath) {
        this.brdPath = brdPath;
    }

    public String getBrdUrl() {
        return brdUrl;
    }

    public void setBrdUrl(String brdUrl) {
        this.brdUrl = brdUrl;
    }

    public String getBrdRemark() {
        return brdRemark;
    }

    public void setBrdRemark(String brdRemark) {
        this.brdRemark = brdRemark;
    }
}
