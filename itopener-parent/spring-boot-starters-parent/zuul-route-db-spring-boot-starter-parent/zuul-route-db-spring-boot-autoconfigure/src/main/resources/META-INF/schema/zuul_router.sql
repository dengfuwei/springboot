drop table if exists `zuul_route_config`;
create table `zuul_route_config` (
  `id` varchar(50) not null,
  `path` varchar(255) not null,
  `service_id` varchar(50) not null default '',
  `url` varchar(255) not null default '',
  `strip_prefix` tinyint(1) not null default 0,
  `retryable` tinyint(1) not null default 0,
  `sensitive_headers` varchar(255) not null default '',
  `custom_sensitive_headers` tinyint(1) not null default 0,
  `enabled` tinyint(1) not null default 0,
  `router_name` varchar(255) not null default '',
  primary key (`id`)
) engine=innodb default charset=utf8;