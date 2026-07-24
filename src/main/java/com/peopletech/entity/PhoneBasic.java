package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

public class PhoneBasic {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long phoneId;
    private String testModel;
    private String launchDate;
    private String processor;
    private String memoryConfig;
    private Integer price;
    private String testVersion;
    private Integer batteryCapacity;
    private String screenSize;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getTestModel() { return testModel; } public void setTestModel(String testModel) { this.testModel = testModel; }
    public String getLaunchDate() { return launchDate; } public void setLaunchDate(String launchDate) { this.launchDate = launchDate; }
    public String getProcessor() { return processor; } public void setProcessor(String processor) { this.processor = processor; }
    public String getMemoryConfig() { return memoryConfig; } public void setMemoryConfig(String memoryConfig) { this.memoryConfig = memoryConfig; }
    public Integer getPrice() { return price; } public void setPrice(Integer price) { this.price = price; }
    public String getTestVersion() { return testVersion; } public void setTestVersion(String testVersion) { this.testVersion = testVersion; }
    public Integer getBatteryCapacity() { return batteryCapacity; } public void setBatteryCapacity(Integer batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    public String getScreenSize() { return screenSize; } public void setScreenSize(String screenSize) { this.screenSize = screenSize; }
    public LocalDateTime getCreateTime() { return createTime; } public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; } public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
