package com.example.servicemodel;

import org.codehaus.jackson.annotate.JsonProperty;
import com.example.model.GetListActiveServiceGroupByTicketCategoryModel;

public class GetListActiveServiceGroupByTicketCategoryService {

	@JsonProperty("data")
	private GetListActiveServiceGroupByTicketCategoryModel data;	
	
	public GetListActiveServiceGroupByTicketCategoryModel getData() {
		return data;
	}
	
	public void setData(GetListActiveServiceGroupByTicketCategoryModel data) {
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
