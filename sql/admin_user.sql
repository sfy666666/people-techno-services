-- 管理员用户表
CREATE TABLE IF NOT EXISTS admin_user (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    username     VARCHAR(64)  NOT NULL UNIQUE COMMENT '用户名',
    password     VARCHAR(128) NOT NULL COMMENT '密码(MD5加盐)',
    nickname     VARCHAR(64)  COMMENT '昵称',
    role         VARCHAR(32)  DEFAULT 'admin' COMMENT '角色',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员用户表';

-- 插入默认管理员 (密码: admin123, MD5加盐: people_techno_admin123)
INSERT IGNORE INTO admin_user (id, username, password, nickname, role)
VALUES (1, 'admin', 'f0b81f20cfeb86e3c6a8d6e93c5a66b6', '管理员', 'admin');
