package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;

import com.example.model.GetListTemporaryServiceActivityByUserAndTicketIDModel;

public class GetListTemporaryServiceActivityByUserAndTicketIDService {

	@JsonProperty("data")
	private GetListTemporaryServiceActivityByUserAndTicketIDModel data;

	public GetListTemporaryServiceActivityByUserAndTicketIDModel getData() {
		return data;
	}

	public void setData(GetListTemporaryServiceActivityByUserAndTicketIDModel data) {
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
