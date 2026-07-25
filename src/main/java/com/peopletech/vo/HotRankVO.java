package com.peopletech.vo;

public class HotRankVO {
    private Long id;
    private Long phoneId;
    private String phoneName;
    private String brand;
    private Integer rankNo;
    private Integer heat;
    private String icon;
    private String period;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPhoneId() { return phoneId; }
    public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public String getPhoneName() { return phoneName; }
    public void setPhoneName(String phoneName) { this.phoneName = phoneName; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Integer getRankNo() { return rankNo; }
    public void setRankNo(Integer rankNo) { this.rankNo = rankNo; }
    public Integer getHeat() { return heat; }
    public void setHeat(Integer heat) { this.heat = heat; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
}