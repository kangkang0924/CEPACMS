/*Community Epidemic Prevention And Control Management System*/
/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : localhost:3306
 Source Schema         : cepacms

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 04/05/2022 20:58:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
--1.用户类表account
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `accountID` INT(11) NOT NULL AUTO_INCREMENT,
  `accountName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accountPassword` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accountStatus` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accountRole` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`AccountID`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1,'cxh', '123', '0', '1');
INSERT INTO `account` VALUES (2,'admin', '123', '0', '1');
INSERT INTO `account` VALUES (3,'superadmin', 'admin', '0', '1');
INSERT INTO `account` VALUES (4,'chichi', '123', '0', '0');
INSERT INTO `account` VALUES (5,'chii', '123', '0', '0');
INSERT INTO `account` VALUES (6,'chchi', '123', '0', '0');
INSERT INTO `account` VALUES (7,'ccc', '123', '1', '1');
INSERT INTO `account` VALUES (8,'zzz', '123', '1', '0');
INSERT INTO `account` VALUES (9,'xxx', '123', '1', '2');
INSERT INTO `account` VALUES (10,'hhh', '123', '2', '0');
INSERT INTO `account` VALUES (11,'gg', '123', '2', '2');

-- ----------------------------
-- Table structure for community
-- ----------------------------
--2.社区表community
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `cId` INT(11) NOT NULL AUTO_INCREMENT,
  `cName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cProvince` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cCity` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cStreet` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cTel` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cId`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES (1,'天虹花园', '辽宁省', '鞍山市', '云门街道', '13965486857');
INSERT INTO `community` VALUES (2,'天宇中兴', '辽宁省', '抚顺市', '李石街道', '15035486517');
INSERT INTO `community` VALUES (3,'绿地剑桥', '辽宁省', '抚顺市', '绥阳街', '15235686517');
INSERT INTO `community` VALUES (4,'南波湾', '辽宁省', '抚顺市', '李石街道', '15033486617');
INSERT INTO `community` VALUES (5,'隆顺花园', '辽宁省', '抚顺市', '临江街', '15035486215');
INSERT INTO `community` VALUES (6,'江畔新苑', '辽宁省', '抚顺市', '恒大广场', '15035482517');
INSERT INTO `community` VALUES (7,'恒大华府', '辽宁省', '抚顺市', '临江街', '15065432517');
INSERT INTO `community` VALUES (8,'东华园', '辽宁省', '抚顺市', '新城东', '15045482517');
INSERT INTO `community` VALUES (9,'天虹花园', '辽宁省', '抚顺市', '裕城街', '15035483317');
INSERT INTO `community` VALUES (10,'格林小镇', '辽宁省', '抚顺市', '葛布北街', '15065482517');
INSERT INTO `community` VALUES (11,'永安小区', '辽宁省', '抚顺市', '宁远街', '15037562517');
--3.住户表member

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `mId` INT(11) NOT NULL AUTO_INCREMENT,
  `mName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mVaccine` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mTel` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mSex` CHAR(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mAge` INT(11) NULL DEFAULT NULL,
  `mHouseNum` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mWorkUnit` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mCarNum` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mIsHousehold` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `mcId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`mId`) USING BTREE,
  INDEX `1`(`mcId`) USING BTREE,
  CONSTRAINT `m_fork` FOREIGN KEY (`mcId`) REFERENCES `community` (`cId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = INNODB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '陈煊赫', '3', '13568989569', '1', 20, '54', '沈阳工学院', '辽A4R379', '1','1');
