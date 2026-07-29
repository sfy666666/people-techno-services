package com.peopletech.vo;

import java.util.List;

/**
 * 对比接口返回
 */
public class CompareVO {
    private Long id;
    private String brand;
    private String name;
    private Integer price;
    private String coverImage;
    private String bgColor;
    private List<KeyValueVO> basicItems;     // 基础参数
    private List<GameTestVO> gameTests;      // 游戏测试
    private CpuSummaryVO cpuSummary;         // CPU 总结
    private List<BenchmarkVO> benchmarks;    // 跑分数据
    private String batteryScore;             // 续航评分
    private List<BatteryTestVO> batteryTests;// 电池测试
    private List<KeyValueVO> screenItems;    // 屏幕参数

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; } public void setBrand(String brand) { this.brand = brand; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public Integer getPrice() { return price; } public void setPrice(Integer price) { this.price = price; }
    public String getCoverImage() { return coverImage; } public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public String getBgColor() { return bgColor; } public void setBgColor(String bgColor) { this.bgColor = bgColor; }
    public List<KeyValueVO> getBasicItems() { return basicItems; } public void setBasicItems(List<KeyValueVO> basicItems) { this.basicItems = basicItems; }
    public List<GameTestVO> getGameTests() { return gameTests; } public void setGameTests(List<GameTestVO> gameTests) { this.gameTests = gameTests; }
    public CpuSummaryVO getCpuSummary() { return cpuSummary; } public void setCpuSummary(CpuSummaryVO cpuSummary) { this.cpuSummary = cpuSummary; }
    public List<BenchmarkVO> getBenchmarks() { return benchmarks; } public void setBenchmarks(List<BenchmarkVO> benchmarks) { this.benchmarks = benchmarks; }
    public String getBatteryScore() { return batteryScore; } public void setBatteryScore(String batteryScore) { this.batteryScore = batteryScore; }
    public List<BatteryTestVO> getBatteryTests() { return batteryTests; } public void setBatteryTests(List<BatteryTestVO> batteryTests) { this.batteryTests = batteryTests; }
    public List<KeyValueVO> getScreenItems() { return screenItems; } public void setScreenItems(List<KeyValueVO> screenItems) { this.screenItems = screenItems; }
}
