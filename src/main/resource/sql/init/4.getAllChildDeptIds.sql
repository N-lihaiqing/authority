--获取部门子节点  逗号分开

-- cast 链接字符
-- group_concat 函数返回一个字符串结果，该结果由分组中的值连接组合而成。
-- FIND_IN_SET 查询在逗号隔开的字符串中符合多个ID的数据
DROP FUNCTION IF EXISTS `getAllChildDeptIds`;
DELIMITER ;;
CREATE FUNCTION getAllChildDeptIds(id INT) 
	RETURNS VARCHAR(4000) CHARSET utf8
    READS SQL DATA
BEGIN  	
DECLARE ids VARCHAR (4000);  	
DECLARE deptId VARCHAR (4000);  	

SET deptId = CAST(id AS CHAR);  
SET ids = deptId;  	
WHILE deptId IS NOT NULL DO 
         
         SELECT GROUP_CONCAT(DPT_ID) INTO deptId FROM DSM_DEPARTMENT WHERE FIND_IN_SET(DPT_PARENTID,deptId)>0; 
         IF deptId IS NOT NULL 
         THEN 
         SET ids= CONCAT(ids,',',deptId); 
         END IF;
       END WHILE; 
       
RETURN ids;  
END;