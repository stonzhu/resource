-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('抢购买卖', '3', '1', 'sale', 'system/sale/index', 1, 0, 'C', '0', '0', 'system:sale:list', '#', 'admin', sysdate(), '', null, '抢购买卖菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('抢购买卖查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:sale:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('抢购买卖新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:sale:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('抢购买卖修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:sale:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('抢购买卖删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:sale:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('抢购买卖导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:sale:export',       '#', 'admin', sysdate(), '', null, '');