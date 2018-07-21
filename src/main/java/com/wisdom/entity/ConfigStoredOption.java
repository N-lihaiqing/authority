package com.wisdom.entity;

import java.io.Serializable;

public class ConfigStoredOption implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SELECTED = "*";
	private String name;
	private String value;
	private boolean selected;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("name=").append(name);
		sb.append(", value=").append(value);
		sb.append(", selected=").append(selected);
		sb.append("}");
		return sb.toString();
	}
}
