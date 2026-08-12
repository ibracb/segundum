CREATE DATABASE IF NOT EXISTS products;
USE products;

CREATE TABLE IF NOT EXISTS categories (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    path LONGTEXT NOT NULL,
    description LONGTEXT,
    parent_category_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sellers (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE IF NOT EXISTS products (
    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description LONGTEXT NOT NULL,
    price DOUBLE NOT NULL,
    publication_date TIMESTAMP NOT NULL,
    condition_status VARCHAR(20) NOT NULL,
    sale_status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    category_id VARCHAR(255) NOT NULL,
    shipping_available BOOLEAN NOT NULL DEFAULT FALSE,
    pickup_description LONGTEXT,
    pickup_latitude DOUBLE,
    pickup_longitude DOUBLE,
    seller_id VARCHAR(255) NOT NULL,
    views BIGINT NOT NULL DEFAULT 0
);
