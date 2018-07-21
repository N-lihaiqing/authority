package com.wisdom.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@Entity
@Table(name="AUTH_USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_ID", length = 19)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="USER_NAME", length = 15, nullable = false)
	private String userName;
	
	@Column(name="PASSWORD", length = 32, nullable = false)
	private String password;
	
	@Column(name="PASSWORD_SALT", length = 32, nullable = false)
	private String passwordSalt;
	
	 /*
     * cascade：为级联操作，里面有级联保存，级联删除等，all为所有 
     * fetch：加载类型，有lazy和eager二种，
     *   eager为急加载，意为立即加载，在类加载时就加载，lazy为慢加载，第一次调用的时候再加载，由于数据量太大，onetomany一般为lazy
     * mappedBy：这个为manytoone中的对象名，这个不要变哦
     * Set<role>：这个类型有两种，一种为list另一种为set
     */
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "user")  
	private Set<UserRole> userRole = new HashSet<UserRole>();
	
	User(){
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}
