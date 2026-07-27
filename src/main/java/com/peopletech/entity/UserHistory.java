package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

public class UserHistory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long phoneId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime viewTime;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; } public void setUserId(Long userId) { this.userId = userId; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public LocalDateTime getViewTime() { return viewTime; } public void setViewTime(LocalDateTime viewTime) { this.viewTime = viewTime; }
}
