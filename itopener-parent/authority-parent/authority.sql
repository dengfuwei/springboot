/**
用户表
state-状态：1.启用;2.停用;
*/
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	name varchar(30) NOT NULL COMMENT '名称',
	state tinyint(4) NOT NULL COMMENT '状态',
	params varchar(1024) NOT NULL COMMENT '自定义参数',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	create_user varchar(64) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user_id bigint(20) NOT NULL COMMENT '修改人ID',
	update_user varchar(64) NOT NULL COMMENT '修改人',
	update_time datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/**
角色表
state-状态：1.启用;2.停用;
*/
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	name varchar(30) NOT NULL COMMENT '名称',
	state tinyint(4) NOT NULL COMMENT '状态',
	params varchar(1024) NOT NULL COMMENT '自定义参数',
	order_number bigint(20) NOT NULL COMMENT '顺序编号',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	create_user varchar(64) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user_id bigint(20) NOT NULL COMMENT '修改人ID',
	update_user varchar(64) NOT NULL COMMENT '修改人',
	update_time datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/**
用户-角色表
*/
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	user_id bigint(20) NOT NULL COMMENT '用户ID',
	role_id bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色表';

/**
应用表
state-状态：1.启用;2.停用;
*/
DROP TABLE IF EXISTS t_app;
CREATE TABLE t_app (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	name varchar(30) NOT NULL COMMENT '名称',
	state tinyint(4) NOT NULL COMMENT '状态',
	params varchar(1024) NOT NULL COMMENT '自定义参数',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	create_user varchar(64) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user_id bigint(20) NOT NULL COMMENT '修改人ID',
	update_user varchar(64) NOT NULL COMMENT '修改人',
	update_time datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';

/**
菜单表
state-状态：1.启用;2.停用;
*/
DROP TABLE IF EXISTS t_menu;
CREATE TABLE t_menu (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	name varchar(30) NOT NULL COMMENT '名称',
	state tinyint(4) NOT NULL COMMENT '状态',
	app_id bigint(20) NOT NULL COMMENT '所属应用ID',
	app_name varchar(64) NOT NULL COMMENT '所属应用名称',
	parent_id bigint(20) NOT NULL COMMENT '父级菜单ID',
	parent_name bigint(20) NOT NULL COMMENT '父级菜单名称',
	params varchar(1024) NOT NULL COMMENT '自定义参数',
	order_number bigint(20) NOT NULL COMMENT '顺序编号',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	create_user varchar(64) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user_id bigint(20) NOT NULL COMMENT '修改人ID',
	update_user varchar(64) NOT NULL COMMENT '修改人',
	update_time datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/**
角色-菜单表

DROP TABLE IF EXISTS t_role_menu;
CREATE TABLE t_role_menu (
  id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	role_id bigint(20) NOT NULL COMMENT '角色ID',
	menu_id bigint(20) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单表';
*/

/**
角色-菜单-功能按钮表
*/
DROP TABLE IF EXISTS t_role_menu_button;
CREATE TABLE t_role_menu_button (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	role_id bigint(20) NOT NULL COMMENT '角色ID',
	menu_id bigint(20) NOT NULL COMMENT '菜单ID',
	button_id bigint(20) NOT NULL COMMENT '功能按钮ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单-功能按钮表';

/**
功能按钮表
state-状态：1.启用;2.停用;
type-按钮类型：1.批量操作按钮；2.单行操作按钮；3.其他
*/
DROP TABLE IF EXISTS t_button;
CREATE TABLE t_button (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	name varchar(30) NOT NULL COMMENT '名称',
	state tinyint(4) NOT NULL COMMENT '状态',
	menu_id bigint(20) NOT NULL COMMENT '所属菜单ID',
	menu_name varchar(64) NOT NULL COMMENT '所属菜单名称',
	type tinyint(4) NOT NULL COMMENT '按钮类型',
	params varchar(1024) NOT NULL COMMENT '自定义参数',
	order_number bigint(20) NOT NULL COMMENT '顺序编号',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	create_user varchar(64) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user_id bigint(20) NOT NULL COMMENT '修改人ID',
	update_user varchar(64) NOT NULL COMMENT '修改人',
	update_time datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能按钮表';