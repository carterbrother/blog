create table if not exists flyway_web_server
(
    id           int auto_increment comment 'id'
        primary key,
    server_ip    varchar(30)                        not null comment 'web服务器IP',
    ssh_port     int                                not null comment 'ssh端口',
    ssh_username varchar(30)                        not null comment 'ssh账号',
    ssh_password varchar(30)                        not null comment 'ssh密码',
    memo         varchar(100)                       not null comment '别名或者备注',
    created      datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint ux_serverip
        unique (server_ip) comment '服务器IP唯一'
) comment 'web服务器资源' ;

