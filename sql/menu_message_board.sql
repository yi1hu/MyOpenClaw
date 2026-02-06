-- 添加留言管理菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
values('留言管理', '1', '10', 'message', 'system/message/index', 1, 0, 'C', '0', '0', 'system:message:view', '#', 'admin', '2023-02-06', '', null, '');

-- 添加留言管理按钮权限
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
values('留言查询', 500, '1', '#', '', 1, 0, 'F', '0', '0', 'system:message:list', '#', 'admin', '2023-02-06', '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
values('留言新增', 500, '2', '#', '', 1, 0, 'F', '0', '0', 'system:message:add', '#', 'admin', '2023-02-06', '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
values('留言修改', 500, '3', '#', '', 1, 0, 'F', '0', '0', 'system:message:edit', '#', 'admin', '2023-02-06', '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
values('留言删除', 500, '4', '#', '', 1, 0, 'F', '0', '0', 'system:message:remove', '#', 'admin', '2023-02-06', '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
values('留言导出', 500, '5', '#', '', 1, 0, 'F', '0', '0', 'system:message:export', '#', 'admin', '2023-02-06', '', null, '');