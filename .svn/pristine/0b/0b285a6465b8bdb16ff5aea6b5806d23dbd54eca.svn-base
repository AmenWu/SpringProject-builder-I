/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.153
Source Server Version : 50617
Source Host           : 192.168.0.153:33060
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-08-29 10:41:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '唯一主键',
  `method_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '方法名',
  `manage_id` int(11) DEFAULT NULL COMMENT '操作人ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `start_time` datetime DEFAULT NULL COMMENT '请求开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `consum` int(11) DEFAULT NULL COMMENT '耗时',
  `args` longtext CHARACTER SET utf8 COMMENT '请求参数',
  `result` longtext CHARACTER SET utf8 COMMENT '返回结果',
  `status` int(11) DEFAULT NULL COMMENT '操作状态  0 成功 1失败',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_id`),
  KEY `method_name` (`method_name`),
  KEY `manage_id` (`manage_id`),
  KEY `permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager`;
CREATE TABLE `sys_manager` (
  `manage_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '管理人员id',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `head_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `status` varchar(1) DEFAULT '0' COMMENT '用户状态(0正常)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operator` int(10) DEFAULT NULL COMMENT '操作人员',
  `opera_time` datetime DEFAULT NULL COMMENT '操作时间',
  `phone` varchar(13) DEFAULT NULL COMMENT '联系电话',
  `id_key` varchar(32) DEFAULT NULL COMMENT '推荐KEY',
  PRIMARY KEY (`manage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_manager
-- ----------------------------
INSERT INTO `sys_manager` VALUES ('-1', 'admin', 'bced6fd149cfcdb85741768da12e41c6', '系统管理员', 'http://localhost:8080/risenb-manage-web/img/manager.jpg', '0', '2014-11-18 07:05:00', '1', '2017-08-09 23:33:40', '18311415392', '20141418095029441');

-- ----------------------------
-- Table structure for sys_manager_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager_role_rel`;
CREATE TABLE `sys_manager_role_rel` (
  `manage_id` int(10) DEFAULT NULL COMMENT '角色用户关联id',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `operator` int(10) DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime DEFAULT NULL COMMENT '操作时间',
  KEY `roleid` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_manager_role_rel
-- ----------------------------
INSERT INTO `sys_manager_role_rel` VALUES ('-1', '1', '1', '2017-08-10 09:25:48');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permission_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `title` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `detail` varchar(500) DEFAULT NULL COMMENT '详细',
  `status` int(10) DEFAULT '0' COMMENT '状态',
  `parent_id` int(10) DEFAULT '0' COMMENT '父级权限',
  `operator` int(10) DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime DEFAULT NULL COMMENT '操作时间',
  `is_menu` int(1) DEFAULT '0' COMMENT '0为是1为否',
  `sort` int(11) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL COMMENT '访问的url',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('-2', '退出登录', null, '0', '1', '1', '2017-08-16 14:27:47', '1', '6', 'login/logout.im');
INSERT INTO `sys_permission` VALUES ('-1', '登录', null, '0', '1', '1', '2017-08-16 13:53:58', '1', '5', 'login/login.im');
INSERT INTO `sys_permission` VALUES ('1', '系统管理', null, '0', '0', '1', '2014-12-05 17:01:56', '0', '1', null);
INSERT INTO `sys_permission` VALUES ('2', '管理员管理', null, '0', '1', '1', '2017-08-23 10:44:59', '0', '1', 'manage/manage.im');
INSERT INTO `sys_permission` VALUES ('3', '权限管理', null, '0', '1', '1', '2015-04-23 14:00:54', '0', '3', 'permission/navList.im');
INSERT INTO `sys_permission` VALUES ('4', '角色管理', null, '0', '1', '1', '2017-08-11 16:35:27', '0', '2', 'role/manage.im');
INSERT INTO `sys_permission` VALUES ('5', '日志管理', null, '0', '1', '1', '2015-02-03 10:55:57', '0', '4', 'syslog/manage.im');
INSERT INTO `sys_permission` VALUES ('6', '冻结管理员', null, '0', '2', '1', '2017-08-11 16:50:45', '1', '0', 'manage/frozen.im');
INSERT INTO `sys_permission` VALUES ('7', '删除管理员', null, '0', '2', '1', '2017-08-11 16:51:15', '1', '0', 'manage/del.im');
INSERT INTO `sys_permission` VALUES ('8', '修改密码', null, '0', '2', '1', '2017-02-14 13:51:16', '1', '3', 'manage/toModifyPwd.im');
INSERT INTO `sys_permission` VALUES ('9', '保存密码', null, '0', '2', '1', '2017-02-14 13:53:55', '1', '4', 'manage/modifyPwd.im');
INSERT INTO `sys_permission` VALUES ('10', '保存分配角色', null, '0', '2', '1', '2017-08-15 12:02:09', '1', '5', 'manage/assignRole.im');
INSERT INTO `sys_permission` VALUES ('11', '分配角色页面', null, '0', '2', '1', '2017-08-16 09:29:29', '1', '6', 'manage/toAssignRole.im');
INSERT INTO `sys_permission` VALUES ('12', '保存管理员页面', null, '0', '2', '1', '2017-08-22 16:03:22', '1', '0', 'manage/toSavePage.im');
INSERT INTO `sys_permission` VALUES ('13', '保存管理员', null, '0', '2', '1', '2017-08-11 16:51:50', '1', '1', 'manage/saveManager.im');
INSERT INTO `sys_permission` VALUES ('14', '权限列表', null, '0', '3', '1', '2017-08-22 16:09:02', '1', '0', 'permission/manage.im');
INSERT INTO `sys_permission` VALUES ('15', '新增权限页面', null, '0', '3', '1', '2017-02-13 16:10:22', '1', '1', 'permission/toSavePage.im');
INSERT INTO `sys_permission` VALUES ('16', '编辑权限页面', null, '0', '3', '1', '2017-02-13 16:11:00', '1', '2', 'permission/toSavePage.im');
INSERT INTO `sys_permission` VALUES ('17', '删除权限', null, '0', '3', '1', '2017-02-13 16:11:43', '1', '4', 'permission/del.im');
INSERT INTO `sys_permission` VALUES ('18', '保存权限', null, '0', '3', '1', '2017-02-13 16:13:59', '1', '1', 'permission/save.im');
INSERT INTO `sys_permission` VALUES ('19', '保存角色', null, '0', '4', '1', '2017-08-11 19:22:13', '1', '1', 'role/save.im');
INSERT INTO `sys_permission` VALUES ('20', '保存角色页面', null, '0', '4', '1', '2017-08-11 18:53:59', '1', '1', 'role/toSavePage.im');
INSERT INTO `sys_permission` VALUES ('21', '编辑角色', null, '0', '4', '1', '2017-08-11 16:40:50', '1', '0', 'role/save.im');
INSERT INTO `sys_permission` VALUES ('22', '删除角色', null, '0', '4', '1', '2017-08-31 16:35:27', '1', '0', 'role/del.im');
INSERT INTO `sys_permission` VALUES ('23', '日志详情', null, '0', '5', '1', '2017-08-15 10:30:57', '1', '1', 'syslog/detail.im');
INSERT INTO `sys_permission` VALUES ('24', '系统设置', null, '0', '0', null, '2017-08-23 08:59:55', '0', '2', 'ad');
INSERT INTO `sys_permission` VALUES ('25', '参数管理', null, '0', '24', null, '2017-08-17 15:40:24', '0', '1', 'system/param.im');

-- ----------------------------
-- Table structure for sys_permission_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role_rel`;
CREATE TABLE `sys_permission_role_rel` (
  `rel_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色权限',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(10) DEFAULT NULL COMMENT '权限id',
  `manage_id` int(10) DEFAULT NULL COMMENT '操作用户',
  `opera_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23826 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_role_rel
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `parent_id` int(10) DEFAULT NULL COMMENT '父级id',
  `status` int(1) DEFAULT '0' COMMENT '状态',
  `operator` int(10) DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('-1', '超级管理员', null, '0', '-1', '2017-08-17 15:40:36');
