package com.emigroup.web.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestInterceptor extends HandlerInterceptorAdapter {
    private static final String username="emilab1";
    private static final String pwd="emilab1";
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        System.out.println("拦截 warning");
//        System.out.println(session.getAttribute("username").equals(username));
        //判断用户ID是否存在，不存在就跳转到登录界面
        System.out.println(session.getAttribute("username"));
        if(session.getAttribute("username") == null){
//            logger.info("------:跳转到login页面！");
            System.out.println("此处应该跳转");
            response.sendRedirect("/login.html");
            return false;
        }else if(session.getAttribute("username").equals(username)&&session.getAttribute("password").equals(pwd)){
            return true;
        }
        else {
            response.sendRedirect("/login.html");
            return false;
        }


    }
}