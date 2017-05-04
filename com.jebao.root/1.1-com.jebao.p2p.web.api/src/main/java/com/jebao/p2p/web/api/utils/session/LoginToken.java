package com.jebao.p2p.web.api.utils.session;

import javax.print.DocFlavor;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/24.
 */
public class LoginToken {

    public LoginToken()
    {}
    public LoginToken(String uuid,String redirectUrl)
    {
        this.setUuid(uuid);
        this.setRedirectUrl(redirectUrl);
        this.setDate(new Date());
    }
    private String uuid;
    private String redirectUrl;
    private Date date;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
