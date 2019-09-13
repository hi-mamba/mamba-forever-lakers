DROP TABLE IF EXISTS `user_info0`;
CREATE TABLE `user_info0`
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

DROP TABLE IF EXISTS `user_info1`;
CREATE TABLE `user_info1`
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



DROP TABLE IF EXISTS `user_info2`;
CREATE TABLE `user_info2`
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


DROP TABLE IF EXISTS `user_info3`;
CREATE TABLE `user_info3`
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