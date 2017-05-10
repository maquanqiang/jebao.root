package com.jebao.erp.web.responseModel.base;

import com.jebao.erp.web.responseModel.ViewModel;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class JsonResultList<T extends ViewModel> extends JsonResult {
    public JsonResultList()
    {
    }
    public JsonResultList(List<T> data)
    {
        this.setSuccess_is_ok(true);
        this.setState(1);
        this.setData(data);
    }
    public JsonResultList(List<T> data,int count)
    {
        this.setSuccess_is_ok(true);
        this.setState(1);
        this.setData(data);
        this.setCount(count);
    }
    private List<T>data;
    private int count;
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
