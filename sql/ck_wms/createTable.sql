CREATE TABLE `office_goods`
(
    `goods_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `goods_name` VARCHAR(50) NOT NULL COMMENT '商品名',
    `finance_code` VARCHAR(50) COMMENT '财务编码',
    `norms_model` VARCHAR(50) COMMENT '规格型号',
    `unit` VARCHAR(5) COMMENT '单位',
    `price` VARCHAR(15) COMMENT '单价',
    `class` VARCHAR(5) COMMENT '类别（是否固定资产）',
    `is_public` VARCHAR(5) COMMENT '公用or专用',
    `picture` VARCHAR(50) COMMENT '商品图片',
    PRIMARY KEY (`goods_id`)
) COMMENT='办公用品商品表';

CREATE TABLE `purchase_record` (
   `purchase_record_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
   `goods_id` BIGINT NOT NULL COMMENT '商品ID',
   `goods_name` VARCHAR(50) NOT NULL COMMENT '商品名',
   `norms_model` VARCHAR(50) COMMENT '规格型号',
   `unit` VARCHAR(5) COMMENT '单位',
   `quantity` VARCHAR(10) COMMENT '数量',
   `price` VARCHAR(15) COMMENT '单价',
   `class` VARCHAR(5) COMMENT '类别',
   `supplier` VARCHAR(100) NOT NULL COMMENT '供货方',
   `purchaser` VARCHAR(100) NOT NULL COMMENT '采购人',
   `use_to` VARCHAR(50) COMMENT '用途（一次性消耗或日常消耗）',
   `has_fapiao` VARCHAR(5) COMMENT '有无发票',
   `fapiao` VARCHAR(50) COMMENT '相应发票',
   `picture` VARCHAR(50) COMMENT '商品图片',
   PRIMARY KEY (`purchase_record_id`),
   FOREIGN KEY (`goods_id`) REFERENCES `office_goods`(`goods_id`) ON DELETE CASCADE
) COMMENT='采购记录表';

CREATE TABLE `receive_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `goods_id` BIGINT NOT NULL COMMENT '商品ID',
  `goods_name` VARCHAR(50) NOT NULL COMMENT '商品名',
  `norms_model` VARCHAR(50) COMMENT '规格型号',
  `quantity` DECIMAL(10, 2) NOT NULL COMMENT '领取数量',
  `recipient` VARCHAR(100) NOT NULL COMMENT '领用人',
  `receive_time` DATETIME NOT NULL COMMENT '领用时间',
  `use_to` VARCHAR(50) COMMENT '用途',
  `approver` VARCHAR(100) COMMENT '审批人',
  `handover` VARCHAR(100) COMMENT '交接办理人',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`goods_id`) REFERENCES `office_goods`(`goods_id`) ON DELETE CASCADE
) COMMENT='领用记录表';

CREATE TABLE `return_record` (
     `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
     `goods_id` BIGINT NOT NULL COMMENT '商品ID',
     `goods_name` VARCHAR(50) NOT NULL COMMENT '商品名',
     `norms_model` VARCHAR(50) COMMENT '规格型号',
     `quantity` DECIMAL(10, 2) NOT NULL COMMENT '交还数量',
     `return_people` VARCHAR(100) NOT NULL COMMENT '交还人',
     `return_time` DATETIME NOT NULL COMMENT '交还时间',
     `recipient` VARCHAR(100) COMMENT '领用人',
     `handover` VARCHAR(100) NOT NULL COMMENT '交接办理人',
     `reason` TEXT COMMENT '交还原因',
     PRIMARY KEY (`id`),
     FOREIGN KEY (`goods_id`) REFERENCES `office_goods`(`goods_id`) ON DELETE CASCADE
) COMMENT='交还记录表';

CREATE TABLE `consumption_statis` (
      `consumption_statis_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
      `goods_id` BIGINT NOT NULL COMMENT '商品ID',
      `goods_name` VARCHAR(50) NOT NULL COMMENT '商品名',
      `norms_model` VARCHAR(50) NOT NULL COMMENT '规格型号',
      `consumption` DECIMAL(10, 2)  COMMENT '消耗量',
      `time` VARCHAR(255)  COMMENT '消耗时间长度',
      `lower_limit` DECIMAL(5, 2) default 0 COMMENT '消耗率下限',
      `upper_limit` DECIMAL(5, 2) default 1 COMMENT '消耗率上限',
      `state` VARCHAR(50) COMMENT '预警状态',
      `reason` VARCHAR(255) COMMENT '异常原因',
      PRIMARY KEY (`consumption_statis_id`),
      FOREIGN KEY (`goods_id`) REFERENCES `office_goods`(`goods_id`) ON DELETE CASCADE
)COMMENT='商品消耗统计表';

CREATE TABLE `goods_statis` (
    `goods_statis_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `goods_id` BIGINT NOT NULL COMMENT '商品ID',
    `goods_name` VARCHAR(50) NOT NULL COMMENT '商品名',
    `norms_model` VARCHAR(50) NOT NULL COMMENT '规格型号',
    `total` DECIMAL(10, 2) NOT NULL COMMENT '总数量',
    `remain` DECIMAL(10, 2) NOT NULL COMMENT '剩余数量',
    PRIMARY KEY (`goods_statis_id`),
    FOREIGN KEY (`goods_id`) REFERENCES `office_goods`(`goods_id`) ON DELETE CASCADE
) COMMENT='商品库存动态表';