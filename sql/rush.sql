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
   goods_id             bigint not null auto_increment comment '��Ʒid',
   user_id              bigint comment '�û�id',
   dept_id              bigint comment '����id',
   name                 varchar(50) comment '��Ʒ����',
   brand                varchar(20) comment 'Ʒ��',
   model                varchar(50) comment '����ͺ�',
   color                varchar(20) comment '��ɫ',
   size                 varchar(20) comment '�ߴ�',
   remark               varchar(100) comment '��ע',
   primary key (goods_id)
);

alter table goods comment '��Ʒ��Ϣ��';

/*==============================================================*/
/* Table: pro_rush_account                                      */
/*==============================================================*/
create table pro_rush_account
(
   account_id           bigint not null auto_increment,
   user_id              bigint,
   dept_id              bigint,
   capital              bigint comment '����',
   income               double(10,3) comment '����',
   profit               double(10,3) comment '����',
   net_profit           double(10,3) comment '������',
   primary key (account_id)
);

alter table pro_rush_account comment '�����ʽ��˻�';

/*==============================================================*/
/* Table: pro_rush_dealinfo                                     */
/*==============================================================*/
create table pro_rush_dealinfo
(
   deal_id              bigint not null comment '����id',
   user_id              bigint comment '�û�id',
   dept_id              bigint comment '����id',
   goods_id             bigint(20) comment '������Ʒ',
   deal_from            varchar(50) comment '����',
   deal_to              varchar(50) comment '��',
   deal_num             varchar(8) comment '���׽��',
   deal_type            varchar(5) comment '��������-buy����Ϊ�� sale����Ϊ����',
   handle_order_id      bigint,
   deal_time            time comment '����ʱ��',
   primary key (deal_id)
);

alter table pro_rush_dealinfo comment '����������Ϣ';

/*==============================================================*/
/* Table: pro_rush_purchaseInfo                                 */
/*==============================================================*/
create table pro_rush_purchaseInfo
(
   purchase_id          bigint not null auto_increment comment '����',
   user_id              bigint comment '�û�id',
   dept_id              bigint comment '����id',
   purch_name           varchar(20) comment '����������',
   phone_num            varchar(11) comment '�������ֻ���',
   goods_id             bigint(20) comment '������Ʒ',
   buy_from             varchar(20) comment '��������',
   buy_price            varchar(10) comment '����۸�',
   buy_time             time comment '�µ�ʱ��',
   order_num            varchar(30) comment '������-����λ',
   order_state          varchar(10) comment '����״̬',
   deal_state           varchar(6) comment '����״̬',
   deal_price           varchar(10) comment '�м��',
   deal_time            time comment '����ʱ��',
   rush_state           varchar(3) comment '������ת״̬ ���� ���� �˻�',
   primary key (purchase_id)
);

alter table pro_rush_purchaseInfo comment '�����˹��������Ϣ';

/*==============================================================*/
/* Table: pro_rush_rushsale                                     */
/*==============================================================*/
create table pro_rush_rushsale
(
   rushsale_id          bigint not null auto_increment comment '�չ��̽���id',
   user_id              bigint comment '�û�id',
   dept_id              bigint comment '����id',
   purchase_id          bigint comment '�����˹���id',
   pay_price            varchar(10) comment '�չ��̽����',
   pay_time             time comment '�չ��̽���ʱ��',
   pay_from             varchar(50) comment '�չ���',
   pay_type             varchar(50) comment '���㷽ʽ',
   deal_price           varchar(10) comment '�м��',
   deal_state           varchar(6),
   rush_state           varchar(20) comment 'ת��״̬ ת�� �˻�',
   primary key (rushsale_id)
);

alter table pro_rush_rushsale comment '���۽�����Ϣ��';

