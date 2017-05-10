package com.jebao.thirdPay.fuiou.model.querycztx;

import com.jebao.thirdPay.fuiou.model.base.BasePlain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwei on 2016/9/26.
 */
public class ResponsePlain extends BasePlain {
    private String busi_tp="";
    private String total_number="";
    @XmlElementWrapper(name="results")
    @XmlElement(name="result")
    List<ResponsePlainResult> results=new ArrayList<ResponsePlainResult>();

    public String getBusi_tp() {
        return busi_tp;
    }

    public void setBusi_tp(String busi_tp) {
        this.busi_tp = busi_tp;
    }

    public String getTotal_number() {
        return total_number;
    }

    public void setTotal_number(String total_number) {
        this.total_number = total_number;
    }
}