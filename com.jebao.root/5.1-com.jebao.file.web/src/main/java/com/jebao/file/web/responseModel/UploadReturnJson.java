package com.jebao.file.web.responseModel;

/**
 * Created by Administrator on 2016/12/15.
 */
//上传返回结果
public class UploadReturnJson {
    public UploadReturnJson() {

    }

    //上传成功
    public UploadReturnJson(String url) {
        setError(0);
        setUrl(url);
    }

    //上传失败
    public UploadReturnJson(int error, String message) {
        if (error < 1) {
            try {
                throw new Exception("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        setError(error);
        setMessage("[上传失败]" + message);
    }

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