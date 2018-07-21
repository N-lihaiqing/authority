package com.wisdom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AUTH_DEPARTMENT")
public class Department implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DEPT_ID", length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	/**组织机构名称*/
	@Column(name = "DEPT_NAME", length = 200)
	protected String name;
	/**父级组织机构ID*/
	@Column(name = "DEPT_PARENTID", length = 20)
	protected long parentid;
	
	@Transient
	String state;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object obj){ //20170616  为了对象可以直接从list删除而添加
		if (obj == null) {
			return false;
		} else if (obj == this) {
			return true;
		} else if (this.getClass() == obj.getClass()) {
			Department other = (Department) obj;
			
			if (this.getId() == other.getId()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("  id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", parentid=").append(parentid);
		sb.append(", state=").append(state);
		sb.append("}");
		return sb.toString();
	}

}
