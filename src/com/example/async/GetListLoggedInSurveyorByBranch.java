package com.example.async;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.frontlinerproject.TransferNonSurveyor;
import com.example.frontlinerproject.TransferSurveyor;
import com.example.frontlinerproject.GlobalClass;
import com.example.model.GetListLoggedInSurveyorByBranchModel;
import com.example.servicemodel.GetListLoggedInSurveyorByBranchService;
import com.example.servicemodel.IConstantMessageStatus;

public class GetListLoggedInSurveyorByBranch extends AsyncTask<String, Void, String>{

	private String service="";
	ProgressDialog pDialog;
	private Activity activity;
	
	public GetListLoggedInSurveyorByBranch(Activity activity)
	{
		this.activity = activity;
		
		pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}
	
	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		service=params[1];
		
		//Template Begin
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		//template end

		// Prepare header
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		try {

			String result = restTemplate.getForObject(url, String.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(String resultData) {
		
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
	
		if(service.equals("GetListLoggedInSurveyorByBranch"))
		{
			 
			try
			{
				JSONObject json_data=new JSONObject(resultData);
				String success = json_data.getString("success");
				String messageStatus = json_data.getString("messageStatus");
				
				if(IConstantMessageStatus.KEY_SUCCESS.equals(success)
						&& IConstantMessageStatus.KEY_MESSAGESTATUS.equals(messageStatus))
				{
					ArrayList<String> initals = new ArrayList<String>();					
					JSONArray data=json_data.getJSONArray("data");
					
					for(int index=0; index<data.length(); index++)
					{
						JSONObject item = data.getJSONObject(index);
						
						GetListLoggedInSurveyorByBranchModel loggedList=new GetListLoggedInSurveyorByBranchModel();
						loggedList.setUserName(item.getString("UserName")); 
						loggedList.setUserDomain(item.getString("UserDomain"));
						loggedList.setUserEmail(item.getString("UserEmail"));
						loggedList.setUserBranchID(item.getString("UserBranchID"));
						loggedList.setBranchName(item.getString("BranchName"));
						loggedList.setUserIsActive(item.getString("UserIsActive"));
						loggedList.setUserPassword(item.getString("UserIsActive"));
							
						GetListLoggedInSurveyorByBranchService loggedInService=new GetListLoggedInSurveyorByBranchService();
						loggedInService.setData(loggedList);
						loggedInService.getData().save();
						
						initals.add(loggedList.getUserName());
					}
					
					updateList(initals);
				}
				
				
			}
			catch(JSONException jex)
			{
				jex.printStackTrace();
			}
		}
	}
	
	public void updateList(final ArrayList<String> initals){
		if(activity instanceof TransferNonSurveyor)
		{
			((TransferNonSurveyor)activity).setInitialId(initals);
		}
		
		if(activity instanceof TransferSurveyor)
		{
			((TransferSurveyor)activity).setInitialId(initals);
		}
    }

}
