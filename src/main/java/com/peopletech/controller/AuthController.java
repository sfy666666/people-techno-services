package com.peopletech.controller;

import com.peopletech.common.Result;
import com.peopletech.service.AdminUserService;
import com.peopletech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import com.peopletech.entity.AdminUser;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return Result.fail(400, "用户名和密码不能为空");
        }
        try {
            Map<String, Object> result = adminUserService.login(username, password);
            return Result.ok(result);
        } catch (RuntimeException e) {
            return Result.fail(401, e.getMessage());
        }
    }

    @GetMapping("/userinfo")
    public Result<Object> getUserInfo(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        String token = authHeader.substring(7);
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(token);
            Long userId = Long.parseLong(claims.getSubject());
            AdminUser user = adminUserService.getUserInfo(userId);
            if (user == null) return Result.fail(404, "用户不存在");
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", user.getId());
            userInfo.put("username", user.getUsername());
            userInfo.put("nickname", user.getNickname());
            userInfo.put("role", user.getRole());
            return Result.ok(userInfo);
        } catch (Exception e) {
            return Result.fail(401, "token无效");
        }
    }
}
