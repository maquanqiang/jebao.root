package com.jebao.erp.web.responseModel.bidplan;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;

/**
 * Created by Lee on 2016/11/25.
 */
public class ProjTempNameVM extends ViewModel {

    public ProjTempNameVM(TbRiskCtlPrjTemp entity) {
        this.setRcptId(entity.getRcptId());
        this.setRcptName(entity.getRcptName());

    }

    private Long rcptId;

    private String rcptName;


    public Long getRcptId() {
        return rcptId;
    }

    public void setRcptId(Long rcptId) {
        this.rcptId = rcptId;
    }

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }
}
