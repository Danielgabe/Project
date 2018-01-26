package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;

import com.example.model.SetTicketOnProcessModel;

public class SetTicketOnProcessService {

	@JsonProperty("data")
	private SetTicketOnProcessModel data;	
	
	public SetTicketOnProcessModel getData() {
		return data;
	}
	
	public void setData(SetTicketOnProcessModel data) {
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

