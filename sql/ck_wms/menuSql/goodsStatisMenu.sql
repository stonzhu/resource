-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品库存动态', '3', '1', 'goodsStatis', 'wms/goodsStatis/index', 1, 0, 'C', '0', '0', 'goodsStatis:goodsStatis:list', '#', 'admin', sysdate(), '', null, '商品库存动态菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品库存动态查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'goodsStatis:goodsStatis:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品库存动态新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'goodsStatis:goodsStatis:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品库存动态修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'goodsStatis:goodsStatis:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品库存动态删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'goodsStatis:goodsStatis:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品库存动态导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'goodsStatis:goodsStatis:export',       '#', 'admin', sysdate(), '', null, '');