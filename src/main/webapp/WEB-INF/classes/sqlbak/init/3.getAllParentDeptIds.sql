-- 返回当前节点的所有父节点，用逗号隔开
CREATE FUNCTION getAllParentDeptIds(id INT)
	RETURNS VARCHAR (4000)
	READS SQL DATA
BEGIN 
	DECLARE ids VARCHAR (4000); 
	DECLARE deptId VARCHAR (4000); 
	
	SET ids = ''; 
	SET deptId = cast(id AS CHAR); 
	
	WHILE deptId IS NOT NULL DO 
	IF ids = '' THEN 
		SET ids = deptId; 
	ELSE 
		SET ids = CONCAT(deptId, ',', ids); 
	END IF; 
	
	SELECT group_concat(DPT_PARENTID) INTO deptId FROM DSM_DEPARTMENT WHERE FIND_IN_SET(DPT_ID, deptId) > 0; 
	END WHILE; 
	
	RETURN ids; 
END;