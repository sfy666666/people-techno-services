package com.peopletech.dto;

import com.peopletech.vo.*;

import java.util.List;

public class HomeDTO {
    private List<BannerVO> banners;
    private List<GridMenuVO> gridMenus;
    private List<PhoneCardVO> phoneList;

    public List<BannerVO> getBanners() { return banners; } public void setBanners(List<BannerVO> banners) { this.banners = banners; }
    public List<GridMenuVO> getGridMenus() { return gridMenus; } public void setGridMenus(List<GridMenuVO> gridMenus) { this.gridMenus = gridMenus; }
    public List<PhoneCardVO> getPhoneList() { return phoneList; } public void setPhoneList(List<PhoneCardVO> phoneList) { this.phoneList = phoneList; }
}
