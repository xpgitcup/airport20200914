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

 Date: 05/09/2020 09:24:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_structure
-- ----------------------------
DROP TABLE IF EXISTS `basic_structure`;
CREATE TABLE `basic_structure`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `basic_structure_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `auxname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `data_property_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKrcaroc83ey6ievi1476inbjf7`(`parent_id`) USING BTREE,
  CONSTRAINT `FKrcaroc83ey6ievi1476inbjf7` FOREIGN KEY (`parent_id`) REFERENCES `basic_structure` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basic_structure
-- ----------------------------
INSERT INTO `basic_structure` VALUES (1, 0, 'ElementType', NULL, 'none', '材料介质', NULL);
INSERT INTO `basic_structure` VALUES (2, 0, 'ElementType', NULL, 'none', '固体', 1);
INSERT INTO `basic_structure` VALUES (3, 0, 'ElementType', NULL, 'none', '流体', 1);
INSERT INTO `basic_structure` VALUES (4, 0, 'ElementType', NULL, 'none', '普通钢', 2);
INSERT INTO `basic_structure` VALUES (5, 2, 'ElementType', 'null', 'none', '液体', 3);
INSERT INTO `basic_structure` VALUES (6, 0, 'ElementType', NULL, 'none', '气体', 3);
INSERT INTO `basic_structure` VALUES (7, 0, 'ElementType', NULL, 'none', '多相流', 3);
INSERT INTO `basic_structure` VALUES (8, 0, 'ElementType', NULL, 'none', '设备', NULL);
INSERT INTO `basic_structure` VALUES (9, 0, 'ElementType', NULL, 'none', '泵', 8);
INSERT INTO `basic_structure` VALUES (10, 0, 'ElementType', NULL, 'none', '阀', 8);
INSERT INTO `basic_structure` VALUES (11, 0, 'ElementType', NULL, 'none', '离心泵', 9);
INSERT INTO `basic_structure` VALUES (12, 0, 'ElementType', NULL, 'none', '往复泵', 9);
INSERT INTO `basic_structure` VALUES (13, 0, 'ElementType', NULL, 'none', '截断阀', 10);
INSERT INTO `basic_structure` VALUES (14, 0, 'ElementType', NULL, 'none', '调节阀', 10);
INSERT INTO `basic_structure` VALUES (15, 0, 'ElementType', NULL, 'none', '管道', 8);
INSERT INTO `basic_structure` VALUES (16, 0, 'ElementType', NULL, 'none', '地井阀', 10);
INSERT INTO `basic_structure` VALUES (17, 0, 'ElementType', NULL, 'none', '储罐', 8);
INSERT INTO `basic_structure` VALUES (18, 0, 'ElementType', NULL, 'none', '设施', NULL);
INSERT INTO `basic_structure` VALUES (19, 0, 'ElementType', NULL, 'none', '站点', 18);
INSERT INTO `basic_structure` VALUES (20, 0, 'ElementType', NULL, 'none', '阀室', 18);
INSERT INTO `basic_structure` VALUES (21, 0, 'ElementType', NULL, 'none', '拓扑结构', NULL);
INSERT INTO `basic_structure` VALUES (22, 0, 'ElementType', NULL, 'none', '节点列表', 21);
INSERT INTO `basic_structure` VALUES (23, 0, 'ElementType', NULL, 'none', '连接关系', 21);
INSERT INTO `basic_structure` VALUES (24, 0, 'ElementType', NULL, 'none', '边界条件', NULL);
INSERT INTO `basic_structure` VALUES (25, 0, 'ElementType', NULL, 'none', '初始条件', NULL);
INSERT INTO `basic_structure` VALUES (26, 0, 'ElementType', NULL, 'none', '事件列表', NULL);
INSERT INTO `basic_structure` VALUES (27, 0, 'ElementType', NULL, 'none', '系统设置', NULL);
INSERT INTO `basic_structure` VALUES (28, 0, 'ElementType', NULL, 'none', '模型设置', 27);
INSERT INTO `basic_structure` VALUES (29, 0, 'ElementType', NULL, 'none', '报告设置', 27);
INSERT INTO `basic_structure` VALUES (30, 0, 'ElementType', NULL, 'none', '组分气', 6);
INSERT INTO `basic_structure` VALUES (31, 0, 'ElementType', NULL, 'none', '混合气', 6);
INSERT INTO `basic_structure` VALUES (32, 2, 'DataProperty', 'kg/m3', 'scalar', '密度', 4);
INSERT INTO `basic_structure` VALUES (33, 4, 'DataProperty', 'MPa', 'scalar', '弹性模量', 4);
INSERT INTO `basic_structure` VALUES (34, 4, 'DataProperty', 'MPa', 'scalar', '许用应力', 4);
INSERT INTO `basic_structure` VALUES (35, 2, 'DataProperty', 'null', 'scalar', '泊松比', 4);
INSERT INTO `basic_structure` VALUES (36, 2, 'DataProperty', 'kg/m3', 'vector2D', '密度', 5);
INSERT INTO `basic_structure` VALUES (37, 6, 'DataProperty', 'm2/s', 'vector2D', '运动粘度', 5);
INSERT INTO `basic_structure` VALUES (38, 3, 'DataProperty', 'Pa', 'scalar', '体积模量', 5);
INSERT INTO `basic_structure` VALUES (39, 1, 'DataProperty', 'W/m2.K', 'scalar', '传热系数', 5);
INSERT INTO `basic_structure` VALUES (40, 3, 'DataProperty', '1/K', 'vector2D', '热膨胀系数', 5);
INSERT INTO `basic_structure` VALUES (41, 2, 'DataProperty', 'm', 'vector2D', '扬程曲线', 11);
INSERT INTO `basic_structure` VALUES (42, 2, 'DataProperty', 'kW', 'vector2D', '功率曲线', 11);
INSERT INTO `basic_structure` VALUES (43, 1, 'DataProperty', 'r/min', 'scalar', '转速', 11);
INSERT INTO `basic_structure` VALUES (44, 1, 'ElementType', 'kg.m2', 'scalar', '转动惯量', 11);
INSERT INTO `basic_structure` VALUES (46, 2, 'DataProperty', 'null', 'vector2D', '效率曲线', 11);
INSERT INTO `basic_structure` VALUES (47, 2, 'DataProperty', NULL, 'scalar', '流量系数', 13);
INSERT INTO `basic_structure` VALUES (48, 1, 'DataProperty', 'null', 'scalar', '阀门开度', 13);
INSERT INTO `basic_structure` VALUES (49, 2, 'DataProperty', 's', 'scalar', '行程', 13);
INSERT INTO `basic_structure` VALUES (50, 1, 'DataProperty', 'null', 'vector2D', '流量特性曲线', 13);
INSERT INTO `basic_structure` VALUES (51, 2, 'DataProperty', 'm', 'scalar', '管长', 15);
INSERT INTO `basic_structure` VALUES (52, 1, 'DataProperty', 'm', 'scalar', '管径', 15);
INSERT INTO `basic_structure` VALUES (53, 2, 'DataProperty', 'm', 'scalar', '壁厚', 15);
INSERT INTO `basic_structure` VALUES (54, 2, 'DataProperty', 'm', 'scalar', '粗糙度', 15);
INSERT INTO `basic_structure` VALUES (55, 1, 'DataProperty', 'W/m2.K', 'scalar', '传热系数', 4);
INSERT INTO `basic_structure` VALUES (56, 0, 'ElementType', NULL, 'none', '加油车', 8);

SET FOREIGN_KEY_CHECKS = 1;
