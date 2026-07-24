package com.peopletech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peopletech.entity.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends BaseMapper<News> {}
