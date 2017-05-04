package com.jebao.thirdPay.fuiou.model.query;

import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ResponsePlain extends BasePlain {
    @XmlElementWrapper(name="opResultSet")
    @XmlElement(name="opResult")
    List<ResponsePlainResultSet> opResults=new ArrayList<ResponsePlainResultSet>();
}
