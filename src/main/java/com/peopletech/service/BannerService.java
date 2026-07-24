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
        return bannerMapper.selectList(
            new LambdaQueryWrapper<Banner>()
                .eq(Banner::getEnabled, true)
                .orderByAsc(Banner::getSort)
        ).stream().map(b -> {
            BannerVO vo = new BannerVO();
            vo.setId(b.getId());
            vo.setBrand(b.getBrand());
            vo.setModel(b.getModel());
            vo.setPrice(b.getPrice());
            vo.setTagline(b.getTagline());
            vo.setScore(b.getScore());
            vo.setScoreLabel(b.getScoreLabel());
            vo.setBgGradient(b.getBgGradient());
            vo.setPhoneColor(b.getPhoneColor());
            vo.setLinkType(b.getLinkType());
            vo.setPhoneId(b.getPhoneId());
            return vo;
        }).collect(Collectors.toList());
    }
}
