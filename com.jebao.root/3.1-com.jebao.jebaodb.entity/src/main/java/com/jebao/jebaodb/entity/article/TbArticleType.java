package com.jebao.jebaodb.entity.article;

public class TbArticleType {
    private Integer atId;

    private String atName;

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName == null ? null : atName.trim();
    }
}