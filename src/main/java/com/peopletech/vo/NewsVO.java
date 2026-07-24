package com.peopletech.vo;

import java.time.LocalDateTime;

public class NewsVO {
    private Long id;
    private String title;
    private String summary;
    private String coverImage;
    private String category;
    private String source;
    private LocalDateTime publishTime;
    private Long phoneId;
    private String phoneName;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; } public void setTitle(String title) { this.title = title; }
    public String getSummary() { return summary; } public void setSummary(String summary) { this.summary = summary; }
    public String getCoverImage() { return coverImage; } public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public String getCategory() { return category; } public void setCategory(String category) { this.category = category; }
    public String getSource() { return source; } public void setSource(String source) { this.source = source; }
    public LocalDateTime getPublishTime() { return publishTime; } public void setPublishTime(LocalDateTime publishTime) { this.publishTime = publishTime; }
    public Long getPhoneId() { return phoneId; } public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getPhoneName() { return phoneName; } public void setPhoneName(String phoneName) { this.phoneName = phoneName; }
}
