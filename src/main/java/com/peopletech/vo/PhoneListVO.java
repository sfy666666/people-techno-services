package com.peopletech.vo;

import java.util.List;

public class PhoneListVO {
    private Long id;
    private String name;
    private String brand;
    private String bgColor;
    private String imageColor;
    private List<String> tags;
    private String score;
    private String scoreLabel;
    private Integer price;
    private Integer sort;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getBgColor() { return bgColor; }
    public void setBgColor(String bgColor) { this.bgColor = bgColor; }
    public String getImageColor() { return imageColor; }
    public void setImageColor(String imageColor) { this.imageColor = imageColor; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }
    public String getScoreLabel() { return scoreLabel; }
    public void setScoreLabel(String scoreLabel) { this.scoreLabel = scoreLabel; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public Integer getSort() { return sort; }
    public void setSort(Integer sort) { this.sort = sort; }
}
