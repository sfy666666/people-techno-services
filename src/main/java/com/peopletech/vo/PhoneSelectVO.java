package com.peopletech.vo;

/**
 * 轻量级机型选择VO：仅包含 id、brand、name，用于弹窗选择
 */
public class PhoneSelectVO {
    private Long id;
    private String name;
    private String brand;

    public PhoneSelectVO() {}

    public PhoneSelectVO(Long id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
}
