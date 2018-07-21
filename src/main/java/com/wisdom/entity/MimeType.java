package com.wisdom.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "MIME_TYPE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MimeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "MT_NAME", length = 128, unique = true)
	private String name;

	@Column(name = "MT_DESCRIPTION", length = 128, unique = true)
	private String description;

	@Column(name = "MT_IMAGE_CONTENT")
	@Lob
	@Type(type = "java.sql.Clob")
	private String imageContent;

	@Column(name = "MT_IMAGE_MIME", length = 32)
	private String imageMime;

	@Column(name = "MT_SEARCH", nullable = false)
	@Type(type = "true_false")
	private boolean search = false;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "MTE_NAME")
	@CollectionTable(name = "MIME_TYPE_EXTENSION", joinColumns = { @JoinColumn(name = "MTE_ID") })
	private Set<String> extensions = new HashSet<String>();

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageContent() {
		return imageContent;
	}

	public void setImageContent(String imageContent) {
		this.imageContent = imageContent;
	}

	public String getImageMime() {
		return imageMime;
	}

	public void setImageMime(String imageMime) {
		this.imageMime = imageMime;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public Set<String> getExtensions() {
		return extensions;
	}

	public void setExtensions(Set<String> extensions) {
		this.extensions = extensions;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", description=").append(description);
		sb.append(", search=").append(search);
		sb.append(", imageMime=").append(imageMime);
		sb.append(", imageContent=").append("[BIG]");
		sb.append(", extensions=").append(extensions);
		sb.append("}");
		return sb.toString();
	}
}
