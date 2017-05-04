package com.jebao.jebaodb.entity.extEntity;

/**
 * Created by Administrator on 2016/10/21.
 */
public class PageWhereByMaxId {
    public PageWhereByMaxId(int maxId,int pageSize)
    {
        maxId=maxId<0?0:maxId;
        pageSize=pageSize<0?0:pageSize;
        this.setMaxId(maxId);
        this.setPageSize(pageSize);
    }
    private int maxId;
    private int pageSize;

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
