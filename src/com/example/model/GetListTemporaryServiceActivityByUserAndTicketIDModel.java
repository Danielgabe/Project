package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table (name= "GetListTemporaryServiceActivityByUserAndTicket")
public class GetListTemporaryServiceActivityByUserAndTicketIDModel extends Model{
	
	@Column(name = "TicketID")	
	@JsonProperty("TicketID")
	private String TicketID;
	
	public String getTicketID() {
		return TicketID;
	}

	public void setTicketID(String ticketID) {
		TicketID = ticketID;
	}

	public String getServiceGroupID() {
		return ServiceGroupID;
	}

	public void setServiceGroupID(String serviceGroupID) {
		ServiceGroupID = serviceGroupID;
	}

	public String getServiceGroupName() {
		return ServiceGroupName;
	}

	public void setServiceGroupName(String serviceGroupName) {
		ServiceGroupName = serviceGroupName;
	}

	public String getServiceTypeID() {
		return ServiceTypeID;
	}

	public void setServiceTypeID(String serviceTypeID) {
		ServiceTypeID = serviceTypeID;
	}

	public String getServiceTypeName() {
		return ServiceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		ServiceTypeName = serviceTypeName;
	}

	public String getStartWhen() {
		return StartWhen;
	}

	public void setStartWhen(String startWhen) {
		StartWhen = startWhen;
	}

	public String getFinishWhen() {
		return FinishWhen;
	}

	public void setFinishWhen(String finishWhen) {
		FinishWhen = finishWhen;
	}

	@Column(name = "ServiceGroupID")	
	@JsonProperty("ServiceGroupID")
	private String ServiceGroupID;
	
	@Column(name = "ServiceGroupName")	
	@JsonProperty("ServiceGroupName")
	private String ServiceGroupName;
	
	@Column(name = "ServiceTypeID")	
	@JsonProperty("ServiceTypeID")
	private String ServiceTypeID;
	
	@Column(name = "ServiceTypeName")	
	@JsonProperty("ServiceTypeName")
	private String ServiceTypeName;
	
	@Column(name = "StartWhen")	
	@JsonProperty("StartWhen")
	private String StartWhen;
	
	@Column(name = "FinishWhen")	
	@JsonProperty("FinishWhen")
	private String FinishWhen;
}
