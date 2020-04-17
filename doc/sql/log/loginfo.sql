CREATE TABLE `sys_log_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `level` varchar(255) DEFAULT NULL COMMENT '级别',
  `method` varchar(255) NOT NULL COMMENT '方法名',
  `args` varchar(255) NOT NULL COMMENT '参数',
  `user_id` varchar(64) DEFAULT NULL COMMENT '操作人id',
  `user_name` varchar(64) DEFAULT NULL COMMENT '操作人',
  `create_time` varchar(255) NOT NULL,
  `describe` varchar(255) NOT NULL COMMENT '描述',
  `run_time` varchar(255) NOT NULL COMMENT '运行时间',
  `caller_ip` varchar(32) NOT NULL COMMENT '调用方ip',
  `local_host_ip` varchar(32) NOT NULL COMMENT '本地ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;