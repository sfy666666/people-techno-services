package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.AdminUser;
import com.peopletech.mapper.AdminUserMapper;
import com.peopletech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, Object> login(String username, String password) {
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<AdminUser>()
                .eq(AdminUser::getUsername, username);
        AdminUser user = adminUserMapper.selectOne(wrapper);

        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        String md5pwd = DigestUtils.md5DigestAsHex(("people_techno_" + password).getBytes(StandardCharsets.UTF_8));
        if (!md5pwd.equals(user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        userInfo.put("nickname", user.getNickname());
        userInfo.put("role", user.getRole());
        result.put("user", userInfo);
        return result;
    }

    public AdminUser getUserInfo(Long userId) {
        return adminUserMapper.selectById(userId);
    }

    public void initAdmin() {
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<AdminUser>()
                .eq(AdminUser::getUsername, "admin");
        AdminUser exist = adminUserMapper.selectOne(wrapper);
        if (exist != null) return;

        String md5pwd = DigestUtils.md5DigestAsHex(("people_techno_" + "admin123").getBytes(StandardCharsets.UTF_8));
        AdminUser admin = new AdminUser();
        admin.setUsername("admin");
        admin.setPassword(md5pwd);
        admin.setNickname("管理员");
        admin.setRole("admin");
        adminUserMapper.insert(admin);
    }
}
