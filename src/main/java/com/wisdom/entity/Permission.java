package com.wisdom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name="AUTH_PERMISSION")
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PERMISSION_ID", length=20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int permissionId;
	
	@Column(name="PERMISSION_DESC", length = 30, nullable = true)
	private String permissionDesc;
	
	@Column(name="URL", length = 30, nullable = true)
	private String url;
	
	@Column(name="IS_NAVI", nullable = false, columnDefinition = "char(1) default 'F'")
	@Type(type = "true_false")
	private boolean isNavi;
	
	@Column(name="PERMISSION_CODE", nullable = true, length = 30)
	private String permissionCode;
	
//	@JsonIgnore
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "permission")
//	private RolePermission rolePermission;

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getIsNavi() {
		return isNavi;
	}

	public void setIsNavi(boolean isNavi) {
		this.isNavi = isNavi;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
}
