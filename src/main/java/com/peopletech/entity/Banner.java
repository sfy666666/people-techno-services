package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

public class Banner {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private Integer price;
    private String tagline;
    private String score;
    private String scoreLabel;
    private String bgGradient;
    private String phoneColor;
    private Long phoneId;
    private String linkType;
    private String linkUrl;
    private Integer sort;
    private Boolean enabled;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    private Integer deleted;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; } public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; } public void setModel(String model) { this.model = model; }
    public Integer getPrice() { return price; } public void setPrice(Integer price) { this.price = price; }
    public String getTagline() { return tagline; } public void setTagline(String tagline) { this.tagline = tagline; }
    public String getScore() { return score; } public void setScore(String score) { this.score = score; }
    public String getScoreLabel() { return scoreLabel; } public void setScoreLabel(String scoreLabel) { this.scoreLabel = scoreLabel; }
    public String getBgGradient() { return bgGradient; } public void setBgGradient(String bgGradient) { this.bgGradient = bgGradient; }
    public String getPhoneColor() { return phoneColor; } public void setPhoneColor(String phoneColor) { this.phoneColor = phoneColor; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getLinkType() { return linkType; } public void setLinkType(String linkType) { this.linkType = linkType; }
    public String getLinkUrl() { return linkUrl; } public void setLinkUrl(String linkUrl) { this.linkUrl = linkUrl; }
    public Integer getSort() { return sort; } public void setSort(Integer sort) { this.sort = sort; }
    public Boolean getEnabled() { return enabled; } public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public LocalDateTime getCreateTime() { return createTime; } public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; } public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
    public Integer getDeleted() { return deleted; } public void setDeleted(Integer deleted) { this.deleted = deleted; }
}
