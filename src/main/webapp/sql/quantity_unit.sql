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

 Date: 07/08/2020 11:24:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for quantity_unit
-- ----------------------------
DROP TABLE IF EXISTS `quantity_unit`;
CREATE TABLE `quantity_unit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `unit_system_id` bigint(20) NOT NULL,
  `factora` double NOT NULL,
  `factorb` double NOT NULL,
  `symbol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `physical_quantity_id` bigint(20) NOT NULL,
  `english_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `unit_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ci4q57gmqf45pue6iloo00mb4`(`unit_name`) USING BTREE,
  INDEX `FK2bhdf7ah0gxrcb0foc6to5af6`(`unit_system_id`) USING BTREE,
  INDEX `FKtrp3lcbvtcn45ocge85p5v1q3`(`physical_quantity_id`) USING BTREE,
  CONSTRAINT `FK2bhdf7ah0gxrcb0foc6to5af6` FOREIGN KEY (`unit_system_id`) REFERENCES `unit_system` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtrp3lcbvtcn45ocge85p5v1q3` FOREIGN KEY (`physical_quantity_id`) REFERENCES `physical_quantity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quantity_unit
-- ----------------------------
INSERT INTO `quantity_unit` VALUES (1, 0, 1, 1, 0, 'kg', 1, 'mass', '质量');
INSERT INTO `quantity_unit` VALUES (2, 0, 1, 1, 0, 'm', 2, 'length', '长度');
INSERT INTO `quantity_unit` VALUES (3, 0, 1, 1, 0, 's', 3, 'time', '时间');

SET FOREIGN_KEY_CHECKS = 1;
