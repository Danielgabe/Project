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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.frontlinerproject.GlobalClass;
import com.example.model.GetListTemporaryServiceActivityByUserAndTicketIDModel;
import com.example.servicemodel.GetListTemporaryServiceActivityByUserAndTicketIDService;

public class GetListTemporaryServiceActivityByUserAndTicketIDTask extends AsyncTask<String, Void, String> { 
	private Context context;
	ProgressDialog pDialog;
	private String service="";
	
	public GetListTemporaryServiceActivityByUserAndTicketIDTask(Context mainContext)
	{
		context=mainContext;
		
		pDialog = new ProgressDialog(mainContext);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
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

			String result = restTemplate.getForObject(url,
					String.class);
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
	
		
			if(service.equals("GetListTemporaryServiceActivityByUserAndTicketID"))
			{
				 
				try
				{
					ArrayList<String> stringArray = new ArrayList<String>();
					JSONObject json_data=new JSONObject(resultData);
					
					JSONArray jArray=json_data.getJSONArray("data");
					for(int x=0;x<jArray.length();x++)
					{
						
						GetListTemporaryServiceActivityByUserAndTicketIDModel alternated=new GetListTemporaryServiceActivityByUserAndTicketIDModel();
							alternated.setTicketID(jArray.getJSONObject(x).getString("TicketID"));
							alternated.setServiceGroupID(jArray.getJSONObject(x).getString("ServiceGroupID")); 
							alternated.setServiceGroupName(jArray.getJSONObject(x).getString("ServiceGroupName")); 
							alternated.setServiceTypeID(jArray.getJSONObject(x).getString("ServiceTypeID"));   
							alternated.setServiceTypeName(jArray.getJSONObject(x).getString("ServiceTypeName")); 
							alternated.setStartWhen(jArray.getJSONObject(x).getString("StartWhen")); 
							alternated.setFinishWhen(jArray.getJSONObject(x).getString("FinishWhen")); 
							GetListTemporaryServiceActivityByUserAndTicketIDService alternatedService=new GetListTemporaryServiceActivityByUserAndTicketIDService();
							alternatedService.setData(alternated);
							alternatedService.getData().save();
							/*final GlobalClass globalVariable = (GlobalClass)context.getApplicationContext();
							for (int i=0; i<mp3List.legnth; i++)
							    Log.v("MyTag", Integer.toString(x) + ":" + jArray[x].getPath());
							 stringArray.add(jArray.toString()); 
							 String RoleCode = jArray.getJSONObject(x).getString("RoleCode");
							 Log.i("log_tag", RoleCode); 
							 globalVariable.setRoleCode(RoleCode);*/
					}				
				}
				
				catch(JSONException jex)
				{
					jex.printStackTrace();
				}
			  
			

			// Close Login Screen
			// finish();
		}  
	}

}
 
		
		
	 
 

 
 