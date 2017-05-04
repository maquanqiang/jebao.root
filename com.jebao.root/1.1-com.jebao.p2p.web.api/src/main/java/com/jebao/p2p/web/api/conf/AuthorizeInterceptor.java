package com.jebao.p2p.web.api.conf;

import com.jebao.common.utils.fastjson.FastJsonUtil;
import com.jebao.p2p.web.api.utils.session.LoginSessionUtil;
import com.jebao.p2p.web.api.responseModel.base.AuthorizeResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try{
                String jsonResult= FastJsonUtil.serialize(new AuthorizeResult());
                response.getWriter().write(jsonResult);
            }catch (IOException e1) {
                e1.printStackTrace();
            }
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

