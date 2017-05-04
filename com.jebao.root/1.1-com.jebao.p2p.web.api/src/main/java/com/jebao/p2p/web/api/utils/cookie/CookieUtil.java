package com.jebao.p2p.web.api.utils.cookie;

import com.jebao.common.utils.encrypt.EncryptDESUtil;
import com.jebao.p2p.web.api.utils.constants.ProjectSetting;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/9/21.
 */
public class CookieUtil {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private int age;//设置cookie经过多长秒后被删除
    private String domain=getDomainVal();
    public CookieUtil(HttpServletRequest request,
                      HttpServletResponse response, int age) {
        this.request = request;
        this.response = response;
        this.age = age;
    }

    public void addCookie(String name, String value) {
        if(value!=null)
        {
            value= EncryptDESUtil.toEncrypt(value);
        }
        Cookie cookies = new Cookie(name, value);
        cookies.setPath("/");
        if(!StringUtils.isBlank(domain))
        {
            cookies.setDomain(domain);
        }
        //cookies.setMaxAge(-1);//设置cookie经过多长秒后被删除。如果0，就说明立即删除。如果是负数就表明当浏览器关闭时自动删除。
        cookies.setMaxAge(age);
        response.addCookie(cookies);
    }

    public String getCookieValue(String cookieName) {
        if (cookieName != null) {
            Cookie cookie = getCookie(cookieName);
            if (cookie != null) {
                String cookieValue= cookie.getValue();
                return EncryptDESUtil.toDecrypt(cookieValue);
            }
        }
        return "";
    }

    public Cookie getCookie(String cookieName) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        try {
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if (cookie.getName().equals(cookieName)) {
                        return cookie;
                    }
                }
                //注意：如果这里不做设置的话可能返回的cookie不是你想要的值
                cookie = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;
    }

    public boolean deleteCookie(String cookieName) {
        if (cookieName != null) {
            Cookie cookie = getCookie(cookieName);
            if (cookie != null) {
                cookie.setMaxAge(0);//如果0，就说明立即删除
                cookie.setPath("/");//不要漏掉
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAllCookie() {
        Cookie[] cookies = request.getCookies();
        try {
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    deleteCookie(cookie.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String getDomainVal() {
        return ProjectSetting.getConfigProperty("project.login.session.domain");
    }

    /**
     public static void main(String[] args) {
     CookieUtil util=new CookieUtil(request,response,-1);
     util.addCookie("name","value");
     String value=util.getCookieValue("name");
     System.out.println("value="+value);
     }*/
}