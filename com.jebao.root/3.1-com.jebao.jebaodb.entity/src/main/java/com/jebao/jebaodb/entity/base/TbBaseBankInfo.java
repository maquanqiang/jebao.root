package com.jebao.jebaodb.entity.base;

public class TbBaseBankInfo {
    private Integer blId;

    private String blBankCode;

    private String blBankName;

    private String blBankBin;

    private Boolean blIsDel;

    public Integer getBlId() {
        return blId;
    }

    public void setBlId(Integer blId) {
        this.blId = blId;
    }

    public String getBlBankCode() {
        return blBankCode;
    }

    public void setBlBankCode(String blBankCode) {
        this.blBankCode = blBankCode == null ? null : blBankCode.trim();
    }

    public String getBlBankName() {
        return blBankName;
    }

    public void setBlBankName(String blBankName) {
        this.blBankName = blBankName == null ? null : blBankName.trim();
    }

    public String getBlBankBin() {
        return blBankBin;
    }

    public void setBlBankBin(String blBankBin) {
        this.blBankBin = blBankBin == null ? null : blBankBin.trim();
    }

    public Boolean getBlIsDel() {
        return blIsDel;
    }

    public void setBlIsDel(Boolean blIsDel) {
        this.blIsDel = blIsDel;
    }
}