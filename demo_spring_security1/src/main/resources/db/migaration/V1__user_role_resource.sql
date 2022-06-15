use demo_datasource;

drop table if exists security_app;

create table security_app(
    id bigint unsigned auto_increment primary key comment '自增id',
    app_chinese_name varchar(50) not null comment '应用中文名称',
    note varchar(100) not null comment '备注'
)comment '应用表';

create unique index ux_appchinesename on security_app(app_chinese_name) comment '应用名必须唯一';


drop table if exists security_role;

create table security_role(
    id bigint unsigned auto_increment primary key comment '自增id',
    app_id bigint unsigned not null comment '应用id',
    role_english_name varchar(30) not null comment '角色英文名称',
    role_chinese_name varchar(30) not null comment '角色中文名称'
)comment '角色表';

create unique index ux_appid_rolename on security_role(app_id,role_english_name) comment '系统角色名必须唯一';


drop table if exists security_login;

create table security_login(
    id bigint unsigned auto_increment primary key comment '自增id',
    username varchar(50)  not null comment '用户名',
    password char(32) not null comment '密码',
    password_expire_date datetime not null  comment '密码过期时间',
    lock_flag int unsigned not null default 1 comment '锁定状态0锁定1正常',
    enable_flag int unsigned not null default 1 comment '可用状态1可用0不可用',
    created datetime default current_timestamp() comment '创建日期',
    updated datetime default current_timestamp() on update current_timestamp() comment '更新日期'
)comment '登录表';

create unique index ux_username on security_login(username) comment '登录用户名必须唯一';



drop table if exists security_login_role;

create table security_login_role(
    id bigint unsigned auto_increment primary key comment '自增id',
    login_id bigint unsigned  not null comment '登录id',
    role_id bigint unsigned  not null comment '角色id',
    created datetime default current_timestamp() comment '创建日期',
    updated datetime default current_timestamp() on update current_timestamp() comment '更新日期'
)comment '登录账号角色关系表';

create unique index ux_loginid_roleid on security_login_role(login_id, role_id)  comment '用户id跟角色一一对应';

# alter table security_login_role add constraint  fk_1 foreign key (role_id) references security_role(id) on DELETE restrict on UPDATE restrict ;
# alter table security_login_role add constraint  fk_2 foreign key (login_id) references security_login(id) on DELETE restrict on UPDATE restrict ;
# alter table security_role add constraint  fk_3 foreign key (app_id) references security_app(id) on DELETE restrict on UPDATE restrict ;

#
# drop table if exists security_resource;
#
# create table security_resource(
#     id bigint unsigned auto_increment primary key comment '自增id',
#     app_id bigint unsigned not null comment '应用id',
#     pid bigint unsigned  comment '父id',
#     resource_english_name varchar(30)  not null comment '资源英文名',
#     resource_chinese_name varchar(30)  not null comment '资源中文名',
#     resource_path varchar(100)  comment 'rest的path',
#     created datetime default current_timestamp() comment '创建日期',
#     updated datetime default current_timestamp() on update current_timestamp() comment '更新日期'
# )comment '后台系统前端资源配置';
#
# create unique index ux_loginid_roleid on security_resource(login_id, role_id)  comment '用户id跟角色一一对应';
