-- =============================================
-- 百姓数码 · 数据库初始化脚本
-- 数据库：people_techno
-- =============================================

CREATE DATABASE IF NOT EXISTS people_techno DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE people_techno;

-- ----------------------------
-- 1. 机型表
-- ----------------------------
CREATE TABLE phone (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    brand        VARCHAR(32)  NOT NULL COMMENT '品牌',
    name         VARCHAR(64)  NOT NULL COMMENT '型号',
    price        INT          COMMENT '起始价格（元）',
    cover_image  VARCHAR(255) COMMENT '封面图URL',
    bg_color     VARCHAR(255) COMMENT '卡片背景渐变',
    image_color  VARCHAR(255) COMMENT '手机图颜色',
    score        VARCHAR(8)   COMMENT '评分（如5.0）',
    score_label  VARCHAR(32)  COMMENT '评分标签',
    tagline      VARCHAR(128) COMMENT '简介标语',
    bg_gradient  VARCHAR(255) COMMENT '轮播图背景渐变',
    phone_color  VARCHAR(255) COMMENT '轮播手机颜色',
    category     VARCHAR(32)  COMMENT '分类',
    sort         INT DEFAULT 0 COMMENT '排序',
    show_home    TINYINT(1) DEFAULT 0 COMMENT '是否在首页展示',
    enabled      TINYINT(1) DEFAULT 1 COMMENT '是否上架',
    deleted      TINYINT(1) DEFAULT 0 COMMENT '逻辑删除',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_brand (brand),
    INDEX idx_show_home (show_home),
    INDEX idx_enabled_deleted (enabled, deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机型表';

-- ----------------------------
-- 2. 机型基础数据
-- ----------------------------
CREATE TABLE phone_basic (
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone_id         BIGINT NOT NULL COMMENT '关联机型ID',
    test_model       VARCHAR(64) COMMENT '测试机型',
    launch_date      VARCHAR(32) COMMENT '上市时间',
    processor        VARCHAR(64) COMMENT '处理器',
    memory_config    VARCHAR(64) COMMENT '内存组合',
    test_version     VARCHAR(64) COMMENT '测试系统版本',
    battery_capacity INT COMMENT '电池容量(mAh)',
    screen_size      VARCHAR(16) COMMENT '屏幕尺寸',
    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_phone_id (phone_id),
    INDEX idx_phone_id (phone_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机型基础数据';

-- ----------------------------
-- 3. 游戏测试
-- ----------------------------
CREATE TABLE phone_game_test (
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone_id   BIGINT NOT NULL,
    game_name  VARCHAR(64)  NOT NULL COMMENT '游戏名',
    avg_fps    DECIMAL(6,2) COMMENT '平均帧率',
    settings   VARCHAR(64)  COMMENT '画质设置',
    power      DECIMAL(5,2) COMMENT '功耗(W)',
    remark     VARCHAR(128) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_phone_id (phone_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='游戏测试';

-- ----------------------------
-- 4. 处理器跑分
-- ----------------------------
CREATE TABLE phone_benchmark (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone_id    BIGINT NOT NULL,
    name        VARCHAR(64) NOT NULL COMMENT '跑分项目名',
    version     VARCHAR(32) COMMENT '版本',
    score       INT COMMENT '分数',
    percentile  INT COMMENT '百分位',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_phone_id (phone_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='处理器跑分';

-- ----------------------------
-- 5. 续航测试
-- ----------------------------
CREATE TABLE phone_battery (
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone_id       BIGINT NOT NULL,
    scene          VARCHAR(64) NOT NULL COMMENT '测试场景',
    duration       VARCHAR(32) COMMENT '持续时长',
    discharge_rate DECIMAL(6,2) COMMENT '放电速率（%/h）',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_phone_id (phone_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='续航测试';

-- ----------------------------
-- 6. 屏幕参数
-- ----------------------------
CREATE TABLE phone_screen (
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone_id   BIGINT NOT NULL,
    name       VARCHAR(64) NOT NULL COMMENT '参数名',
    value      VARCHAR(128) COMMENT '参数值',
    remark     VARCHAR(128) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_phone_id (phone_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='屏幕参数';

-- ----------------------------
-- 7. 其他参数
-- ----------------------------
CREATE TABLE phone_other (
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone_id   BIGINT NOT NULL,
    name       VARCHAR(64) NOT NULL COMMENT '参数名',
    value      VARCHAR(128) COMMENT '参数值',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_phone_id (phone_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='其他参数';

-- ----------------------------
-- 8. 最新动态
-- ----------------------------
CREATE TABLE news (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(128) NOT NULL COMMENT '标题',
    summary      VARCHAR(255) COMMENT '摘要',
    content      TEXT COMMENT '内容',
    cover_image  VARCHAR(255) COMMENT '封面图',
    phone_id     BIGINT COMMENT '关联机型ID',
    phone_name   VARCHAR(64) COMMENT '关联机型名',
    category     VARCHAR(32) COMMENT '分类：news|update|report',
    source       VARCHAR(64) COMMENT '来源',
    publish_time DATETIME COMMENT '发布时间',
    sort         INT DEFAULT 0,
    show_home    TINYINT(1) DEFAULT 0 COMMENT '是否在首页展示',
    enabled      TINYINT(1) DEFAULT 1,
    deleted      TINYINT(1) DEFAULT 0,
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_category (category),
    INDEX idx_publish_time (publish_time),
    INDEX idx_enabled_deleted (enabled, deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='最新动态';

-- ----------------------------
-- 9. 轮播图
-- ----------------------------
CREATE TABLE banner (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    brand        VARCHAR(32) COMMENT '品牌',
    model        VARCHAR(64) COMMENT '型号',
    price        INT COMMENT '价格',
    tagline      VARCHAR(128) COMMENT '标语',
    score        VARCHAR(8) COMMENT '评分',
    score_label  VARCHAR(32) COMMENT '评分标签',
    bg_gradient  VARCHAR(255) COMMENT '背景渐变',
    phone_color  VARCHAR(255) COMMENT '手机图颜色',
    phone_id     BIGINT COMMENT '关联机型ID',
    link_type    VARCHAR(16) DEFAULT 'phone' COMMENT '跳转类型',
    link_url     VARCHAR(255) COMMENT '跳转路径',
    sort         INT DEFAULT 0,
    enabled      TINYINT(1) DEFAULT 1,
    deleted      TINYINT(1) DEFAULT 0,
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_sort (sort),
    INDEX idx_enabled_deleted (enabled, deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图';

-- =============================================
-- 初始数据
-- =============================================

-- 轮播图
INSERT INTO banner (brand, model, price, tagline, score, score_label, bg_gradient, phone_color, phone_id, sort) VALUES
('OPPO', 'Find X9 Ultra', 7499, '小白测评 数据库', '5.0', '数据库', 'linear-gradient(135deg, #1a1a1a 0%, #2a2a2a 100%)', 'linear-gradient(135deg, #4a3a2a 0%, #2a1a1a 100%)', 1, 1),
('HUAWEI', 'Mate 70 Pro+', 7999, '麒麟 9100 旗舰芯', '4.9', '数据库', 'linear-gradient(135deg, #1a2a3a 0%, #0a1a2a 100%)', 'linear-gradient(135deg, #2a3a4a 0%, #1a2a3a 100%)', 2, 2),
('Xiaomi', '15 Ultra', 6499, '徕卡四摄 影像旗舰', '4.8', '数据库', 'linear-gradient(135deg, #2a1a1a 0%, #1a0a0a 100%)', 'linear-gradient(135deg, #1a1a1a 0%, #000000 100%)', 3, 3);

-- 机型
INSERT INTO phone (brand, name, price, bg_color, image_color, score, score_label, tagline, category, sort, show_home, enabled) VALUES
('REDMI', 'REDMI K90 至尊版', 4299, 'linear-gradient(135deg, #0d2e2e 0%, #1a4040 100%)', 'linear-gradient(135deg, #2a4a4a 0%, #1a3030 100%)', '5.0', '数据库', '天玑9400+ | 5500mAh | IP68', 'android', 1, 1, 1),
('HONOR', '荣耀 X80 Pro Max', 4999, 'linear-gradient(135deg, #1a2e1a 0%, #2a4a2a 100%)', 'linear-gradient(135deg, #3a4a3a 0%, #1a2a1a 100%)', '4.9', '数据库', '骁龙8 Gen4 | 6000mAh | 100W', 'android', 2, 1, 1),
('iQOO', 'iQOO Neo11 Pro', 3999, 'linear-gradient(135deg, #2e1a2e 0%, #4a2a4a 100%)', 'linear-gradient(135deg, #4a3a4a 0%, #2a1a2a 100%)', '4.8', '数据库', '天玑9300+ | 5000mAh | 120W', 'android', 3, 1, 1),
('realme', 'realme GT7 Pro', 3799, 'linear-gradient(135deg, #1a1a2e 0%, #2a2a4a 100%)', 'linear-gradient(135deg, #3a3a4a 0%, #1a1a2a 100%)', '4.7', '数据库', '骁龙8 Gen4 | 5800mAh | 100W', 'android', 4, 1, 1),
('OPPO', 'Find X9 Ultra', 7499, 'linear-gradient(135deg, #1a1a1a 0%, #2a2a2a 100%)', 'linear-gradient(135deg, #4a3a2a 0%, #2a1a1a 100%)', '5.0', '数据库', '骁龙8 Gen4 | 5000mAh | 100W', 'android', 5, 1, 1),
('HUAWEI', 'Mate 70 Pro+', 7999, 'linear-gradient(135deg, #1a2a3a 0%, #0a1a2a 100%)', 'linear-gradient(135deg, #2a3a4a 0%, #1a2a3a 100%)', '4.9', '数据库', '麒麟9100 | 5500mAh | 100W', 'android', 6, 1, 1),
('Xiaomi', '15 Ultra', 6499, 'linear-gradient(135deg, #2a1a1a 0%, #1a0a0a 100%)', 'linear-gradient(135deg, #1a1a1a 0%, #000000 100%)', '4.8', '数据库', '骁龙8 Gen4 | 5500mAh | 90W', 'android', 7, 1, 1),
('Apple', 'iPhone 16 Pro Max', 9999, 'linear-gradient(135deg, #1a1a1a 0%, #2e2e2e 100%)', 'linear-gradient(135deg, #2a2a2a 0%, #1a1a1a 100%)', '4.9', '数据库', 'A19 Pro | 4685mAh | 45W', 'ios', 8, 1, 1);

-- 机型基础数据（以第1条机型为例）
INSERT INTO phone_basic (phone_id, test_model, launch_date, processor, memory_config, price, test_version, battery_capacity, screen_size) VALUES
(1, 'REDMI K90 至尊版 16+512GB', '2025年6月', '天玑9400+', '16GB+512GB', 4299, 'MIUI 16 / HyperOS 3.0', 5500, '6.78英寸'),
(2, '荣耀 X80 Pro Max 16+512GB', '2025年5月', '骁龙8 Gen4', '16GB+512GB', 4999, 'MagicOS 9.0', 6000, '6.92英寸'),
(3, 'iQOO Neo11 Pro 16+512GB', '2025年4月', '天玑9300+', '16GB+512GB', 3999, 'OriginOS 5', 5000, '6.78英寸'),
(4, 'realme GT7 Pro 16+512GB', '2025年5月', '骁龙8 Gen4', '16GB+512GB', 3799, 'realme UI 6.0', 5800, '6.78英寸'),
(5, 'OPPO Find X9 Ultra 16+512GB', '2025年7月', '骁龙8 Gen4', '16GB+512GB', 7499, 'ColorOS 16', 5000, '6.82英寸'),
(6, 'HUAWEI Mate 70 Pro+ 16+512GB', '2025年9月', '麒麟9100', '16GB+512GB', 7999, 'HarmonyOS 5.0', 5500, '6.9英寸'),
(7, 'Xiaomi 15 Ultra 16+512GB', '2025年3月', '骁龙8 Gen4', '16GB+512GB', 6499, 'MIUI 16 / HyperOS 2.0', 5500, '6.73英寸'),
(8, 'iPhone 16 Pro Max 256GB', '2025年9月', 'A19 Pro', '8GB+256GB', 9999, 'iOS 19', 4685, '6.9英寸');

-- 游戏测试（第1条机型示例）
INSERT INTO phone_game_test (phone_id, game_name, avg_fps, settings, power, remark) VALUES
(1, '原神', 59.6, '极高画质 60帧', 4.2, '接近满帧'),
(1, '王者荣耀', 119.8, '极致画质 120帧', 3.1, '满帧稳定'),
(1, '和平精英', 89.4, 'HDR高清 90帧', 3.8, '流畅'),
(1, '崩坏：星穹铁道', 58.3, '最高画质 60帧', 4.5, '轻微波动'),
(1, '逆水寒', 59.1, '高画质 60帧', 5.1, '发热明显');

-- 处理器跑分
INSERT INTO phone_benchmark (phone_id, name, version, score, percentile) VALUES
(1, '安兔兔', 'V11', 2165000, 98),
(1, 'GeekBench6 单核', '6.3', 2650, 97),
(1, 'GeekBench6 多核', '6.3', 7150, 98),
(1, '3DMark WildLife', 'Extreme', 18200, 96),
(1, 'AI Benchmark', 'V5', 3820, 95);

-- 续航测试
INSERT INTO phone_battery (phone_id, scene, duration, discharge_rate) VALUES
(1, '游戏续航', '5.2小时', 19.2),
(1, '在线视频', '14.5小时', 6.9),
(1, '网页浏览', '12.8小时', 7.8),
(1, '微信通话', '32小时', 3.1),
(1, '待机续航', '1.2%/天', 1.2);

-- 屏幕参数
INSERT INTO phone_screen (phone_id, name, value, remark) VALUES
(1, '分辨率', '2K+ 3168×1440', '顶级分辨率'),
(1, '刷新率', '1-120Hz LTPO', '自适应刷新'),
(1, '峰值亮度', '4500nit', '阳光下清晰'),
(1, '触控采样率', '480Hz', '游戏跟手'),
(1, 'PWM调光', '2160Hz', '护眼'),
(1, '色域', 'DCI-P3 100%', '专业色准');

-- 其他参数
INSERT INTO phone_other (phone_id, name, value) VALUES
(1, '机身重量', '223g'),
(1, '有线充电', '100W'),
(1, '无线充电', '50W'),
(1, '主摄像头', '5000万像素 一英寸大底'),
(1, '防水防尘', 'IP68'),
(1, '系统', 'HyperOS 3.0'),
(1, '解锁方式', '超声波指纹 + 3D人脸'),
(1, '马达', 'X轴线性马达'),
(1, '扬声器', '立体声双扬声器');

-- 最新动态
INSERT INTO news (title, summary, category, source, publish_time, show_home) VALUES
('REDMI K90 至尊版发布：天玑9400+加持，定价4299元起', 'REDMI正式发布K90系列，搭载联发科天玑9400+旗舰芯，定价4299元起。', 'news', '小白测评', NOW(), 1),
('2025年旗舰手机性能排行：骁龙8 Gen4霸榜', '最新旗舰手机性能排行榜出炉，骁龙8 Gen4机型全面霸榜，麒麟9100紧随其后。', 'report', '小白测评', NOW(), 1),
('小白数据库更新：50款机型续航实测出炉', '小白测评数据库完成新一轮续航测试，涵盖50款主流机型。', 'update', '小白测评', NOW(), 1);
