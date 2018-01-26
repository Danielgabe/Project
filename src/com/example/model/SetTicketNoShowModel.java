package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="SetTicketNoShow")
public class SetTicketNoShowModel extends Model{
	
	@Column(name = "message")
	@JsonProperty("message")
	private String message;
	 
	public String getmessage() {
		return message;
	}
	public void setmessage(String umessage) {
		message = umessage;
	}
	 
}
