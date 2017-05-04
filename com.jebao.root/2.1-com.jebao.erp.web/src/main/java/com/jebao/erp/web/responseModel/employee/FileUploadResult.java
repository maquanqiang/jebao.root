package com.jebao.erp.web.responseModel.employee;

/**
 * Created by Jack on 2017/1/5.
 */
public class FileUploadResult {
    private int error;
    private String message;
    private String url;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
