/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023-3-24 16:00:15                           */
/*==============================================================*/


drop table if exists goods;

drop table if exists pro_rush_account;

drop table if exists pro_rush_dealinfo;

drop table if exists pro_rush_purchaseInfo;

drop table if exists pro_rush_rushsale;

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   goods_id             bigint not null auto_increment comment '商品id',
   user_id              bigint comment '用户id',
   dept_id              bigint comment '部门id',
   name                 varchar(50) comment '商品名称',
   brand                varchar(20) comment '品牌',
   model                varchar(50) comment '规格型号',
   color                varchar(20) comment '颜色',
   size                 varchar(20) comment '尺寸',
   remark               varchar(100) comment '备注',
   primary key (goods_id)
);

alter table goods comment '商品信息表';

/*==============================================================*/
/* Table: pro_rush_account                                      */
/*==============================================================*/
create table pro_rush_account
(
   account_id           bigint not null auto_increment,
   user_id              bigint,
   dept_id              bigint,
   capital              bigint comment '本金',
   income               double(10,3) comment '收入',
   profit               double(10,3) comment '利润',
   net_profit           double(10,3) comment '净利润',
   primary key (account_id)
);

alter table pro_rush_account comment '抢购资金账户';

/*==============================================================*/
/* Table: pro_rush_dealinfo                                     */
/*==============================================================*/
create table pro_rush_dealinfo
(
   deal_id              bigint not null comment '交易id',
   user_id              bigint comment '用户id',
   dept_id              bigint comment '部门id',
   goods_id             bigint(20) comment '交易商品',
   deal_from            varchar(50) comment '卖方',
   deal_to              varchar(50) comment '买方',
   deal_num             varchar(8) comment '交易金额',
   deal_type            varchar(5) comment '交易类型-buy：我为买方 sale：我为卖方',
   handle_order_id      bigint,
   deal_time            time comment '交易时间',
   primary key (deal_id)
);

alter table pro_rush_dealinfo comment '抢购交易信息';

/*==============================================================*/
/* Table: pro_rush_purchaseInfo                                 */
/*==============================================================*/
create table pro_rush_purchaseInfo
(
   purchase_id          bigint not null auto_increment comment '主键',
   user_id              bigint comment '用户id',
   dept_id              bigint comment '部门id',
   purch_name           varchar(20) comment '购买人姓名',
   phone_num            varchar(11) comment '购买人手机号',
   goods_id             bigint(20) comment '购买商品',
   buy_from             varchar(20) comment '购买渠道',
   buy_price            varchar(10) comment '购买价格',
   buy_time             time comment '下单时间',
   order_num            varchar(30) comment '订单号-后六位',
   order_state          varchar(10) comment '订单状态',
   deal_state           varchar(6) comment '结算状态',
   deal_price           varchar(10) comment '中间价',
   deal_time            time comment '结算时间',
   rush_state           varchar(3) comment '抢购流转状态 发货 到货 退货',
   primary key (purchase_id)
);

alter table pro_rush_purchaseInfo comment '抢购人购买结算信息';

/*==============================================================*/
/* Table: pro_rush_rushsale                                     */
/*==============================================================*/
create table pro_rush_rushsale
(
   rushsale_id          bigint not null auto_increment comment '收购商结算id',
   user_id              bigint comment '用户id',
   dept_id              bigint comment '部门id',
   purchase_id          bigint comment '抢购人购买id',
   pay_price            varchar(10) comment '收购商结算价',
   pay_time             time comment '收购商结算时间',
   pay_from             varchar(50) comment '收购商',
   pay_type             varchar(50) comment '结算方式',
   deal_price           varchar(10) comment '中间价',
   deal_state           varchar(6),
   rush_state           varchar(20) comment '转送状态 转出 退回',
   primary key (rushsale_id)
);

alter table pro_rush_rushsale comment '出售交易信息表';

