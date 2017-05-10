package com.jebao.p2p.web.api.responseModel.product;

import com.jebao.p2p.web.api.responseModel.ViewModel;

/**
 * Created by Lee on 2016/12/19.
 */
public class ProductResultVM extends ViewModel {

    private boolean flag;            // 成功失败标识
    private String msg;             // 返回（成功/失败）信息

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
