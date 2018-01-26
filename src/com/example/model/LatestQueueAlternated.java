package com.example.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name="LatestQueueAlternated")
public class LatestQueueAlternated extends Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1204121113981140195L;

	@Column(name = "TicketID", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)	
	@JsonProperty("TicketID")
	private String TicketID;
	
	@Column(name = "TicketDate")	
	@JsonProperty("TicketDate")
	private String TicketDate;
	
	@Column(name = "BranchID")	
	@JsonProperty("BranchID")
	private String BranchID;
	
	@Column(name = "TicketCategoryCode")	
	@JsonProperty("TicketCategoryCode")
	private String TicketCategoryCode;
	
	@Column(name = "TicketNumber")	
	@JsonProperty("TicketNumber")
	private String TicketNumber;
	
	@Column(name = "TicketStatus")	
	@JsonProperty("TicketStatus")
	private int TicketStatus;
	
	@Column(name = "Sequence")	
	@JsonProperty("Sequence")
	private int Sequence;
	
	@Column(name = "QueueTypeCode")	
	@JsonProperty("QueueTypeCode")
	private String QueueTypeCode;
	
	@Column(name = "QueueEntryPriority")	
	@JsonProperty("QueueEntryPriority")
	private int QueueEntryPriority;
	
	@Column(name = "QueueReEntryPriority")	
	@JsonProperty("QueueReEntryPriority")
	private int QueueReEntryPriority;
	
	
	public String getTicketID() {
		return TicketID;
	}
	public void setTicketID(String ticketID) {
		TicketID = ticketID;
	}
	public String getTicketDate() {
		return TicketDate;
	}
	public void setTicketDate(String ticketDate) {
		TicketDate = ticketDate;
	}
	public String getBranchID() {
		return BranchID;
	}
	public void setBranchID(String branchID) {
		BranchID = branchID;
	}
	public String getTicketCategoryCode() {
		return TicketCategoryCode;
	}
	public void setTicketCategoryCode(String ticketCategoryCode) {
		TicketCategoryCode = ticketCategoryCode;
	}
	public String getTicketNumber() {
		return TicketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		TicketNumber = ticketNumber;
	}
	public int getTicketStatus() {
		return TicketStatus;
	}
	public void setTicketStatus(int ticketStatus) {
		TicketStatus = ticketStatus;
	}
	public int getSequence() {
		return Sequence;
	}
	public void setSequence(int sequence) {
		Sequence = sequence;
	}
	public String getQueueTypeCode() {
		return QueueTypeCode;
	}
	public void setQueueTypeCode(String queueTypeCode) {
		QueueTypeCode = queueTypeCode;
	}
	public int getQueueEntryPriority() {
		return QueueEntryPriority;
	}
	public void setQueueEntryPriority(int queueEntryPriority) {
		QueueEntryPriority = queueEntryPriority;
	}
	public int getQueueReEntryPriority() {
		return QueueReEntryPriority;
	}
	public void setQueueReEntryPriority(int queueReEntryPriority) {
		QueueReEntryPriority = queueReEntryPriority;
	}
	

}
