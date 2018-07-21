-- 返回当前节点的所有父节点，用逗号隔开

-- cast 链接字符
-- group_concat 函数返回一个字符串结果，该结果由分组中的值连接组合而成。
-- FIND_IN_SET 查询在逗号隔开的字符串中符合多个ID的数据
DROP FUNCTION IF EXISTS `getAllParentDeptIds`;
DELIMITER ;;
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
	
	SELECT group_concat(DEPT_PARENTID) INTO deptId FROM AUTH_DEPARTMENT WHERE FIND_IN_SET(DEPT_ID, deptId) > 0; 
	END WHILE; 
	
	RETURN ids; 
END;