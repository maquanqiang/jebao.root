package com.jebao.jebaodb.entity.base;

public class TbBaseRegionInfo {
    private Integer riId;

    private String riCode;

    private String riName;

    private String riParentCode;

    private Integer riLevel;

    public Integer getRiId() {
        return riId;
    }

    public void setRiId(Integer riId) {
        this.riId = riId;
    }

    public String getRiCode() {
        return riCode;
    }

    public void setRiCode(String riCode) {
        this.riCode = riCode == null ? null : riCode.trim();
    }

    public String getRiName() {
        return riName;
    }

    public void setRiName(String riName) {
        this.riName = riName == null ? null : riName.trim();
    }

    public String getRiParentCode() {
        return riParentCode;
    }

    public void setRiParentCode(String riParentCode) {
        this.riParentCode = riParentCode == null ? null : riParentCode.trim();
    }

    public Integer getRiLevel() {
        return riLevel;
    }

    public void setRiLevel(Integer riLevel) {
        this.riLevel = riLevel;
    }
}