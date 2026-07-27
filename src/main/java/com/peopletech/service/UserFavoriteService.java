package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.Phone;
import com.peopletech.entity.UserFavorite;
import com.peopletech.mapper.PhoneMapper;
import com.peopletech.mapper.UserFavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserFavoriteService {

    @Autowired
    private UserFavoriteMapper favoriteMapper;

    @Autowired
    private PhoneMapper phoneMapper;

    /** 添加收藏 */
    @Transactional
    public void addFavorite(Long userId, Long phoneId) {
        // 查是否已收藏
        LambdaQueryWrapper<UserFavorite> q = new LambdaQueryWrapper<>();
        q.eq(UserFavorite::getUserId, userId).eq(UserFavorite::getPhoneId, phoneId);
        if (favoriteMapper.selectCount(q) > 0) {
            return; // 已收藏，忽略
        }
        UserFavorite f = new UserFavorite();
        f.setUserId(userId);
        f.setPhoneId(phoneId);
        favoriteMapper.insert(f);
    }

    /** 取消收藏 */
    @Transactional
    public void removeFavorite(Long userId, Long phoneId) {
        LambdaQueryWrapper<UserFavorite> q = new LambdaQueryWrapper<>();
        q.eq(UserFavorite::getUserId, userId).eq(UserFavorite::getPhoneId, phoneId);
        favoriteMapper.delete(q);
    }

    /** 是否已收藏 */
    public boolean isFavorite(Long userId, Long phoneId) {
        LambdaQueryWrapper<UserFavorite> q = new LambdaQueryWrapper<>();
        q.eq(UserFavorite::getUserId, userId).eq(UserFavorite::getPhoneId, phoneId);
        return favoriteMapper.selectCount(q) > 0;
    }

    /** 获取收藏列表 */
    public List<Map<String, Object>> getFavorites(Long userId) {
        LambdaQueryWrapper<UserFavorite> q = new LambdaQueryWrapper<>();
        q.eq(UserFavorite::getUserId, userId).orderByDesc(UserFavorite::getCreateTime);
        List<UserFavorite> list = favoriteMapper.selectList(q);
        if (list.isEmpty()) return Collections.emptyList();

        List<Map<String, Object>> result = new ArrayList<>();
        for (UserFavorite f : list) {
            Phone p = phoneMapper.selectById(f.getPhoneId());
            if (p == null) continue;
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("phoneId", p.getId());
            item.put("brand", p.getBrand());
            item.put("name", p.getName());
            item.put("coverImage", p.getCoverImage());
            item.put("price", p.getPrice());
            item.put("createTime", f.getCreateTime() != null ? f.getCreateTime().toString() : "");
            result.add(item);
        }
        return result;
    }

    /** 获取收藏数量 */
    public long getFavoriteCount(Long userId) {
        LambdaQueryWrapper<UserFavorite> q = new LambdaQueryWrapper<>();
        q.eq(UserFavorite::getUserId, userId);
        return favoriteMapper.selectCount(q);
    }
}
