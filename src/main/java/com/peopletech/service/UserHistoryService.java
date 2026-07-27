package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.peopletech.entity.Phone;
import com.peopletech.entity.UserHistory;
import com.peopletech.mapper.PhoneMapper;
import com.peopletech.mapper.UserHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserHistoryService {

    @Autowired
    private UserHistoryMapper historyMapper;

    @Autowired
    private PhoneMapper phoneMapper;

    /** 添加/更新浏览记录（同一机型只保留最新） */
    @Transactional
    public void addHistory(Long userId, Long phoneId) {
        // 先查是否有重复
        LambdaQueryWrapper<UserHistory> q = new LambdaQueryWrapper<>();
        q.eq(UserHistory::getUserId, userId).eq(UserHistory::getPhoneId, phoneId);
        List<UserHistory> exist = historyMapper.selectList(q);
        if (!exist.isEmpty()) {
            // 更新为最新时间
            LambdaUpdateWrapper<UserHistory> u = new LambdaUpdateWrapper<>();
            u.eq(UserHistory::getUserId, userId).eq(UserHistory::getPhoneId, phoneId)
              .set(UserHistory::getViewTime, LocalDateTime.now());
            historyMapper.update(null, u);
        } else {
            // 新增
            UserHistory h = new UserHistory();
            h.setUserId(userId);
            h.setPhoneId(phoneId);
            h.setViewTime(LocalDateTime.now());
            historyMapper.insert(h);
        }
    }

    /** 获取浏览历史列表（最多50条，带机型信息） */
    public List<Map<String, Object>> getHistory(Long userId) {
        LambdaQueryWrapper<UserHistory> q = new LambdaQueryWrapper<>();
        q.eq(UserHistory::getUserId, userId)
         .orderByDesc(UserHistory::getViewTime)
         .last("LIMIT 50");
        List<UserHistory> list = historyMapper.selectList(q);
        if (list.isEmpty()) return Collections.emptyList();

        List<Map<String, Object>> result = new ArrayList<>();
        for (UserHistory h : list) {
            Phone p = phoneMapper.selectById(h.getPhoneId());
            if (p == null) continue;
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("phoneId", p.getId());
            item.put("brand", p.getBrand());
            item.put("name", p.getName());
            item.put("coverImage", p.getCoverImage());
            item.put("viewTime", h.getViewTime() != null ? h.getViewTime().toString() : "");
            result.add(item);
        }
        return result;
    }

    /** 清空用户浏览历史 */
    @Transactional
    public void clearHistory(Long userId) {
        historyMapper.delete(new LambdaQueryWrapper<UserHistory>().eq(UserHistory::getUserId, userId));
    }

    /** 删除单条记录 */
    @Transactional
    public void removeHistory(Long userId, Long phoneId) {
        LambdaQueryWrapper<UserHistory> q = new LambdaQueryWrapper<>();
        q.eq(UserHistory::getUserId, userId).eq(UserHistory::getPhoneId, phoneId);
        historyMapper.delete(q);
    }
}
