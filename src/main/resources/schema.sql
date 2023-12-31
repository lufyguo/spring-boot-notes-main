DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`     varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
    `age`      int(11) DEFAULT NULL COMMENT '年龄',
    `email`    varchar(100)         DEFAULT NULL COMMENT '邮箱',
    `password` varchar(100)         DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`
(
    `id`      int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) DEFAULT NULL,
    `state`   varchar(255) DEFAULT NULL,
    `country` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;