package com.example.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "GetListActiveServiceTypeByTicketCategory")
public class GetListActiveServiceTypeByTicketCategoryUmumModel extends Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4794924140478668732L;

	@Column(name = "ServiceTypeID", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	@JsonProperty("ServiceTypeID")
	private String ServiceTypeID;

	public String getServiceTypeID() {
		return ServiceTypeID;
	}

	public void setServiceTypeID(String uServiceTypeID) {
		ServiceTypeID = uServiceTypeID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String uName) {
		Name = uName;
	}

	public String getTicketCategoryCode() {
		return TicketCategoryCode;
	}

	public void setTicketCategoryCode(String uTicketCategoryCode) {
		TicketCategoryCode = uTicketCategoryCode;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String uIsActive) {
		IsActive = uIsActive;
	}

	@Column(name = "Name")
	@JsonProperty("Name")
	private String Name;

	@Column(name = "TicketCategoryCode")
	@JsonProperty("TicketCategoryCode")
	private String TicketCategoryCode;

	@Column(name = "IsActive")
	@JsonProperty("IsActive")
	private String IsActive;

}
