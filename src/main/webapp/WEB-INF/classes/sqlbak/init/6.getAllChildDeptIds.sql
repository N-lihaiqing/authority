--获取部门子节点  逗号分开
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