package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

public class Phone {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String brand;
    private String name;
    private Integer price;
    private String coverImage;
    private String bgColor;
    private String imageColor;
    private String score;
    private String scoreLabel;
    private String tagline;
    private String bgGradient;
    private String phoneColor;
    private String category;
    private Integer sort;
    private Boolean showHome;
    private Boolean enabled;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    private Integer deleted;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; } public void setBrand(String brand) { this.brand = brand; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public Integer getPrice() { return price; } public void setPrice(Integer price) { this.price = price; }
    public String getCoverImage() { return coverImage; } public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public String getBgColor() { return bgColor; } public void setBgColor(String bgColor) { this.bgColor = bgColor; }
    public String getImageColor() { return imageColor; } public void setImageColor(String imageColor) { this.imageColor = imageColor; }
    public String getScore() { return score; } public void setScore(String score) { this.score = score; }
    public String getScoreLabel() { return scoreLabel; } public void setScoreLabel(String scoreLabel) { this.scoreLabel = scoreLabel; }
    public String getTagline() { return tagline; } public void setTagline(String tagline) { this.tagline = tagline; }
    public String getBgGradient() { return bgGradient; } public void setBgGradient(String bgGradient) { this.bgGradient = bgGradient; }
    public String getPhoneColor() { return phoneColor; } public void setPhoneColor(String phoneColor) { this.phoneColor = phoneColor; }
    public String getCategory() { return category; } public void setCategory(String category) { this.category = category; }
    public Integer getSort() { return sort; } public void setSort(Integer sort) { this.sort = sort; }
    public Boolean getShowHome() { return showHome; } public void setShowHome(Boolean showHome) { this.showHome = showHome; }
    public Boolean getEnabled() { return enabled; } public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public LocalDateTime getCreateTime() { return createTime; } public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; } public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
    public Integer getDeleted() { return deleted; } public void setDeleted(Integer deleted) { this.deleted = deleted; }
}
