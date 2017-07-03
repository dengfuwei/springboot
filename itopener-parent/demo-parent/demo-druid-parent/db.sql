DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`) COMMENT '姓名唯一约束'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO `t_user` VALUES ('1', 'fuwei.deng', '1', '9');