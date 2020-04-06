/*
 Navicat Premium Data Transfer

 Source Server         : 47.105.196.42
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 47.105.196.42:3306
 Source Schema         : db1

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 06/04/2020 18:02:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_logistics
-- ----------------------------
DROP TABLE IF EXISTS `sys_logistics`;
CREATE TABLE `sys_logistics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressee_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人',
  `addressee` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人地址',
  `addressee_phone` int(11) NOT NULL,
  `sender_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄件人',
  `sender_add` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄件人地址',
  `ems_status` int(1) NOT NULL DEFAULT 0 COMMENT '0,已接单，1,已发货，2，已签收',
  `retreat_status` int(1) NULL DEFAULT 0 COMMENT '0,为退货，1，退货中，2，已退货',
  `retreat_info` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退货原因',
  `create_date` date NOT NULL COMMENT '创建时间',
  `update_date` date NULL DEFAULT NULL,
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logistics
-- ----------------------------
INSERT INTO `sys_logistics` VALUES (1, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (2, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (3, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (4, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (5, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (6, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (7, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (8, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (9, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (10, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');
INSERT INTO `sys_logistics` VALUES (11, 'yanghai', '北京', 1234565432, 'dylan', '北京', 0, 0, NULL, '2019-11-02', NULL, 'admin');

SET FOREIGN_KEY_CHECKS = 1;
