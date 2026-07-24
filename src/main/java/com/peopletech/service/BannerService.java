package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.Banner;
import com.peopletech.mapper.BannerMapper;
import com.peopletech.vo.BannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerService {

    private final BannerMapper bannerMapper;

    @Autowired
    public BannerService(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    public List<BannerVO> getBanners() {
        return bannerMapper.selectBannersWithCover();
    }
}
