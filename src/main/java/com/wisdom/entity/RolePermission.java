package com.wisdom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUTH_ROLE_PERMISSION")
public class RolePermission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID", length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ROLE_ID", length = 20, nullable = false)
	private int roleId;
	
	
	@Column(name="PERMISSION_ID", length = 20, nullable = false)
	private int permissionId;
	
	RolePermission(){
		
	}
	
	public RolePermission(int roleId, int permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

}
