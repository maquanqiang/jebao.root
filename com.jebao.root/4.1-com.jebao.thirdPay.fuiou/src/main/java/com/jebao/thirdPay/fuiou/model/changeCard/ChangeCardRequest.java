package com.jebao.thirdPay.fuiou.model.changeCard;


import com.jebao.thirdPay.fuiou.model.base.BaseRequest;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ChangeCardRequest extends BaseRequest{

    private String login_id; //个人用户
    private String page_notify_url; //商户返回地址
    private String signature; //签名数据

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getPage_notify_url() {
        return page_notify_url;
    }

    public void setPage_notify_url(String page_notify_url) {
        this.page_notify_url = page_notify_url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * 商户P2P网站免登录用户更换银行卡接口
     *
     * @return
     */
    public String requestSignPlain() {
        String src = login_id + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|"+ page_notify_url;
        return src;
    }
}
