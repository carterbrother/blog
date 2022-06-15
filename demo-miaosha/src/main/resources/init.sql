create table product
(
    id            bigint auto_increment primary key,
    product_name  varchar(60) comment '产品名称',
    store_qty     int comment '库存',
    product_price decimal(16, 2) comment '单价',
    version       int comment '版本',
    memo          varchar(256) comment '备注'
)
    comment '产品信息表';


create table order_list
(
    id          bigint auto_increment primary key,
    user_id     bigint comment '用户编号',
    product_id  bigint comment '产品id',
    price       decimal(16, 2) comment '价格',
    qty         int comment '数量',
    total_price decimal(16, 2) comment '总价',
    buy_time    timestamp comment '购买时间',
    memo        varchar(512) comment '备注'
) comment '购买信息表';