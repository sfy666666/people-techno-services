package com.peopletech.vo;

/** 充电榜单 VO：按综合充电能力排序（W） */
public class ChargeRankVO {
    private Long id;
    private String brand;
    private String name;
    private String coverImage;
    private String bgColor;
    private String imageColor;
    private Integer wiredW;       // 有线充电功率 W（数值）
    private Integer wirelessW;    // 无线充电功率 W（数值）
    private Integer totalW;       // 综合充电能力 W（有线+无线）
    private Integer batteryCapacity;
    private String screenSize;
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
    public Integer getWiredW() { return wiredW; }
    public void setWiredW(Integer wiredW) { this.wiredW = wiredW; }
    public Integer getWirelessW() { return wirelessW; }
    public void setWirelessW(Integer wirelessW) { this.wirelessW = wirelessW; }
    public Integer getTotalW() { return totalW; }
    public void setTotalW(Integer totalW) { this.totalW = totalW; }
    public Integer getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(Integer batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    public String getScreenSize() { return screenSize; }
    public void setScreenSize(String screenSize) { this.screenSize = screenSize; }
    public String getProcessor() { return processor; }
    public void setProcessor(String processor) { this.processor = processor; }
    public String getSpec() { return spec; }
    public void setSpec(String spec) { this.spec = spec; }
}
