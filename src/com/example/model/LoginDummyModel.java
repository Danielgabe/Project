package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table (name= "Login")
public class LoginDummyModel extends Model {
	
@Column(name = "UserName", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)	
@JsonProperty("UserName")
private String UserName;

public String getUserName() {
	return UserName;
}

public void setUserName(String userName) {
	UserName = userName;
}

public String getUserDomain() {
	return UserDomain;
}

public void setUserDomain(String userDomain) {
	UserDomain = userDomain;
}

public String getUserEmail() {
	return UserEmail;
}

public void setUserEmail(String userEmail) {
	UserEmail = userEmail;
}

public String getUserBranchID() {
	return UserBranchID;
}

public void setUserBranchID(String userBranchID) {
	UserBranchID = userBranchID;
}

public String getBranchName() {
	return BranchName;
}

public void setBranchName(String branchName) {
	BranchName = branchName;
}

public String getUserIsActive() {
	return UserIsActive;
}

public void setUserIsActive(String userIsActive) {
	UserIsActive = userIsActive;
}

public String getUserPassword() {
	return UserPassword;
}

public void setUserPassword(String userPassword) {
	UserPassword = userPassword;
}

public String getBranchID() {
	return BranchID;
}

public void setBranchID(String branchID)
{
	BranchID = branchID;
}

@Column(name = "UserDomain")	
@JsonProperty("UserDomain")
private String UserDomain;

@Column(name = "UserEmail")	
@JsonProperty("UserEmail")
private String UserEmail;

@Column(name = "UserBranchID")	
@JsonProperty("UserBranchID")
private String UserBranchID;

@Column(name = "BranchName")	
@JsonProperty("BranchName")
private String BranchName;

@Column(name = "UserIsActive")	
@JsonProperty("UserIsActive")
private String UserIsActive;

@Column(name = "UserPassword")	
@JsonProperty("UserPassword")
private String UserPassword; 
 
@JsonProperty("BranchID")
private String BranchID;

}
