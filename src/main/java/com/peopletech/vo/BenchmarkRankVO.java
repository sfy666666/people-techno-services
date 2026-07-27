package com.peopletech.vo;

/**
 * 处理器性能榜单单项
 * 三个 score 字段均返回（方便对比），前端按榜单类型取对应字段展示
 */
public class BenchmarkRankVO {
    private Long id;
    private String brand;
    private String name;
    private String coverImage;
    private String bgColor;
    private String imageColor;
    private Long singleCore;     // GeekBench6 单核
    private Long multiCore;     // GeekBench6 多核
    private Long antutu;        // 安兔兔总分
    private String processor;
    private String memoryConfig; // 如 12GB+512GB
    private String spec;        // 显示用规格串

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
