-- 用户收藏表
CREATE TABLE IF NOT EXISTS user_favorite (
    id          BIGINT         AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT         NOT NULL  COMMENT '用户ID',
    phone_id    BIGINT         NOT NULL  COMMENT '机型ID',
    create_time DATETIME       DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
    UNIQUE KEY uk_user_phone (user_id, phone_id),
    INDEX idx_user_time (user_id, create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户收藏';
