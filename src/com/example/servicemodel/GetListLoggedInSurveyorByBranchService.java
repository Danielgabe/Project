package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;

import com.example.model.GetListLoggedInSurveyorByBranchModel;

public class GetListLoggedInSurveyorByBranchService {

	@JsonProperty("data")
	private GetListLoggedInSurveyorByBranchModel data;

	public GetListLoggedInSurveyorByBranchModel getData() {
		return data;
	}

	public void setData(GetListLoggedInSurveyorByBranchModel data) {
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
