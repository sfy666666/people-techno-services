package com.peopletech.controller;

import com.peopletech.common.Result;
import com.peopletech.dto.HomeDTO;
import com.peopletech.service.BannerService;
import com.peopletech.service.PhoneService;
import com.peopletech.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final BannerService bannerService;
    private final PhoneService phoneService;

    @Autowired
    public HomeController(BannerService bannerService, PhoneService phoneService) {
        this.bannerService = bannerService;
        this.phoneService = phoneService;
    }

    @GetMapping
    public Result<HomeDTO> getHomeData() {
        HomeDTO dto = new HomeDTO();
        dto.setBanners(bannerService.getBanners());
        dto.setGridMenus(Arrays.asList(
            new GridMenuVO("perf", "综合性能", "🚀", "rgba(74, 222, 128, 0.15)", "/pages/category/category"),
            new GridMenuVO("battery", "续航", "🔋", "rgba(255, 107, 53, 0.15)", "/pages/category/category"),
            new GridMenuVO("charge", "充电 屏幕", "⚡", "rgba(74, 222, 128, 0.15)", "/pages/category/category"),
            new GridMenuVO("phone", "机型", "📱", "rgba(74, 222, 128, 0.15)", "/pages/phone/list"),
            new GridMenuVO("cpu", "处理器性能", "🧠", "rgba(255, 107, 53, 0.15)", "/pages/category/category"),
            new GridMenuVO("flash", "闪小白", "⚙️", "rgba(74, 222, 128, 0.15)", "/pages/category/category")
        ));
        dto.setPhoneList(phoneService.getHomePhoneList());
        return Result.ok(dto);
    }
}
