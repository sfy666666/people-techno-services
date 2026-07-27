package com.peopletech.vo;

/**
 * 处理器性能榜单项（单核/多核通用结构）
 * sortType: "single_core" | "multi_core" | "antutu"
 * scoreField: 对应的分数字段名（用于前端判断显示哪个）
 */
public class PerformanceRankVO {
    private Long id;
    private String brand;
    private String name;
    private String coverImage;
    private String bgColor;
    private String imageColor;
    private Long singleCore;
    private Long multiCore;
    private Long antutu;
    private String processor;
    private String memoryConfig;
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
    public Long getSingleCore() { return singleCore; }
    public void setSingleCore(Long singleCore) { this.singleCore = singleCore; }
    public Long getMultiCore() { return multiCore; }
    public void setMultiCore(Long multiCore) { this.multiCore = multiCore; }
    public Long getAntutu() { return antutu; }
    public void setAntutu(Long antutu) { this.antutu = antutu; }
    public String getProcessor() { return processor; }
    public void setProcessor(String processor) { this.processor = processor; }
    public String getMemoryConfig() { return memoryConfig; }
    public void setMemoryConfig(String memoryConfig) { this.memoryConfig = memoryConfig; }
    public String getSpec() { return spec; }
    public void setSpec(String spec) { this.spec = spec; }
}
