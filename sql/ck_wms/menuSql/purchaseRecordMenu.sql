-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购记录', '3', '1', 'purchaseRecord', 'wms/purchaseRecord/index', 1, 0, 'C', '0', '0', 'purchase:purchaseRecord:list', '#', 'admin', sysdate(), '', null, '采购记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'purchase:purchaseRecord:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'purchase:purchaseRecord:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'purchase:purchaseRecord:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'purchase:purchaseRecord:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'purchase:purchaseRecord:export',       '#', 'admin', sysdate(), '', null, '');