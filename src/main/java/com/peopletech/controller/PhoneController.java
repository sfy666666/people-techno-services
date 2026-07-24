package com.peopletech.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.common.Result;
import com.peopletech.entity.*;
import com.peopletech.mapper.*;
import com.peopletech.service.PhoneService;
import com.peopletech.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {

    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;
    private final PhoneBasicMapper basicMapper;
    private final PhoneGameTestMapper gameTestMapper;
    private final PhoneBenchmarkMapper benchmarkMapper;
    private final PhoneBatteryMapper batteryMapper;
    private final PhoneScreenMapper screenMapper;
    private final PhoneOtherMapper otherMapper;

    @Autowired
    public PhoneController(PhoneService phoneService, PhoneMapper phoneMapper,
                           PhoneBasicMapper basicMapper, PhoneGameTestMapper gameTestMapper,
                           PhoneBenchmarkMapper benchmarkMapper, PhoneBatteryMapper batteryMapper,
                           PhoneScreenMapper screenMapper, PhoneOtherMapper otherMapper) {
        this.phoneService = phoneService;
        this.phoneMapper = phoneMapper;
        this.basicMapper = basicMapper;
        this.gameTestMapper = gameTestMapper;
        this.benchmarkMapper = benchmarkMapper;
        this.batteryMapper = batteryMapper;
        this.screenMapper = screenMapper;
        this.otherMapper = otherMapper;
    }

    // ===== 小程序接口 =====

    @GetMapping("/list")
    public Result<Map<String, Object>> getPhoneList(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return Result.ok(phoneService.getPhoneListPage(brand, category, keyword, sortBy, minPrice, maxPrice, page, pageSize));
    }

    @GetMapping("/detail/{id}")
    public Result<PhoneDetailVO> getPhoneDetail(@PathVariable Long id) {
        PhoneDetailVO vo = phoneService.getPhoneDetail(id);
        if (vo == null) return Result.fail(404, "机型不存在");
        return Result.ok(vo);
    }

    // ===== 管理后台接口 =====

    @GetMapping("/admin/list")
    public Result<Map<String, Object>> adminList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        int offset = (page - 1) * pageSize;
        List<PhoneAdminVO> list = phoneMapper.adminList(keyword, offset, pageSize);
        int total = phoneMapper.countAll(keyword);
        Map<String, Object> result = new java.util.HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("page", page);
        result.put("pageSize", pageSize);
        return Result.ok(result);
    }

    @PostMapping("/admin")
    public Result<Phone> createPhone(@RequestBody Phone phone) {
        phoneMapper.insert(phone);
        return Result.ok(phone);
    }

    @PutMapping("/admin/{id}")
    public Result<Void> updatePhone(@PathVariable Long id, @RequestBody Phone phone) {
        phone.setId(id);
        phoneMapper.updateById(phone);
        return Result.ok();
    }

    @DeleteMapping("/admin/{id}")
    public Result<Void> deletePhone(@PathVariable Long id) {
        phoneMapper.deleteById(id);
        return Result.ok();
    }

    // ===== 子表查询接口 =====

    @GetMapping("/admin/{phoneId}/basic")
    public Result<PhoneBasic> getBasic(@PathVariable Long phoneId) {
        PhoneBasic basic = basicMapper.selectOne(new LambdaQueryWrapper<PhoneBasic>().eq(PhoneBasic::getPhoneId, phoneId));
        return Result.ok(basic);
    }

    @GetMapping("/admin/{phoneId}/game-tests")
    public Result<List<PhoneGameTest>> getGameTests(@PathVariable Long phoneId) {
        return Result.ok(gameTestMapper.selectList(new LambdaQueryWrapper<PhoneGameTest>().eq(PhoneGameTest::getPhoneId, phoneId)));
    }

    @GetMapping("/admin/{phoneId}/benchmarks")
    public Result<List<PhoneBenchmark>> getBenchmarks(@PathVariable Long phoneId) {
        return Result.ok(benchmarkMapper.selectList(new LambdaQueryWrapper<PhoneBenchmark>().eq(PhoneBenchmark::getPhoneId, phoneId)));
    }

    @GetMapping("/admin/{phoneId}/batteries")
    public Result<List<PhoneBattery>> getBatteries(@PathVariable Long phoneId) {
        return Result.ok(batteryMapper.selectList(new LambdaQueryWrapper<PhoneBattery>().eq(PhoneBattery::getPhoneId, phoneId)));
    }

    @GetMapping("/admin/{phoneId}/screens")
    public Result<List<PhoneScreen>> getScreens(@PathVariable Long phoneId) {
        return Result.ok(screenMapper.selectList(new LambdaQueryWrapper<PhoneScreen>().eq(PhoneScreen::getPhoneId, phoneId)));
    }

    @GetMapping("/admin/{phoneId}/others")
    public Result<List<PhoneOther>> getOthers(@PathVariable Long phoneId) {
        return Result.ok(otherMapper.selectList(new LambdaQueryWrapper<PhoneOther>().eq(PhoneOther::getPhoneId, phoneId)));
    }

    @PostMapping("/admin/{phoneId}/basic")
    public Result<Void> saveBasic(@PathVariable Long phoneId, @RequestBody PhoneBasic basic) {
        basic.setPhoneId(phoneId);
        PhoneBasic exist = basicMapper.selectOne(new LambdaQueryWrapper<PhoneBasic>().eq(PhoneBasic::getPhoneId, phoneId));
        if (exist != null) { basic.setId(exist.getId()); basicMapper.updateById(basic); }
        else basicMapper.insert(basic);
        return Result.ok();
    }

    @PostMapping("/admin/{phoneId}/game-tests")
    public Result<Void> saveGameTests(@PathVariable Long phoneId, @RequestBody List<PhoneGameTest> tests) {
        gameTestMapper.delete(new LambdaQueryWrapper<PhoneGameTest>().eq(PhoneGameTest::getPhoneId, phoneId));
        tests.forEach(t -> { t.setPhoneId(phoneId); t.setId(null); });
        tests.forEach(gameTestMapper::insert);
        return Result.ok();
    }

    @PostMapping("/admin/{phoneId}/benchmarks")
    public Result<Void> saveBenchmarks(@PathVariable Long phoneId, @RequestBody List<PhoneBenchmark> benchmarks) {
        benchmarkMapper.delete(new LambdaQueryWrapper<PhoneBenchmark>().eq(PhoneBenchmark::getPhoneId, phoneId));
        benchmarks.forEach(b -> { b.setPhoneId(phoneId); b.setId(null); });
        benchmarks.forEach(benchmarkMapper::insert);
        return Result.ok();
    }

    @PostMapping("/admin/{phoneId}/batteries")
    public Result<Void> saveBatteries(@PathVariable Long phoneId, @RequestBody List<PhoneBattery> batteries) {
        batteryMapper.delete(new LambdaQueryWrapper<PhoneBattery>().eq(PhoneBattery::getPhoneId, phoneId));
        batteries.forEach(b -> { b.setPhoneId(phoneId); b.setId(null); });
        batteries.forEach(batteryMapper::insert);
        return Result.ok();
    }

    @PostMapping("/admin/{phoneId}/screens")
    public Result<Void> saveScreens(@PathVariable Long phoneId, @RequestBody List<PhoneScreen> screens) {
        screenMapper.delete(new LambdaQueryWrapper<PhoneScreen>().eq(PhoneScreen::getPhoneId, phoneId));
        screens.forEach(s -> { s.setPhoneId(phoneId); s.setId(null); });
        screens.forEach(screenMapper::insert);
        return Result.ok();
    }

    @PostMapping("/admin/{phoneId}/others")
    public Result<Void> saveOthers(@PathVariable Long phoneId, @RequestBody List<PhoneOther> others) {
        otherMapper.delete(new LambdaQueryWrapper<PhoneOther>().eq(PhoneOther::getPhoneId, phoneId));
        others.forEach(o -> { o.setPhoneId(phoneId); o.setId(null); });
        others.forEach(otherMapper::insert);
        return Result.ok();
    }
}
