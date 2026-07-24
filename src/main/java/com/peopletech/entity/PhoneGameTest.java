package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PhoneGameTest {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long phoneId;
    private String gameName;
    private BigDecimal avgFps;
    private String settings;
    private BigDecimal power;
    private String remark;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getGameName() { return gameName; } public void setGameName(String gameName) { this.gameName = gameName; }
    public BigDecimal getAvgFps() { return avgFps; } public void setAvgFps(BigDecimal avgFps) { this.avgFps = avgFps; }
    public String getSettings() { return settings; } public void setSettings(String settings) { this.settings = settings; }
    public BigDecimal getPower() { return power; } public void setPower(BigDecimal power) { this.power = power; }
    public String getRemark() { return remark; } public void setRemark(String remark) { this.remark = remark; }
    public LocalDateTime getCreateTime() { return createTime; } public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; } public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
