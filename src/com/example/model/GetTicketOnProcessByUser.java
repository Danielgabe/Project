package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="GetTicketOnProcessByUser")
public class GetTicketOnProcessByUser extends Model{
	
	@Column(name = "UserName", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)	
	@JsonProperty("UserName")
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
	private int TicketNumber;
	
	@Column(name = "ComingWhen")
	@JsonProperty("ComingWhen")
	private String ComingWhen;
	
	@Column(name = "TicketStatus")
	@JsonProperty("TicketStatus")
	private int TicketStatus;
	
	@Column(name = "StationNumber")
	@JsonProperty("StationNumber")
	private String StationNumber;
	
	@Column(name = "ServedBy")
	@JsonProperty("ServedBy")
	private String ServedBy;
	
	@Column(name = "ServedWhen")
	@JsonProperty("ServedWhen")
	private String ServedWhen;
	
	@Column(name = "FinishWhen")
	@JsonProperty("FinishWhen")
	private String FinishWhen;
	
	@Column(name = "BookingID")
	@JsonProperty("BookingID")
	private int BookingID;
	
	@Column(name = "TicketNumberingCode")
	@JsonProperty("TicketNumberingCode")
	private String TicketNumberingCode;

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

	public int getTicketNumber() {
		return TicketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		TicketNumber = ticketNumber;
	}

	public String getComingWhen() {
		return ComingWhen;
	}

	public void setComingWhen(String comingWhen) {
		ComingWhen = comingWhen;
	}

	public int getTicketStatus() {
		return TicketStatus;
	}

	public void setTicketStatus(int ticketStatus) {
		TicketStatus = ticketStatus;
	}

	public String getStationNumber() {
		return StationNumber;
	}

	public void setStationNumber(String stationNumber) {
		StationNumber = stationNumber;
	}

	public String getServedBy() {
		return ServedBy;
	}

	public void setServedBy(String servedBy) {
		ServedBy = servedBy;
	}

	public String getServedWhen() {
		return ServedWhen;
	}

	public void setServedWhen(String servedWhen) {
		ServedWhen = servedWhen;
	}

	public String getFinishWhen() {
		return FinishWhen;
	}

	public void setFinishWhen(String finishWhen) {
		FinishWhen = finishWhen;
	}

	public int getBookingID() {
		return BookingID;
	}

	public void setBookingID(int bookingID) {
		BookingID = bookingID;
	}

	public String getTicketNumberingCode() {
		return TicketNumberingCode;
	}

	public void setTicketNumberingCode(String ticketNumberingCode) {
		TicketNumberingCode = ticketNumberingCode;
	}

}
