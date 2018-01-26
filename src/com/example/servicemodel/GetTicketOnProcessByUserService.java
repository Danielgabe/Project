package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;

import com.example.model.GetTicketOnProcessByUser;


public class GetTicketOnProcessByUserService {

	@JsonProperty("data")
	private GetTicketOnProcessByUser data;

	public GetTicketOnProcessByUser getData() {
		return data;
	}

	public void setData(GetTicketOnProcessByUser data) {
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
