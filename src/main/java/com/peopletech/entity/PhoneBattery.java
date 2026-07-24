package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PhoneBattery {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long phoneId;
    private String scene;
    private String duration;
    private BigDecimal dischargeRate;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getScene() { return scene; } public void setScene(String scene) { this.scene = scene; }
    public String getDuration() { return duration; } public void setDuration(String duration) { this.duration = duration; }
    public BigDecimal getDischargeRate() { return dischargeRate; } public void setDischargeRate(BigDecimal dischargeRate) { this.dischargeRate = dischargeRate; }
    public LocalDateTime getCreateTime() { return createTime; } public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; } public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
