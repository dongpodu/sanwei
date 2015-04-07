# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: sanwei
# ------------------------------------------------------
# Server version 5.0.18-nt

#
# Source for table t_recourse
#

DROP TABLE IF EXISTS `t_recourse`;
CREATE TABLE `t_recourse` (
  `id` int(11) NOT NULL auto_increment,
  `resourceType` varchar(5) NOT NULL default '' COMMENT '资源类型 （视屏/课件）',
  `primaryCategory` varchar(50) NOT NULL default '' COMMENT '一级分类',
  `sencondCategory` varchar(50) NOT NULL default '' COMMENT '二级分类',
  `thirdCategory` varchar(50) NOT NULL default '' COMMENT '三级分类',
  `point` varchar(500) NOT NULL default '' COMMENT '知识点',
  `courseName` varchar(200) default NULL COMMENT '文件名称（用户填的名称）',
  `outline` varchar(5000) default NULL COMMENT '大纲',
  `resourceUrl` varchar(100) NOT NULL default '' COMMENT '资源的url',
  `price` double default '0' COMMENT '价格',
  `coverUrl` varchar(100) NOT NULL default '' COMMENT '封面图片地址',
  `fileType` varchar(10) default NULL COMMENT '文件类型',
  `status` int(6) NOT NULL default '0' COMMENT '0未审核  1不通过  2审核通过',
  `createAt` datetime default NULL COMMENT '创建时间',
  `creatorId` int(11) NOT NULL default '0',
  `creatName` varchar(100) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学习资源（视屏/课件）';

DROP TABLE IF EXISTS `t_liveCourse`;
CREATE TABLE `t_liveCourse` (
  `id` int(11) NOT NULL auto_increment,
  `primaryCategory` varchar(50) NOT NULL default '' COMMENT '一级分类',
  `sencondCategory` varchar(50) NOT NULL default '' COMMENT '二级分类',
  `thirdCategory` varchar(50) NOT NULL default '' COMMENT '三级分类',
  `point` varchar(500) NOT NULL default '' COMMENT '知识点',
  `name` varchar(200) default NULL COMMENT '文件名称（用户填的名称）',
  `outline` varchar(5000) default NULL COMMENT '大纲',
  `beginTime` datetime default NULL COMMENT '上课时间',
  `duration` int default 0 COMMENT '上课时长（单位：分钟）',
  `studentCount` int default 0 COMMENT ' 学生人数',
  `price` double default '0' COMMENT '价格',
  `coverUrl` varchar(100) NOT NULL default '' COMMENT '封面图片地址',
  `fileType` varchar(10) default NULL COMMENT '文件类型',
  `status` int(6) NOT NULL default '0' COMMENT '0未审核  1不通过  2审核通过',
  `createAt` datetime default NULL COMMENT '创建时间',
  `creatorId` int(11) NOT NULL default '0',
  `creatName` varchar(100) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直播课程';

#
# Source for table t_resource_comment
#

DROP TABLE IF EXISTS `t_resource_comment`;
CREATE TABLE `t_resource_comment` (
  `id` int(11) NOT NULL auto_increment,
  `resourceId` int(11) NOT NULL default '0',
  `score` int(11) default NULL COMMENT '评分（1、2、3、4、5）',
  `detail` varchar(5000) default NULL COMMENT '评价详情',
  `creatorId` int(11) NOT NULL default '0',
  `creatName` varchar(100) default NULL,
  `createAt` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源评论';

#
# Source for table t_user
#

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `email` varchar(100) NOT NULL default '' COMMENT '邮箱（用邮箱注册）',
  `password` varchar(50) NOT NULL default '' COMMENT '密码',
  `sex` varchar(1) default NULL COMMENT '性别',
  `name` varchar(50) default NULL COMMENT '姓名',
  `province` varchar(50) default NULL COMMENT '省份',
  `city` varchar(50) default NULL COMMENT '所在市',
  `profileUrl` varchar(100) default NULL COMMENT '头像地址',
  `createAt` datetime default NULL COMMENT '注册时间',
  `updateAt` datetime default NULL COMMENT '更新时间',
  `userType` int default NULL COMMENT '用户类型（1：教师，2：学生）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
