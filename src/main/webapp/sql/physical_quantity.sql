/*
 Navicat Premium Data Transfer

 Source Server         : sample
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : db20200322

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 26/07/2020 15:11:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for physical_quantity
-- ----------------------------
DROP TABLE IF EXISTS `physical_quantity`;
CREATE TABLE `physical_quantity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `unit_nameiso` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `symbol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `english_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `unit_symboliso` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_cpuqeliwc1d2jmf3hnepyxk2i`(`unit_nameiso`) USING BTREE,
  UNIQUE INDEX `UK_fvjl7n44qj2rt794kyxebmrrm`(`symbol`) USING BTREE,
  UNIQUE INDEX `UK_iffxi2ig0lqg7purmewhu5gmj`(`quantity_name`) USING BTREE,
  UNIQUE INDEX `UK_4wmo55vaoxr4c9r3ko2ors6v7`(`english_name`) USING BTREE,
  UNIQUE INDEX `UK_nc2iwf666lhi74hj6jbqd3c2n`(`unit_symboliso`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of physical_quantity
-- ----------------------------
INSERT INTO `physical_quantity` VALUES (1, 0, '千克', 'M', '质量', 'mass', 'kg');
INSERT INTO `physical_quantity` VALUES (2, 0, '米', 'L', '长度', 'length', 'm');
INSERT INTO `physical_quantity` VALUES (3, 0, '秒', 'T', '时间', 'time', 's');

SET FOREIGN_KEY_CHECKS = 1;
