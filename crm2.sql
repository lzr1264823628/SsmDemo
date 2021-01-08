/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : crm2

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 08/01/2021 11:09:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for SPRING_SESSION
-- ----------------------------
DROP TABLE IF EXISTS `SPRING_SESSION`;
CREATE TABLE `SPRING_SESSION`  (
  `PRIMARY_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SESSION_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`) USING BTREE,
  UNIQUE INDEX `SPRING_SESSION_IX1`(`SESSION_ID`) USING BTREE,
  INDEX `SPRING_SESSION_IX2`(`EXPIRY_TIME`) USING BTREE,
  INDEX `SPRING_SESSION_IX3`(`PRINCIPAL_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of SPRING_SESSION
-- ----------------------------
INSERT INTO `SPRING_SESSION` VALUES ('9cb6210c-6dbd-4f11-aec1-78eadf0b7d3d', '26fc81a4-6c0a-433e-9418-0f5e39dda9d6', 1610075087593, 1610075274998, 1800, 1610077074998, NULL);

-- ----------------------------
-- Table structure for SPRING_SESSION_ATTRIBUTES
-- ----------------------------
DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES`  (
  `SESSION_PRIMARY_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`) USING BTREE,
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of SPRING_SESSION_ATTRIBUTES
-- ----------------------------
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('9cb6210c-6dbd-4f11-aec1-78eadf0b7d3d', 'uId', 0xACED0005737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000004);

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student`  (
  `sId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `stuNum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `cLass` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dorm` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imageUrl` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sId`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `stuNum`(`stuNum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of Student
-- ----------------------------
INSERT INTO `Student` VALUES (1, '180804201', '艾培彩', 0, '1808042', '3419', '软件工程', '18375091211', NULL);
INSERT INTO `Student` VALUES (2, '180804202', '陈雪', 0, '1808042', '3419', '软件工程', '19962528629', NULL);
INSERT INTO `Student` VALUES (3, '180804203', '顾正龙', 1, '1808042', '10408', '软件工程', '19852820302', NULL);
INSERT INTO `Student` VALUES (4, '180804204', '何鹏', 1, '1808042', '10408', '软件工程', '18212100265', NULL);
INSERT INTO `Student` VALUES (5, '180804205', '胡迪', 0, '1808042', '3419', '软件工程', '19852851168', NULL);
INSERT INTO `Student` VALUES (6, '180804206', '黄嘉澎', 1, '1808042', '10408', '软件工程', '18977484736', NULL);
INSERT INTO `Student` VALUES (7, '180804207', '季浩楠', 1, '1808042', '10409', '软件工程', '17851674382', NULL);
INSERT INTO `Student` VALUES (8, '180804208', '贾亚敏', 0, '1808042', '3420', '软件工程', '19852851529', NULL);
INSERT INTO `Student` VALUES (9, '180804209', '李澳', 1, '1808042', '10409', '软件工程', '13659021787', NULL);
INSERT INTO `Student` VALUES (10, '180804210', '李宗瑞', 1, '1808042', '10409', '软件工程', '18118601328', NULL);
INSERT INTO `Student` VALUES (11, '180804211', '林帅', 1, '1808042', '10409', '软件工程', '17688081849', NULL);
INSERT INTO `Student` VALUES (12, '180804212', '刘霞清', 0, '1808042', '3420', '软件工程', '19852836588', NULL);
INSERT INTO `Student` VALUES (13, '180804213', '倪靖', 1, '1808042', '10409', '软件工程', '18952573568', NULL);
INSERT INTO `Student` VALUES (14, '180804214', '唐正坤', 1, '1808042', '10409', '软件工程', '15120194140', NULL);
INSERT INTO `Student` VALUES (15, '180804215', '王青鹏', 1, '1808042', '10410', '软件工程', '18362075035', NULL);
INSERT INTO `Student` VALUES (16, '180804216', '王晴', 0, '1808042', '3420', '软件工程', '19852898838', NULL);
INSERT INTO `Student` VALUES (17, '180804217', '吴沁', 1, '1808042', '10410', '软件工程', '13812560356', NULL);
INSERT INTO `Student` VALUES (18, '180804218', '徐欣然', 0, '1808042', '3420', '软件工程', '15996306229', NULL);
INSERT INTO `Student` VALUES (19, '180804219', '徐馨蕾', 0, '1808042', '3420', '软件工程', '19852832862', NULL);
INSERT INTO `Student` VALUES (20, '180804220', '刘承奕', 1, '1808042', '10410', '软件工程', '15261865566', NULL);
INSERT INTO `Student` VALUES (21, '180804221', '杨浩然', 1, '1808042', '10410', '软件工程', '19852852608', NULL);
INSERT INTO `Student` VALUES (22, '180804222', '杨信龙', 1, '1808042', '10410', '软件工程', '18726038964', NULL);
INSERT INTO `Student` VALUES (23, '180804223', '喻成', 1, '1808042', '10410', '软件工程', '13608722908', NULL);
INSERT INTO `Student` VALUES (24, '180804224', '张帆', 1, '1808042', '10411', '软件工程', '15617697996', NULL);
INSERT INTO `Student` VALUES (25, '180804225', '张奇', 1, '1808042', '10411', '软件工程', '19852821113', NULL);
INSERT INTO `Student` VALUES (26, '180804226', '张文杰', 1, '1808042', '10411', '软件工程', '19852856368', NULL);
INSERT INTO `Student` VALUES (27, '180804227', '赵科', 1, '1808042', '10411', '软件工程', '15996755619', NULL);
INSERT INTO `Student` VALUES (28, '180804228', '郑晓阳', 1, '1808042', '10411', '软件工程', '18118029538', NULL);
INSERT INTO `Student` VALUES (29, '180804229', '周云雪', 0, '1808042', '3420', '软件工程', '19852835231', NULL);
INSERT INTO `Student` VALUES (30, '180804230', '邹颀玮', 1, '1808042', '10411', '软件工程', '18016224588', NULL);
INSERT INTO `Student` VALUES (31, '180404114', '秦齐隆', 1, '1808042', '10532', '软件工程', '15850792674', NULL);
INSERT INTO `Student` VALUES (32, '180604120', '谭森', 1, '1808042', '10425', '软件工程', '18550610815', NULL);
INSERT INTO `Student` VALUES (33, '181102209', '黄峥远', 1, '1808042', '10616', '软件工程', '15195607128', NULL);
INSERT INTO `Student` VALUES (34, '181604101', '程昊', 1, '1808042', '10130', '软件工程', '18452609836', NULL);
INSERT INTO `Student` VALUES (35, '181604317', '谭嘉嘉', 0, '1808042', '3719', '软件工程', '13533318071', NULL);
INSERT INTO `Student` VALUES (36, '171501403', '都铭', 0, '1808042', '20617', '软件工程', '18816213951', NULL);
INSERT INTO `Student` VALUES (42, '180804210', 'test', 1, NULL, '10409', '软件工程', '12345678900', NULL);
INSERT INTO `Student` VALUES (43, '180804210', 'test', 1, NULL, '10409', '软件工程', '12345678900', NULL);
INSERT INTO `Student` VALUES (44, '180804210', 'test', 1, NULL, '10409', '软件工程', '12345678900', NULL);
INSERT INTO `Student` VALUES (45, '180804210', 'test', 0, '1808042', '10409', '软件工程', '12345678900', NULL);
INSERT INTO `Student` VALUES (46, '180804210', 'test', 1, '1808042', '10409', '软件工程', '12345678900', NULL);

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`  (
  `uId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uId`) USING BTREE,
  UNIQUE INDEX `username_2`(`username`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO `User` VALUES (4, 'admin', '18138372fad4b94533cd4881f03dc6c69296dd897234e0cee83f727e2e6b1f63', NULL);

SET FOREIGN_KEY_CHECKS = 1;
