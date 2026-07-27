package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.AppUser;
import com.peopletech.mapper.AppUserMapper;
import com.peopletech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppUserService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /** 注册 */
    public Map<String, Object> register(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("用户名不能为空");
        }
        if (password == null || password.length() < 6) {
            throw new RuntimeException("密码至少6位");
        }
        // 查重
        long count = appUserMapper.selectCount(
            new LambdaQueryWrapper<AppUser>().eq(AppUser::getUsername, username.trim())
        );
        if (count > 0) {
            throw new RuntimeException("用户名已存在");
        }
        AppUser user = new AppUser();
        user.setUsername(username.trim());
        user.setPassword(encoder.encode(password));
        user.setNickname("用户" + username.trim().substring(0, Math.min(3, username.trim().length())));
        appUserMapper.insert(user);

        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), "app_user");
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getId());
        result.put("username", user.getUsername());
        result.put("nickname", user.getNickname());
        result.put("token", token);
        return result;
    }

    /** 登录 */
    public Map<String, Object> login(String username, String password) {
        AppUser user = appUserMapper.selectOne(
            new LambdaQueryWrapper<AppUser>().eq(AppUser::getUsername, username.trim())
        );
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), "app_user");
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getId());
        result.put("username", user.getUsername());
        result.put("nickname", user.getNickname());
        result.put("avatar", user.getAvatar());
        result.put("token", token);
        return result;
    }

    /** 查询用户信息 */
    public AppUser getUserInfo(Long userId) {
        return appUserMapper.selectById(userId);
    }

    /** 更新昵称/头像 */
    public void updateProfile(Long userId, String nickname, String avatar) {
        AppUser user = new AppUser();
        user.setId(userId);
        if (nickname != null) user.setNickname(nickname);
        if (avatar != null) user.setAvatar(avatar);
        appUserMapper.updateById(user);
    }
}
