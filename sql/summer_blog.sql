/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : summer_blog

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 06/11/2022 11:20:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ix_article_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'Spring入门', '<h1><span style=\"text-decoration: line-through; color: #e03e2d;\"><em><img src=\"uploads/e120239dbe784c4e9ad05ccc51369089.jpg\" alt=\"\" width=\"96\" height=\"96\" />hello test</em></span></h1>\n<p><img src=\"uploads/0cfbfc9d2f5f4b368fb362410127f3b2.jpg\" alt=\"\" width=\"200\" height=\"230\" /></p>\n<p>test</p>\n<p>&nbsp;</p>\n<h2><span style=\"color: #2dc26b;\">test</span></h2>\n<p>&nbsp;</p>\n<p><span style=\"color: #2dc26b;\">testtt</span></p>\n<p>&nbsp;</p>\n<p><span style=\"color: #2dc26b;\"><img src=\"uploads/5bc4513fcac346719da78585d38adfa1.jpg\" alt=\"\" width=\"108\" height=\"216\" /></span></p>', 1, '2022-10-28 17:00:40');
INSERT INTO `article` VALUES (9, 'test', '<h1><strong><span style=\"text-decoration: line-through; color: #e03e2d;\">1111</span></strong></h1>\n<p><strong><span style=\"text-decoration: line-through; color: #e03e2d;\"><img src=\"uploads/0f233db457314e8a994a6d4fbfd3a339.jpg\" alt=\"\" width=\"108\" height=\"216\" /></span></strong></p>\n<p><span style=\"background-color: #2dc26b;\">test</span></p>', 1, '2022-11-01 16:46:08');
INSERT INTO `article` VALUES (10, 'test2', '2222', 1, '2022-11-01 16:46:27');

-- ----------------------------
-- Table structure for article_category_rel
-- ----------------------------
DROP TABLE IF EXISTS `article_category_rel`;
CREATE TABLE `article_category_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(20) NULL DEFAULT NULL COMMENT '文章id',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `article_category_rel_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_category_rel_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 187 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章分类关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_category_rel
-- ----------------------------
INSERT INTO `article_category_rel` VALUES (43, 10, 10, 1);
INSERT INTO `article_category_rel` VALUES (44, 10, 24, 2);
INSERT INTO `article_category_rel` VALUES (45, 10, 25, 3);
INSERT INTO `article_category_rel` VALUES (46, 10, 26, 4);
INSERT INTO `article_category_rel` VALUES (132, 9, 27, 1);
INSERT INTO `article_category_rel` VALUES (183, 1, 10, 1);
INSERT INTO `article_category_rel` VALUES (184, 1, 24, 2);
INSERT INTO `article_category_rel` VALUES (185, 1, 25, 3);
INSERT INTO `article_category_rel` VALUES (186, 1, 26, 4);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级分类id，0为第一级',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ix_category_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Java', 1, '2022-10-28 16:55:34', 0);
INSERT INTO `category` VALUES (2, 'Spring', 1, '2022-10-28 16:56:02', 1);
INSERT INTO `category` VALUES (3, 'Mybatis', 1, '2022-10-28 16:56:18', 1);
INSERT INTO `category` VALUES (10, 'test', 1, '2022-10-31 16:14:36', 0);
INSERT INTO `category` VALUES (23, 'SpringCache', 1, '2022-10-31 16:49:11', 2);
INSERT INTO `category` VALUES (24, 't2', 1, '2022-10-31 16:50:00', 10);
INSERT INTO `category` VALUES (25, 't3', 1, '2022-10-31 16:50:36', 24);
INSERT INTO `category` VALUES (26, 't4', 1, '2022-10-31 16:50:43', 25);
INSERT INTO `category` VALUES (27, '1111', 1, '2022-11-01 16:22:18', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像URL',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'user' COMMENT '角色，\'admin\'为管理员，\'user\'为用户，默认为\'user\'',
  `create_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '1234', '张三123', '/uploads/dbec5d5e93bc4bdd8cd1689cd56b8ec0.jpg', 'admin', '2022-10-28 16:50:15');
INSERT INTO `user` VALUES (11, 'zhao', '1111111', '赵六', '/uploads/692e923016384ce98de45153795bb308.jpg', 'user', NULL);
INSERT INTO `user` VALUES (15, 'wang', '1111111111', '李四', '/uploads/51a1d37b18114700ad1f8b29e4d776dc.jpg', 'user', NULL);
INSERT INTO `user` VALUES (16, 'lisi', '1111111111', '里斯', NULL, 'user', NULL);
INSERT INTO `user` VALUES (17, '1234', '1111111111', 'ichi', NULL, 'user', NULL);
INSERT INTO `user` VALUES (18, '4321', '11111111111', 'san', NULL, 'user', NULL);
INSERT INTO `user` VALUES (19, 'zhang', '1234', 'zhang', '/uploads/ff10b85e2679435598bddef2ce6cae67.jpg', 'user', '2022-10-30 17:33:27');
INSERT INTO `user` VALUES (20, 'zhan', '1234', 'zhan', '/api/avatar/default.png', 'user', '2022-10-30 17:34:39');
INSERT INTO `user` VALUES (21, 'test', '11111111111', '11', NULL, 'user', NULL);
INSERT INTO `user` VALUES (22, '1111', '1111', '1111', '/uploads/fa881ad2f26f42c58ef7969b551415e0.jpg', 'user', NULL);
INSERT INTO `user` VALUES (23, '222', '222', '22', NULL, 'user', '2022-11-29 17:36:03');
INSERT INTO `user` VALUES (24, '22', '22', '22', NULL, 'user', '2022-11-29 17:37:07');

SET FOREIGN_KEY_CHECKS = 1;
