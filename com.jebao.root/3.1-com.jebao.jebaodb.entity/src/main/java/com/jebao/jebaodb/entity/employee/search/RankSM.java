package com.jebao.jebaodb.entity.employee.search;

import com.jebao.jebaodb.entity.extEntity.PageWhere;

/**
 * Created by Jack on 2016/12/5.
 */
public class RankSM extends PageWhere {
    public RankSM(){
        super(0,1000);
    }
    private String name;
    private Integer parentId;
    /**
     * 根据parentId查询时，是否包含parent自身
     */
    private boolean parentAndSelf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isParentAndSelf() {
        return parentAndSelf;
    }

    public void setParentAndSelf(boolean parentAndSelf) {
        this.parentAndSelf = parentAndSelf;
    }
}
