package com.peopletech.config;

import com.peopletech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行公开接口
        if (request.getRequestURI().contains("/api/auth/login")
                || request.getRequestURI().contains("/api/app-user/register")
                || request.getRequestURI().contains("/api/app-user/login")) {
            return true;
        }
        // 放行公开接口
        if (request.getRequestURI().contains("/api/home")
                || request.getRequestURI().contains("/api/phone/list")
                || request.getRequestURI().contains("/api/phone/detail")
                || request.getRequestURI().contains("/api/phone/compare")
                || request.getRequestURI().contains("/api/news")
                || request.getRequestURI().contains("/api/hot-rank")
                || request.getRequestURI().contains("/api/phone/battery-rank")
                || request.getRequestURI().contains("/api/phone/charge-rank")
                || request.getRequestURI().contains("/api/phone/screen-rank")
                || request.getRequestURI().contains("/api/phone/benchmark-rank")) {
            return true;
        }

        String auth = request.getHeader("Authorization");
        if (!StringUtils.hasText(auth) || !auth.startsWith("Bearer ")) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"未登录，请先登录\"}");
            return false;
        }

        String token = auth.substring(7);
        if (!jwtUtil.validateToken(token)) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"登录已过期，请重新登录\"}");
            return false;
        }
        return true;
    }
}
