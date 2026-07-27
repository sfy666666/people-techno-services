-- 用户浏览历史表
CREATE TABLE IF NOT EXISTS user_history (
    id          BIGINT         AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT         NOT NULL  COMMENT '用户ID',
    phone_id    BIGINT         NOT NULL  COMMENT '机型ID',
    view_time   DATETIME       DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
    INDEX idx_user_time (user_id, view_time),
    INDEX idx_phone (phone_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户浏览历史';
