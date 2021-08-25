CREATE DATABASE IF NOT EXISTS `lakers`;
GRANT ALL ON `lakers`.* TO 'mamba'@'%';

use lakers;

drop table if exists users;
create table users
(
    id   bigint auto_increment not null primary key,
    name varchar(100)          not null default ''
);

CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `resource_ids`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `client_secret`           varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `scope`                   varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `authorized_grant_types`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `authorities`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `access_token_validity`   int(11)                                                  NULL DEFAULT NULL,
    `refresh_token_validity`  int(11)                                                  NULL DEFAULT NULL,
    `additional_information`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `autoapprove`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

INSERT INTO `oauth_client_details`
VALUES ('client', NULL, '123456', 'all', 'password,refresh_token', '', NULL, NULL, NULL, NULL, NULL);





-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `member_level_id` bigint(20) DEFAULT NULL,
                              `username` varchar(64) DEFAULT NULL COMMENT '用户名',
                              `password` varchar(64) DEFAULT NULL COMMENT '密码',
                              `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
                              `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
                              `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
                              `create_time` datetime DEFAULT NULL COMMENT '注册时间',
                              `icon` varchar(500) DEFAULT NULL COMMENT '头像',
                              `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
                              `birthday` date DEFAULT NULL COMMENT '生日',
                              `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
                              `job` varchar(100) DEFAULT NULL COMMENT '职业',
                              `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
                              `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
                              `integration` int(11) DEFAULT NULL COMMENT '积分',
                              `growth` int(11) DEFAULT NULL COMMENT '成长值',
                              `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
                              `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数量',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `idx_username` (`username`),
                              UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of ums_member
-- ----------------------------
INSERT INTO `ums_member` VALUES ('1', '4', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'windir', '18061581849', '1', '2018-08-02 10:35:44', null, '1', '2009-06-01', '上海', '学生', 'test', null, '5000', null, null, null);
INSERT INTO `ums_member` VALUES ('3', '4', 'windy', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'windy', '18061581848', '1', '2018-08-03 16:46:38', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('4', '4', 'zhengsan', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'zhengsan', '18061581847', '1', '2018-11-12 14:12:04', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('5', '4', 'lisi', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'lisi', '18061581841', '1', '2018-11-12 14:12:38', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('6', '4', 'wangwu', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'wangwu', '18061581842', '1', '2018-11-12 14:13:09', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('7', '4', 'lion', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'lion', '18061581845', '1', '2018-11-12 14:21:39', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('8', '4', 'shari', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'shari', '18061581844', '1', '2018-11-12 14:22:00', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('9', '4', 'aewen', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'aewen', '18061581843', '1', '2018-11-12 14:22:55', null, null, null, null, null, null, null, null, null, null, null);
