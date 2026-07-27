-- 小程序用户表
CREATE TABLE IF NOT EXISTS app_user (
    id          BIGINT         AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(64)    NOT NULL  COMMENT '用户名/手机号',
    password    VARCHAR(128)   NOT NULL  COMMENT '密码（BCrypt加密）',
    nickname    VARCHAR(64)    DEFAULT NULL  COMMENT '昵称',
    avatar      VARCHAR(512)   DEFAULT NULL  COMMENT '头像URL',
    create_time DATETIME       DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='小程序用户';
