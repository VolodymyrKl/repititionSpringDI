CREATE DATABASE IF NOT EXISTS Products;
USE Products;
CREATE TABLE IF NOT EXISTS Product
(
    id         INTEGER not NULL AUTO_INCREMENT,
    name       VARCHAR(255),
    price      INTEGER,
    status     VARCHAR(255),
    created_at VARCHAR(255),
    PRIMARY KEY (id)
);