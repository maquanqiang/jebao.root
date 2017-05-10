package com.jebao.p2p.h5.conf;

import com.jebao.p2p.h5.utils.session.LoginSessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截未登录的用户信息
 * Created by Administrator on 2016/10/11.
 */
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证用户是否登陆
        boolean isLogin = LoginSessionUtil.isLogin(request, response);
        if (!isLogin) {
            String redirectUrl = request.getRequestURI();
            response.sendRedirect(request.getContextPath() + "/account/login?redirect="+redirectUrl);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}

