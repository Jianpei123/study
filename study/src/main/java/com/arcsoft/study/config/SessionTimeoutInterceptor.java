package com.arcsoft.study.config;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionTimeoutInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO 自动生成的方法存根
        HttpSession session = request.getSession(false);
        String url=request.getRequestURL().toString();
        if (session != null) {
            return true;
        }
        else if (url.contains("login")||url.contains("user")) {
                return true;
        } 
        else {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("{\"timeout\":true}");
                return false;
            }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO 自动生成的方法存根

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO 自动生成的方法存根

    }

}
