/*package com.example.frontlinerprojekservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

import com.example.frontlinerprojek.GlobalClass;

public class UserFunctions {
	
	private JSONParser jsonParser;
	
 
	private static String GetActiveUserInfo = globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetActiveUserInfo&userName=";
	private static String IsCounterAvailable = globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=isCounterAvailable&userName=";
	private static String GetListUserRole = globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetListUserRole&userName=";
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	
	*//**
	 * function make Login Request
	 * @param email
	 * @param password
	 * *//*
	public JSONObject GetActiveUserInfo(String UserName, String UserDomain){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("KEY_USER_NAME", UserName));
		params.add(new BasicNameValuePair("KEY_USER_NAME", UserDomain));
		JSONObject json = jsonParser.makeHttpRequest(GetActiveUserInfo+UserName+"&userDomain="+UserDomain, "POST", params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	
	public JSONObject IsCounterAvailable(String UserDomain, String CounterNo, String BranchID ){
		// Building Parameters
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		List<NameValuePair> params = new ArrayList<NameValuePair>();  
		params.add(new BasicNameValuePair("KEY_USER_DOMAIN", UserDomain));
		params.add(new BasicNameValuePair("KEY_COUNTER_NO", CounterNo));
		params.add(new BasicNameValuePair("KEY_BRANCH_ID", BranchID)); 
		String urlIsCounterAvailable = IsCounterAvailable+UserDomain+"&userDomain="+UserDomain;
		globalVariable.setUrlIsCounterAvailable(urlIsCounterAvailable);
		JSONObject json = jsonParser.makeHttpRequest(urlIsCounterAvailable, "POST", params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	
	private GlobalClass getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject GetListUserRole(String UserName, String UserDomain){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("KEY_USER_NAME", UserName));
		params.add(new BasicNameValuePair("KEY_USER_DOMAIN", UserDomain)); 
		JSONObject json = jsonParser.makeHttpRequest(GetListUserRole+UserName+"&userDomain="+UserDomain, "POST", params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	 
	
	public JSONObject csoButton(String nomerNya){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//params.add(new BasicNameValuePair("taginitialandpassword", initialAndpassword)); 
		params.add(new BasicNameValuePair("KEY_BRANCHID", nomerNya)); 
		JSONObject json = jsonParser.makeHttpRequest(urlcservice+nomerNya, "POST", params);
		//JSONObject json = jsonParser.makeHttpRequest(loginIntialPassword, "POST", params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	*//**
	 * function make Login Request
	 * @param name
	 * @param email
	 * @param password
	 * *//*
	 
	
	*//**
	 * Function get Login status
	 * *//*
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	
	*//**
	 * Function to logout user
	 * Reset Database
	 * *//*
	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
}
*/