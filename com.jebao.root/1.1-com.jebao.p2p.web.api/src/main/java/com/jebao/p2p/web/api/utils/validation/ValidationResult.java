package com.jebao.p2p.web.api.utils.validation;

/**
 * Created by Administrator on 2016/10/14.
 */

import java.util.Map;

/**
 * 校验结果
 * @author wdmcygah
 *
 */
public class ValidationResult {

    //校验结果是否有错
    private boolean hasErrors;

    //校验错误信息
    private Map<String,String> errorMsg;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
                + errorMsg + "]";
    }

}
