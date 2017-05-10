package com.jebao.p2p.h5.conf;

import com.jebao.p2p.h5.utils.session.CurrentUser;
import com.jebao.p2p.h5.utils.session.CurrentUserContextHolder;
import com.jebao.p2p.h5.utils.session.LoginSessionUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  //设置当前请求用户信息的上下文
 * Created by Administrator on 2016/10/19.
 */
@Component
public class CurrentUserContext implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) res;
        String uri = request.getRequestURI();
        //过滤静态文件请求
        if(isRequestStaticFile(uri)) {
            chain.doFilter(req, res);
            return;
        }
        //设置当前用户信息的上下文
        CurrentUser currentUser=getCurrentUser(request, response);
        CurrentUserContextHolder.set(currentUser);
        chain.doFilter(req, res);
    }

    private boolean isRequestStaticFile(String uri) {
        String uriPath =uri.toLowerCase();
        return uriPath.indexOf("/content/")==0||
                uriPath.indexOf("/favicon.ico")==0
                ;
    }

    private CurrentUser getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
        CurrentUser currentUser = LoginSessionUtil.User(request, response);
        return currentUser;
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
