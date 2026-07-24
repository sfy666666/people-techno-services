package com.peopletech.controller;

import com.peopletech.common.Result;
import com.peopletech.service.HotRankService;
import com.peopletech.vo.HotRankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hot-rank")
public class HotRankController {

    private final HotRankService hotRankService;

    @Autowired
    public HotRankController(HotRankService hotRankService) {
        this.hotRankService = hotRankService;
    }

    @GetMapping
    public Result<List<HotRankVO>> getHotRank(@RequestParam(defaultValue = "today") String period) {
        return Result.ok(hotRankService.getHotRank(period));
    }
}