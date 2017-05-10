package com.jebao.thirdPay.fuiou.model.balanceAction;

import com.jebao.thirdPay.fuiou.model.base.BasePlain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ResponsePlain extends BasePlain {
    private List<ResponsePlainResult> results = new ArrayList<ResponsePlainResult>();

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "result")
    public List<ResponsePlainResult> getResults() {
        return results;
    }

    public void setResults(List<ResponsePlainResult> results) {
        this.results = results;
    }
}
