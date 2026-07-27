package com.peopletech.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peopletech.entity.*;
import com.peopletech.mapper.*;
import com.peopletech.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.*;
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

    /** 首页精选机型（最新录入的10条，按上市时间倒序） */
    public List<PhoneCardVO> getHomePhoneList() {
        List<PhoneCardVO> list = phoneMapper.selectHomeList();
        if (list == null || list.isEmpty()) return list;
        List<Long> ids = list.stream().map(PhoneCardVO::getId).collect(Collectors.toList());
        // 从 phone_other 查 tags（name='tags'）
        Map<Long, String> tagMap = otherMapper.selectList(
                new LambdaQueryWrapper<PhoneOther>()
                        .in(PhoneOther::getPhoneId, ids)
                        .eq(PhoneOther::getName, "tags")
        ).stream().collect(Collectors.toMap(
                o -> o.getPhoneId(),
                PhoneOther::getVal,
                (a, b) -> a
        ));
        for (PhoneCardVO vo : list) {
            String raw = tagMap.get(vo.getId());
            vo.setTags(raw == null ? null : parseTags(raw));
        }
        return list;
    }

    /** 机型列表 */
    public List<PhoneListVO> getPhoneList(String category, String keyword) {
        LambdaQueryWrapper<Phone> q = new LambdaQueryWrapper<Phone>()
            .eq(Phone::getEnabled, true)
            .orderByAsc(Phone::getSort);
        if (category != null && !category.trim().isEmpty()) {
            q.eq(Phone::getCategory, category);
        }
        // 关键词搜索（支持空格分词 + 去空格容错，如 iPhone17 也能匹配 iPhone 17）
        if (keyword != null && !keyword.trim().isEmpty()) {
            String trimmed = keyword.trim();
            if (trimmed.contains(" ")) {
                String[] words = trimmed.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        final String w2 = word;
                        q.and(wrapper -> wrapper.like(Phone::getName, w2).or().like(Phone::getBrand, w2));
                    }
                }
            } else {
                q.and(wrapper -> wrapper
                    .like(Phone::getName, trimmed)
                    .or().like(Phone::getBrand, trimmed)
                    .or().apply("REPLACE(name, ' ', '') LIKE {0}", "%" + trimmed + "%")
                    .or().apply("REPLACE(brand, ' ', '') LIKE {0}", "%" + trimmed + "%")
                    .or().apply("REPLACE(CONCAT(IFNULL(brand, ''), IFNULL(name, '')), ' ', '') LIKE {0}", "%" + trimmed + "%")
                );
            }
        }
        List<Phone> phones = phoneMapper.selectList(q);
        // 批量获取 processor
        Map<Long, String> procMap = new HashMap<Long, String>();
        if (!phones.isEmpty()) {
            List<Long> ids = phones.stream().map(Phone::getId).collect(Collectors.toList());
            LambdaQueryWrapper<PhoneBasic> bq = new LambdaQueryWrapper<PhoneBasic>().in(PhoneBasic::getPhoneId, ids);
            for (PhoneBasic b : basicMapper.selectList(bq)) {
                procMap.put(b.getPhoneId(), b.getProcessor());
            }
        }
        final Map<Long, String> procMapFinal = procMap;
        return phones.stream().map(p -> {
            PhoneListVO vo = toListVO(p);
            vo.setProcessor(procMapFinal.get(p.getId()));
            return vo;
        }).collect(Collectors.toList());
    }

    /** 机型列表（分页） */
    public Map<String, Object> getPhoneListPage(String brand, String category, String keyword,
                                                 String sortBy, Integer minPrice, Integer maxPrice,
                                                 int page, int pageSize) {
        LambdaQueryWrapper<Phone> q = new LambdaQueryWrapper<Phone>()
            .eq(Phone::getEnabled, true);

        // 品牌筛选（前端传 brand，后端用 brand 字段匹配）
        if (brand != null && !brand.trim().isEmpty()) {
            q.eq(Phone::getBrand, brand);
        }
        // 档位筛选（保留旧 category 逻辑）
        if (category != null && !category.trim().isEmpty()) {
            q.eq(Phone::getCategory, category);
        }
        // 关键词搜索（支持空格分词 + 去空格容错，如 iPhone17 也能匹配 iPhone 17）
        if (keyword != null && !keyword.trim().isEmpty()) {
            String trimmed = keyword.trim();
            if (trimmed.contains(" ")) {
                String[] words = trimmed.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        final String w2 = word;
                        q.and(wrapper -> wrapper.like(Phone::getName, w2).or().like(Phone::getBrand, w2));
                    }
                }
            } else {
                q.and(wrapper -> wrapper
                    .like(Phone::getName, trimmed)
                    .or().like(Phone::getBrand, trimmed)
                    .or().apply("REPLACE(name, ' ', '') LIKE {0}", "%" + trimmed + "%")
                    .or().apply("REPLACE(brand, ' ', '') LIKE {0}", "%" + trimmed + "%")
                    .or().apply("REPLACE(CONCAT(IFNULL(brand, ''), IFNULL(name, '')), ' ', '') LIKE {0}", "%" + trimmed + "%")
                );
            }
        }
        // 价格区间
        if (minPrice != null && minPrice > 0) {
            q.ge(Phone::getPrice, minPrice);
        }
        if (maxPrice != null && maxPrice > 0 && maxPrice < 99999) {
            q.le(Phone::getPrice, maxPrice);
        }
        // 排序
        if ("price-asc".equals(sortBy)) {
            q.orderByAsc(Phone::getPrice);
        } else if ("price-desc".equals(sortBy)) {
            q.orderByDesc(Phone::getPrice);
        } else if ("newest".equals(sortBy)) {
            // newest = 按 id 降序（id 大 = 新录入 = 较新机型）
            q.orderByDesc(Phone::getId);
        } else {
            q.orderByAsc(Phone::getSort);
        }

        // 总数（独立查，不受 LIMIT 影响）
        long total = phoneMapper.selectCount(q);

        // 分页
        q.last("LIMIT " + ((page - 1) * pageSize) + ", " + pageSize);
        List<Phone> phones = phoneMapper.selectList(q);

        // 批量获取 processor
        Map<Long, String> procMap = new HashMap<Long, String>();
        if (!phones.isEmpty()) {
            List<Long> ids = phones.stream().map(Phone::getId).collect(Collectors.toList());
            LambdaQueryWrapper<PhoneBasic> bq = new LambdaQueryWrapper<PhoneBasic>().in(PhoneBasic::getPhoneId, ids);
            for (PhoneBasic b : basicMapper.selectList(bq)) {
                procMap.put(b.getPhoneId(), b.getProcessor());
            }
        }
        final Map<Long, String> procMapFinal = procMap;
        List<PhoneListVO> list = phones.stream().map(p -> {
            PhoneListVO vo = toListVO(p);
            vo.setProcessor(procMapFinal.get(p.getId()));
            return vo;
        }).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("total", total);
        result.put("page", page);
        result.put("pageSize", pageSize);
        return result;
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
        vo.setCoverImage(phone.getCoverImage());

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
        vo.setPrice(p.getPrice());
        vo.setCoverImage(p.getCoverImage());
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
        vo.setCoverImage(p.getCoverImage());
        vo.setTags(parseTags(p.getScoreLabel()));
        vo.setShowHome(p.getShowHome() != null && p.getShowHome());
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

    private static final Pattern PATTERN_HOURS = Pattern.compile("([\\d.]+)\\s*小时");

    /**
     * 续航排行榜：综合续航 = (游戏续航 + 日常使用均值) / 2
     * - 游戏续航：覆盖全部机型（游戏续航 / 重度游戏续航）
     * - 日常使用：优先真实"日常续航/日常综合"场景；无则用 在线视频、网页浏览 的均值作代理
     */
    public List<BatteryRankVO> getBatteryRank() {
        List<Phone> phones = phoneMapper.selectList(
            new LambdaQueryWrapper<Phone>().eq(Phone::getEnabled, true));
        if (phones.isEmpty()) return Collections.emptyList();

        List<Long> ids = phones.stream().map(Phone::getId).collect(Collectors.toList());

        Set<String> SCENES = new HashSet<String>(Arrays.asList(
            "游戏续航", "重度游戏续航",
            "日常续航", "日常综合",
            "在线视频", "视频播放", "在线视频播放",
            "网页浏览", "浏览网页"));
        List<PhoneBattery> batteries = batteryMapper.selectList(
            new LambdaQueryWrapper<PhoneBattery>()
                .in(PhoneBattery::getPhoneId, ids)
                .in(PhoneBattery::getScene, SCENES));
        Map<Long, List<PhoneBattery>> batMap = new HashMap<Long, List<PhoneBattery>>();
        for (PhoneBattery b : batteries) {
            List<PhoneBattery> l = batMap.get(b.getPhoneId());
            if (l == null) { l = new ArrayList<PhoneBattery>(); batMap.put(b.getPhoneId(), l); }
            l.add(b);
        }

        List<PhoneBasic> basics = basicMapper.selectList(
            new LambdaQueryWrapper<PhoneBasic>().in(PhoneBasic::getPhoneId, ids));
        Map<Long, PhoneBasic> basicMap = new HashMap<Long, PhoneBasic>();
        for (PhoneBasic b : basics) basicMap.put(b.getPhoneId(), b);

        List<BatteryRankVO> result = new ArrayList<BatteryRankVO>();
        for (Phone p : phones) {
            List<PhoneBattery> list = batMap.get(p.getId());
            if (list == null || list.isEmpty()) continue;
            Double composite = computeComposite(list);
            if (composite == null) continue;
            BatteryRankVO vo = new BatteryRankVO();
            vo.setId(p.getId());
            vo.setBrand(p.getBrand());
            String displayName;
            if ("Apple".equals(p.getBrand())) {
                displayName = p.getName();
            } else if (p.getName() != null && p.getName().startsWith(p.getBrand())) {
                displayName = p.getName();
            } else {
                displayName = p.getBrand() + " " + p.getName();
            }
            vo.setName(displayName);
            vo.setCoverImage(p.getCoverImage());
            vo.setBgColor(p.getBgColor());
            vo.setImageColor(p.getImageColor());
            vo.setDuration(composite);
            PhoneBasic basic = basicMap.get(p.getId());
            if (basic != null) {
                vo.setBatteryCapacity(basic.getBatteryCapacity());
                vo.setScreenSize(basic.getScreenSize());
                vo.setProcessor(basic.getProcessor());
                StringBuilder spec = new StringBuilder();
                if (basic.getScreenSize() != null && !basic.getScreenSize().trim().isEmpty()) spec.append(basic.getScreenSize());
                if (basic.getProcessor() != null && !basic.getProcessor().trim().isEmpty()) {
                    if (spec.length() > 0) spec.append(" ");
                    spec.append(basic.getProcessor());
                }
                if (basic.getBatteryCapacity() != null) {
                    if (spec.length() > 0) spec.append(" ");
                    spec.append(basic.getBatteryCapacity()).append("mAh");
                }
                vo.setSpec(spec.toString());
            }
            result.add(vo);
        }
        // 按综合续航降序
        result.sort((a, b) -> Double.compare(b.getDuration(), a.getDuration()));
        return result;
    }

    /** 综合续航 = (游戏续航 + 日常使用均值) / 2 */
    private Double computeComposite(List<PhoneBattery> list) {
        Map<String, Double> m = new HashMap<String, Double>();
        for (PhoneBattery b : list) {
            Double h = parseHours(b.getDuration());
            if (h != null && !m.containsKey(b.getScene())) m.put(b.getScene(), h);
        }
        Double game = pick(m, "游戏续航", "重度游戏续航");
        if (game == null) return null;
        Double daily;
        Double dailyScene = pick(m, "日常综合", "日常续航");
        if (dailyScene != null) {
            daily = dailyScene;
        } else {
            List<Double> parts = new ArrayList<Double>();
            Double v = pick(m, "在线视频", "视频播放", "在线视频播放");
            if (v != null) parts.add(v);
            Double w = pick(m, "网页浏览", "浏览网页");
            if (w != null) parts.add(w);
            daily = parts.isEmpty() ? null : avg(parts);
        }
        if (daily == null) return game;
        return (game + daily) / 2.0;
    }

    private Double pick(Map<String, Double> m, String... scenes) {
        for (String s : scenes) if (m.containsKey(s)) return m.get(s);
        return null;
    }

    private Double avg(List<Double> list) {
        double sum = 0;
        for (Double d : list) sum += d;
        return sum / list.size();
    }

    private Double parseHours(String s) {
        if (s == null) return null;
        Matcher m = PATTERN_HOURS.matcher(s);
        return m.find() ? Double.parseDouble(m.group(1)) : null;
    }

    /** 充电榜单：综合充电能力 W = 有线 + 无线，按 totalW 降序 */
    public List<ChargeRankVO> getChargeRank() {
        List<Phone> phones = phoneMapper.selectList(
            new LambdaQueryWrapper<Phone>().eq(Phone::getEnabled, true));
        if (phones.isEmpty()) return Collections.emptyList();

        List<Long> ids = phones.stream().map(Phone::getId).collect(Collectors.toList());

        // 有线充电 / 无线充电（覆盖率各 198 款）
        List<PhoneOther> others = otherMapper.selectList(
            new LambdaQueryWrapper<PhoneOther>()
                .in(PhoneOther::getPhoneId, ids)
                .in(PhoneOther::getName, Arrays.asList("有线充电", "无线充电")));
        Map<Long, Integer> wiredMap = new HashMap<Long, Integer>();
        Map<Long, Integer> wirelessMap = new HashMap<Long, Integer>();
        for (PhoneOther o : others) {
            Integer w = parseWatt(o.getVal());
            if (w == null) continue;
            if ("有线充电".equals(o.getName())) {
                if (!wiredMap.containsKey(o.getPhoneId())) wiredMap.put(o.getPhoneId(), w);
            } else if ("无线充电".equals(o.getName())) {
                if (!wirelessMap.containsKey(o.getPhoneId())) wirelessMap.put(o.getPhoneId(), w);
            }
        }

        List<PhoneBasic> basics = basicMapper.selectList(
            new LambdaQueryWrapper<PhoneBasic>().in(PhoneBasic::getPhoneId, ids));
        Map<Long, PhoneBasic> basicMap = new HashMap<Long, PhoneBasic>();
        for (PhoneBasic b : basics) basicMap.put(b.getPhoneId(), b);

        List<ChargeRankVO> result = new ArrayList<ChargeRankVO>();
        for (Phone p : phones) {
            Integer wired = wiredMap.get(p.getId());
            Integer wireless = wirelessMap.get(p.getId());
            if (wired == null && wireless == null) continue;
            int total = (wired == null ? 0 : wired) + (wireless == null ? 0 : wireless);
            ChargeRankVO vo = new ChargeRankVO();
            vo.setId(p.getId());
            vo.setBrand(p.getBrand());
            String displayName;
            if ("Apple".equals(p.getBrand())) displayName = p.getName();
            else if (p.getName() != null && p.getName().startsWith(p.getBrand())) displayName = p.getName();
            else displayName = p.getBrand() + " " + p.getName();
            vo.setName(displayName);
            vo.setCoverImage(p.getCoverImage());
            vo.setBgColor(p.getBgColor());
            vo.setImageColor(p.getImageColor());
            vo.setWiredW(wired);
            vo.setWirelessW(wireless);
            vo.setTotalW(total);
            PhoneBasic basic = basicMap.get(p.getId());
            if (basic != null) {
                vo.setBatteryCapacity(basic.getBatteryCapacity());
                vo.setScreenSize(basic.getScreenSize());
                vo.setProcessor(basic.getProcessor());
                StringBuilder sb = new StringBuilder();
                if (basic.getScreenSize() != null && !basic.getScreenSize().trim().isEmpty()) sb.append(basic.getScreenSize());
                if (basic.getProcessor() != null && !basic.getProcessor().trim().isEmpty()) { if (sb.length()>0) sb.append(" "); sb.append(basic.getProcessor()); }
                if (basic.getBatteryCapacity() != null) { if (sb.length()>0) sb.append(" "); sb.append(basic.getBatteryCapacity()).append("mAh"); }
                vo.setSpec(sb.toString());
            }
            result.add(vo);
        }
        result.sort((a, b) -> Integer.compare(
            b.getTotalW() == null ? 0 : b.getTotalW(),
            a.getTotalW() == null ? 0 : a.getTotalW()));
        return result;
    }

    /** 屏幕榜单：按峰值亮度 nits 降序 */
    public List<ScreenRankVO> getScreenRank() {
        List<Phone> phones = phoneMapper.selectList(
            new LambdaQueryWrapper<Phone>().eq(Phone::getEnabled, true));
        if (phones.isEmpty()) return Collections.emptyList();

        List<Long> ids = phones.stream().map(Phone::getId).collect(Collectors.toList());

        List<PhoneScreen> screens = screenMapper.selectList(
            new LambdaQueryWrapper<PhoneScreen>()
                .in(PhoneScreen::getPhoneId, ids)
                .in(PhoneScreen::getName, Arrays.asList("峰值亮度", "刷新率", "分辨率", "屏幕尺寸")));
        Map<Long, Integer> brightnessMap = new HashMap<Long, Integer>();
        Map<Long, String> refreshMap = new HashMap<Long, String>();
        Map<Long, String> resolutionMap = new HashMap<Long, String>();
        Map<Long, String> screenSizeMap = new HashMap<Long, String>();
        for (PhoneScreen s : screens) {
            Long pid = s.getPhoneId();
            String val = s.getVal();
            if ("峰值亮度".equals(s.getName())) {
                if (!brightnessMap.containsKey(pid)) {
                    Integer n = parseNits(val);
                    if (n != null) brightnessMap.put(pid, n);
                }
            } else if ("刷新率".equals(s.getName())) {
                if (!refreshMap.containsKey(pid)) refreshMap.put(pid, val);
            } else if ("分辨率".equals(s.getName())) {
                if (!resolutionMap.containsKey(pid)) resolutionMap.put(pid, val);
            } else if ("屏幕尺寸".equals(s.getName())) {
                if (!screenSizeMap.containsKey(pid)) screenSizeMap.put(pid, val);
            }
        }

        List<PhoneBasic> basics = basicMapper.selectList(
            new LambdaQueryWrapper<PhoneBasic>().in(PhoneBasic::getPhoneId, ids));
        Map<Long, PhoneBasic> basicMap = new HashMap<Long, PhoneBasic>();
        for (PhoneBasic b : basics) basicMap.put(b.getPhoneId(), b);

        List<ScreenRankVO> result = new ArrayList<ScreenRankVO>();
        for (Phone p : phones) {
            Integer brightness = brightnessMap.get(p.getId());
            if (brightness == null) continue;
            ScreenRankVO vo = new ScreenRankVO();
            vo.setId(p.getId());
            vo.setBrand(p.getBrand());
            String displayName;
            if ("Apple".equals(p.getBrand())) displayName = p.getName();
            else if (p.getName() != null && p.getName().startsWith(p.getBrand())) displayName = p.getName();
            else displayName = p.getBrand() + " " + p.getName();
            vo.setName(displayName);
            vo.setCoverImage(p.getCoverImage());
            vo.setBgColor(p.getBgColor());
            vo.setImageColor(p.getImageColor());
            vo.setPeakBrightness(brightness);
            vo.setRefreshRate(refreshMap.get(p.getId()));
            vo.setResolution(resolutionMap.get(p.getId()));
            String size = screenSizeMap.get(p.getId());
            PhoneBasic basic = basicMap.get(p.getId());
            if (basic != null && basic.getScreenSize() != null && !basic.getScreenSize().trim().isEmpty()) {
                size = basic.getScreenSize();
            }
            vo.setScreenSize(size);
            if (basic != null) vo.setProcessor(basic.getProcessor());
            StringBuilder sb = new StringBuilder();
            if (size != null && !size.trim().isEmpty()) sb.append(size);
            if (vo.getRefreshRate() != null && !vo.getRefreshRate().trim().isEmpty()) { if (sb.length()>0) sb.append(" "); sb.append(vo.getRefreshRate()); }
            if (vo.getResolution() != null && !vo.getResolution().trim().isEmpty()) { if (sb.length()>0) sb.append(" "); sb.append(vo.getResolution()); }
            if (basic != null && basic.getBatteryCapacity() != null) { if (sb.length()>0) sb.append(" "); sb.append(basic.getBatteryCapacity()).append("mAh"); }
            vo.setSpec(sb.toString());
            result.add(vo);
        }
        result.sort((a, b) -> Integer.compare(b.getPeakBrightness(), a.getPeakBrightness()));
        return result;
    }

    /** 解析 "100W" / "80W有线快充" / "50W华为无线超级快充" / "不支持" → 数值（不支持返回 0） */
    private Integer parseWatt(String s) {
        if (s == null) return null;
        if (s.contains("不支持")) return 0;
        Matcher m = Pattern.compile("(\\d+)\\s*W").matcher(s);
        return m.find() ? Integer.parseInt(m.group(1)) : null;
    }

    /** 解析 "3000nits" / "3000 nit" / "600nits典型" → 数值 */
    private Integer parseNits(String s) {
        if (s == null) return null;
        Matcher m = Pattern.compile("(\\d{3,5})\\s*nits?").matcher(s.toLowerCase());
        if (m.find()) return Integer.parseInt(m.group(1));
        Matcher m2 = Pattern.compile("(\\d{3,5})\\s*nit[^a-z]").matcher(s.toLowerCase());
        return m2.find() ? Integer.parseInt(m2.group(1)) : null;
    }

    /** 处理器性能榜单：GeekBench6 单核/多核 + 安兔兔，按指定类型排序 */
    public List<BenchmarkRankVO> getBenchmarkRank(String sortType) {
        // sortType: single_core / multi_core / antutu
        String benchName = "single_core".equals(sortType) ? "GeekBench6 单核"
            : "multi_core".equals(sortType) ? "GeekBench6 多核"
            : "安兔兔";
        String benchVersion = (benchName.length() >= 9 && "GeekBench6".equals(benchName.substring(0, 9))) ? ("GeekBench6 多核".equals(benchName) ? "GeekBench6 多核" : "GeekBench6 单核") : benchName;

        // 查所有 phone
        List<Phone> phones = phoneMapper.selectList(
            new LambdaQueryWrapper<Phone>().eq(Phone::getEnabled, true));
        if (phones.isEmpty()) return Collections.emptyList();
        List<Long> allIds = phones.stream().map(Phone::getId).collect(Collectors.toList());

        // 查三种 benchmark 全部
        List<PhoneBenchmark> benches = benchmarkMapper.selectList(
            new LambdaQueryWrapper<PhoneBenchmark>()
                .in(PhoneBenchmark::getPhoneId, allIds)
                .in(PhoneBenchmark::getName, Arrays.asList("GeekBench6 单核", "GeekBench6 多核", "安兔兔")));
        Map<Long, Long> singleMap = new HashMap<Long, Long>();
        Map<Long, Long> multiMap = new HashMap<Long, Long>();
        Map<Long, Long> antutuMap = new HashMap<Long, Long>();
        for (PhoneBenchmark b : benches) {
            Long pid = b.getPhoneId();
            if (b.getScore() == null) continue;
            Long score = b.getScore().longValue();
            if ("GeekBench6 单核".equals(b.getName()) && !singleMap.containsKey(pid)) {
                singleMap.put(pid, score);
            }
            if ("GeekBench6 多核".equals(b.getName()) && !multiMap.containsKey(pid)) {
                multiMap.put(pid, score);
            }
            if ("安兔兔".equals(b.getName()) && !antutuMap.containsKey(pid)) {
                antutuMap.put(pid, score);
            }
        }

        // 查 basic
        List<PhoneBasic> basics = basicMapper.selectList(
            new LambdaQueryWrapper<PhoneBasic>().in(PhoneBasic::getPhoneId, allIds));
        Map<Long, PhoneBasic> basicMap = new HashMap<Long, PhoneBasic>();
        for (PhoneBasic b : basics) basicMap.put(b.getPhoneId(), b);

        // 构建列表，过滤无该类型数据的机型
        List<BenchmarkRankVO> list = new ArrayList<BenchmarkRankVO>();
        for (Phone p : phones) {
            Long score = singleMap.containsKey(p.getId()) ? singleMap.get(p.getId())
                : multiMap.containsKey(p.getId()) ? multiMap.get(p.getId())
                : antutuMap.get(p.getId());
            if (score == null) continue;

            BenchmarkRankVO vo = new BenchmarkRankVO();
            vo.setId(p.getId());
            vo.setBrand(p.getBrand());
            String displayName;
            if ("Apple".equals(p.getBrand())) displayName = p.getName();
            else if (p.getName() != null && p.getName().startsWith(p.getBrand())) displayName = p.getName();
            else displayName = p.getBrand() + " " + p.getName();
            vo.setName(displayName);
            vo.setCoverImage(p.getCoverImage());
            vo.setBgColor(p.getBgColor());
            vo.setImageColor(p.getImageColor());
            vo.setSingleCore(singleMap.get(p.getId()));
            vo.setMultiCore(multiMap.get(p.getId()));
            vo.setAntutu(antutuMap.get(p.getId()));
            PhoneBasic basic = basicMap.get(p.getId());
            if (basic != null) {
                vo.setProcessor(basic.getProcessor());
                vo.setMemoryConfig(basic.getMemoryConfig());
                String memCfg = basic.getMemoryConfig();
                StringBuilder sb = new StringBuilder();
                if (memCfg != null && !memCfg.trim().isEmpty()) sb.append(memCfg);
                if (basic.getProcessor() != null && !basic.getProcessor().trim().isEmpty()) {
                    if (sb.length() > 0) sb.append(" · ");
                    sb.append(basic.getProcessor());
                }
                if (basic.getBatteryCapacity() != null) {
                    if (sb.length() > 0) sb.append(" · ");
                    sb.append(basic.getBatteryCapacity()).append("mAh");
                }
                vo.setSpec(sb.toString());
            }
            list.add(vo);
        }

        // 按 sortType 对应字段排序
        if ("single_core".equals(sortType)) {
            list.sort((a, b) -> Long.compare(
                b.getSingleCore() != null ? b.getSingleCore() : 0L,
                a.getSingleCore() != null ? a.getSingleCore() : 0L));
        } else if ("multi_core".equals(sortType)) {
            list.sort((a, b) -> Long.compare(
                b.getMultiCore() != null ? b.getMultiCore() : 0L,
                a.getMultiCore() != null ? a.getMultiCore() : 0L));
        } else {
            list.sort((a, b) -> Long.compare(
                b.getAntutu() != null ? b.getAntutu() : 0L,
                a.getAntutu() != null ? a.getAntutu() : 0L));
        }
        return list;
    }

    /** 无参默认返回多核榜 */
    public List<BenchmarkRankVO> getBenchmarkRank() {
        return getBenchmarkRank("multi_core");
    }
}
