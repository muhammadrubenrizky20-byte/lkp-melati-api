CREATE TABLE IF NOT EXISTS site_settings (
    id INT PRIMARY KEY DEFAULT 1,
    site_title VARCHAR(255),
    hero_subtitle TEXT,
    contact_phone VARCHAR(50),
    contact_email VARCHAR(100),
    about_text TEXT
);

CREATE TABLE IF NOT EXISTS courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    icon_class VARCHAR(100),
    duration VARCHAR(50),
    badge VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS instructors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    image_url VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS facilities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    icon_class VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS media_gallery (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    type VARCHAR(20),
    media_url VARCHAR(500) NOT NULL,
    category VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS certificates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    issued_by VARCHAR(255),
    image_url VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS testimonials (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    course_name VARCHAR(255),
    review TEXT NOT NULL,
    rating INT DEFAULT 5,
    avatar_url VARCHAR(500)
);