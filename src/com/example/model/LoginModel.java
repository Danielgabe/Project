package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Login")
public class LoginModel extends Model {

	@Column(name = "UserName", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	@JsonProperty("UserName")
	private String UserName;
	
	@Column(name = "Domain")
	@JsonProperty("Domain")
	private String Domain;

	@Column(name = "Password")
	@JsonProperty("Password")
	private String Password;

	@Column(name = "Email")
	@JsonProperty("Email")
	private String Email;

	@Column(name = "BranchID")
	@JsonProperty("BranchID")
	private String BranchID;
	
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
	
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getDomain() {
		return Domain;
	}

	public void setDomain(String domain) {
		Domain = domain;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getBranchID() {
		return BranchID;
	}

	public void setBranchID(String branchID) {
		BranchID = branchID;
	}
}
