package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;

import com.example.model.LatestQueueAlternated;

public class LatestQueueAlternatedService {
	
	@JsonProperty("data")
	private LatestQueueAlternated data;
	
	public LatestQueueAlternated getData() {
		return data;
	}

	public void setData(LatestQueueAlternated data) {
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
