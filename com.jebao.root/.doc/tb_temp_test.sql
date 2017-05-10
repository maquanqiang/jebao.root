/*
Navicat MySQL Data Transfer

Source Server         : jebao
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : debug.db

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-10-20 17:31:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 用于测试Mybatis与数据库的联通
-- Table structure for tb_temp_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_temp_test`;
CREATE TABLE `tb_temp_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
