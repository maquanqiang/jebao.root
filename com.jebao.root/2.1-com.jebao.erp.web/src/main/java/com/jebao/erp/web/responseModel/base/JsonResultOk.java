package com.jebao.erp.web.responseModel.base;

/**
 * Created by Administrator on 2016/10/20.
 */
public class JsonResultOk extends JsonResult {
    public JsonResultOk()
    {
        this.setSuccess_is_ok(true);
        this.setState(1);
    }
    public JsonResultOk(String msg)
    {
        this.setSuccess_is_ok(true);
        this.setState(1);
        this.setMsg(msg);
    }
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
