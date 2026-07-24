package com.peopletech.vo;

import java.util.List;

public class PhoneDetailVO {
    private Long id;
    private String brand;
    private String name;
    private Integer price;
    private String imageColor;
    private String coverImage;
    private List<KeyValueVO> basicItems;
    private List<GameTestVO> gameTests;
    private CpuSummaryVO cpuSummary;
    private List<BenchmarkVO> benchmarks;
    private String batteryScore;
    private List<BatteryTestVO> batteryTests;
    private List<KeyValueVO> screenItems;
    private List<KeyValueVO> otherItems;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; } public void setBrand(String brand) { this.brand = brand; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public Integer getPrice() { return price; } public void setPrice(Integer price) { this.price = price; }
    public String getImageColor() { return imageColor; } public void setImageColor(String imageColor) { this.imageColor = imageColor; }
    public String getCoverImage() { return coverImage; } public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public List<KeyValueVO> getBasicItems() { return basicItems; } public void setBasicItems(List<KeyValueVO> basicItems) { this.basicItems = basicItems; }
    public List<GameTestVO> getGameTests() { return gameTests; } public void setGameTests(List<GameTestVO> gameTests) { this.gameTests = gameTests; }
    public CpuSummaryVO getCpuSummary() { return cpuSummary; } public void setCpuSummary(CpuSummaryVO cpuSummary) { this.cpuSummary = cpuSummary; }
    public List<BenchmarkVO> getBenchmarks() { return benchmarks; } public void setBenchmarks(List<BenchmarkVO> benchmarks) { this.benchmarks = benchmarks; }
    public String getBatteryScore() { return batteryScore; } public void setBatteryScore(String batteryScore) { this.batteryScore = batteryScore; }
    public List<BatteryTestVO> getBatteryTests() { return batteryTests; } public void setBatteryTests(List<BatteryTestVO> batteryTests) { this.batteryTests = batteryTests; }
    public List<KeyValueVO> getScreenItems() { return screenItems; } public void setScreenItems(List<KeyValueVO> screenItems) { this.screenItems = screenItems; }
    public List<KeyValueVO> getOtherItems() { return otherItems; } public void setOtherItems(List<KeyValueVO> otherItems) { this.otherItems = otherItems; }
}
