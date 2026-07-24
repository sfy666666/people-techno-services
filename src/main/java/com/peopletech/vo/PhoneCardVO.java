package com.peopletech.vo;

import java.util.List;

public class PhoneCardVO {
    private Long id;
    private String name;
    private String brand;
    private String bgColor;
    private String imageColor;
    private List<String> tags;
    private String coverImage;
    private String score;
    private Integer price;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; } public void setBrand(String brand) { this.brand = brand; }
    public String getBgColor() { return bgColor; } public void setBgColor(String bgColor) { this.bgColor = bgColor; }
    public String getImageColor() { return imageColor; } public void setImageColor(String imageColor) { this.imageColor = imageColor; }
    public List<String> getTags() { return tags; } public void setTags(List<String> tags) { this.tags = tags; }
    public String getCoverImage() { return coverImage; } public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public String getScore() { return score; } public void setScore(String score) { this.score = score; }
    public Integer getPrice() { return price; } public void setPrice(Integer price) { this.price = price; }
}
