package com.peopletech.controller;

import com.peopletech.common.Result;
import com.peopletech.entity.AppUser;
import com.peopletech.service.AppUserService;
import com.peopletech.service.UserHistoryService;
import com.peopletech.service.UserFavoriteService;
import com.peopletech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/app-user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserHistoryService userHistoryService;

    @Autowired
    private UserFavoriteService userFavoriteService;

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

    /** 添加浏览记录 */
    @PostMapping("/history/add")
    public Result<Void> addHistory(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody Map<String, Object> params) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            Long phoneId = params.get("phoneId") != null ? Long.parseLong(params.get("phoneId").toString()) : null;
            if (phoneId == null) return Result.fail(400, "phoneId不能为空");
            userHistoryService.addHistory(userId, phoneId);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 获取浏览历史列表 */
    @GetMapping("/history/list")
    public Result<List<Map<String, Object>>> getHistoryList(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            List<Map<String, Object>> list = userHistoryService.getHistory(userId);
            return Result.ok(list);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 清空浏览历史 */
    @DeleteMapping("/history/clear")
    public Result<Void> clearHistory(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            userHistoryService.clearHistory(userId);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 删除单条浏览记录 */
    @DeleteMapping("/history/{phoneId}")
    public Result<Void> removeHistory(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable Long phoneId) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            userHistoryService.removeHistory(userId, phoneId);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    // ========== 收藏相关 ==========

    /** 添加收藏 */
    @PostMapping("/favorite/add")
    public Result<Void> addFavorite(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody Map<String, Long> params) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            Long phoneId = params.get("phoneId");
            if (phoneId == null) return Result.fail(400, "phoneId不能为空");
            userFavoriteService.addFavorite(userId, phoneId);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 取消收藏 */
    @DeleteMapping("/favorite/{phoneId}")
    public Result<Void> removeFavorite(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable Long phoneId) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            userFavoriteService.removeFavorite(userId, phoneId);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 是否已收藏 */
    @GetMapping("/favorite/check/{phoneId}")
    public Result<Map<String, Object>> checkFavorite(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable Long phoneId) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            boolean isFav = userFavoriteService.isFavorite(userId, phoneId);
            Map<String, Object> map = new HashMap<>();
            map.put("isFavorite", isFav);
            return Result.ok(map);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 获取收藏列表 */
    @GetMapping("/favorite/list")
    public Result<List<Map<String, Object>>> listFavorites(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            List<Map<String, Object>> list = userFavoriteService.getFavorites(userId);
            return Result.ok(list);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }

    /** 获取收藏数量 */
    @GetMapping("/favorite/count")
    public Result<Map<String, Object>> favoriteCount(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail(401, "未登录");
        }
        try {
            io.jsonwebtoken.Claims claims = jwtUtil.parseToken(authHeader.substring(7));
            Long userId = Long.parseLong(claims.getSubject());
            long count = userFavoriteService.getFavoriteCount(userId);
            Map<String, Object> map = new HashMap<>();
            map.put("count", count);
            return Result.ok(map);
        } catch (Exception e) {
            return Result.fail(401, "token无效或已过期");
        }
    }
}
