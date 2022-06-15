create table  if not exists flyway_user
(
    id           int auto_increment comment 'id' primary key,
    username varchar(30)                        not null comment '账号',
    password varchar(30)                        not null comment '密码',
    memo         varchar(100)                   not null comment '备注',
    created      datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint ux_username
    unique (username) comment '服务器IP唯一1'
) comment '用户表' ;

