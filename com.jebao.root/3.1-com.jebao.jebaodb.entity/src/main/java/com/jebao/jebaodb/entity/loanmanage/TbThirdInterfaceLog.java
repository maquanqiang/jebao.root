package com.jebao.jebaodb.entity.loanmanage;

import java.util.Date;

public class TbThirdInterfaceLog {
    private Long tilId;

    private Integer tilType;        //富友文档接口编号

    private String tilSerialNumber;

    private String tilReturnCode;

    private String tilReqText;

    private String tilRespText;

    private Date tilCreateTime;

    public Long getTilId() {
        return tilId;
    }

    public void setTilId(Long tilId) {
        this.tilId = tilId;
    }

    public Integer getTilType() {
        return tilType;
    }

    public void setTilType(Integer tilType) {
        this.tilType = tilType;
    }

    public String getTilSerialNumber() {
        return tilSerialNumber;
    }

    public void setTilSerialNumber(String tilSerialNumber) {
        this.tilSerialNumber = tilSerialNumber == null ? null : tilSerialNumber.trim();
    }

    public String getTilReturnCode() {
        return tilReturnCode;
    }

    public void setTilReturnCode(String tilReturnCode) {
        this.tilReturnCode = tilReturnCode == null ? null : tilReturnCode.trim();
    }

    public String getTilReqText() {
        return tilReqText;
    }

    public void setTilReqText(String tilReqText) {
        this.tilReqText = tilReqText == null ? null : tilReqText.trim();
    }

    public String getTilRespText() {
        return tilRespText;
    }

    public void setTilRespText(String tilRespText) {
        this.tilRespText = tilRespText == null ? null : tilRespText.trim();
    }

    public Date getTilCreateTime() {
        return tilCreateTime;
    }

    public void setTilCreateTime(Date tilCreateTime) {
        this.tilCreateTime = tilCreateTime;
    }
}