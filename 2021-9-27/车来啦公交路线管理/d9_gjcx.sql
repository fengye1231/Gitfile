/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : localhost:3306
 Source Schema         : d9_gjcx

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 09/03/2019 16:12:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_bus
-- ----------------------------
DROP TABLE IF EXISTS `t_bus`;
CREATE TABLE `t_bus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pai` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wei` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `pai`(`pai`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_bus
-- ----------------------------
INSERT INTO `t_bus` VALUES (1, '  川', '川A45874', 20);
INSERT INTO `t_bus` VALUES (2, '', '川A86657', 23);

-- ----------------------------
-- Table structure for t_database_file
-- ----------------------------
DROP TABLE IF EXISTS `t_database_file`;
CREATE TABLE `t_database_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_database_file
-- ----------------------------
INSERT INTO `t_database_file` VALUES (1, '2019-02-24 23:30:09', '20190224233009.sql');
INSERT INTO `t_database_file` VALUES (2, '2019-02-24 23:34:41', '20190224233441.sql');

-- ----------------------------
-- Table structure for t_driver
-- ----------------------------
DROP TABLE IF EXISTS `t_driver`;
CREATE TABLE `t_driver`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `cardno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `driverno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `cardno`(`cardno`) USING BTREE,
  UNIQUE INDEX `driverno`(`driverno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_driver
-- ----------------------------
INSERT INTO `t_driver` VALUES (1, 32, '3213213', '1232131', '女', '胜多');
INSERT INTO `t_driver` VALUES (2, 45, '1564561464', '65456465', '女', '李四');

-- ----------------------------
-- Table structure for t_line
-- ----------------------------
DROP TABLE IF EXISTS `t_line`;
CREATE TABLE `t_line`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iccard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `linetime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_id` int(11) NULL DEFAULT NULL,
  `start_id` int(11) NULL DEFAULT NULL,
  `distance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stations` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `sid`(`sid`) USING BTREE,
  INDEX `FKCB5F90DF74FC4ED3`(`start_id`) USING BTREE,
  INDEX `FKCB5F90DFD9160BBA`(`end_id`) USING BTREE,
  CONSTRAINT `FKCB5F90DF74FC4ED3` FOREIGN KEY (`start_id`) REFERENCES `t_station` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKCB5F90DFD9160BBA` FOREIGN KEY (`end_id`) REFERENCES `t_station` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_line
-- ----------------------------
INSERT INTO `t_line` VALUES (3, '有效', '08:00 - 23:00', '101', 19, 1, '已配置', '>站点1>站点10>站点11>站点12>站点13>站点14>站点18>');
INSERT INTO `t_line` VALUES (4, '有效', '08:00 - 23:00', '102', 5, 1, '已配置', '>站点1>站点11>站点12>站点14>站点8>站点9>站点6>站点5>');
INSERT INTO `t_line` VALUES (5, '无效', '08:00-22:00', '103', 10, 11, '已配置', '>站点10>站点12>站点13>站点14>站点18>站点8>站点9>');
INSERT INTO `t_line` VALUES (6, '有效', '8:00 - 23:00', '187', 16, 1, '已配置', '>站点1>站点10>站点11>站点12>站点15>');

-- ----------------------------
-- Table structure for t_line_bus
-- ----------------------------
DROP TABLE IF EXISTS `t_line_bus`;
CREATE TABLE `t_line_bus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shou` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bus_id` int(11) NULL DEFAULT NULL,
  `driver_id` int(11) NULL DEFAULT NULL,
  `line_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FKFD8AB720B7250053`(`driver_id`) USING BTREE,
  INDEX `FKFD8AB720A8E350C1`(`bus_id`) USING BTREE,
  INDEX `FKFD8AB7206FCB6F13`(`line_id`) USING BTREE,
  CONSTRAINT `FKFD8AB7206FCB6F13` FOREIGN KEY (`line_id`) REFERENCES `t_line` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKFD8AB720A8E350C1` FOREIGN KEY (`bus_id`) REFERENCES `t_bus` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKFD8AB720B7250053` FOREIGN KEY (`driver_id`) REFERENCES `t_driver` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_line_bus
-- ----------------------------
INSERT INTO `t_line_bus` VALUES (1, '8:00', '12:00', 1, 1, 3);

-- ----------------------------
-- Table structure for t_line_station
-- ----------------------------
DROP TABLE IF EXISTS `t_line_station`;
CREATE TABLE `t_line_station`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staindex` int(11) NOT NULL,
  `line_id` int(11) NULL DEFAULT NULL,
  `station_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FKBDE7E9D420A108C1`(`station_id`) USING BTREE,
  INDEX `FKBDE7E9D46FCB6F13`(`line_id`) USING BTREE,
  CONSTRAINT `FKBDE7E9D420A108C1` FOREIGN KEY (`station_id`) REFERENCES `t_station` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKBDE7E9D46FCB6F13` FOREIGN KEY (`line_id`) REFERENCES `t_line` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_line_station
-- ----------------------------
INSERT INTO `t_line_station` VALUES (20, 1, 3, 1);
INSERT INTO `t_line_station` VALUES (21, 2, 3, 11);
INSERT INTO `t_line_station` VALUES (22, 3, 3, 12);
INSERT INTO `t_line_station` VALUES (23, 4, 3, 13);
INSERT INTO `t_line_station` VALUES (24, 5, 3, 14);
INSERT INTO `t_line_station` VALUES (25, 6, 3, 15);
INSERT INTO `t_line_station` VALUES (26, 7, 3, 19);
INSERT INTO `t_line_station` VALUES (27, 1, 4, 1);
INSERT INTO `t_line_station` VALUES (28, 2, 4, 12);
INSERT INTO `t_line_station` VALUES (29, 3, 4, 13);
INSERT INTO `t_line_station` VALUES (30, 4, 4, 15);
INSERT INTO `t_line_station` VALUES (31, 5, 4, 8);
INSERT INTO `t_line_station` VALUES (32, 6, 4, 10);
INSERT INTO `t_line_station` VALUES (33, 7, 4, 6);
INSERT INTO `t_line_station` VALUES (34, 8, 4, 5);
INSERT INTO `t_line_station` VALUES (35, 1, 5, 11);
INSERT INTO `t_line_station` VALUES (36, 2, 5, 13);
INSERT INTO `t_line_station` VALUES (37, 3, 5, 14);
INSERT INTO `t_line_station` VALUES (38, 4, 5, 15);
INSERT INTO `t_line_station` VALUES (39, 5, 5, 19);
INSERT INTO `t_line_station` VALUES (40, 6, 5, 8);
INSERT INTO `t_line_station` VALUES (41, 7, 5, 10);
INSERT INTO `t_line_station` VALUES (42, 1, 6, 1);
INSERT INTO `t_line_station` VALUES (43, 2, 6, 11);
INSERT INTO `t_line_station` VALUES (44, 3, 6, 12);
INSERT INTO `t_line_station` VALUES (45, 4, 6, 13);
INSERT INTO `t_line_station` VALUES (46, 5, 6, 16);

-- ----------------------------
-- Table structure for t_line_station_distance
-- ----------------------------
DROP TABLE IF EXISTS `t_line_station_distance`;
CREATE TABLE `t_line_station_distance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dlen` double NOT NULL,
  `line_id` int(11) NULL DEFAULT NULL,
  `station1_id` int(11) NULL DEFAULT NULL,
  `station2_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FK3156A20070780837`(`station2_id`) USING BTREE,
  INDEX `FK3156A200707793D8`(`station1_id`) USING BTREE,
  INDEX `FK3156A2006FCB6F13`(`line_id`) USING BTREE,
  CONSTRAINT `FK3156A2006FCB6F13` FOREIGN KEY (`line_id`) REFERENCES `t_line` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK3156A200707793D8` FOREIGN KEY (`station1_id`) REFERENCES `t_station` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK3156A20070780837` FOREIGN KEY (`station2_id`) REFERENCES `t_station` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_line_station_distance
-- ----------------------------
INSERT INTO `t_line_station_distance` VALUES (7, 680, 3, 1, 11);
INSERT INTO `t_line_station_distance` VALUES (8, 574, 3, 11, 12);
INSERT INTO `t_line_station_distance` VALUES (9, 588, 3, 12, 13);
INSERT INTO `t_line_station_distance` VALUES (10, 987, 3, 13, 14);
INSERT INTO `t_line_station_distance` VALUES (11, 547, 3, 14, 15);
INSERT INTO `t_line_station_distance` VALUES (12, 257, 3, 15, 19);
INSERT INTO `t_line_station_distance` VALUES (13, 580, 4, 1, 12);
INSERT INTO `t_line_station_distance` VALUES (14, 588, 4, 12, 13);
INSERT INTO `t_line_station_distance` VALUES (15, 574, 4, 13, 15);
INSERT INTO `t_line_station_distance` VALUES (16, 965, 4, 15, 8);
INSERT INTO `t_line_station_distance` VALUES (17, 124, 4, 8, 10);
INSERT INTO `t_line_station_distance` VALUES (18, 568, 4, 10, 6);
INSERT INTO `t_line_station_distance` VALUES (19, 756, 4, 6, 5);
INSERT INTO `t_line_station_distance` VALUES (20, 582, 5, 11, 13);
INSERT INTO `t_line_station_distance` VALUES (21, 555, 5, 13, 14);
INSERT INTO `t_line_station_distance` VALUES (22, 222, 5, 14, 15);
INSERT INTO `t_line_station_distance` VALUES (23, 444, 5, 15, 19);
INSERT INTO `t_line_station_distance` VALUES (24, 666, 5, 19, 8);
INSERT INTO `t_line_station_distance` VALUES (25, 888, 5, 8, 10);
INSERT INTO `t_line_station_distance` VALUES (26, 577, 6, 1, 11);
INSERT INTO `t_line_station_distance` VALUES (27, 777, 6, 11, 12);
INSERT INTO `t_line_station_distance` VALUES (28, 857, 6, 12, 13);
INSERT INTO `t_line_station_distance` VALUES (29, 1000, 6, 13, 16);

-- ----------------------------
-- Table structure for t_lost_property
-- ----------------------------
DROP TABLE IF EXISTS `t_lost_property`;
CREATE TABLE `t_lost_property`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `checi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `findDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lingDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wuping` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `imgFile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES (1, '2019-03-04 13:48:34', '<p class=\"MsoNormal\" style=\"margin-left:0.0000pt;text-indent:0.0000pt;text-align:justify;\" align=\"justify\">\r\n	 自2018年2月10日起，6路/96路、9路/99路公交线恢复原线路行驶，其中6路/96路公交线不再停靠海宁宾馆公交站，停靠华联、东方商厦、京昇堂大药房公交站点，9路99路公交线不再停靠建设银行、京昇堂大药房、东方商厦公交站，停靠紫微小学、中医院公交站点。\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	                                                                                          \r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:110.0000pt;\">\r\n	望相互转告！\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	 \r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-align:center;\" align=\"center\">\r\n	                                        海宁市大元公交有限责任公司 \r\n</p>', '1551678509564.jpg', ' 自2018年2月10日起，6路/96路、9路/99路公交线恢复原线路行驶，其中6路/96路公交线不再停靠海宁宾馆公交站，停靠华联、东方商厦、京昇堂大药房公交站点，9路99路公交线不再停靠建设银行、京昇堂大药房、东方商厦公交站，停靠紫微小学、中医院公交站点。', '96路', '自2018年2月10日起，6路/96路、9路/99路公交线恢复原线路行驶', '公交线路');

-- ----------------------------
-- Table structure for t_simple_user
-- ----------------------------
DROP TABLE IF EXISTS `t_simple_user`;
CREATE TABLE `t_simple_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FK99BA02ED336F8FCA`(`userID`) USING BTREE,
  CONSTRAINT `FK99BA02ED336F8FCA` FOREIGN KEY (`userID`) REFERENCES `t_user` (`userID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_simple_user
-- ----------------------------
INSERT INTO `t_simple_user` VALUES (1, 2);

-- ----------------------------
-- Table structure for t_station
-- ----------------------------
DROP TABLE IF EXISTS `t_station`;
CREATE TABLE `t_station`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_station
-- ----------------------------
INSERT INTO `t_station` VALUES (1, '站点1');
INSERT INTO `t_station` VALUES (11, '站点10');
INSERT INTO `t_station` VALUES (12, '站点11');
INSERT INTO `t_station` VALUES (13, '站点12');
INSERT INTO `t_station` VALUES (14, '站点13');
INSERT INTO `t_station` VALUES (15, '站点14');
INSERT INTO `t_station` VALUES (16, '站点15');
INSERT INTO `t_station` VALUES (17, '站点16');
INSERT INTO `t_station` VALUES (18, '站点17');
INSERT INTO `t_station` VALUES (19, '站点18');
INSERT INTO `t_station` VALUES (20, '站点19');
INSERT INTO `t_station` VALUES (2, '站点2');
INSERT INTO `t_station` VALUES (21, '站点20');
INSERT INTO `t_station` VALUES (3, '站点3');
INSERT INTO `t_station` VALUES (4, '站点4');
INSERT INTO `t_station` VALUES (5, '站点5');
INSERT INTO `t_station` VALUES (6, '站点6');
INSERT INTO `t_station` VALUES (7, '站点7');
INSERT INTO `t_station` VALUES (8, '站点8');
INSERT INTO `t_station` VALUES (10, '站点9');

-- ----------------------------
-- Table structure for t_sysuser
-- ----------------------------
DROP TABLE IF EXISTS `t_sysuser`;
CREATE TABLE `t_sysuser`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FK54046F4D336F8FCA`(`userID`) USING BTREE,
  CONSTRAINT `FK54046F4D336F8FCA` FOREIGN KEY (`userID`) REFERENCES `t_user` (`userID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sysuser
-- ----------------------------
INSERT INTO `t_sysuser` VALUES (1, 1);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userAddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userBirth` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userEmail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userGender` int(11) NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userPassword` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE,
  UNIQUE INDEX `userID`(`userID`) USING BTREE,
  UNIQUE INDEX `uname`(`uname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', NULL, NULL, NULL, 1, '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', NULL);
INSERT INTO `t_user` VALUES (2, 'user1', '', NULL, '', 1, '李四', 'E10ADC3949BA59ABBE56E057F20F883E', '');

-- ----------------------------
-- Table structure for t_usernote
-- ----------------------------
DROP TABLE IF EXISTS `t_usernote`;
CREATE TABLE `t_usernote`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `recontent` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FKA6D631A8C08441C5`(`user_id`) USING BTREE,
  CONSTRAINT `FKA6D631A8C08441C5` FOREIGN KEY (`user_id`) REFERENCES `t_simple_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
