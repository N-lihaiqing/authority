package com.wisdom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "AUTH_CONFIG")
public class Config implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String STRING = "string";
	public static final String TEXT = "text";
	public static final String BOOLEAN = "boolean";
	public static final String INTEGER = "integer";
	public static final String LONG = "long";
	public static final String FILE = "file";
	public static final String SELECT = "select";
	public static final String HIDDEN = "hidden";
	public static final String LIST = "list";

	@Id
	@Column(name = "CFG_KEY", length = 64)
	private String key;

	@Column(name = "CFG_TYPE", length = 32)
	private String type;

	@Column(name = "CFG_INDEX")
	private String index;

	@Column(name = "CFG_VALUE")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("key=");
		sb.append(key);
		sb.append(", type=");
		sb.append(type);
		sb.append(", index=");
		sb.append(index);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");
		return sb.toString();
	}
}
