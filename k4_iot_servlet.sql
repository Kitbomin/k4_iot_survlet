-- k4_iot_servelt Connection -- 

DROP DATABASE IF EXISTS `k4_iot_servlet`;

CREATE DATABASE IF NOT EXISTS `k4_iot_servlet`
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `k4_iot_servlet`;
CREATE TABLE user (
	id int auto_increment primary key,
    name varchar(100) not null,
    email varchar(100) not null unique,
    country varchar(100) not null
);