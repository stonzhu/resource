-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('出售交易信息', '2006', '1', 'rushsale', 'rushsale/rushsale/index', 1, 0, 'C', '0', '0', 'rushsale:rushsale:list', '#', 'admin', sysdate(), '', null, '出售交易信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('出售交易信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'rushsale:rushsale:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('出售交易信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'rushsale:rushsale:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('出售交易信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'rushsale:rushsale:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('出售交易信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'rushsale:rushsale:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('出售交易信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'rushsale:rushsale:export',       '#', 'admin', sysdate(), '', null, '');