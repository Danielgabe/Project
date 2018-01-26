package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;

import com.example.model.GetCreateLoggedUser;

public class GetCreateLoggedUserService {

	@JsonProperty("data")
	private GetCreateLoggedUser data;

	@JsonProperty("success")
	private String success;

	@JsonProperty("messageStatus")
	private String messageStatus;

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

	public GetCreateLoggedUser getData() {
		return data;
	}

	public void setData(GetCreateLoggedUser data) {
		this.data = data;
	}

}
