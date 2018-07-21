package com.wisdom.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@Entity
@Table(name="AUTH_USER_ROLE")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID", length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="USER_ID", length = 20, nullable = false)
	private int userId;
	
	@Column(name="ROLE_ID", length = 20, nullable = false)
	private int roleId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="USER_ID", insertable = false,updatable = false) //加入一列作为外键
	private User user;
	
	UserRole(){
		
	}
	
	public UserRole(int userId, int roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
