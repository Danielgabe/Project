package com.example.frontlinerproject;

import java.util.ArrayList;

public class GlobalClass extends com.activeandroid.app.Application {
	private String serviceGroupID;
	private String UserName;
	private String UserDomain;
	private String UserEmail;
	private String UserBranchID;
	private String BranchName;
	private String CounterNO;
	private String UserIsActive;
	private String UserPassword;
	private String BranchID;
	private String CSO;
	private String SVY;
	private String CSH;
	private String RoleID;
	private String RoleCode;
	private String BookOnlineID;
	private String TicketID;
	private String TicketNumber;
	private String Name;
	private String StationNumber;
	private ArrayList<String> uNomor;
	private String ServedBy;
	private String BaseUrl;
	private ArrayList<String> listRole = new ArrayList<String>();
	private String TicketCategoryCode;
	private String serviceGroupName;
	private String selmoFronLinerURL;

	public String getServiceGroupName() {
		return serviceGroupName;
	}

	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName;
	}

	public String getTicketCategoryCode() {
		return TicketCategoryCode;
	}

	public void setTicketCategoryCode(String ticketCategoryCode) {
		TicketCategoryCode = ticketCategoryCode;
	}

	public String getBaseUrl() {
		return BaseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		BaseUrl = baseUrl;
	}

	public String getCounterNO() {
		return CounterNO;
	}

	public void setCounterNO(String counterNO) {
		CounterNO = counterNO;
	}

	public ArrayList<String> getListRole() {
		return listRole;
	}

	public void addRole(String role) {
		this.listRole.add(role);
	}

	public String getRoleCode() {
		return RoleCode;
	}

	public void setRoleCode(String roleCode) {
		RoleCode = roleCode;
	}

	public String getRoleID() {
		return RoleID;
	}

	public void setRoleID(String roleID) {
		RoleID = roleID;
	}

	private com.example.servicemodel.LoginService LoginService;

	public com.example.servicemodel.LoginService getLoginService() {
		return LoginService;
	}

	public void setLoginService(com.example.servicemodel.LoginService resultData) {
		LoginService = resultData;
	}

	public String getUserName() {

		return UserName;
	}

	public void setUserName(String aUserName) {

		UserName = aUserName;

	}

	public String getUserDomain() {

		return UserDomain;
	}

	public void setUserDomain(String aUserDomain) {

		UserDomain = aUserDomain;

	}

	public String getUserEmail() {

		return UserEmail;
	}

	public void setUserEmail(String aUserEmail) {

		UserEmail = aUserEmail;

	}

	public String getUserBranchID() {

		return UserBranchID;
	}

	public void setUserBranchID(String aUserBranchID) {

		UserBranchID = aUserBranchID;

	}

	public void setBranchID(String aBranchID) {
		BranchID = aBranchID;
	}

	public String getBranchID() {
		return BranchID;
	}

	public String getBranchName() {

		return BranchName;
	}

	public void setBranchName(String aBranchName) {

		BranchName = aBranchName;

	}

	public String getUserIsActive() {

		return UserIsActive;
	}

	public void setUserIsActive(String aUserIsActive) {

		UserIsActive = aUserIsActive;

	}

	public String getUserPassword() {

		return UserPassword;
	}

	public void setUserPassword(String aUserPassword) {

		UserPassword = aUserPassword;

	}

	public String getCSO() {

		return CSO;
	}

	public void setCSO(String aCSO) {

		CSO = aCSO;

	}

	public String getSVY() {

		return SVY;
	}

	public void setSVY(String aSVY) {

		SVY = aSVY;

	}

	public String getCSH() {

		return CSH;
	}

	public void setCSH(String aCSH) {

		CSH = aCSH;

	}

	public String getBookOnlineID() {

		return BookOnlineID;
	}

	public void setBookOnlineID(String aBookOnlineID) {

		BookOnlineID = aBookOnlineID;

	}

	public String getTicketID() {

		return TicketID;
	}

	public void setTicketID(String aTicketID) {

		TicketID = aTicketID;

	}

	public String getTicketNumber() {

		return TicketNumber;
	}

	public void setTicketNumber(String aTicketNumber) {

		TicketNumber = aTicketNumber;

	}

	public String getName() {

		return Name;
	}

	public void setName(String aName) {

		Name = aName;

	}

	public ArrayList<String> getuNomor() {

		return uNomor;
	}

	public void setuNomor(ArrayList<String> uNomor2) {

		uNomor = uNomor2;

	}

	public String getServiceGroupID() {
		return serviceGroupID;
	}

	public void setServiceGroupID(String serviceGroupID) {
		this.serviceGroupID = serviceGroupID;
	}

	public String getServedBy() {
		return ServedBy;
	}

	public void setServedBy(String servedBy) {
		ServedBy = servedBy;
	}

	public String getStationNumber() {
		return StationNumber;
	}

	public void setStationNumber(String sStationNumber) {
		StationNumber = sStationNumber;
	}
	public String getselmoFronLinerURL() {
		return selmoFronLinerURL;
	}

	public void setselmoFronLinerURL(String sselmoFronLinerURL) {
		selmoFronLinerURL = sselmoFronLinerURL;
	}
}