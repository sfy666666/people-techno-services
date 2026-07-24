package com.peopletech.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.common.Result;
import com.peopletech.entity.Banner;
import com.peopletech.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
public class BannerController {

    @Autowired
    private BannerMapper bannerMapper;

    @GetMapping("/admin/list")
    public Result<List<Banner>> adminList() {
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<Banner>()
                .orderByDesc(Banner::getSort)
                .orderByAsc(Banner::getId);
        return Result.ok(bannerMapper.selectList(wrapper));
    }

    @PostMapping("/admin")
    public Result<Banner> create(@RequestBody Banner banner) {
        bannerMapper.insert(banner);
        return Result.ok(banner);
    }

    @PutMapping("/admin/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Banner banner) {
        banner.setId(id);
        bannerMapper.updateById(banner);
        return Result.ok();
    }

    @DeleteMapping("/admin/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        bannerMapper.deleteById(id);
        return Result.ok();
    }
}
