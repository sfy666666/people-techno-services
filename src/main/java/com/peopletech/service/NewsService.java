package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.News;
import com.peopletech.mapper.NewsMapper;
import com.peopletech.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    public News createNews(News news) {
        newsMapper.insert(news);
        return news;
    }

    public List<NewsVO> getNewsList(String category, Integer limit) {
        LambdaQueryWrapper<News> q = new LambdaQueryWrapper<News>()
            .eq(News::getEnabled, true)
            .orderByDesc(News::getPublishTime);
        if (category != null && !category.trim().isEmpty()) {
            q.eq(News::getCategory, category);
        }
        if (limit != null) {
            q.last("LIMIT " + limit);
        }
        return newsMapper.selectList(q).stream().map(this::toVO).collect(Collectors.toList());
    }

    public NewsVO getNewsDetail(Long id) {
        News n = newsMapper.selectById(id);
        return n == null ? null : toVO(n);
    }

    private NewsVO toVO(News n) {
        NewsVO vo = new NewsVO();
        vo.setId(n.getId());
        vo.setTitle(n.getTitle());
        vo.setSummary(n.getSummary());
        vo.setCoverImage(n.getCoverImage());
        vo.setCategory(n.getCategory());
        vo.setSource(n.getSource());
        vo.setPublishTime(n.getPublishTime());
        vo.setPhoneId(n.getPhoneId());
        vo.setPhoneName(n.getPhoneName());
        return vo;
    }
}
