package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.*;
import com.peopletech.mapper.*;
import com.peopletech.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private final PhoneMapper phoneMapper;
    private final PhoneBasicMapper basicMapper;
    private final PhoneGameTestMapper gameTestMapper;
    private final PhoneBenchmarkMapper benchmarkMapper;
    private final PhoneBatteryMapper batteryMapper;
    private final PhoneScreenMapper screenMapper;
    private final PhoneOtherMapper otherMapper;

    @Autowired
    public PhoneService(PhoneMapper phoneMapper, PhoneBasicMapper basicMapper,
                        PhoneGameTestMapper gameTestMapper, PhoneBenchmarkMapper benchmarkMapper,
                        PhoneBatteryMapper batteryMapper, PhoneScreenMapper screenMapper,
                        PhoneOtherMapper otherMapper) {
        this.phoneMapper = phoneMapper;
        this.basicMapper = basicMapper;
        this.gameTestMapper = gameTestMapper;
        this.benchmarkMapper = benchmarkMapper;
        this.batteryMapper = batteryMapper;
        this.screenMapper = screenMapper;
        this.otherMapper = otherMapper;
    }

    /** 首页精选机型 */
    public List<PhoneCardVO> getHomePhoneList() {
        List<Phone> phones = phoneMapper.selectList(
            new LambdaQueryWrapper<Phone>()
                .eq(Phone::getEnabled, true)
                .eq(Phone::getShowHome, true)
                .orderByAsc(Phone::getSort)
                .last("LIMIT 4")
        );
        return phones.stream().map(this::toCardVO).collect(Collectors.toList());
    }

    /** 机型列表 */
    public List<PhoneListVO> getPhoneList(String category, String keyword) {
        LambdaQueryWrapper<Phone> q = new LambdaQueryWrapper<Phone>()
            .eq(Phone::getEnabled, true)
            .orderByAsc(Phone::getSort);
        if (category != null && !category.trim().isEmpty()) {
            q.eq(Phone::getCategory, category);
        }
        if (keyword != null && !keyword.trim().isEmpty()) {
            q.and(w -> w.like(Phone::getName, keyword).or().like(Phone::getBrand, keyword));
        }
        return phoneMapper.selectList(q).stream().map(this::toListVO).collect(Collectors.toList());
    }

    /** 机型详情 */
    public PhoneDetailVO getPhoneDetail(Long id) {
        Phone phone = phoneMapper.selectById(id);
        if (phone == null) return null;

        PhoneDetailVO vo = new PhoneDetailVO();
        vo.setId(phone.getId());
        vo.setBrand(phone.getBrand());
        vo.setName(phone.getName());
        vo.setPrice(phone.getPrice());
        vo.setImageColor(phone.getImageColor());

        // 基础数据
        PhoneBasic basic = basicMapper.selectOne(new LambdaQueryWrapper<PhoneBasic>().eq(PhoneBasic::getPhoneId, id));
        if (basic != null) {
            vo.setBasicItems(Arrays.asList(
                new KeyValueVO("测试机型", basic.getTestModel()),
                new KeyValueVO("上市时间", basic.getLaunchDate()),
                new KeyValueVO("处理器", basic.getProcessor()),
                new KeyValueVO("内存组合", basic.getMemoryConfig()),
                new KeyValueVO("起始价格", "¥" + basic.getPrice(), true),
                new KeyValueVO("测试版本", basic.getTestVersion()),
                new KeyValueVO("电池容量", basic.getBatteryCapacity() + "mAh"),
                new KeyValueVO("屏幕尺寸", basic.getScreenSize())
            ));
        }

        // 游戏测试
        List<PhoneGameTest> games = gameTestMapper.selectList(
            new LambdaQueryWrapper<PhoneGameTest>().eq(PhoneGameTest::getPhoneId, id).orderByAsc(PhoneGameTest::getId));
        vo.setGameTests(games.stream().map(g -> {
            GameTestVO gv = new GameTestVO();
            gv.setGameName(g.getGameName());
            gv.setAvgFps(g.getAvgFps());
            gv.setSettings(g.getSettings());
            gv.setPower(g.getPower());
            gv.setRemark(g.getRemark());
            return gv;
        }).collect(Collectors.toList()));

        // 处理器跑分
        List<PhoneBenchmark> benches = benchmarkMapper.selectList(
            new LambdaQueryWrapper<PhoneBenchmark>().eq(PhoneBenchmark::getPhoneId, id).orderByAsc(PhoneBenchmark::getId));
        vo.setBenchmarks(benches.stream().map(b -> {
            BenchmarkVO bv = new BenchmarkVO();
            bv.setName(b.getName());
            bv.setVersion(b.getVersion());
            bv.setScore(b.getScore());
            bv.setPercentile(b.getPercentile());
            return bv;
        }).collect(Collectors.toList()));

        // CPU 综合评分
        CpuSummaryVO cpuSummary = new CpuSummaryVO();
        cpuSummary.setOverallScore("92");
        cpuSummary.setRanking("旗舰级");
        cpuSummary.setPerformanceNote("性能释放激进");
        cpuSummary.setEfficiencyNote("能效比优秀");
        cpuSummary.setThermalsNote("发热控制良好");
        vo.setCpuSummary(cpuSummary);

        // 续航
        List<PhoneBattery> batteries = batteryMapper.selectList(
            new LambdaQueryWrapper<PhoneBattery>().eq(PhoneBattery::getPhoneId, id).orderByAsc(PhoneBattery::getId));
        vo.setBatteryScore("5.0");
        vo.setBatteryTests(batteries.stream().map(b -> {
            BatteryTestVO bv = new BatteryTestVO();
            bv.setScene(b.getScene());
            bv.setDuration(b.getDuration());
            bv.setDischargeRate(b.getDischargeRate());
            return bv;
        }).collect(Collectors.toList()));

        // 屏幕
        List<PhoneScreen> screens = screenMapper.selectList(
            new LambdaQueryWrapper<PhoneScreen>().eq(PhoneScreen::getPhoneId, id).orderByAsc(PhoneScreen::getId));
        vo.setScreenItems(screens.stream().map(s -> new KeyValueVO(s.getName(), s.getVal())).collect(Collectors.toList()));

        // 其他
        List<PhoneOther> others = otherMapper.selectList(
            new LambdaQueryWrapper<PhoneOther>().eq(PhoneOther::getPhoneId, id).orderByAsc(PhoneOther::getId));
        vo.setOtherItems(others.stream().map(o -> new KeyValueVO(o.getName(), o.getVal())).collect(Collectors.toList()));

        return vo;
    }

    private PhoneCardVO toCardVO(Phone p) {
        PhoneCardVO vo = new PhoneCardVO();
        vo.setId(p.getId());
        vo.setName(p.getName());
        vo.setBrand(p.getBrand());
        vo.setBgColor(p.getBgColor());
        vo.setImageColor(p.getImageColor());
        vo.setScore(p.getScore());
        vo.setTags(parseTags(p.getScoreLabel()));
        return vo;
    }

    private PhoneListVO toListVO(Phone p) {
        PhoneListVO vo = new PhoneListVO();
        vo.setId(p.getId());
        vo.setName(p.getName());
        vo.setBrand(p.getBrand());
        vo.setBgColor(p.getBgColor());
        vo.setImageColor(p.getImageColor());
        vo.setScore(p.getScore());
        vo.setScoreLabel(p.getScoreLabel());
        vo.setPrice(p.getPrice());
        vo.setSort(p.getSort());
        vo.setTags(parseTags(p.getScoreLabel()));
        return vo;
    }
    // 解析 scoreLabel (如 "iOS,A系列,旗舰") 为标签列表
    private List<String> parseTags(String label) {
        if (label == null || label.trim().isEmpty()) return Collections.emptyList();
        return Arrays.stream(label.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
