CREATE DATABASE IF NOT EXISTS users;
USE users;

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    phone VARCHAR(255) UNIQUE,
    purchases BIGINT NOT NULL DEFAULT 0,
    sales BIGINT NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Seed admin user (email: admin@segundum.com, password: admin123)
INSERT IGNORE INTO users (id, name, surname, email, password, birthdate, phone, purchases, sales, status, registration_date)
VALUES ('a0000000-0000-0000-0000-000000000001', 'Admin', 'SegundUM', 'admin@segundum.com', '$2a$12$w85WdtKDSzStEIEqVo.TsOZyeSV9w8DJnhSCBmw3kYGqKjkr617ii', '1990-01-01', '+34000000000', 0, 0, 'ACTIVE', CURRENT_TIMESTAMP);

INSERT IGNORE INTO user_roles (user_id, role)
VALUES ('a0000000-0000-0000-0000-000000000001', 'ADMINISTRATOR');
