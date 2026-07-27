package com.peopletech.controller;

import com.peopletech.common.Result;
import com.peopletech.entity.AppUser;
import com.peopletech.service.AppUserService;
import com.peopletech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/app-user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JwtUtil jwtUtil;

    /** 注册 */
    @PostMapping("/register")
    public Result<Map<String, Object>> register(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        if (username == null || password == null) {
            return Result.fail(400, "用户名和密码不能为空");
        }
        try {
            Map<String, Object> data = appUserService.register(username, password);
            return Result.ok(data);
        } catch (RuntimeException e) {
            return Result.fail(400, e.getMessage());
        }
    }

    /** 登录 */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        if (username == null || password == null) {
            return Result.fail(400, "用户名和密码不能为空");
        }
        try {
            Map<String, Object> data = appUserService.login(username, password);
            return Result.ok(data);
        } catch (RuntimeException e) {
            return Result.fail(401, e.getMessage());
        }
    }

    /** 获取当前用户信息 */
    @GetMapping("/info")
    public Result<Object> getUserInfo(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            AppUser user = appUserService.getUserInfo(userId);
            if (user == null) return Result.fail(404, "用户不存在");
            Map<String, Object> info = new HashMap<>();
            info.put("userId", user.getId());
            info.put("username", user.getUsername());
            info.put("nickname", user.getNickname());
            info.put("avatar", user.getAvatar() != null ? user.getAvatar() : "");
            return Result.ok(info);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 更新个人资料 */
    @PutMapping("/profile")
    public Result<Void> updateProfile(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody Map<String, String> params) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            appUserService.updateProfile(userId, params.get("nickname"), params.get("avatar"));
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }
}
