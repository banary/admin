SET FOREIGN_KEY_CHECKS=0;

#用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `role` varchar(128) NOT NULL COMMENT '主键',
  `real_name` char(128) NOT NULL COMMENT '真实姓名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0:新建，1:启用，2:锁定',
  `remark` varchar(256) NOT NULL DEFAULT '' COMMENT '备注',
  `version` int(20) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

#系统日志表
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operator_id` bigint(20) NOT NULL COMMENT '操作人id',
  `operator_name` varchar(128) NOT NULL COMMENT '操作人姓名',
  `operate_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '操作类型：1:登录，1:启用，2:锁定',
  `operator_ip` varchar(128) NOT NULL COMMENT '操作人ip地址',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '操作内容',
  `version` int(20) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_operator_id` (`operator_id`),
  KEY `idx_operator_name` (`operator_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

#初始化用户数据
INSERT INTO `user`(user_name,password,role,real_name,mobile) VALUES ('admin', '$2a$04$KTLRUlYgbDXLjvuJ2trf1etfZTRwaEv98B0fg45P2Ulx7Ah7loP9W', 'ROLE_USER', 'admin', '17321128719');
