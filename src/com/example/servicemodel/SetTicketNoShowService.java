package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;

import com.example.model.SetTicketNoShowModel;

public class SetTicketNoShowService {

	@JsonProperty("data")
	private SetTicketNoShowModel data;	
	
	public SetTicketNoShowModel getData() {
		return data;
	}
	
	public void setData(SetTicketNoShowModel data) {
		this.data = data;
	}
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	@JsonProperty("success")
	private String success;

	@JsonProperty("messageStatus")
	private String messageStatus;
	
}
