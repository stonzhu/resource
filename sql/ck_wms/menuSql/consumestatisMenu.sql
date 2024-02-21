-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品消耗统计', '2000', '1', 'consumestatis', 'wms/consumestatis/index', 1, 0, 'C', '0', '0', 'wms:consumestatis:list', '#', 'admin', sysdate(), '', null, '商品消耗统计菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品消耗统计查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'wms:consumestatis:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品消耗统计新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'wms:consumestatis:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品消耗统计修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'wms:consumestatis:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品消耗统计删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'wms:consumestatis:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品消耗统计导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'wms:consumestatis:export',       '#', 'admin', sysdate(), '', null, '');