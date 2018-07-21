-- 统计用户文档数量和大小，用于控制上传空间限制
drop trigger if exists tri_sync_ui_add;
create trigger tri_sync_ui_add 
after insert on DSM_NODE_DOCUMENT_VERSION 
for each row
begin
	declare ui_cnt int;

	set ui_cnt = (select count(*) from dsm_user_items where ui_user = new.NDV_AUTHOR);
	if ui_cnt > 0 then
		update dsm_user_items set ui_size = ui_size + new.NDV_SIZE, ui_documents = ui_documents + 1 where ui_user = new.NDV_AUTHOR;
	else
		insert into dsm_user_items(UI_USER,UI_DOCUMENTS,UI_SIZE,UI_FOLDERS,UI_MAILS)values(new.NDV_AUTHOR, 1, new.ndv_size, 0, 0);
	end if;
end;

drop trigger if exists tri_sync_ui_del;
create trigger tri_sync_ui_del 
before delete on DSM_NODE_DOCUMENT_VERSION 
for each row
begin
	declare ui_cnt int;

	set ui_cnt = (select count(*) from dsm_user_items where ui_user = old.NDV_AUTHOR);
	if ui_cnt > 0 then
		update dsm_user_items set ui_size = ui_size - old.NDV_SIZE, ui_documents = ui_documents - 1 where ui_user = old.NDV_AUTHOR;
	end if;
end;