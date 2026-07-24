package com.peopletech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peopletech.entity.Banner;
import com.peopletech.vo.BannerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    @Select("SELECT b.id, b.brand, b.model, b.price, b.tagline, b.score, b.score_label, " +
            "b.bg_gradient, b.phone_color, b.link_type, b.phone_id, p.cover_image " +
            "FROM banner b " +
            "LEFT JOIN phone p ON b.phone_id = p.id " +
            "WHERE b.enabled = 1 AND b.deleted = 0 " +
            "ORDER BY b.sort ASC")
    List<BannerVO> selectBannersWithCover();
}
