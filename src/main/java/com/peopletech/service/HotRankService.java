package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.HotRank;
import com.peopletech.mapper.HotRankMapper;
import com.peopletech.vo.HotRankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotRankService {

    private final HotRankMapper hotRankMapper;

    @Autowired
    public HotRankService(HotRankMapper hotRankMapper) {
        this.hotRankMapper = hotRankMapper;
    }

    public List<HotRankVO> getHotRank(String period) {
        LambdaQueryWrapper<HotRank> q = new LambdaQueryWrapper<HotRank>()
            .eq(HotRank::getEnabled, true)
            .eq(HotRank::getPeriod, period)
            .orderByAsc(HotRank::getRankNo)
            .last("LIMIT 12");
        return hotRankMapper.selectList(q).stream().map(this::toVO).collect(Collectors.toList());
    }

    private HotRankVO toVO(HotRank h) {
        HotRankVO v = new HotRankVO();
        v.setId(h.getId());
        v.setPhoneId(h.getPhoneId());
        v.setPhoneName(h.getPhoneName());
        v.setBrand(h.getBrand());
        v.setRankNo(h.getRankNo());
        v.setHeat(h.getHeat());
        v.setIcon(h.getIcon());
        v.setPeriod(h.getPeriod());
        return v;
    }
}