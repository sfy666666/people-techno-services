-- 轮播图
INSERT INTO banner (brand, model, price, tagline, score, score_label, bg_gradient, phone_color, phone_id, sort) VALUES
('OPPO', 'Find X9 Ultra', 7499, '小白测评 数据库', '5.0', '数据库', 'linear-gradient(135deg, #1a1a1a 0%, #2a2a2a 100%)', 'linear-gradient(135deg, #4a3a2a 0%, #2a1a1a 100%)', 1, 1),
('HUAWEI', 'Mate 70 Pro+', 7999, '麒麟 9100 旗舰芯', '4.9', '数据库', 'linear-gradient(135deg, #1a2a3a 0%, #0a1a2a 100%)', 'linear-gradient(135deg, #2a3a4a 0%, #1a2a3a 100%)', 2, 2),
('Xiaomi', '15 Ultra', 6499, '徕卡四摄 影像旗舰', '4.8', '数据库', 'linear-gradient(135deg, #2a1a1a 0%, #1a0a0a 100%)', 'linear-gradient(135deg, #1a1a1a 0%, #000000 100%)', 3, 3);

-- 机型
INSERT INTO phone (brand, name, price, bg_color, image_color, score, score_label, tagline, category, sort, show_home, enabled) VALUES
('REDMI', 'REDMI K90 至尊版', 4299, 'linear-gradient(135deg, #0d2e2e 0%, #1a4040 100%)', 'linear-gradient(135deg, #2a4a4a 0%, #1a3030 100%)', '5.0', '数据库', '天玑9400+ | 5500mAh | IP68', 'android', 1, TRUE, TRUE),
('HONOR', '荣耀 X80 Pro Max', 4999, 'linear-gradient(135deg, #1a2e1a 0%, #2a4a2a 100%)', 'linear-gradient(135deg, #3a4a3a 0%, #1a2a1a 100%)', '4.9', '数据库', '骁龙8 Gen4 | 6000mAh | 100W', 'android', 2, TRUE, TRUE),
('iQOO', 'iQOO Neo11 Pro', 3999, 'linear-gradient(135deg, #2e1a2e 0%, #4a2a4a 100%)', 'linear-gradient(135deg, #4a3a4a 0%, #2a1a2a 100%)', '4.8', '数据库', '天玑9300+ | 5000mAh | 120W', 'android', 3, TRUE, TRUE),
('realme', 'realme GT7 Pro', 3799, 'linear-gradient(135deg, #1a1a2e 0%, #2a2a4a 100%)', 'linear-gradient(135deg, #3a3a4a 0%, #1a1a2a 100%)', '4.7', '数据库', '骁龙8 Gen4 | 5800mAh | 100W', 'android', 4, TRUE, TRUE),
('OPPO', 'Find X9 Ultra', 7499, 'linear-gradient(135deg, #1a1a1a 0%, #2a2a2a 100%)', 'linear-gradient(135deg, #4a3a2a 0%, #2a1a1a 100%)', '5.0', '数据库', '骁龙8 Gen4 | 5000mAh | 100W', 'android', 5, TRUE, TRUE),
('HUAWEI', 'Mate 70 Pro+', 7999, 'linear-gradient(135deg, #1a2a3a 0%, #0a1a2a 100%)', 'linear-gradient(135deg, #2a3a4a 0%, #1a2a3a 100%)', '4.9', '数据库', '麒麟9100 | 5500mAh | 100W', 'android', 6, TRUE, TRUE),
('Xiaomi', '15 Ultra', 6499, 'linear-gradient(135deg, #2a1a1a 0%, #1a0a0a 100%)', 'linear-gradient(135deg, #1a1a1a 0%, #000000 100%)', '4.8', '数据库', '骁龙8 Gen4 | 5500mAh | 90W', 'android', 7, TRUE, TRUE),
('Apple', 'iPhone 16 Pro Max', 9999, 'linear-gradient(135deg, #1a1a1a 0%, #2e2e2e 100%)', 'linear-gradient(135deg, #2a2a2a 0%, #1a1a1a 100%)', '4.9', '数据库', 'A19 Pro | 4685mAh | 45W', 'ios', 8, TRUE, TRUE);

-- 基础数据
INSERT INTO phone_basic (phone_id, test_model, launch_date, processor, memory_config, price, test_version, battery_capacity, screen_size) VALUES
(1, 'REDMI K90 至尊版 16+512GB', '2025年6月', '天玑9400+', '16GB+512GB', 4299, 'MIUI 16 / HyperOS 3.0', 5500, '6.78英寸'),
(2, '荣耀 X80 Pro Max 16+512GB', '2025年5月', '骁龙8 Gen4', '16GB+512GB', 4999, 'MagicOS 9.0', 6000, '6.92英寸'),
(3, 'iQOO Neo11 Pro 16+512GB', '2025年4月', '天玑9300+', '16GB+512GB', 3999, 'OriginOS 5', 5000, '6.78英寸'),
(4, 'realme GT7 Pro 16+512GB', '2025年5月', '骁龙8 Gen4', '16GB+512GB', 3799, 'realme UI 6.0', 5800, '6.78英寸'),
(5, 'OPPO Find X9 Ultra 16+512GB', '2025年7月', '骁龙8 Gen4', '16GB+512GB', 7499, 'ColorOS 16', 5000, '6.82英寸'),
(6, 'HUAWEI Mate 70 Pro+ 16+512GB', '2025年9月', '麒麟9100', '16GB+512GB', 7999, 'HarmonyOS 5.0', 5500, '6.9英寸'),
(7, 'Xiaomi 15 Ultra 16+512GB', '2025年3月', '骁龙8 Gen4', '16GB+512GB', 6499, 'MIUI 16 / HyperOS 2.0', 5500, '6.73英寸'),
(8, 'iPhone 16 Pro Max 256GB', '2025年9月', 'A19 Pro', '8GB+256GB', 9999, 'iOS 19', 4685, '6.9英寸');

-- 游戏测试
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
INSERT INTO phone_screen (phone_id, name, val, remark) VALUES
(1, '分辨率', '2K+ 3168×1440', '顶级分辨率'),
(1, '刷新率', '1-120Hz LTPO', '自适应刷新'),
(1, '峰值亮度', '4500nit', '阳光下清晰'),
(1, '触控采样率', '480Hz', '游戏跟手'),
(1, 'PWM调光', '2160Hz', '护眼'),
(1, '色域', 'DCI-P3 100%', '专业色准');

-- 其他参数
INSERT INTO phone_other (phone_id, name, val) VALUES
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
INSERT INTO news (title, summary, category, source, publish_time, show_home, enabled) VALUES
('REDMI K90 至尊版发布：天玑9400+加持，定价4299元起', 'REDMI正式发布K90系列，搭载联发科天玑9400+旗舰芯，定价4299元起。', 'news', '小白测评', CURRENT_TIMESTAMP, TRUE, TRUE),
('2025年旗舰手机性能排行：骁龙8 Gen4霸榜', '最新旗舰手机性能排行榜出炉，骁龙8 Gen4机型全面霸榜，麒麟9100紧随其后。', 'report', '小白测评', CURRENT_TIMESTAMP, TRUE, TRUE),
('小白数据库更新：50款机型续航实测出炉', '小白测评数据库完成新一轮续航测试，涵盖50款主流机型。', 'update', '小白测评', CURRENT_TIMESTAMP, TRUE, TRUE);
