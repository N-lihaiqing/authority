package com.wisdom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AUTH_ROLE")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ROLE_ID", length = 19)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@Column(name="ROLE_NAME", length = 15, nullable = true)
	private String roleName;
	
	@Column(name="ROLE_DESC", length = 30, nullable = true)
	private String roleDesc;
	
	@Column(name="ROLE_CODE", length = 15, nullable = true)
	private String roleCode;
	
	@Column(name = "ROL_PARENT_ID", length = 20, nullable = false)
	private int parentId;
	
	@OneToOne
	@JoinColumn(name="ROLE_ID")
	private RolePermission  rolePermission;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public RolePermission getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(RolePermission rolePermission) {
		this.rolePermission = rolePermission;
	}
	
}
