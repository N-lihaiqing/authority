package com.wisdom.entity;

import java.io.Serializable;

public class ConfigStoredFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String mime;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
		
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("name="); sb.append(name);
		sb.append(", mime="); sb.append(mime);
		sb.append(", content="); sb.append(content);
		sb.append("}");
		return sb.toString();
	}
}
