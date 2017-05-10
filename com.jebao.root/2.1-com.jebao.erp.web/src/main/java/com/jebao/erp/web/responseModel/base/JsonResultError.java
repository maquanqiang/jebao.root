package com.jebao.erp.web.responseModel.base;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/20.
 */
public class JsonResultError extends JsonResult {
    public JsonResultError()
    {

    }
    public JsonResultError(String error)
    {
        this.setSuccess_is_ok(false);
        this.setState(0);
        this.setError(error);
        this.setCode(1);
    }
    public JsonResultError(String error,int code)
    {
        this.setSuccess_is_ok(false);
        this.setState(0);
        this.setError(error);
        this.setCode(code);
    }
    public JsonResultError(Map<String,String> errorMap)
    {
        this.setSuccess_is_ok(false);
        this.setState(0);
        this.setCode(999);
        this.setErrorMap(errorMap);
    }
    private String error;
    private int code;
    private Map<String,String> errorMap;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
