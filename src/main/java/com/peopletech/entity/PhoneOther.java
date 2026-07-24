package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

public class PhoneOther {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long phoneId;
    private String name;
    private String val;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getVal() { return val; } public void setVal(String val) { this.val = val; }
    public LocalDateTime getCreateTime() { return createTime; } public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; } public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
