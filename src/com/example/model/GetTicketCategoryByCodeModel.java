package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name=" GetTicketCategoryByCode")
public class GetTicketCategoryByCodeModel extends Model {
	
	@Column(name = "Code", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)	 
	@JsonProperty("Code")
	private String Code;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Column(name = "Name")
	@JsonProperty("Name")
	private String Name;
	
} 