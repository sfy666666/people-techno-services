package com.peopletech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peopletech.entity.Phone;
import com.peopletech.vo.PhoneAdminVO;
import com.peopletech.vo.PhoneCardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhoneMapper extends BaseMapper<Phone> {

    @Select("<script>"
            + "SELECT p.id, p.brand, p.name, p.bg_color, p.image_color, p.cover_image, p.score, p.price, "
            + "pb.launch_date "
            + "FROM phone p "
            + "LEFT JOIN phone_basic pb ON p.id = pb.phone_id "
            + "WHERE p.enabled = 1 AND p.deleted = 0 "
            + "ORDER BY pb.launch_date DESC, p.id DESC "
            + "LIMIT 10"
            + "</script>")
    List<PhoneCardVO> selectHomeList();


    @Select("<script>"
            + "SELECT p.id, p.brand, p.name, p.category, p.price, p.score, p.score_label, p.sort, "
            + "p.show_home, p.enabled, p.cover_image, p.bg_color, p.image_color, "
            + "pb.processor "
            + "FROM phone p "
            + "LEFT JOIN phone_basic pb ON p.id = pb.phone_id "
            + "WHERE p.deleted = 0 "
            + "<if test='keyword != null and keyword != \"\"'>"
            + "AND (p.name LIKE CONCAT('%', #{keyword}, '%') OR p.brand LIKE CONCAT('%', #{keyword}, '%'))"
            + "</if>"
            + " ORDER BY p.id DESC LIMIT #{offset}, #{pageSize}"
            + "</script>")
    List<PhoneAdminVO> adminList(@Param("keyword") String keyword, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("<script>"
            + "SELECT COUNT(*) FROM phone WHERE deleted = 0 "
            + "<if test='keyword != null and keyword != \"\"'>"
            + "AND (name LIKE CONCAT('%', #{keyword}, '%') OR brand LIKE CONCAT('%', #{keyword}, '%'))"
            + "</if>"
            + "</script>")
    int countAll(@Param("keyword") String keyword);
}
