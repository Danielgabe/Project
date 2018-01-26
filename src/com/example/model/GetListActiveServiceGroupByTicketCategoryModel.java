package com.example.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="GetListActiveServiceGroupByTicketCategory")
public class GetListActiveServiceGroupByTicketCategoryModel extends Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4754133932027075823L;

	@Column(name = "ServiceGroupID", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)	
	@JsonProperty("ServiceGroupID")
	private int ServiceGroupID;
	
	@Column(name = "Name")
	@JsonProperty("Name")
	private String Name;
	
	@Column(name = "TicketCategoryCode")
	@JsonProperty("TicketCategoryCode")
	private String TicketCategoryCode;
	
	@Column(name = "IsActive")
	@JsonProperty("IsActive")
	private boolean IsActive;
	
	public int getServiceGroupID() {
		return ServiceGroupID;
	}
	public void setServiceGroupID(int serviceGroupID) {
		ServiceGroupID = serviceGroupID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTicketCategoryCode() {
		return TicketCategoryCode;
	}
	public void setTicketCategoryCode(String ticketCategoryCode) {
		TicketCategoryCode = ticketCategoryCode;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

}