INSERT INTO `member` VALUES (2, '高宏鹏', '3', '13568989569', '1', 20, '12', '沈阳工学院', '辽A4R879', '1','2');
INSERT INTO `member` VALUES (3, '孙嘉伟', '3', '13568989569', '1', 21, '17', '沈阳工学院', '辽B4R079', '1','3');
INSERT INTO `member` VALUES (4, '田春豪', '3', '13568989569', '1', 21, '12', '沈阳工学院', '辽A4R779', '1','4');
INSERT INTO `member` VALUES (5, '韩德康', '3', '13568989569', '1', 21, '18', '沈阳工学院', '辽C4R379', '0','5');
INSERT INTO `member` VALUES (6, '兰皓宇', '3', '13568989569', '1', 20, '13', '沈阳工学院', '辽D3B379', '0','6');
INSERT INTO `member` VALUES (7, '夏斌'  , '3', '13568989569', '1', 20, '16', '沈阳工学院', '辽A6H379', '1','7');
INSERT INTO `member` VALUES (8, '柴宛宁', '3', '13568989569', '0', 20, '16', '沈阳工学院', '辽A46H79', '0','8');
INSERT INTO `member` VALUES (9, '石津榕', '3', '13568989569', '1', 20, '16', '沈阳工学院', '辽A48J79', '1','9');
INSERT INTO `member` VALUES (10,'喻刘兢', '3', '13568989569', '0', 20, '16', '沈阳工学院', '辽A48F79', '1','10');
--4.出入记录表record
-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `rId` INT(11) NOT NULL AUTO_INCREMENT,
  `rmId` INT(11) NULL DEFAULT NULL,
  `rcId` INT(11) NULL DEFAULT NULL,
  `rIsNucleicAcidTest` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `rIsOutCity` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `rIsFromHB` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `rIsHousehold` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `rNowTime` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`rId`) USING BTREE,
  INDEX `rmId`(`rmId`) USING BTREE,
  INDEX `rcId`(`rcId`) USING BTREE,
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`rmId`) REFERENCES `member` (`mId`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `record_ibfk_2` FOREIGN KEY (`rcId`) REFERENCES `community` (`cId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = INNODB AUTO_INCREMENT = 1010 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO record VALUES (1, 1, 1, '1', '1', '0', '0', '2021-07-05 00:00:00');
INSERT INTO record VALUES (2, 2, 2, '1', '1', '0', '1', '2021-07-07 00:00:00');
INSERT INTO record VALUES (3, 3, 3, '1', '1', '0', '0', '2021-07-05 00:00:00');
INSERT INTO `record` VALUES (4, 4, 4, '1', '0', '1', '1', '2021-07-05 00:00:00');
INSERT INTO `record` VALUES (5, 5, 5, '1', '0', '0', '1', '2021-07-07 00:00:00');
INSERT INTO `record` VALUES (6, 6, 6, '1', '0', '0', '0', '2021-07-05 00:00:00');
--5.文件上传表upload
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload`  (
  `uploadID` INT(11) NOT NULL AUTO_INCREMENT,
  `uploadName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `umId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`uploadID`) USING BTREE,
  INDEX `umId`(`umId`) USING BTREE,
  CONSTRAINT `upload_ibfk` FOREIGN KEY (`umId`) REFERENCES `member` (`mId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = INNODB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of upload
-- ----------------------------
INSERT INTO upload VALUES (1, '陈煊赫核酸检测表', 1);
INSERT INTO upload VALUES (2, '高宏鹏核酸检测表', 2);
INSERT INTO upload VALUES (3, '孙嘉伟核酸检测表', 3);
--6.文件上传表download
DROP TABLE IF EXISTS `download`;
CREATE TABLE `download`  (
  `downloadID` INT(11) NOT NULL AUTO_INCREMENT,
  `downloadName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dmId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`downloadID`) USING BTREE,
  INDEX `dmId`(`dmId`) USING BTREE,
  CONSTRAINT `download_ibfk` FOREIGN KEY (`dmId`) REFERENCES `member` (`mId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = INNODB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of download
-- ----------------------------
INSERT INTO download VALUES (1, '第三针疫苗通知', 1);
INSERT INTO download VALUES (2, '关于最近封区通知', 2);
INSERT INTO download VALUES (3, '核酸检测通知', 3);
SET FOREIGN_KEY_CHECKS = 1;