-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品商品', '3', '1', 'goods', 'wms/goods/index', 1, 0, 'C', '0', '0', 'goods:goods:list', '#', 'admin', sysdate(), '', null, '办公用品商品菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品商品查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'goods:goods:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品商品新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'goods:goods:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品商品修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'goods:goods:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品商品删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'goods:goods:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品商品导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'goods:goods:export',       '#', 'admin', sysdate(), '', null, '');