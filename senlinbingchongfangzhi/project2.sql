/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : project2

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-08-06 16:54:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for f_student
-- ----------------------------
DROP TABLE IF EXISTS `f_student`;
CREATE TABLE `f_student` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_class_id` int(11) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `f_gender` varchar(255) DEFAULT NULL,
  `f_birth` date DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of f_student
-- ----------------------------
INSERT INTO `f_student` VALUES ('1', '1', '李好', '男', '2018-07-19');
INSERT INTO `f_student` VALUES ('2', '1', '陈美', '女', '2018-07-19');
INSERT INTO `f_student` VALUES ('3', '1', '陈成', '男', '2018-07-13');
INSERT INTO `f_student` VALUES ('4', '2', '陈是', '女', '2018-07-03');
INSERT INTO `f_student` VALUES ('5', '2', '刘', '男', '2018-07-12');

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_species` varchar(255) DEFAULT NULL,
  `f_great` varchar(255) DEFAULT NULL,
  `f_gentle` varchar(255) DEFAULT NULL,
  `fk_class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `FK_Reference_1` (`fk_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_area
-- ----------------------------
INSERT INTO `t_area` VALUES ('1', '成都', '松树', '松林', '林地', '1');
INSERT INTO `t_area` VALUES ('2', '德阳', '桃树', '桃林', '山地', '2');
INSERT INTO `t_area` VALUES ('3', '绵阳', '苹果树', '梨园', '沼泽地', '3');

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_principal` varchar(255) DEFAULT NULL,
  `f_phoneNum` varchar(255) DEFAULT NULL,
  `f_num` int(11) DEFAULT NULL,
  `f_date` date DEFAULT NULL,
  `fk_area_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `FK_Reference_2` (`fk_area_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`fk_area_id`) REFERENCES `t_area` (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('1', '成都一班1112', '还是得开回家', '123456', '10', '2018-06-05', '1');
INSERT INTO `t_class` VALUES ('2', '成都二班', '的感觉看到', '648654862486', '8', '2018-06-07', '1');
INSERT INTO `t_class` VALUES ('3', '德阳二班', '反过来看风景可能会', '5659789789', '5', '2018-06-16', '2');
INSERT INTO `t_class` VALUES ('4', '德阳一班', '年龄电话库管理', '4864862486624', '6', '2018-06-22', '2');
INSERT INTO `t_class` VALUES ('5', '绵阳一班', '大会上更好', '123', '5', '2018-06-19', '3');
INSERT INTO `t_class` VALUES ('6', '绵阳二班', '是电饭锅和', '324856', '7', '2018-06-05', '3');
INSERT INTO `t_class` VALUES ('7', '成都3', '陈', '123456', '15', null, '1');
INSERT INTO `t_class` VALUES ('8', '成都3', '陈', '123456', '15', null, '1');
INSERT INTO `t_class` VALUES ('9', '牛逼', '123', '123', '122', null, '1');
INSERT INTO `t_class` VALUES ('10', '牛逼', '123', '123', '122', null, '1');
INSERT INTO `t_class` VALUES ('11', '重庆', '杨', '123', '10', '2018-07-02', '1');
INSERT INTO `t_class` VALUES ('12', '大秦林', '利好', '13896782361', '20', '2018-07-03', '1');

-- ----------------------------
-- Table structure for t_class1
-- ----------------------------
DROP TABLE IF EXISTS `t_class1`;
CREATE TABLE `t_class1` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_date` date DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class1
-- ----------------------------
INSERT INTO `t_class1` VALUES ('1', 'j111', '2018-07-04');
INSERT INTO `t_class1` VALUES ('2', 'j112', '2018-07-11');

-- ----------------------------
-- Table structure for t_consultation
-- ----------------------------
DROP TABLE IF EXISTS `t_consultation`;
CREATE TABLE `t_consultation` (
  `pk_id` int(11) NOT NULL,
  `f_content` varchar(255) DEFAULT NULL,
  `f_result` varchar(255) DEFAULT NULL,
  `fk_event_id` int(11) DEFAULT NULL,
  `f_date` date DEFAULT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `FK_Reference_9` (`fk_event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_consultation
-- ----------------------------
INSERT INTO `t_consultation` VALUES ('1', '易中天，叶挺，朱德', '放大量老鼠药，定期清理', '1', '2017-07-01');
INSERT INTO `t_consultation` VALUES ('2', '易中天，叶挺，朱德', '喷洒杀虫剂，黑光诱飞蛾', '2', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('3', '易中天，叶挺，朱德', '喷洒多菌灵', '2', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('4', '易中天，叶挺，朱德', '喷洒威猛先生', '3', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('5', '易中天，叶挺，朱德', '大量安置大符文', '3', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('6', '易中天，叶挺，朱德', '安装超级离子炮', '1', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('7', '易中天，叶挺，朱德', '派驻战机', '4', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('8', '易中天，叶挺，朱德', '燃烧弹轰炸', '4', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('9', '易中天，叶挺，朱德', '喷洒白磷弹', '5', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('10', '易中天，叶挺，朱德', '轨道炮轰炸', '5', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('11', '易中天，叶挺，朱德', '155MM火炮覆盖', '6', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('12', '易中天，叶挺，朱德', '舰炮攻击', '7', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('13', '易中天，叶挺，朱德', '使用超音速反舰导弹', '7', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('14', '易中天，叶挺，朱德', '必须使用核武器！', '8', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('15', '易中天，叶挺，朱德', '使用纳米机器人渗透', '9', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('16', '易中天，叶挺，朱德', '安装超级离子炮', '10', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('17', '易中天，叶挺，朱德', '派驻战机', '11', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('18', '易中天，叶挺，朱德', '燃烧弹轰炸', '11', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('19', '易中天，叶挺，朱德', '喷洒白磷弹', '12', '2018-06-27');
INSERT INTO `t_consultation` VALUES ('20', '易中天，叶挺，朱德', '轨道炮轰炸', '12', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('21', '易中天，叶挺，朱德', '155MM火炮覆盖', '13', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('22', '易中天，叶挺，朱德', '舰炮攻击', '13', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('23', '易中天，叶挺，朱德', '喷洒杀虫剂，黑光诱飞蛾', '14', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('24', '易中天，叶挺，朱德', '喷洒多菌灵', '14', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('25', '易中天，叶挺，朱德', '喷洒威猛先生', '15', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('26', '易中天，叶挺，朱德', '大量安置大符文', '15', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('27', '易中天，叶挺，朱德', '安装超级离子炮', '16', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('28', '易中天，叶挺，朱德', '使用纳米机器人渗透', '16', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('29', '易中天，叶挺，朱德', '安装超级离子炮', '17', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('30', '易中天，叶挺，朱德', '派驻战机', '17', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('31', '易中天，叶挺，朱德', '燃烧弹轰炸', '18', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('32', '易中天，叶挺，朱德', '喷洒白磷弹', '18', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('33', '易中天，叶挺，朱德', '轨道炮轰炸', '19', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('34', '易中天，叶挺，朱德', '必须使用核武器！', '19', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('35', '易中天，叶挺，朱德', '使用纳米机器人渗透', '20', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('36', '易中天，叶挺，朱德', '安装超级离子炮', '20', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('37', '易中天，叶挺，朱德', '派驻战机', '21', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('38', '易中天，叶挺，朱德', '燃烧弹轰炸', '21', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('39', '易中天，叶挺，朱德', '喷洒白磷弹', '22', '2018-06-04');
INSERT INTO `t_consultation` VALUES ('40', '易中天，叶挺，朱德', '轨道炮轰炸', '22', '2018-06-04');

-- ----------------------------
-- Table structure for t_disease
-- ----------------------------
DROP TABLE IF EXISTS `t_disease`;
CREATE TABLE `t_disease` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_pathogen` varchar(255) DEFAULT NULL,
  `f_sysmptom` varchar(255) DEFAULT NULL,
  `f_law` varchar(255) DEFAULT NULL,
  `f_imgPath` varchar(255) DEFAULT NULL,
  `f_prevention` varchar(255) DEFAULT NULL,
  `f_harm` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_disease
-- ----------------------------
INSERT INTO `t_disease` VALUES ('1', '玉米螟', '大肠杆菌', '病例变白', '春秋发病较多', null, '清除病叶', '影响玉米光合作用');
INSERT INTO `t_disease` VALUES ('2', '蚜虫', '大肠杆菌', '病例变白', '春秋发病较多', '', '清除病叶', '影响玉米光合作用');
INSERT INTO `t_disease` VALUES ('3', '白粉虱', '大肠杆菌', '病例变白', '春秋发病较多', '', '清除病叶', '影响玉米光合作用');

-- ----------------------------
-- Table structure for t_event
-- ----------------------------
DROP TABLE IF EXISTS `t_event`;
CREATE TABLE `t_event` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_imgPath` varchar(255) DEFAULT NULL,
  `f_date` varchar(255) DEFAULT NULL,
  `f_type` varchar(255) DEFAULT NULL,
  `f_phase` varchar(255) DEFAULT NULL,
  `f_describe` varchar(255) DEFAULT NULL,
  `f_findPath` varchar(255) DEFAULT NULL,
  `f_areaName` varchar(255) DEFAULT NULL,
  `f_lose` varchar(255) DEFAULT NULL,
  `f_affectArea` varchar(255) DEFAULT NULL,
  `f_plan` varchar(255) DEFAULT NULL,
  `f_opinon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `FK_Reference_4` (`f_areaName`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_event
-- ----------------------------
INSERT INTO `t_event` VALUES ('1', '东南亚鼠害事件', '1.png', '2018-06-20', '鼠害', '无法解决，申请专家会商', null, '公众发现', '成都', '不是太大', '2562亩', '用药', '埋好死鼠');
INSERT INTO `t_event` VALUES ('2', '东南亚鼠害事件', '1.png', '2018-06-20', '鼠害', '无法解决，申请专家会商', '死了好多老鼠', '公众发现', '成都', '不是太大', '2562亩', '用药', '埋好死鼠');
INSERT INTO `t_event` VALUES ('3', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '无法解决，申请专家会商', '菜没了', '小班查询', '绵阳', '小', '555亩', '打药', '喂猪');
INSERT INTO `t_event` VALUES ('4', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '无法解决，申请专家会商', '菜没了', '小班查询', '绵阳', '小', '555亩', '打药', '喂猪');
INSERT INTO `t_event` VALUES ('5', '太平病害事件', '3.png', '2018-06-13', '病害', '防治中', null, '公众发现', '绵阳', '小', '555亩', '打打捞', '打打捞');
INSERT INTO `t_event` VALUES ('6', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '无法解决，申请专家会商', '菜没了', '上级部门通报', '绵阳', '小', '555亩', '捕捞', '捕捞');
INSERT INTO `t_event` VALUES ('7', '东南亚鼠害事件', '1.png', '2018-06-20', '鼠害', '防止中', '死了好多老鼠', '公众发现', '成都', '不是太大', '2562亩', '用药', '埋好死鼠');
INSERT INTO `t_event` VALUES ('8', '东南亚鼠害事件', '1.png', '2018-06-20', '鼠害', '以得到操控之', '死了好多老鼠', '公众发现', '成都', '不是太大', '2562亩', '用药', '埋好死鼠');
INSERT INTO `t_event` VALUES ('9', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '无法解决，申请商家会商', '菜没了', '小班查询', '德阳', '小', '555亩', '打药', '喂猪');
INSERT INTO `t_event` VALUES ('10', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '以得到操控之', '菜没了', '小班查询', '德阳', '小', '555亩', '打药', '喂猪');
INSERT INTO `t_event` VALUES ('11', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '无法解决，申请商家会商', '菜没了', '小班查询', '德阳', '小', '555亩', '打药', '喂猪');
INSERT INTO `t_event` VALUES ('12', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '以得到操控之', '菜没了', '小班查询', '德阳', '小', '555亩', '打药', '喂猪');
INSERT INTO `t_event` VALUES ('13', '太平病害事件', '3.png', '2018-06-13', '病害', '防止中', '鱼没了', '公众发现', '德阳', '小', '555亩', '打打捞', '打打捞');
INSERT INTO `t_event` VALUES ('14', '田坝坝虫害事件', '3.png', '2018-06-13', '虫害', '以得到操控之', '菜没了', '上级部门通报', '德阳', '小', '555亩', '捕捞', '捕捞');
INSERT INTO `t_event` VALUES ('15', '东南亚鼠害事件', '1.png', '2018-06-20', '鼠害', '防止中', '死了好多老鼠', '公众发现', '绵阳', '不是太大', '2562亩', '用药', '埋好死鼠');
INSERT INTO `t_event` VALUES ('30', '大秦林侵略事件(String)', 'desk.jpg(String)', null, '鼠害(String)', '已经得到控制(String)', '123(String)', '公众发现(String)', '成都(String)', '2000(String)', '200(String)', '没有(String)', null);
INSERT INTO `t_event` VALUES ('31', '大秦林侵略事件(String)', 'desk.jpg(String)', '2018-06-18', '鼠害(String)', '已经得到控制(String)', '123(String)', '公众发现(String)', '成都(String)', '2000(String)', '200(String)', '没有(String)', null);
INSERT INTO `t_event` VALUES ('34', '大秦林侵略事件', 'desk.jpg', '2018-06-30', '鼠害', '防治中', '123', '公众发现', '成都', '20', '200', '没有', null);
INSERT INTO `t_event` VALUES ('35', '一二三', 'desk.jpg', '2018-07-07', '鼠害', '防治中', '阿萨德', '公众发现', '成都', '2000', '2000', '阿萨德', null);
INSERT INTO `t_event` VALUES ('36', '你好成都', 'desk.jpg', '2018-07-11', '鼠害', '防治中', '你好', '公众发现', '成都', '121312', '20000', '你好', null);
INSERT INTO `t_event` VALUES ('37', '阿萨德', 'desk.jpg', '2018-07-13', '鼠害', '防治中', null, '小班巡查发现', '成都', '200', '500', '按时', null);

-- ----------------------------
-- Table structure for t_expert
-- ----------------------------
DROP TABLE IF EXISTS `t_expert`;
CREATE TABLE `t_expert` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_gender` varchar(255) DEFAULT NULL,
  `f_birthday` varchar(255) DEFAULT NULL,
  `f_imgPath` varchar(255) DEFAULT NULL,
  `f_special` varchar(255) DEFAULT NULL,
  `f_position` varchar(255) DEFAULT NULL,
  `f_phoneNum` varchar(255) DEFAULT NULL,
  `f_workPlace` varchar(255) DEFAULT NULL,
  `f_address` varchar(255) DEFAULT NULL,
  `f_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_expert
-- ----------------------------
INSERT INTO `t_expert` VALUES ('1', '骏喆', '男', '1983-07-13', null, '虫害防治', '教授', '\r\n18328551691', '成都市灾害防治中心', '天仙桥南路1-附1-2号附近', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('2', '锦灵', '女', '1991-09-29', null, '虫害防治', '研究员', '18328551703', '绵阳市灾害防治中心', '四川省成都市锦江区水碾河路南三街48号水河名居', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('3', '高大山', '男', '1989-07-19', null, '病害防治', '副教授', '18328551756', '成都市灾害防治中心', '\r\n成都市区域地址\r\n\r\n\r\n成都市锦江区区域医疗联合体', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('4', '马宏宇', '男', '1979-01-23', null, '鼠害防治', '教授', '18328551623', '成都市灾害防治中心', '\r\n成都市区域地址\r\n\r\n\r\n成都市武候区第三人民医院区域联盟医院', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('5', '冯兴国', '男', '1989-07-19', null, '鼠害防治', '副教授', '13980572592', '绵阳市灾害防治中心', '\r\n成都市区域地址\r\n\r\n\r\n四川省成都市区域医疗联合体四川省人民医院锦江区牛市口社区卫生服务中心', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('6', '李文信', '男', '1979-01-23', null, '病害防治', '教授', '13880619677', '成都市灾害防治中心', '凯德广场办公区域', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('7', '李宗仁', '男', '1999-01-23', null, '病害防治', '研究员', '13881777065', '南充市灾害防治中心', '民航成都区域管制中心', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('8', '孙天民', '男', '1989-07-19', null, '虫害防治', '副教授', '13980521528', '南充市灾害防治中心', '\r\n成都市区域地址\r\n\r\n\r\n汶川县地税局驻成都地区便民服务处', 'TT@qq.com');
INSERT INTO `t_expert` VALUES ('9', '孙振中', '男', '1979-01-23', null, '鼠害防治', '教授', '18328551623', '南充市灾害防治中心', '四川省南充市嘉陵区耀目路', 'TT@qq.com');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `pk_id` int(11) NOT NULL,
  `f_log` varchar(255) DEFAULT NULL,
  `f_date` date DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', '和奇偶的法规及偶尔他儿童机个哦看见了', '2018-07-05');
INSERT INTO `t_log` VALUES ('2', '的发给风帆股份电饭锅和杜兰特御华万里口语呕吐', '2018-06-08');
INSERT INTO `t_log` VALUES ('3', 'rtyrtyrtjklrtywiopwoertyip【欧文IE， ', '2018-06-06');
INSERT INTO `t_log` VALUES ('4', '电饭锅和豆腐干假道伐虢父节点', '2018-06-27');
INSERT INTO `t_log` VALUES ('5', '活动和规范化市规划局上', '2018-06-27');
INSERT INTO `t_log` VALUES ('6', '水电费水电费和时代峰峻', '2018-06-27');
INSERT INTO `t_log` VALUES ('7', '十多个粉红色的规划规划收到货后', '2018-06-27');

-- ----------------------------
-- Table structure for t_machine
-- ----------------------------
DROP TABLE IF EXISTS `t_machine`;
CREATE TABLE `t_machine` (
  `pk_id` int(11) NOT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `f_defeat` varchar(255) DEFAULT NULL,
  `f_kind` varchar(255) DEFAULT NULL,
  `f_use` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_machine
-- ----------------------------
INSERT INTO `t_machine` VALUES ('1', '杀鼠灵', '鼠害', '药剂', '可以毒杀对一代抗凝血剂灭鼠毒饵产生抗性的鼠类');
INSERT INTO `t_machine` VALUES ('2', '鼠得克', '鼠害', '药剂', '诱饵毒杀老鼠、对人体也有害');
INSERT INTO `t_machine` VALUES ('3', '溴敌隆', '鼠害', '药剂', '具有较强的急性毒力');
INSERT INTO `t_machine` VALUES ('4', '黄鼠狼', '鼠害', '器械', '生物灭鼠');
INSERT INTO `t_machine` VALUES ('5', '老鼠笼', '鼠害', '器械', '诱捕各种老鼠');
INSERT INTO `t_machine` VALUES ('6', '敌敌畏常用型', '虫害', '药剂', '对蝇类和各种地下害虫特效');
INSERT INTO `t_machine` VALUES ('7', '灭蝇颗粒剂（兑水）', '虫害', '药剂', '对付各种飞行虫类');
INSERT INTO `t_machine` VALUES ('8', '灭蝇灯', '虫害', '器械', '对付各种飞行虫类');
INSERT INTO `t_machine` VALUES ('9', '背负式喷雾器', '虫害', '器械', '喷洒农药');
INSERT INTO `t_machine` VALUES ('10', '百菌清', '病害', '药剂', '对霜类病菌无效');
INSERT INTO `t_machine` VALUES ('11', '银发利', '病害', '药剂', '对叶菜类、茄果类、瓜类、豆类、葱蒜类常见病害都有效');
INSERT INTO `t_machine` VALUES ('12', '无人机', '病害', '器械', '对叶菜类、茄果类、瓜类、豆类、葱蒜类常见病害都有效');
INSERT INTO `t_machine` VALUES ('13', '病情测报仪器', '病害', '器械', '检测病害');

-- ----------------------------
-- Table structure for t_out
-- ----------------------------
DROP TABLE IF EXISTS `t_out`;
CREATE TABLE `t_out` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_class` int(11) DEFAULT NULL,
  `fk_user` int(11) DEFAULT NULL,
  `f_date` date DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_out
-- ----------------------------
INSERT INTO `t_out` VALUES ('1', '1', '1', '2018-06-27');
INSERT INTO `t_out` VALUES ('2', '2', '2', '2018-06-14');
INSERT INTO `t_out` VALUES ('3', '2', '2', '2018-06-13');
INSERT INTO `t_out` VALUES ('4', '5', '5', '2018-06-12');
INSERT INTO `t_out` VALUES ('5', '3', '3', '2018-06-13');
INSERT INTO `t_out` VALUES ('6', '4', '4', '2018-06-01');
INSERT INTO `t_out` VALUES ('7', '5', '5', '2018-06-13');
INSERT INTO `t_out` VALUES ('8', '3', '7', '2018-06-14');
INSERT INTO `t_out` VALUES ('9', '4', '9', '2018-06-12');
INSERT INTO `t_out` VALUES ('10', '2', '5', '2018-06-13');
INSERT INTO `t_out` VALUES ('11', '1', '2', '2018-06-20');

-- ----------------------------
-- Table structure for t_outmachine
-- ----------------------------
DROP TABLE IF EXISTS `t_outmachine`;
CREATE TABLE `t_outmachine` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_num` int(11) DEFAULT NULL,
  `f_type` varchar(255) DEFAULT NULL,
  `f_prevent` varchar(255) DEFAULT NULL,
  `f_out_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_outmachine
-- ----------------------------
INSERT INTO `t_outmachine` VALUES ('1', '肉毒梭菌素', '200', '药剂', '鼠害', '1');
INSERT INTO `t_outmachine` VALUES ('2', '溴鼠灵', '200', '药剂', '鼠害', '2');
INSERT INTO `t_outmachine` VALUES ('3', '贝奥雄性不育灭鼠剂', '200', '药剂', '鼠害', '5');
INSERT INTO `t_outmachine` VALUES ('4', '吡虫啉', '300', '药剂', '病害', '3');
INSERT INTO `t_outmachine` VALUES ('5', '氟虫腈', '300', '药剂', '病害', '4');
INSERT INTO `t_outmachine` VALUES ('6', '敌百虫', '300', '药剂', '病害', '2');
INSERT INTO `t_outmachine` VALUES ('7', '粘鼠板', '200', '器械', '鼠害', '4');
INSERT INTO `t_outmachine` VALUES ('8', '捕鼠笼', '20', '器械', '鼠害', '5');
INSERT INTO `t_outmachine` VALUES ('9', '手动喷雾剂', '300', '器械', '病害', '1');
INSERT INTO `t_outmachine` VALUES ('10', '无人机喷雾', '200', '器械', '病害', '3');

-- ----------------------------
-- Table structure for t_pestsl
-- ----------------------------
DROP TABLE IF EXISTS `t_pestsl`;
CREATE TABLE `t_pestsl` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) DEFAULT NULL,
  `f_host` varchar(255) DEFAULT NULL,
  `f_duction` varchar(255) DEFAULT NULL,
  `f_enemy` varchar(255) DEFAULT NULL,
  `f_larvaImg` varchar(255) DEFAULT NULL,
  `f_adultImg` varchar(255) DEFAULT NULL,
  `f_prevention` varchar(255) DEFAULT NULL,
  `f_harm` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pestsl
-- ----------------------------
INSERT INTO `t_pestsl` VALUES ('1', '樱花介壳虫', '连翘，白蜡树', '一年6胎', '啄木鸟', '', null, '修剪树叶', '幼虫食叶造成叶子枯死');
INSERT INTO `t_pestsl` VALUES ('2', '蝗虫', '稻子', '一年10胎', '跳小蜂', null, null, '黑灯光诱杀', '食果实导致植物枯死');
INSERT INTO `t_pestsl` VALUES ('3', '蝗虫', '稻子', '一年10胎', '跳小蜂', '', '', '黑灯光诱杀', '食果实导致植物枯死');

-- ----------------------------
-- Table structure for t_ratdamage
-- ----------------------------
DROP TABLE IF EXISTS `t_ratdamage`;
CREATE TABLE `t_ratdamage` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(20) DEFAULT NULL,
  `f_food` varchar(255) DEFAULT NULL,
  `f_duction` varchar(255) DEFAULT NULL,
  `f_enemy` varchar(255) DEFAULT NULL,
  `f_imgPath` varchar(255) DEFAULT NULL,
  `f_prevention` varchar(255) DEFAULT NULL,
  `f_harm` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ratdamage
-- ----------------------------
INSERT INTO `t_ratdamage` VALUES ('1', '中华田园老鼠', '各种谷子作物', '一年6~8胎', '狐狸，蛇', null, '放养', '破坏植被庄家');
INSERT INTO `t_ratdamage` VALUES ('2', '大沙鼠', '各种谷子作物', '一年6~8胎', '狐狸，蛇', '', '放养', '破坏植被庄家');
INSERT INTO `t_ratdamage` VALUES ('3', '黄鼠', '各种谷子作物', '一年6~8胎', '狐狸，蛇', '', '放养天敌', '破坏植被庄家');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_userName` varchar(255) DEFAULT NULL,
  `f_pwd` varchar(255) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `f_level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'xiaomi', '123', '小米', '超级管理员');
INSERT INTO `t_user` VALUES ('2', 'hehe', '123', '呵呵', '资料管理员');
INSERT INTO `t_user` VALUES ('3', 'zhaoqing', '123', '肇庆', '灾情管理员');
INSERT INTO `t_user` VALUES ('4', 'zhaowu', '123', '赵武', '专家管理员');
INSERT INTO `t_user` VALUES ('5', 'zaoliu', '123', '赵六', '库房管理员');

-- ----------------------------
-- Procedure structure for Updateway1
-- ----------------------------
DROP PROCEDURE IF EXISTS `Updateway1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Updateway1`(in id int)
begin
	  update t_event set f_phase='无法解决，申请专家会商' where pk_id = id;
end
;;
DELIMITER ;
