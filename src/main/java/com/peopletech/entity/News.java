package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

public class News {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String summary;
    private String content;
    private String coverImage;
    private Long phoneId;
    private String phoneName;
    private String category;
    private String source;
    private LocalDateTime publishTime;
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
    public String getTitle() { return title; } public void setTitle(String title) { this.title = title; }
    public String getSummary() { return summary; } public void setSummary(String summary) { this.summary = summary; }
    public String getContent() { return content; } public void setContent(String content) { this.content = content; }
    public String getCoverImage() { return coverImage; } public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getPhoneName() { return phoneName; } public void setPhoneName(String phoneName) { this.phoneName = phoneName; }
    public String getCategory() { return category; } public void setCategory(String category) { this.category = category; }
    public String getSource() { return source; } public void setSource(String source) { this.source = source; }
    public LocalDateTime getPublishTime() { return publishTime; } public void setPublishTime(LocalDateTime publishTime) { this.publishTime = publishTime; }
    public Integer getSort() { return sort; } public void setSort(Integer sort) { this.sort = sort; }
    public Boolean getShowHome() { return showHome; } public void setShowHome(Boolean showHome) { this.showHome = showHome; }
    public Boolean getEnabled() { return enabled; } public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public LocalDateTime getCreateTime() { return createTime; } public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; } public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
    public Integer getDeleted() { return deleted; } public void setDeleted(Integer deleted) { this.deleted = deleted; }
}
