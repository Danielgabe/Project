package com.example.servicemodel;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty; 

import com.example.model.GetListUserRoleModel;
import com.example.model.GetTicketInfoModel;

public class GetListUserRoleService {

	@JsonProperty("data")
	private List<GetListUserRoleModel> data = new ArrayList<GetListUserRoleModel>();
	
	public void addRoleModel(GetListUserRoleModel role){
		data.add(role);
	}

	public List<GetListUserRoleModel> getData() {
		return data;
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
