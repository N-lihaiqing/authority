package com.wisdom.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ConfigStoredSelect implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<ConfigStoredOption> options = new ArrayList<ConfigStoredOption>();
	
	public List<ConfigStoredOption> getOptions() {
		return options;
	}
	
	public void setOptions(List<ConfigStoredOption> options) {
		this.options = options;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("options=").append(options);
		sb.append("}");
		return sb.toString();
	}


}
