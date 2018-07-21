package com.wisdom.entity;

import java.io.Serializable;
import java.util.List;

public class Navigation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id; //菜单ID
	private String navigationName; //菜单名称
	private int size; // 子菜单数量
	private List<Permission> childNavigations;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getNavigationName() {
		return navigationName;
	}

	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}

	public List<Permission> getChildNavigations() {
		return childNavigations;
	}

	public void setChildNavigations(List<Permission> childNavigations) {
		this.childNavigations = childNavigations;
	}
}
