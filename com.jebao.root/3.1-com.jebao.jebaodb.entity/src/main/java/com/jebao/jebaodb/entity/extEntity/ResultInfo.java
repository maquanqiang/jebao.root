package com.jebao.jebaodb.entity.extEntity;

/**
 * Created by Jack on 2016/11/23.
 */
public class ResultInfo {
    public ResultInfo(boolean success){
        this.setSuccess_is_ok(success);
    }
    public ResultInfo(boolean success,String msg){
        this.setSuccess_is_ok(success);
        this.setMsg(msg);
    }
    public ResultInfo(boolean success,String msg,int code){
        this.success_is_ok=success;
        this.msg=msg;
        this.code=code;
    }
    private boolean success_is_ok;
    private String msg;
    private int code;

    public boolean getSuccess_is_ok() {
        return success_is_ok;
    }

    public void setSuccess_is_ok(boolean success_is_ok) {
        this.success_is_ok = success_is_ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
