package com.jebao.jebaodb.entity.user;

public class TbBankCity {
    private Integer bcCityId;

    private String bcProvincesCode;

    private String bcCityCode;

    private String bcCityName;

    public Integer getBcCityId() {
        return bcCityId;
    }

    public void setBcCityId(Integer bcCityId) {
        this.bcCityId = bcCityId;
    }

    public String getBcProvincesCode() {
        return bcProvincesCode;
    }

    public void setBcProvincesCode(String bcProvincesCode) {
        this.bcProvincesCode = bcProvincesCode == null ? null : bcProvincesCode.trim();
    }

    public String getBcCityCode() {
        return bcCityCode;
    }

    public void setBcCityCode(String bcCityCode) {
        this.bcCityCode = bcCityCode == null ? null : bcCityCode.trim();
    }

    public String getBcCityName() {
        return bcCityName;
    }

    public void setBcCityName(String bcCityName) {
        this.bcCityName = bcCityName == null ? null : bcCityName.trim();
    }
}