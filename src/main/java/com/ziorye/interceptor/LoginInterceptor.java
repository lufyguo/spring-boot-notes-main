package com.ziorye.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ziorye
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor@preHandle ---> " + request.getRequestURI());

        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") != null) {
            return true;
        }

        request.setAttribute("msg", "未登录");
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }
}
