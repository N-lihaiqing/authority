-- 云文档中根据uuid获取节点全路径的函数
CREATE FUNCTION getPathFromUuid(uuid varchar(64)) 
	RETURNS varchar(4000)
	READS SQL DATA
BEGIN 
	DECLARE nodeName VARCHAR(500); 
	DECLARE fullPath VARCHAR(4000);
	DECLARE parentUuid varchar(64); 
	
	if STRCMP(uuid, 'cafebabe-cafe-babe-cafe-babecafebabe') = 0 THEN 
		return '/'; 
	end if; 
	
	set nodeName = ''; 
	set fullPath = ''; 
	
	WHILE STRCMP(uuid, 'cafebabe-cafe-babe-cafe-babecafebabe') !=0 DO 
		SELECT a.nbs_name, a.nbs_parent INTO nodeName, parentUuid FROM dsm_node_base a where nbs_uuid = uuid;
		SET fullPath = concat('/', nodeName, fullPath);
		if STRCMP(uuid, parentUuid) = 0 or length(fullPath) > 3500 or STRCMP(parentUuid, '') = 0 then
			return fullPath;
		end if;
		set uuid = parentUuid; 
	END WHILE; 
	
	RETURN fullPath; 
END;

