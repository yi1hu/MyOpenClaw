-- ----------------------------
-- 1. 创建留言表
-- ----------------------------
drop table if exists sys_message;
create table sys_message (
  message_id          bigint(20)      not null auto_increment    comment '留言ID',
  user_name           varchar(50)     default ''                 comment '用户姓名',
  contact_info        varchar(100)    default ''                 comment '联系方式',
  message_content     varchar(1000)   not null                   comment '留言内容',
  ip_address          varchar(50)     default ''                 comment 'IP地址',
  user_agent          varchar(500)    default ''                 comment '用户代理',
  status              char(1)         default '0'                comment '留言状态（0正常 1拉黑）',
  create_time         datetime                                   comment '创建时间',
  update_time         datetime                                   comment '更新时间',
  primary key (message_id)
) engine=innodb auto_increment=100 comment = '留言表';

-- ----------------------------
-- 2. 初始化数据
-- ----------------------------
insert into sys_message values(1, '张三', 'zhangsan@example.com', '这是一个很棒的留言板功能！', '127.0.0.1', 'Mozilla/5.0...', '0', now(), now());