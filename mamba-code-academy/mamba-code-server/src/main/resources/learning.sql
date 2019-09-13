CREATE TABLE `user_info`
(
    `id`              bigint(32)   NOT NULL auto_increment primary key COMMENT '主键ID',
    `username`        VARCHAR(50)  NOT NULL COMMENT '用户名',
    `password`        VARCHAR(255) NOT NULL COMMENT '密码',
    `email`           VARCHAR(50)  NOT NULL COMMENT '用户邮箱',
    `mobile`          VARCHAR(50)  NOT NULL COMMENT '用户手机',
    `reg_time`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `reg_ip`          bigint(20)   NOT NULL DEFAULT '0' COMMENT '注册IP',
    `last_login_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
    `last_login_ip`   bigint(20)   NOT NULL DEFAULT '0' COMMENT '最后登录IP',
    `update_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`          tinyint(4)            DEFAULT '0' COMMENT '用户状态',
    `create_time`     datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户表';



-- 分库分表例子
CREATE TABLE `t_address`
(
    `id`   bigint(20)  NOT NULL,
    `code` varchar(64)          DEFAULT NULL COMMENT '编码',
    `name` varchar(64)          DEFAULT NULL COMMENT '名称',
    `pid`  varchar(64) NOT NULL DEFAULT '0' COMMENT '父id',
    `type` int(11)              DEFAULT NULL COMMENT '1国家2省3市4县区',
    `lit`  int(11)              DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_user0`
(
    `id`          bigint(20) NOT NULL,
    `name`        varchar(64)         DEFAULT NULL COMMENT '名称',
    `city_id`     int(12)             DEFAULT NULL COMMENT '城市',
    `sex`         tinyint(1)          DEFAULT NULL COMMENT '性别',
    `phone`       varchar(32)         DEFAULT NULL COMMENT '电话',
    `email`       varchar(32)         DEFAULT NULL COMMENT '邮箱',
    `create_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `password`    varchar(32)         DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_user1`
(
    `id`          bigint(20) NOT NULL,
    `name`        varchar(64)         DEFAULT NULL COMMENT '名称',
    `city_id`     int(12)             DEFAULT NULL COMMENT '城市',
    `sex`         tinyint(1)          DEFAULT NULL COMMENT '性别',
    `phone`       varchar(32)         DEFAULT NULL COMMENT '电话',
    `email`       varchar(32)         DEFAULT NULL COMMENT '邮箱',
    `create_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `password`    varchar(32)         DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
