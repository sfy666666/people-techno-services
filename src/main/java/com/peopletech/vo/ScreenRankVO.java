package com.peopletech.vo;

/** 屏幕榜单 VO：按峰值亮度排序 */
public class ScreenRankVO {
    private Long id;
    private String brand;
    private String name;
    private String coverImage;
    private String bgColor;
    private String imageColor;
    private Integer peakBrightness;  // 峰值亮度 nits
    private String refreshRate;      // 刷新率
    private String resolution;       // 分辨率
    private String screenSize;       // 屏幕尺寸
    private String processor;
    private String spec;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public String getBgColor() { return bgColor; }
    public void setBgColor(String bgColor) { this.bgColor = bgColor; }
    public String getImageColor() { return imageColor; }
    public void setImageColor(String imageColor) { this.imageColor = imageColor; }
    public Integer getPeakBrightness() { return peakBrightness; }
    public void setPeakBrightness(Integer peakBrightness) { this.peakBrightness = peakBrightness; }
    public String getRefreshRate() { return refreshRate; }
    public void setRefreshRate(String refreshRate) { this.refreshRate = refreshRate; }
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
    public String getScreenSize() { return screenSize; }
    public void setScreenSize(String screenSize) { this.screenSize = screenSize; }
    public String getProcessor() { return processor; }
    public void setProcessor(String processor) { this.processor = processor; }
    public String getSpec() { return spec; }
    public void setSpec(String spec) { this.spec = spec; }
}
