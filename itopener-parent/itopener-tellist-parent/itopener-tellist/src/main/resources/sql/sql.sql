/**
分部表
*/
DROP TABLE IF EXISTS t_dept;
CREATE TABLE t_dept (
	id bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
  serial_number bigint(20) NOT NULL COMMENT '序号',
	name varchar(30) NOT NULL COMMENT '名称',
	parent_id bigint(20) NOT NULL COMMENT '父级ID',
	create_time datetime NOT NULL COMMENT '创建时间',
	create_user_id bigint(20) NOT NULL COMMENT '创建人ID',
	update_time datetime NOT NULL COMMENT '最后更新时间',
	update_user_id bigint(20) NOT NULL COMMENT '最后更新人ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分部表';