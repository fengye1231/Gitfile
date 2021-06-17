DROP DATABASE IF EXISTS dms;
CREATE DATABASE dms;
USE dms;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `dailyId` int(10) unsigned NOT NULL auto_increment,
  `submitDate` date NOT NULL,
  `dailyDesc` varchar(255) NOT NULL,
  `totalWorkload` float(11,0) NOT NULL,
  `overTimeLoad` float(11,0) default '0',
  `tomorrowPlan` varchar(255) default NULL,
  `status` enum('未审核','未通过','已通过') default '未审核',
  `reviewDate` date default NULL,
  `reason` varchar(255) default NULL,
  `prjId` int(10) unsigned NOT NULL,
  `prpId` int(10) unsigned NOT NULL,
  `empId` int(10) unsigned NOT NULL,
  `reviewEmpId` int(10) unsigned default NULL,
  PRIMARY KEY  (`dailyId`),
  KEY `prjId` (`prjId`),
  KEY `prpId` (`prpId`),
  KEY `empId` (`empId`),
  KEY `reviewEmpId` (`reviewEmpId`),
  CONSTRAINT `daily_ibfk_1` FOREIGN KEY (`prjId`) REFERENCES `prj` (`prjId`) ON UPDATE CASCADE,
  CONSTRAINT `daily_ibfk_2` FOREIGN KEY (`prpId`) REFERENCES `prp` (`prpId`) ON UPDATE CASCADE,
  CONSTRAINT `daily_ibfk_3` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON UPDATE CASCADE,
  CONSTRAINT `daily_ibfk_4` FOREIGN KEY (`reviewEmpId`) REFERENCES `employee` (`empId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daily
-- ----------------------------
INSERT INTO `daily` VALUES ('6', '2015-07-01', 'XXX', '6', '0', 'XXX', '未通过', '2015-07-07', null, '1', '1', '3', '2');
INSERT INTO `daily` VALUES ('7', '2015-07-03', 'XXX', '7', '1', 'XXX', '已通过', '2015-07-07', null, '1', '1', '4', '2');
INSERT INTO `daily` VALUES ('8', '2015-07-06', 'XXX', '6', '1', 'XXX', '已通过', '2015-07-07', null, '1', '1', '5', '2');
INSERT INTO `daily` VALUES ('9', '2015-07-03', 'XXX', '6', '1', 'XXX', '已通过', '2015-07-07', null, '1', '1', '6', '2');
INSERT INTO `daily` VALUES ('10', '2015-07-01', 'XXX', '6', '1', 'XXX', '未审核', null, null, '1', '1', '2', null);
INSERT INTO `daily` VALUES ('12', '2015-07-07', '2', '1', '1', '', '未审核', null, null, '3', '3', '2', null);
INSERT INTO `daily` VALUES ('13', '2015-07-07', '1', '1', '1', '', '未审核', null, null, '1', '4', '2', null);

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptId` int(10) unsigned NOT NULL auto_increment,
  `deptCode` varchar(255) NOT NULL,
  `deptName` varchar(255) NOT NULL,
  `deptRemark` varchar(255) default NULL,
  `seniorDeptId` int(10) unsigned default '0',
  PRIMARY KEY  (`deptId`),
  UNIQUE KEY `deptCode` (`deptCode`),
  UNIQUE KEY `deptName` (`deptName`),
  KEY `seniorDeptId` (`seniorDeptId`),
  CONSTRAINT `dept_ibfk_1` FOREIGN KEY (`seniorDeptId`) REFERENCES `dept` (`deptId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', 'D0000', '东软集团', '东软集团', null);
INSERT INTO `dept` VALUES ('2', 'D1001', '部门1001', '部门1001', '1');
INSERT INTO `dept` VALUES ('3', 'D1002', '部门1002', '部门1002', '2');
INSERT INTO `dept` VALUES ('4', 'D1003', '部门1003', '部门1003', '2');
INSERT INTO `dept` VALUES ('5', 'D1301', '部门1301', '部门1301', '4');
INSERT INTO `dept` VALUES ('6', 'D2001', '部门2001', '部门2001', '1');
INSERT INTO `dept` VALUES ('7', 'D2002', '部门2002', '部门2002', '6');
INSERT INTO `dept` VALUES ('8', 'D2003', '部门2003', '部门2003', '6');
INSERT INTO `dept` VALUES ('9', 'D2004', '部门2004', '部门2004', '6');
INSERT INTO `dept` VALUES ('10', 'D3001', '部门3001', '部门3001', '1');
INSERT INTO `dept` VALUES ('11', 'D3002', '部门3002', '部门3002', '10');
INSERT INTO `dept` VALUES ('12', 'D3003', '部门3003', '部门3003', '10');
INSERT INTO `dept` VALUES ('13', 'D3004', '部门3004', '部门3004', '10');
INSERT INTO `dept` VALUES ('14', 'D4001', '部门4001', '部门4001', '1');
INSERT INTO `dept` VALUES ('15', 'D4002', '部门4002', '部门4002', '14');
INSERT INTO `dept` VALUES ('16', 'D4003', '部门4003', '部门4003', '14');
INSERT INTO `dept` VALUES ('17', 'D4004', '部门4004', '部门4004', '14');
INSERT INTO `dept` VALUES ('18', 'D5001', '部门5001', '部门5001', '1');
INSERT INTO `dept` VALUES ('19', 'D5002', '部门5002', '部门5002', '18');
INSERT INTO `dept` VALUES ('20', 'D5003', '部门5003', '部门5003', '18');
INSERT INTO `dept` VALUES ('21', 'D5004', '部门5004', '部门5004', '18');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `empId` int(10) unsigned NOT NULL auto_increment,
  `empName` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` enum('保密','男','女') default '保密',
  `joindate` date default NULL,
  `englishName` varchar(255) default NULL,
  `birth` date default NULL,
  `nativePlace` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `qq` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `homeTel` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `school` varchar(255) default NULL,
  `graduateDate` date default NULL,
  `remark` varchar(255) default NULL,
  `isleader` tinyint(1) default '0',
  `question` varchar(255) default NULL,
  `answer` varchar(255) default NULL,
  `superiorId` int(10) unsigned default NULL,
  `roleId` int(10) unsigned NOT NULL,
  `deptId` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`empId`),
  UNIQUE KEY `username` (`username`),
  KEY `superiorId` (`superiorId`),
  KEY `roleId` (`roleId`),
  KEY `deptId` (`deptId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`superiorId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON UPDATE CASCADE,
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`deptId`) REFERENCES `dept` (`deptId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '员工1', 'emp1', '1000:900bd0efda94dd67a1032e9b54db517bd4a364f44196f98e:ed3dd491c238fee810e06d81eda01a4786f3dfd8cc6d0b5f', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 领导', '1', null, null, null, '1', '1');
INSERT INTO `employee` VALUES ('2', '员工2', 'emp2', '1000:379ff761cb800d63d82685047a7fab02d67d1cc9c6713530:f8bef5828b7db02a598b46be667bd48062363cebf276fa4c', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', '你父亲的名字是什么？', '爸', '1', '1', '3');
INSERT INTO `employee` VALUES ('3', '员工3', 'emp3', '1000:3dca796b4362d20225b37c6f98e3f90a1a2e45744e367135:d369b592dc603997ff983db65b4f02ee7d84ab64eac333a4', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '2', '3', '3');
INSERT INTO `employee` VALUES ('4', '员工4', 'emp4', '1000:55549353da4ccb124e50b81dcb4a1fbf91becd6891e262ae:d9c4a1cd78701fcc1ca21251655d6d21a58f3092fbf2d676', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '2', '3', '3');
INSERT INTO `employee` VALUES ('5', '员工5', 'emp5', '1000:c29500ae4ed4256260320a10171db6273ea02226b962db55:e01b0b4bdc0e1f17c1315f7b45a4e2d565e055b64a20463d', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '2', '3', '3');
INSERT INTO `employee` VALUES ('6', '员工6', 'emp6', '1000:77649da8002c4cae440d747cee4d837792cc31c32dd31c7e:b1389a6cbc244f41177991f3d27b1f21a0bc70dd3c59937f', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '1', '5', '3');
INSERT INTO `employee` VALUES ('7', '员工7', 'emp7', '1000:dd8e8cd06dbcf0af76abb04d4732f1fc1889a5390df955cf:d6c7aede168c6f5fcd6c1bc5bafcfcd18ce78bd05c7bb7ce', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '4');
INSERT INTO `employee` VALUES ('8', '员工8', 'emp8', '1000:e4e10d0b7dc5d411355581fe117ac5da8d9c0445819b3320:96e086d2edd36bc69dfa3ba550cd7cd28d6dac3610f88fbb', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '7', '3', '4');
INSERT INTO `employee` VALUES ('9', '员工9', 'emp9', '1000:51e27c4e5be63b54852ceb481dd555f413361e4b23760318:cbadb40606665718a285ec94cab447d399ab7dc3ed92f194', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '7', '3', '4');
INSERT INTO `employee` VALUES ('10', '员工10', 'emp10', '1000:8cab3059a47747a1fd5c4c276dfd33c366f7788e4336b84f:58bdbe89ca868a97ec8d794b80ebc2e4b4980cf80fba2c80', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '7', '3', '4');
INSERT INTO `employee` VALUES ('11', '员工11', 'emp11', '1000:26e3f2e4f8f2d8137c1fa79b57e5258e032465079f941a36:6b61ec9f308d5658de130459a8a7af091077fc7fffe48948', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '7', '3', '4');
INSERT INTO `employee` VALUES ('12', '员工12', 'emp12', '1000:d58cf15b434ef934756377c6e01e6eeb5e1a1de68bd8692d:0c839d17652e8e863c8d4569e24df5cb18fc50369853386d', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '7', '3', '4');
INSERT INTO `employee` VALUES ('13', '员工13', 'emp13', '1000:7b0b32683db9b4faea23e32b60c92109df92822e6d65e8c4:e1ae88ae28b29b25ab431385e030419ccf9d0b5ad1e4c1cf', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '5');
INSERT INTO `employee` VALUES ('14', '员工14', 'emp14', '1000:1216529b260a93c331511475467ab8e7364630a10b7f14f8:2f1b67890e4189f7580079b8ae5e2999233013963c69b00a', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '13', '3', '5');
INSERT INTO `employee` VALUES ('15', '员工15', 'emp15', '1000:b732d190b92f9107ab5c0347c2c608220349a46e73209f00:9c83daa6e5b0ca40ed27557f4af35ae100263b05cfb38a51', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '13', '3', '5');
INSERT INTO `employee` VALUES ('16', '员工16', 'emp16', '1000:b510a3da10739c45f726ee4f2dd474006c69bc07502ec0b9:220d6fcb7da1d5dfffc07ab4d57d07b5ee4d315bf37ec6a9', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '13', '3', '5');
INSERT INTO `employee` VALUES ('17', '员工17', 'emp17', '1000:889c7c58d83fc8f9bbb7ff5a612c49dde63c986bb21b6a40:142b028809d7d0527c13dbe40053e8ddddb176700f83b5d5', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '13', '3', '5');
INSERT INTO `employee` VALUES ('18', '员工18', 'emp18', '1000:c193a06f0cd1b29e162d89026d4b8b8162d11b6d56f871fc:731111654f206fba431371b2f9861ff345e833df44241363', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '13', '3', '5');
INSERT INTO `employee` VALUES ('19', '员工19', 'emp19', '1000:4ffb495b72a39e18fcb75373880862553dc4a36c2b31d7d5:86e36af6fc7792ffd70f0ad28ac04bfb5440173899f68f5d', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '7');
INSERT INTO `employee` VALUES ('20', '员工20', 'emp20', '1000:a912429e22fe88004ca9d1ff32bcca4c0381749d5a714806:890fd2651e16a62021ef2841cad368f06e325226f7a25f7b', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '19', '3', '7');
INSERT INTO `employee` VALUES ('21', '员工21', 'emp21', '1000:b0b60d4dd0b0b96ae29d96db0234d040356930191fe04691:946c20ff2340faf8c6c2f9c3dcd5ab99e7fd6e9e10dc0289', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '19', '3', '7');
INSERT INTO `employee` VALUES ('22', '员工22', 'emp22', '1000:878ed746cf9e7137691cc6ccfabac5f78b17a59211688425:616b957ea77d4e476ae6781c3383f6071951cd7fa6925468', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '19', '3', '7');
INSERT INTO `employee` VALUES ('23', '员工23', 'emp23', '1000:ae1ac9b9f2da2d7db00f38591001663e748468588a05bcd4:8e1e448027d3d5a275b42acbc2822531b1364eafae6b5f37', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '19', '3', '7');
INSERT INTO `employee` VALUES ('24', '员工24', 'emp24', '1000:96b41f033fa35a5452ac988a33d3cb7ceb17f8450743dff5:3d4680ad43eff43741dd4968418ddfb20da0259d90b4e57d', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '19', '3', '7');
INSERT INTO `employee` VALUES ('25', '员工25', 'emp25', '1000:4fc809bbcabc3b06234c928601ce295002a6d77512d1dd34:686185c7edf4d8a17cadbf33f8e886de514a9af385103a87', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '8');
INSERT INTO `employee` VALUES ('26', '员工26', 'emp26', '1000:28e8048b624e9124ff530e77fe0779df2c27973908c4fdfc:4ed6d963b4985ea45a08c382c683c59e35faee783ca1782d', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '25', '3', '8');
INSERT INTO `employee` VALUES ('27', '员工27', 'emp27', '1000:b8938b67f89663bbc472ad9a9c32e00d3048c8f7e9372124:fcf7837a3ee065a4f9a54bf08bebd7c19fac84f994a8ebff', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '25', '3', '8');
INSERT INTO `employee` VALUES ('28', '员工28', 'emp28', '1000:1340b0445286c2113180d7f8db4ff88c9c1ec7f4bd2a4fba:03c98309bac4079f22ccca53262dba1da9c184d108e7821b', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '25', '3', '8');
INSERT INTO `employee` VALUES ('29', '员工29', 'emp29', '1000:a3e0683569660487f9377c263920cd0a6e695523ff0f1881:6828e85d5ffa7d671f0606e0b3e93c7dba4fd45b61e8e05e', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '25', '3', '8');
INSERT INTO `employee` VALUES ('30', '员工30', 'emp30', '1000:7517b48cc7696457d44192c514482905323d5574faccd202:2bcc3ea1bd7d15d51fbe55666e1353e75b72e12b927e0599', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '25', '3', '8');
INSERT INTO `employee` VALUES ('31', '员工31', 'emp31', '1000:ab7829f22572b12bbb12a48112c2acbd43d6be16acd57773:8bccb8bee794bbc0e1a885ce9e5cd1b6a8097184a0287b95', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 领导', '1', null, null, '1', '2', '9');
INSERT INTO `employee` VALUES ('32', '员工32', 'emp32', '1000:b3476be23b9556c761b5a1ecdd58cc731f94ec63aece5944:5c0d239f00c671fce4458e69b13d0f223225569eb51bef79', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '31', '3', '9');
INSERT INTO `employee` VALUES ('33', '员工33', 'emp33', '1000:046259396a11158d07b11e40eaf1f6c49a7bdf97cf358f5e:0ec82901c13ce09709c02fa94104ecc63bda9c5259caaa64', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '31', '3', '9');
INSERT INTO `employee` VALUES ('34', '员工34', 'emp34', '1000:8cc8be3c69bff9c0256033dcdffa6d7a6d6538fc3879446a:3361437f0c2f4d41de0960db439ac8625b299fccee7a8f21', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '31', '3', '9');
INSERT INTO `employee` VALUES ('35', '员工35', 'emp35', '1000:e1c4edd3c26e92719a9883e9fd211cf9b18d6c8d9bb1c931:f1749a5cff2cafb30bf215a54b57324a769e5c4790b5f6a4', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '31', '3', '9');
INSERT INTO `employee` VALUES ('36', '员工36', 'emp36', '1000:e275900bfdca26c7da4655d5e948f36810658453be64b00c:73d4aee713d8719134c4a55a26791194b59e73746de0f002', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '31', '3', '9');
INSERT INTO `employee` VALUES ('37', '员工37', 'emp37', '1000:fa00d048c2f72cba762cbda4f337084f214af640af01aecd:4434bf3d388b6e89c13ee759bd1fe3a3e5adceafd2f62430', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '11');
INSERT INTO `employee` VALUES ('38', '员工38', 'emp38', '1000:19534d9b9861d5e6a6cd3e20e753a09fe39df9cb49bc8bb1:87dc5e78dcb05dd2bf7a713621dfe27da8543011c3c31a99', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '37', '3', '11');
INSERT INTO `employee` VALUES ('39', '员工39', 'emp39', '1000:c7f76228def4e80e22bc8a5cd5c1109546cc3f0fbe730ec1:361591c5d45861420e5d656bb086e7d66059a1d2bcdef947', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '37', '3', '11');
INSERT INTO `employee` VALUES ('40', '员工40', 'emp40', '1000:d6842a54200cadf822bdeda68c771fbaf99165e550a7128a:bbf28e653ae52f97c4a6a935a516eca88fcd91875d0d34f2', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '37', '3', '11');
INSERT INTO `employee` VALUES ('41', '员工41', 'emp41', '1000:615f3eca14d4e4a38520dfcf785b4e5c86385c0fd83a7bf5:460f7689d3e5602b2166dd6476fe7bb272561e6681184791', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '37', '3', '11');
INSERT INTO `employee` VALUES ('42', '员工42', 'emp42', '1000:1e65d7d0b833862b1bd8510adc461ce96c5dbcf0c5923739:e91a423a7e5df8a74a229726c486682b6546519952a095e3', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '37', '3', '11');
INSERT INTO `employee` VALUES ('43', '员工43', 'emp43', '1000:3db29b3b3eeaf16966211c1df4a9f2464d73468a9589a240:e38432d577606e7093338234e8084e6e4c3093b712c808f3', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '12');
INSERT INTO `employee` VALUES ('44', '员工44', 'emp44', '1000:e7f7dce6242eb6bd525268ae0d1314eed7f5e798f8a054ad:0f9d2a9b45065cdb9bdb34acf7ea308a28eb7b2b22f68a72', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '43', '3', '12');
INSERT INTO `employee` VALUES ('45', '员工45', 'emp45', '1000:12f3feca8eef30642866698e8bc7e4c0ee847b2268b02088:9fcea1ce3d784de00d502d12046e56ba60120851692e11b6', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '43', '3', '12');
INSERT INTO `employee` VALUES ('46', '员工46', 'emp46', '1000:3d8a04a4249019424b824cba5b13e3282a5cffe827c8d75c:e6c27b459a1ca4b7ff3976606104be9dee548185841d3938', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '43', '3', '12');
INSERT INTO `employee` VALUES ('47', '员工47', 'emp47', '1000:9a2ce0f39630beb3d1faaa8f5024b48d5d703a94287dffad:e00600911b97a72c1dd36c1ca511a78a13c604599f59d3b5', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '43', '3', '12');
INSERT INTO `employee` VALUES ('48', '员工48', 'emp48', '1000:e1aee21675ac51bd2ba0a9e8b2b08f55cba8646b03ba08c3:6b8542f5ba53ddd5457528544a6aee2b4b64758f9230f7fd', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '43', '3', '12');
INSERT INTO `employee` VALUES ('49', '员工49', 'emp49', '1000:361ad094de1a892ef0057bbb17b9a9fc088d8351ba60dc48:c7b217dc34b951a4df2753aaebe848bdb724b2a4ffe4c93b', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '13');
INSERT INTO `employee` VALUES ('50', '员工50', 'emp50', '1000:f949f8d50d7db87e5f04f26dedcfa242bfc5b77b736cb7b8:c505dc751ff5cedc5f473ce3aba246c067c797bd4150d02b', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '49', '3', '13');
INSERT INTO `employee` VALUES ('51', '员工51', 'emp51', '1000:3866fcfd0037bb5789f4f5e573e1355141493664b79199a9:413f3a58726570e84104b847d8a43a5705252a24e4a7f846', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '49', '3', '13');
INSERT INTO `employee` VALUES ('52', '员工52', 'emp52', '1000:e39702c53e3af02dca5afee05587bd0d7ed53291ff71164a:b13a0fb039c860af709004f323428ef735eb0b7f1f7688d3', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '49', '3', '13');
INSERT INTO `employee` VALUES ('53', '员工53', 'emp53', '1000:ee7df1ee4d5db346355e775c41470cfa96b3a44ea508ce85:84af4eb5a6a10d8b13c995723e719102306495a0ff339dcc', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '49', '3', '13');
INSERT INTO `employee` VALUES ('54', '员工54', 'emp54', '1000:aab0617811d03a25613346db76325a40c47e5586b13a9be9:06de508b30aeb576fc1d81ff56a77c20d4f7461b98c2873e', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '49', '3', '13');
INSERT INTO `employee` VALUES ('55', '员工55', 'emp55', '1000:9c136302c7863ad369e60e27d715c0378a3947f4cde2b68f:479f7a5b73f784cf355d03d3361b418a8167418cae2ef496', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '15');
INSERT INTO `employee` VALUES ('56', '员工56', 'emp56', '1000:e89ec9501efc83e3f7eabd3c093f23e7034cacc103507164:a8366b7b830dc5ae57fd4845e6504d587236806304a0b17c', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '55', '3', '15');
INSERT INTO `employee` VALUES ('57', '员工57', 'emp57', '1000:df88465030b872d829321fd76d6e344cc853bece7d59e966:ef522109beb65b4910df66648a40a948bb83a1da3fddde59', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '55', '3', '15');
INSERT INTO `employee` VALUES ('58', '员工58', 'emp58', '1000:b07a1a580abae7aec019cbef5a6f3635fdeb4f6608945f8c:42f8da7a2d5611735261b5b62d2fbf93c9583f2374de1373', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '55', '3', '15');
INSERT INTO `employee` VALUES ('59', '员工59', 'emp59', '1000:c8cff85b7c0c2ed3f3e9f919ff1794b194d2e67654cee43e:70e43288886b7e70d1d867f26a7b4cae8e0580155484fe71', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '55', '3', '15');
INSERT INTO `employee` VALUES ('60', '员工60', 'emp60', '1000:222575497ac2e6fa07017d06b66eb44865d45561a75e1deb:2fcd420d17f9c23fd90f3e94630ee715a3fc06a6c2d17cd5', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '55', '3', '15');
INSERT INTO `employee` VALUES ('61', '员工61', 'emp61', '1000:00affca8c4273a23895a55d5b20173f3afbd9803afa8acc0:558be5f2815e60832286d09ad18f7a0174aca6a936d99fd9', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '16');
INSERT INTO `employee` VALUES ('62', '员工62', 'emp62', '1000:d7588c2597f7a5f80366296a4e377ceba2a4885714200ea0:5b303b03ec28cd1a236bd75a10df0de152388860ee31864b', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '61', '3', '16');
INSERT INTO `employee` VALUES ('63', '员工63', 'emp63', '1000:d7139f3b8397163c6cc38a303605986a683c679b2a325890:24817a17dde4777a1d71deb7cf598855da1f78c393d6aea7', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '61', '3', '16');
INSERT INTO `employee` VALUES ('64', '员工64', 'emp64', '1000:d56f267f4d56e5a85dfbda3be5266e21075d60e513dbb91c:f5e0dd648372cd2b39e0d4b4b9163b7fc2afc21d171279a8', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '61', '3', '16');
INSERT INTO `employee` VALUES ('65', '员工65', 'emp65', '1000:0ed59aab17e833bfdb61c6eaf106b497d9d0e581bca9571b:66b16921a7dbc4356c07330ea6fee2ce19f73370b68fe1da', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '61', '3', '16');
INSERT INTO `employee` VALUES ('66', '员工66', 'emp66', '1000:b6b2e0de1c2e4a2303c23ac301cdac50c5740c3c1aed1dad:86642f8105297eed5df4f9afc9c745ca891786e12d21c61a', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '61', '3', '16');
INSERT INTO `employee` VALUES ('67', '员工67', 'emp67', '1000:09af83c08b94ae8b45f58e4d93bc1c4e793ce1ad98164ab0:7398d565ad8ae619ea399e6f23794422fb2bcd31d884c87f', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '17');
INSERT INTO `employee` VALUES ('68', '员工68', 'emp68', '1000:b478b315aadaa4f7c9fbf8208b7b35f02c5ce5e8967aa3be:d76e25f4fb8be9e5ee362655cf7eeb7e26605703b8fd16e5', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '67', '3', '17');
INSERT INTO `employee` VALUES ('69', '员工69', 'emp69', '1000:60fd3377d38d8d69fbfaca04cd825902a9a4d24ff42ae3b5:10e04e8a8bba4574ad340e9c6078725f5edb56a0845d07ce', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '67', '3', '17');
INSERT INTO `employee` VALUES ('70', '员工70', 'emp70', '1000:371e50726c608f4d60d501541dc8244d6d1bbf66ae0aebd4:75ac5bfced58e6fdcfa99351880547b1dafb7369525f6ce8', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '67', '3', '17');
INSERT INTO `employee` VALUES ('71', '员工71', 'emp71', '1000:e7203e089dc8b0ec4d4a5ca7a7e1d9b4450209db5b42a49b:e1a30d478c78ee184ebc735af32d0fc793513c94010f1832', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '67', '3', '17');
INSERT INTO `employee` VALUES ('72', '员工72', 'emp72', '1000:a9c0df63317a99992c20ec56590a81469716a140adef58cd:643b40bf6462049ff934a20d1178ee5034134f1534fbc4d9', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '67', '3', '17');
INSERT INTO `employee` VALUES ('73', '员工73', 'emp73', '1000:48aaed3484f1e890ced1b038f5a21e42bdcac6e518f88c7f:afd38bfa85a4f5ebc40d71171c829bf787b47c3d5c8ac8b5', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '19');
INSERT INTO `employee` VALUES ('74', '员工74', 'emp74', '1000:0e4d14787a1a7e8fdd75d8b2fc180ce17aa8a7d311d70e72:3b60c5cd7afb68c88cb9c9f958646b9ff1092728d389cf1a', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '73', '3', '19');
INSERT INTO `employee` VALUES ('75', '员工75', 'emp75', '1000:cdb6ec671230501e75e41414cf6a5c803ef49bfd313778c9:a4c47ad569295128547d246aa4b8e6aa9d211da9b525d3cc', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '73', '3', '19');
INSERT INTO `employee` VALUES ('76', '员工76', 'emp76', '1000:9ff73d3205428c120aecc580674c3be94a8b783cb033a4f1:f4f61e59724e5e03609310b3c9c9c2e1dda02103206a385a', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '73', '3', '19');
INSERT INTO `employee` VALUES ('77', '员工77', 'emp77', '1000:0ab52a21ac9633d6455a6cfea6adf62dacc961d316f69e65:b9130a36428feb38ce557bcb7fcf518052d6d73714486989', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '73', '3', '19');
INSERT INTO `employee` VALUES ('78', '员工78', 'emp78', '1000:695b641cd14b139123226a519915b7f3063ec014e4e93b08:dead0d9d70eab92ca5ef9d9505ffec4459ef0290d091a502', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '73', '3', '19');
INSERT INTO `employee` VALUES ('79', '员工79', 'emp79', '1000:a13595672d9ae11a42956bf0cf8ac160b7540e8ae21779aa:d88138975d07e99e405cfb59d437b64ca8a6579449e897c2', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', '  领导', '1', null, null, '1', '2', '20');
INSERT INTO `employee` VALUES ('80', '员工80', 'emp80', '1000:8b6c314b6e936d213c2bf663eb362f9a20d6c36d2ccd16c2:81ac97f37c9d142f1e3865a5fd7a9f04d4786ce605946948', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '79', '3', '20');
INSERT INTO `employee` VALUES ('81', '员工81', 'emp81', '1000:8e0ada15c04280b25a4678cee32c2f6926cfc717791c7e6b:d4d12a8bea85491ed54eb7a5898670695f5db56acf040d73', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '79', '3', '20');
INSERT INTO `employee` VALUES ('82', '员工82', 'emp82', '1000:18204fd628b2dd96305fb951d366fa1eb39e3b5f5f45d1ea:ffd528a7f1ed719b370af739a2b4eecbde08c0973344c8b5', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '79', '3', '20');
INSERT INTO `employee` VALUES ('83', '员工83', 'emp83', '1000:b983723171a009b53d344d18224e7d814871364b2ec49aa2:e96d08d1f1afccb1da4ec7ebc56eeaff027627b8449883c0', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '79', '3', '20');
INSERT INTO `employee` VALUES ('84', '员工84', 'emp84', '1000:0d3f1f2bd1ac50ce664793f10705abd36a452cda8419e439:ae67f2d10b2346576f02375511c5473d92366516c4cd8c34', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '79', '3', '20');
INSERT INTO `employee` VALUES ('85', '员工85', 'emp85', '1000:730d1936a5b5e53f7dee2f8364b2a79c6660f9c47e00feba:47ba9b64bc01ca66ee98dc917144ebedfec01e5a6400c25c', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 领导', '1', null, null, '1', '2', '21');
INSERT INTO `employee` VALUES ('86', '员工86', 'emp86', '1000:663d091ece8c6cbeaa7433ab48c856fb787f842a6445af82:efbc726396a78e1626f3386de5dde73dea3be4f4797ab3ce', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '85', '3', '21');
INSERT INTO `employee` VALUES ('87', '员工87', 'emp87', '1000:fd1fd33d26fdf14c76f37cc9df1ebb7c9337d69fba868e90:65945c9ff111e3ec88d483dbff9f867ce77d8943c1424baa', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '85', '3', '21');
INSERT INTO `employee` VALUES ('88', '员工88', 'emp88', '1000:eda86f2c2176cf2a33e789d7fb463bea86653356090e156c:87b8c6154d5d1b66e55c924a02669cdb60b3e17cd1d3a525', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '85', '3', '21');
INSERT INTO `employee` VALUES ('89', '员工89', 'emp89', '1000:4185097a736179950bd8d6559a2265432bc0ff0153e46eea:638500cbeeee5dd7d4d038175ad6683a02fe3319ae838064', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '85', '3', '21');
INSERT INTO `employee` VALUES ('90', '员工90', 'emp90', '1000:0639e2ef870dc2d60fe910b6f4ba071cb506cedbab3769a0:eadffba441bfb8d3754f53495b02eaf18218752b72a5e383', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '85', '3', '21');
INSERT INTO `employee` VALUES ('91', '员工91', 'emp91', '1000:df6522163a7b45b9afbed059d192481a262f928ea40ccf17:84395f4ae04ef0478cd3c9e4f35d9c38c0fbfa84974cd86b', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 领导', '1', null, null, '1', '2', '5');
INSERT INTO `employee` VALUES ('92', '员工92', 'emp92', '1000:a086ba3acf4d028311ae9afde31a7438f45acc26619c8c3e:184cfdd9b665cc22e6c75dc9f4b70ec30aed08cb0945c565', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('93', '员工93', 'emp93', '1000:2030006de20bdcb71fb36a868578038b09eea9c56efbac71:e0845d8c51179d020a1e1677ffd58a568c86aca1891bc14a', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('94', '员工94', 'emp94', '1000:e0dc9038e1eca4c376730720b613da96533cb0ad8f4b1ab7:85f3a3602165c8f3e50d5945e322146c493aa675888a7fd2', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('95', '员工95', 'emp95', '1000:056b9f3fe14c90d8ffc5f522e925cef3b19007d598c45d94:14115c1ff156d73d0f84728a55c2c029da51f11ebc772272', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('96', '员工96', 'emp96', '1000:ee0fbc42e99f4890f97ecbdcf2d932808e88258d7d9fee93:1b8959996154acc253cb989049e6a0ab974abb6a70093e29', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('97', '员工97', 'emp97', '1000:7c73cf619ed0977a1003b393d979be60dc6f6c4f5ec68228:d55ea77e594e98afca642d38a8525c9848c916f2fff204f8', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('98', '员工98', 'emp98', '1000:82a5c42833459327c5cff277f1a7fcc7bc739745e3b96f7c:fa4ad82970082927b0ed37ad70e2bb0caf9e1a4d4f2ddbf9', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('99', '员工99', 'emp99', '1000:55f771559eec30964878cf8ae320605ab393ea9200e7a044:b253771fea420364ed60640c9a242b710056c1905f518151', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '3', '5');
INSERT INTO `employee` VALUES ('100', '员工100', 'emp100', '1000:c155be580d093ac7ddec883205b629424352b4bf4e558f97:7eae74d0d28ce32835bfe0031a77d9aaf7d4db8ee619661f', '保密', '2015-06-27', 'emp', '2000-06-08', '籍贯', '11111111111', 'mmmm@mmm.m', '11111111', '11111111111', '11111111111', '住址', '毕业院校', '2015-06-08', ' 员工', '0', null, null, '91', '1', '5');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuId` int(10) NOT NULL auto_increment,
  `menuCode` varchar(255) NOT NULL,
  `menuName` varchar(255) NOT NULL,
  `menuPath` varchar(255) default NULL,
  `parentMenuId` int(10) default NULL,
  PRIMARY KEY  (`menuId`),
  UNIQUE KEY `menuCode` (`menuCode`),
  UNIQUE KEY `menuPN` (`menuName`,`parentMenuId`),
  KEY `parentMenuId` (`parentMenuId`),
  CONSTRAINT `idpk` FOREIGN KEY (`parentMenuId`) REFERENCES `menu` (`menuId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '1', '日报设定', null, null);
INSERT INTO `menu` VALUES ('2', '2', 'PRP阶段设定', 'daily/setting/prp', '1');
INSERT INTO `menu` VALUES ('3', '3', '部门设定', 'daily/setting/dept', '1');
INSERT INTO `menu` VALUES ('4', '4', '项目设定', 'daily/setting/proj', '1');
INSERT INTO `menu` VALUES ('5', '5', '日报登录', null, null);
INSERT INTO `menu` VALUES ('6', '6', '日报管理', 'daily/entry/manage', '5');
INSERT INTO `menu` VALUES ('7', '7', '日报审核', 'daily/entry/check', '5');
INSERT INTO `menu` VALUES ('8', '8', '个人日报查询', null, null);
INSERT INTO `menu` VALUES ('9', '9', '按日期查询', 'daily/query/personal/date', '8');
INSERT INTO `menu` VALUES ('10', '10', '按项目查询', 'daily/query/personal/proj', '8');
INSERT INTO `menu` VALUES ('11', '11', '未写日报查询', 'daily/query/personal/unsubmit', '8');
INSERT INTO `menu` VALUES ('12', '12', '个人周报导出', 'daily/query/personal/export', '8');
INSERT INTO `menu` VALUES ('13', '13', '部门工作量查询', null, null);
INSERT INTO `menu` VALUES ('14', '14', '按项目查询', 'daily/query/dept/proj', '13');
INSERT INTO `menu` VALUES ('15', '15', '项目投入汇总', 'daily/query/dept/summary', '13');
INSERT INTO `menu` VALUES ('16', '16', '部门工作量导出', 'daily/query/dept/export', '13');
INSERT INTO `menu` VALUES ('17', '17', '部门未写日报查询', 'daily/query/dept/unsubmit', '13');
INSERT INTO `menu` VALUES ('18', '18', '部门未审核日报查询', 'daily/query/dept/uncheck', '13');
INSERT INTO `menu` VALUES ('19', '19', '项目工作量查询', null, null);
INSERT INTO `menu` VALUES ('20', '20', '按里程碑查询', 'daily/query/proj/milestone', '19');
INSERT INTO `menu` VALUES ('21', '21', '项目投入汇总', 'daily/query/proj/summary', '19');
INSERT INTO `menu` VALUES ('22', '22', '用户管理', null, null);
INSERT INTO `menu` VALUES ('23', '23', '个人信息管理', 'info/user', '22');
INSERT INTO `menu` VALUES ('24', '24', '员工信息管理', 'info/employee', '22');
INSERT INTO `menu` VALUES ('25', '25', '系统管理', null, null);
INSERT INTO `menu` VALUES ('26', '26', '角色管理', 'admin/role', '25');
INSERT INTO `menu` VALUES ('27', '27', '权限管理', 'admin/permission', '25');
INSERT INTO `menu` VALUES ('28', '28', '菜单管理', 'admin/menu', '25');
INSERT INTO `menu` VALUES ('29', '29', '按部门查询', 'daily/query/proj/dept', '19');

-- ----------------------------
-- Table structure for perminfo
-- ----------------------------
DROP TABLE IF EXISTS `perminfo`;
CREATE TABLE `perminfo` (
  `perId` int(10) unsigned NOT NULL auto_increment,
  `perName` varchar(255) NOT NULL,
  `perPath` varchar(255) default NULL,
  `leaderPermissionId` int(10) unsigned default NULL,
  PRIMARY KEY  (`perId`),
  KEY `leaderPermissionId` (`leaderPermissionId`),
  CONSTRAINT `perminfo_ibfk_1` FOREIGN KEY (`leaderPermissionId`) REFERENCES `perminfo` (`perId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of perminfo
-- ----------------------------
INSERT INTO `perminfo` VALUES ('1', '系统管理', '*', null);
INSERT INTO `perminfo` VALUES ('5', '个人信息', 'info/user', null);
INSERT INTO `perminfo` VALUES ('6', '日报管理', 'daily/entry/manage', null);
INSERT INTO `perminfo` VALUES ('7', '日报登录', 'daily/entry', '1');

-- ----------------------------
-- Table structure for prj
-- ----------------------------
DROP TABLE IF EXISTS `prj`;
CREATE TABLE `prj` (
  `prjId` int(10) unsigned NOT NULL auto_increment,
  `prjCode` varchar(255) NOT NULL,
  `prjName` varchar(255) NOT NULL,
  `startDate` date default NULL,
  `endDate` date default NULL,
  `prjRemark` varchar(255) default NULL,
  `prjStatus` enum('关闭','挂起','运行中') default '运行中',
  PRIMARY KEY  (`prjId`),
  UNIQUE KEY `prjCode` (`prjCode`),
  UNIQUE KEY `prjName` (`prjName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prj
-- ----------------------------
INSERT INTO `prj` VALUES ('1', 'P1', '项目1', '2015-05-20', '2015-07-30', '项目1fdsg', '运行中');
INSERT INTO `prj` VALUES ('2', 'P2', '项目2', '2015-02-03', '2015-07-24', '项目2', '运行中');
INSERT INTO `prj` VALUES ('3', 'P3', '项目3', '2015-01-19', '2015-06-01', '项目3', '关闭');
INSERT INTO `prj` VALUES ('4', 'P4', '项目4', '2015-02-11', '2015-06-08', '项目4', '关闭');
INSERT INTO `prj` VALUES ('5', 'P5', '项目5', '2015-04-01', '2015-06-17', '项目5', '关闭');
INSERT INTO `prj` VALUES ('6', 'P6', '项目6', '2015-05-05', '2015-06-29', '项目6', '关闭');
INSERT INTO `prj` VALUES ('7', 'P7', '项目7', '2015-06-03', '2015-08-04', '项目7', '运行中');
INSERT INTO `prj` VALUES ('8', 'P8', '项目8', '2015-06-10', '2015-08-11', '项目8', '运行中');
INSERT INTO `prj` VALUES ('9', 'P9', '项目9', '2015-06-23', '2015-08-20', '项目9', '运行中');
INSERT INTO `prj` VALUES ('10', 'P10', '项目10', '2015-07-01', '2015-09-16', '项目10', '运行中');
INSERT INTO `prj` VALUES ('12', 'fdg', 'hdf', '2015-07-03', '2015-07-04', '暂无', null);

-- ----------------------------
-- Table structure for prj_dept
-- ----------------------------
DROP TABLE IF EXISTS `prj_dept`;
CREATE TABLE `prj_dept` (
  `pdId` int(10) unsigned NOT NULL auto_increment,
  `prjId` int(10) unsigned default NULL,
  `deptId` int(10) unsigned default NULL,
  PRIMARY KEY  (`pdId`),
  KEY `prjId` (`prjId`),
  KEY `deptId` (`deptId`),
  CONSTRAINT `prj_dept_ibfk_1` FOREIGN KEY (`prjId`) REFERENCES `prj` (`prjId`) ON UPDATE CASCADE,
  CONSTRAINT `prj_dept_ibfk_2` FOREIGN KEY (`deptId`) REFERENCES `dept` (`deptId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prj_dept
-- ----------------------------
INSERT INTO `prj_dept` VALUES ('8', '2', '7');
INSERT INTO `prj_dept` VALUES ('9', '2', '8');
INSERT INTO `prj_dept` VALUES ('10', '2', '9');
INSERT INTO `prj_dept` VALUES ('11', '3', '11');
INSERT INTO `prj_dept` VALUES ('12', '3', '12');
INSERT INTO `prj_dept` VALUES ('13', '3', '13');
INSERT INTO `prj_dept` VALUES ('14', '4', '15');
INSERT INTO `prj_dept` VALUES ('15', '4', '16');
INSERT INTO `prj_dept` VALUES ('16', '4', '17');
INSERT INTO `prj_dept` VALUES ('17', '5', '19');
INSERT INTO `prj_dept` VALUES ('18', '5', '20');
INSERT INTO `prj_dept` VALUES ('19', '5', '21');
INSERT INTO `prj_dept` VALUES ('20', '6', '7');
INSERT INTO `prj_dept` VALUES ('21', '6', '8');
INSERT INTO `prj_dept` VALUES ('22', '6', '9');
INSERT INTO `prj_dept` VALUES ('23', '7', '11');
INSERT INTO `prj_dept` VALUES ('24', '7', '12');
INSERT INTO `prj_dept` VALUES ('25', '7', '13');
INSERT INTO `prj_dept` VALUES ('26', '8', '15');
INSERT INTO `prj_dept` VALUES ('27', '8', '16');
INSERT INTO `prj_dept` VALUES ('28', '8', '17');
INSERT INTO `prj_dept` VALUES ('29', '9', '19');
INSERT INTO `prj_dept` VALUES ('30', '9', '20');
INSERT INTO `prj_dept` VALUES ('31', '9', '21');
INSERT INTO `prj_dept` VALUES ('32', '10', '3');
INSERT INTO `prj_dept` VALUES ('33', '10', '5');
INSERT INTO `prj_dept` VALUES ('34', '1', '3');
INSERT INTO `prj_dept` VALUES ('35', '1', '4');
INSERT INTO `prj_dept` VALUES ('36', '1', '5');

-- ----------------------------
-- Table structure for prj_emp
-- ----------------------------
DROP TABLE IF EXISTS `prj_emp`;
CREATE TABLE `prj_emp` (
  `peId` int(10) unsigned NOT NULL auto_increment,
  `prjId` int(10) unsigned default NULL,
  `empId` int(10) unsigned default NULL,
  `prId` int(10) unsigned default NULL,
  PRIMARY KEY  (`peId`),
  KEY `prjId` (`prjId`),
  KEY `empId` (`empId`),
  KEY `prId` (`prId`),
  CONSTRAINT `prj_emp_ibfk_1` FOREIGN KEY (`prjId`) REFERENCES `prj` (`prjId`) ON UPDATE CASCADE,
  CONSTRAINT `prj_emp_ibfk_2` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON UPDATE CASCADE,
  CONSTRAINT `prj_emp_ibfk_3` FOREIGN KEY (`prId`) REFERENCES `prj_resp` (`prId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prj_emp
-- ----------------------------
INSERT INTO `prj_emp` VALUES ('9', '1', '2', '1');
INSERT INTO `prj_emp` VALUES ('10', '1', '3', '2');
INSERT INTO `prj_emp` VALUES ('11', '1', '4', '3');
INSERT INTO `prj_emp` VALUES ('12', '1', '5', '4');
INSERT INTO `prj_emp` VALUES ('13', '1', '6', '5');
INSERT INTO `prj_emp` VALUES ('14', '2', '19', '1');
INSERT INTO `prj_emp` VALUES ('15', '2', '20', '2');
INSERT INTO `prj_emp` VALUES ('16', '2', '21', '3');
INSERT INTO `prj_emp` VALUES ('17', '2', '25', '4');
INSERT INTO `prj_emp` VALUES ('18', '2', '26', '5');
INSERT INTO `prj_emp` VALUES ('19', '2', '27', '6');
INSERT INTO `prj_emp` VALUES ('20', '2', '31', '7');
INSERT INTO `prj_emp` VALUES ('21', '2', '32', '8');
INSERT INTO `prj_emp` VALUES ('22', '2', '33', '9');
INSERT INTO `prj_emp` VALUES ('23', '1', '8', '6');
INSERT INTO `prj_emp` VALUES ('24', '1', '7', '12');
INSERT INTO `prj_emp` VALUES ('25', '1', '9', '12');
INSERT INTO `prj_emp` VALUES ('26', '1', '10', '12');

-- ----------------------------
-- Table structure for prj_prp
-- ----------------------------
DROP TABLE IF EXISTS `prj_prp`;
CREATE TABLE `prj_prp` (
  `ppId` int(10) unsigned NOT NULL auto_increment,
  `prpId` int(10) unsigned default NULL,
  `prjId` int(10) unsigned default NULL,
  PRIMARY KEY  (`ppId`),
  KEY `prpId` (`prpId`),
  KEY `prjId` (`prjId`),
  CONSTRAINT `prj_prp_ibfk_1` FOREIGN KEY (`prpId`) REFERENCES `prp` (`prpId`) ON UPDATE CASCADE,
  CONSTRAINT `prj_prp_ibfk_2` FOREIGN KEY (`prjId`) REFERENCES `prj` (`prjId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prj_prp
-- ----------------------------
INSERT INTO `prj_prp` VALUES ('18', '2', '2');
INSERT INTO `prj_prp` VALUES ('19', '3', '2');
INSERT INTO `prj_prp` VALUES ('20', '4', '2');
INSERT INTO `prj_prp` VALUES ('21', '3', '3');
INSERT INTO `prj_prp` VALUES ('22', '4', '3');
INSERT INTO `prj_prp` VALUES ('23', '5', '3');
INSERT INTO `prj_prp` VALUES ('24', '1', '4');
INSERT INTO `prj_prp` VALUES ('25', '2', '4');
INSERT INTO `prj_prp` VALUES ('26', '3', '4');
INSERT INTO `prj_prp` VALUES ('27', '2', '5');
INSERT INTO `prj_prp` VALUES ('28', '3', '5');
INSERT INTO `prj_prp` VALUES ('29', '4', '5');
INSERT INTO `prj_prp` VALUES ('30', '3', '6');
INSERT INTO `prj_prp` VALUES ('31', '4', '6');
INSERT INTO `prj_prp` VALUES ('32', '5', '6');
INSERT INTO `prj_prp` VALUES ('33', '1', '7');
INSERT INTO `prj_prp` VALUES ('34', '2', '7');
INSERT INTO `prj_prp` VALUES ('35', '3', '7');
INSERT INTO `prj_prp` VALUES ('36', '2', '8');
INSERT INTO `prj_prp` VALUES ('37', '3', '8');
INSERT INTO `prj_prp` VALUES ('38', '4', '8');
INSERT INTO `prj_prp` VALUES ('39', '3', '9');
INSERT INTO `prj_prp` VALUES ('40', '4', '9');
INSERT INTO `prj_prp` VALUES ('41', '5', '9');
INSERT INTO `prj_prp` VALUES ('42', '1', '10');
INSERT INTO `prj_prp` VALUES ('43', '2', '10');
INSERT INTO `prj_prp` VALUES ('44', '3', '10');
INSERT INTO `prj_prp` VALUES ('45', '1', '1');
INSERT INTO `prj_prp` VALUES ('46', '3', '1');
INSERT INTO `prj_prp` VALUES ('47', '4', '1');

-- ----------------------------
-- Table structure for prj_resp
-- ----------------------------
DROP TABLE IF EXISTS `prj_resp`;
CREATE TABLE `prj_resp` (
  `prId` int(10) unsigned NOT NULL auto_increment,
  `resName` varchar(255) default NULL,
  PRIMARY KEY  (`prId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prj_resp
-- ----------------------------
INSERT INTO `prj_resp` VALUES ('1', '项目经理');
INSERT INTO `prj_resp` VALUES ('2', '法律/认证');
INSERT INTO `prj_resp` VALUES ('3', '生产负责人');
INSERT INTO `prj_resp` VALUES ('4', '服务负责人');
INSERT INTO `prj_resp` VALUES ('5', '研发负责人');
INSERT INTO `prj_resp` VALUES ('6', '市场负责人');
INSERT INTO `prj_resp` VALUES ('7', '应用负责人');
INSERT INTO `prj_resp` VALUES ('8', '质量负责人');
INSERT INTO `prj_resp` VALUES ('9', '财务负责人');
INSERT INTO `prj_resp` VALUES ('10', '验证负责人');
INSERT INTO `prj_resp` VALUES ('11', '采购负责人');
INSERT INTO `prj_resp` VALUES ('12', '其他参与人员');

-- ----------------------------
-- Table structure for prp
-- ----------------------------
DROP TABLE IF EXISTS `prp`;
CREATE TABLE `prp` (
  `prpId` int(10) unsigned NOT NULL auto_increment,
  `prpName` varchar(255) NOT NULL,
  `prpAbbr` varchar(255) NOT NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`prpId`),
  UNIQUE KEY `prpName` (`prpName`),
  UNIQUE KEY `prpAbbr` (`prpAbbr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prp
-- ----------------------------
INSERT INTO `prp` VALUES ('1', '项目阶段1', 'PRP1', '');
INSERT INTO `prp` VALUES ('2', '项目阶段2', 'PRP2', '项目阶段2');
INSERT INTO `prp` VALUES ('3', '项目阶段3', 'PRP3', '项目阶段3');
INSERT INTO `prp` VALUES ('4', '项目阶段4', 'PRP4', '项目阶段4');
INSERT INTO `prp` VALUES ('5', '项目阶段5', 'PRP5', '项目阶段5');
INSERT INTO `prp` VALUES ('6', '项目阶段6', 'PRP6', '项目阶段6');
INSERT INTO `prp` VALUES ('7', '项目阶段7', 'PRP7', '项目阶段7');
INSERT INTO `prp` VALUES ('9', '项目阶段9', 'PRP9', '项目阶段9');
INSERT INTO `prp` VALUES ('10', '项目阶段10', 'PRP10', '项目阶段10');
INSERT INTO `prp` VALUES ('11', 'PRP11', 'PRP11', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(10) unsigned NOT NULL auto_increment,
  `roleCode` varchar(255) NOT NULL,
  `roleName` varchar(255) NOT NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`roleId`),
  UNIQUE KEY `roleCode` (`roleCode`),
  UNIQUE KEY `roleName` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'R0', '管理员', '管理员');
INSERT INTO `role` VALUES ('2', 'R1', '领导', '领导');
INSERT INTO `role` VALUES ('3', 'R2', '普通员工', '普通员工');
INSERT INTO `role` VALUES ('5', 'lsg', '临时工', null);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `rpid` int(10) unsigned NOT NULL auto_increment,
  `perId` int(10) unsigned default NULL,
  `roleId` int(10) unsigned default NULL,
  PRIMARY KEY  (`rpid`),
  KEY `perId` (`perId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`perId`) REFERENCES `perminfo` (`perId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('6', '5', '3');
INSERT INTO `role_permission` VALUES ('7', '6', '3');
INSERT INTO `role_permission` VALUES ('9', '1', '2');
INSERT INTO `role_permission` VALUES ('10', '7', '5');

-- ----------------------------
-- Table structure for warning
-- ----------------------------
DROP TABLE IF EXISTS `warning`;
CREATE TABLE `warning` (
  `warningId` int(10) unsigned NOT NULL auto_increment,
  `empId` int(10) unsigned NOT NULL,
  `type` enum('未提交','未审核') NOT NULL default '未提交',
  PRIMARY KEY  (`warningId`),
  UNIQUE KEY `empId` (`empId`,`type`),
  CONSTRAINT `warning_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warning
-- ----------------------------
INSERT INTO `warning` VALUES ('3', '1', '未审核');
INSERT INTO `warning` VALUES ('6', '3', '未提交');
INSERT INTO `warning` VALUES ('7', '4', '未提交');
INSERT INTO `warning` VALUES ('8', '5', '未提交');
INSERT INTO `warning` VALUES ('10', '7', '未提交');
INSERT INTO `warning` VALUES ('11', '8', '未提交');
INSERT INTO `warning` VALUES ('12', '9', '未提交');
INSERT INTO `warning` VALUES ('13', '10', '未提交');
INSERT INTO `warning` VALUES ('14', '19', '未提交');
INSERT INTO `warning` VALUES ('15', '20', '未提交');
INSERT INTO `warning` VALUES ('16', '21', '未提交');
INSERT INTO `warning` VALUES ('17', '25', '未提交');
INSERT INTO `warning` VALUES ('18', '26', '未提交');
INSERT INTO `warning` VALUES ('19', '27', '未提交');
INSERT INTO `warning` VALUES ('20', '31', '未提交');
INSERT INTO `warning` VALUES ('21', '32', '未提交');
INSERT INTO `warning` VALUES ('22', '33', '未提交');

-- ----------------------------
-- Procedure structure for getSubDeptIds
-- ----------------------------
DROP PROCEDURE IF EXISTS `getSubDeptIds`;
DELIMITER ;;
CREATE PROCEDURE `getSubDeptIds`(IN `tarId` INT UNSIGNED, IN `tableName` VARCHAR(255))
BEGIN

	DECLARE currCount INT DEFAULT 0;

	SET @SQL = CONCAT('DROP TABLE IF EXISTS ', tableName, ';');
	PREPARE stmt FROM @SQL;
	EXECUTE stmt ;
	SET @SQL = CONCAT('CREATE TEMPORARY TABLE ', tableName, '(id INT UNSIGNED PRIMARY KEY) ENGINE = MEMORY;');
	PREPARE stmt FROM @SQL;
	EXECUTE stmt ;

	DROP TABLE IF EXISTS tmp_currsubdeptids;
	CREATE TEMPORARY TABLE tmp_currsubdeptids(
		id INT UNSIGNED PRIMARY KEY
	) ENGINE = MEMORY;

	DROP TABLE IF EXISTS tmp_tmp_currsubdeptids;
	CREATE TEMPORARY TABLE tmp_tmp_currsubdeptids(
		id INT UNSIGNED PRIMARY KEY
	) ENGINE = MEMORY;

	INSERT INTO tmp_currsubdeptids SELECT deptId FROM dept WHERE seniorDeptId = tarId;

	repeat

		SET @SQL = CONCAT('INSERT INTO ', tableName, ' SELECT id FROM tmp_currsubdeptids');
		PREPARE stmt FROM @SQL;
		EXECUTE stmt ;

		SELECT COUNT(*) INTO currCount FROM tmp_currsubdeptids;
		DELETE FROM tmp_tmp_currsubdeptids;
		INSERT INTO tmp_tmp_currsubdeptids SELECT deptId FROM dept WHERE EXISTS (SELECT id FROM tmp_currsubdeptids WHERE dept.seniorDeptId = id);
		DELETE FROM tmp_currsubdeptids;
		INSERT INTO tmp_currsubdeptids SELECT id FROM tmp_tmp_currsubdeptids;
	until currCount = 0
	end repeat;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for getSubDeptIdsWithSelf
-- ----------------------------
DROP PROCEDURE IF EXISTS `getSubDeptIdsWithSelf`;
DELIMITER ;;
CREATE PROCEDURE `getSubDeptIdsWithSelf`(IN `tarId` INT UNSIGNED, IN `tableName` VARCHAR(255))
BEGIN

	DECLARE currCount INT DEFAULT 0;

	SET @SQL = CONCAT('DROP TABLE IF EXISTS ', tableName, ';');
	PREPARE stmt FROM @SQL;
	EXECUTE stmt ;
	SET @SQL = CONCAT('CREATE TEMPORARY TABLE ', tableName, '(id INT UNSIGNED PRIMARY KEY) ENGINE = MEMORY;');
	PREPARE stmt FROM @SQL;
	EXECUTE stmt ;

	DROP TABLE IF EXISTS tmp_currsubdeptids;
	CREATE TEMPORARY TABLE tmp_currsubdeptids(
		id INT UNSIGNED PRIMARY KEY
	) ENGINE = MEMORY;

	DROP TABLE IF EXISTS tmp_tmp_currsubdeptids;
	CREATE TEMPORARY TABLE tmp_tmp_currsubdeptids(
		id INT UNSIGNED PRIMARY KEY
	) ENGINE = MEMORY;

	INSERT INTO tmp_currsubdeptids VALUES(tarId);

	repeat

		SET @SQL = CONCAT('INSERT INTO ', tableName, ' SELECT id FROM tmp_currsubdeptids');
		PREPARE stmt FROM @SQL;
		EXECUTE stmt ;

		SELECT COUNT(*) INTO currCount FROM tmp_currsubdeptids;
		DELETE FROM tmp_tmp_currsubdeptids;
		INSERT INTO tmp_tmp_currsubdeptids SELECT deptId FROM dept WHERE EXISTS (SELECT id FROM tmp_currsubdeptids WHERE dept.seniorDeptId = id);
		DELETE FROM tmp_currsubdeptids;
		INSERT INTO tmp_currsubdeptids SELECT id FROM tmp_tmp_currsubdeptids;
	until currCount = 0
	end repeat;

END
;;
DELIMITER ;
