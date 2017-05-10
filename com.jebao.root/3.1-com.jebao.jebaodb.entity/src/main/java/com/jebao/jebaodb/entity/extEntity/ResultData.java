package com.jebao.jebaodb.entity.extEntity;

/**
 * Created by Jack on 2016/11/23.
 */
public class ResultData <T> extends ResultInfo{
    public ResultData(T data){
        super(true);
        this.setData(data);
    }
    public ResultData(boolean flag,String msg){
        super(flag,msg);
    }
    public ResultData(boolean flag,T data,String msg){
        super(flag,msg);
        this.setData(data);
    }
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
