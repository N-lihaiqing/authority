update dsm_role_config set CLIENT_VIS='T' where ROL_ID in(1,3);
update dsm_user set USR_PRODUCT_NAME='lddsm,ldfbs,ldpsb' where USR_ID in('admin','logadmin','secadmin','sysadmin');
update dsm_user set USR_MOBILE_LOGIN='T' where USR_ID in('admin','logadmin','secadmin','sysadmin');
update dsm_user u set u.UR_CONFIG_VALID = 1 where u.usr_id = 'admin';

update dsm_role_config set BACKUP_STRATEGY_VIS = 'T',EXCEPTION_REPORT = 'T',BACKUP_CONFIG = 'T' where ROL_ID = '3';
update dsm_role_config set BACKUP_STRATEGY_VIS = 'T',EXCEPTION_REPORT = 'T',BACKUP_CONFIG = 'T' where ROL_ID = '1';
update dsm_role_config set BACKUP_STRATEGY_VIS = 'F' where ROL_ID = '3';
update dsm_role_config set ACTIVITY_LOG_VIS = 'T' where ROL_ID = '5';

alter table dsm_translation modify column  tr_text VARCHAR(500);

alter table dsm_ftp_server modify fs_port varchar(255) null;
alter table dsm_ftp_server modify fs_ip varchar(255) null;

alter table LDFBS_CONFIG modify BS_LIMIT_TIME varchar(255) null;

ALTER TABLE `ldfbs_user_machine` ADD UNIQUE INDEX `UM_MCODE` (`UM_MCODE`) ;

update dsm_role_config set PERMISSION_CONFIG = 'T' where ROL_ID IN ('1', '4');

update ldfbs_backup_strategy set BS_ISGIVE = 'T' where BS_ISGIVE = '1';
update ldfbs_backup_strategy set BS_ISGIVE = 'F' where BS_ISGIVE = '0';
update ldfbs_backup_strategy set BS_ISLDFBS = 'T' where BS_ISLDFBS = '1';
update ldfbs_backup_strategy set BS_ISLDFBS = 'F' where BS_ISLDFBS = '0';

ALTER TABLE `dsm_mime_type_extension` ADD UNIQUE INDEX `UN_MTE` (`MTE_ID`, `MTE_NAME`) ;
replace into dsm_mime_type_extension(mte_id, mte_name) values(18, 'jpeg');

update dsm_node_document_preview set FILE_CONVER_NORMAL = 'T' where FILE_CONVER_NORMAL = '';

DROP  function IF EXISTS getAllChildDeptIds ;
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