package com.jebao.p2p.web.api.responseModel.base;

/**
 * Created by Administrator on 2016/10/20.
 */
public class JsonResult {
    public JsonResult() {
    }

    private boolean success_is_ok;
    private int state;// 0为假；1为真；

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }


    public boolean getSuccess_is_ok() {
        return success_is_ok;
    }

    public void setSuccess_is_ok(boolean success_is_ok) {
        this.success_is_ok = success_is_ok;
    }
}

