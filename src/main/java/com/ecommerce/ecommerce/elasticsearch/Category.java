package com.ecommerce.ecommerce.elasticsearch;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document( indexName = "categories")
public class Category {

	@Id
	private String id;
	private String name;
	private String description;
	private int objType;
	private int status;
	private Long createTs;
	
	
	public Category(String id, String name, String description, int objType, Long createTs) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.objType = objType;
		this.status =1;
		this.createTs = createTs;
	}
	public Category(String name, String description, int objType,  Long createTs) {
		super();
		this.name = name;
		this.description = description;
		this.objType = objType;
		this.status = 1;
		this.createTs = createTs;
	}
	public Category() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public int getObjType() {
		return objType;
	}
	public void setObjType(int objType) {
		this.objType = objType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Long createTs) {
		this.createTs = createTs;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", objType=" + objType
				+ ", status=" + status + ", createTs=" + createTs + "]";
	}
	
}
