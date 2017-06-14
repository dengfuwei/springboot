/**
用户表
state状态：1-正常；2-禁用
role角色：1-普通；2-管理员；3-超级管理员
*/
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	login_name varchar(32) NOT NULL UNIQUE COMMENT '登录名',
	login_pwd varchar(128) NOT NULL COMMENT '登录密码',
	state tinyint(4) NOT NULL COMMENT '状态',
	role tinyint(4) NOT NULL COMMENT '角色',
	create_time datetime NOT NULL COMMENT '创建时间',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	update_time datetime NOT NULL COMMENT '最后更新时间',
	update_user_id bigint(20) NOT NULL COMMENT '最后更新人ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO `t_user` (`id`, `login_name`, `login_pwd`, `state`, `role`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) 
VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '3', '2017-06-13 20:47:14', '0', '2017-06-13 20:47:19', '0');

/**
分部表
*/
DROP TABLE IF EXISTS t_dept;
CREATE TABLE t_dept (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	serial_number bigint(20) NOT NULL COMMENT '序号',
	name varchar(128) NOT NULL COMMENT '名称',
	parent_id bigint(20) NOT NULL COMMENT '父级ID',
	create_time datetime NOT NULL COMMENT '创建时间',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	update_time datetime NOT NULL COMMENT '最后更新时间',
	update_user_id bigint(20) NOT NULL COMMENT '最后更新人ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分部表';

/**
通讯录表
*/
DROP TABLE IF EXISTS t_tel_list;
CREATE TABLE t_tel_list (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	serial_number bigint(20) NOT NULL COMMENT '序号',
	user_id bigint(20) NOT NULL UNIQUE COMMENT '用户ID',
	name varchar(30) NOT NULL COMMENT '姓名',
	dept_id bigint(20) NOT NULL COMMENT '分部ID',
	dept_name varchar(128) NOT NULL COMMENT '分部名称',
	mobile varchar(64) NOT NULL COMMENT '手机',
	vnet_number varchar(32) NOT NULL COMMENT 'V网短号',
	telephone varchar(64) NOT NULL COMMENT '电话',
	position varchar(128) NOT NULL COMMENT '职位',
	email varchar(64) NOT NULL COMMENT '邮箱',
	create_time datetime NOT NULL COMMENT '创建时间',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	update_time datetime NOT NULL COMMENT '最后更新时间',
	update_user_id bigint(20) NOT NULL COMMENT '最后更新人ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通讯录表';