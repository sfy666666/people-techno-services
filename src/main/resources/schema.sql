CREATE TABLE IF NOT EXISTS phone (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(32) NOT NULL,
    name VARCHAR(64) NOT NULL,
    price INT,
    cover_image VARCHAR(255),
    bg_color VARCHAR(255),
    image_color VARCHAR(255),
    score VARCHAR(8),
    score_label VARCHAR(32),
    tagline VARCHAR(128),
    bg_gradient VARCHAR(255),
    phone_color VARCHAR(255),
    category VARCHAR(32),
    sort INT DEFAULT 0,
    show_home BOOLEAN DEFAULT FALSE,
    enabled BOOLEAN DEFAULT TRUE,
    deleted INT DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS phone_basic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_id BIGINT NOT NULL,
    test_model VARCHAR(64),
    launch_date VARCHAR(32),
    processor VARCHAR(64),
    memory_config VARCHAR(64),
    price INT,
    test_version VARCHAR(64),
    battery_capacity INT,
    screen_size VARCHAR(16),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS phone_game_test (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_id BIGINT NOT NULL,
    game_name VARCHAR(64) NOT NULL,
    avg_fps DECIMAL(6,2),
    settings VARCHAR(64),
    power DECIMAL(5,2),
    remark VARCHAR(128),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS phone_benchmark (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_id BIGINT NOT NULL,
    name VARCHAR(64) NOT NULL,
    version VARCHAR(32),
    score INT,
    percentile INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS phone_battery (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_id BIGINT NOT NULL,
    scene VARCHAR(64) NOT NULL,
    duration VARCHAR(32),
    discharge_rate DECIMAL(6,2),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS phone_screen (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_id BIGINT NOT NULL,
    name VARCHAR(64) NOT NULL,
    val VARCHAR(128),
    remark VARCHAR(128),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS phone_other (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_id BIGINT NOT NULL,
    name VARCHAR(64) NOT NULL,
    val VARCHAR(128),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    summary VARCHAR(255),
    content LONGTEXT,
    cover_image VARCHAR(255),
    phone_id BIGINT,
    phone_name VARCHAR(64),
    category VARCHAR(32),
    source VARCHAR(64),
    publish_time TIMESTAMP,
    sort INT DEFAULT 0,
    show_home BOOLEAN DEFAULT FALSE,
    enabled BOOLEAN DEFAULT TRUE,
    deleted INT DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS banner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(32),
    model VARCHAR(64),
    price INT,
    tagline VARCHAR(128),
    score VARCHAR(8),
    score_label VARCHAR(32),
    bg_gradient VARCHAR(255),
    phone_color VARCHAR(255),
    phone_id BIGINT,
    link_type VARCHAR(16) DEFAULT 'phone',
    link_url VARCHAR(255),
    sort INT DEFAULT 0,
    enabled BOOLEAN DEFAULT TRUE,
    deleted INT DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
