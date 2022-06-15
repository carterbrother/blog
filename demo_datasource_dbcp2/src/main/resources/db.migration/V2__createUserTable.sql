
create database if not exists demo_datasource default character set UTF8mb4;
use demo_datasource;
drop table if exists user_login ;

create table user_login(
    id int auto_increment primary key comment '主键',
    user_name varchar(50) not null comment '用户名',
    password  char(32) not null comment '密码',
    sex tinyint not null default 1 comment '1男2女',
    note varchar(256) comment '备注'
)
