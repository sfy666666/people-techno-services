package com.peopletech.controller;

import com.peopletech.common.Result;
import com.peopletech.entity.News;
import com.peopletech.service.NewsService;
import com.peopletech.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public Result<List<NewsVO>> getNewsList(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer limit) {
        return Result.ok(newsService.getNewsList(category, limit));
    }

    @GetMapping("/{id}")
    public Result<NewsVO> getNewsDetail(@PathVariable Long id) {
        NewsVO vo = newsService.getNewsDetail(id);
        if (vo == null) return Result.fail(404, "动态不存在");
        return Result.ok(vo);
    }

    @PostMapping("/admin")
    public Result<News> createNews(@RequestBody News news) {
        return Result.ok(newsService.createNews(news));
    }

    @PutMapping("/admin/{id}")
    public Result<News> updateNews(@PathVariable Long id, @RequestBody News news) {
        news.setId(id);
        return Result.ok(newsService.updateNews(news));
    }

    @DeleteMapping("/admin/{id}")
    public Result<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return Result.ok(null);
    }
}
