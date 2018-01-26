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

import com.example.frontlinerproject.CounterActivity;
import com.example.frontlinerproject.GlobalClass;
import com.example.model.GetListUserRoleModel;
import com.example.servicemodel.GetListUserRoleService;

public class GetListUserRoleTask extends AsyncTask<String, Void, String> { 
	private CounterActivity context;
	
	public GetListUserRoleTask(CounterActivity mainContext)
	{
		context = mainContext;
	}

	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		
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
			Log.e("GetListUserRoleTask", e.getMessage());
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(String resultData) {
		if (resultData != null) {
			try {
				ArrayList<String> stringArray = new ArrayList<String>();
				JSONObject json_data = new JSONObject(resultData);

				JSONArray jArray = json_data.getJSONArray("data");
				for (int x = 0; x < jArray.length(); x++) {

					GetListUserRoleModel alternated = new GetListUserRoleModel();
					alternated.setUserName(jArray.getJSONObject(x).getString("UserName"));
					alternated.setUserDomain(jArray.getJSONObject(x).getString("UserDomain"));
					alternated.setRoleID(Integer.parseInt(jArray.getJSONObject(x).getString("RoleID")));
					alternated.setRoleCode(jArray.getJSONObject(x).getString("RoleCode"));
					alternated.setRoleName(jArray.getJSONObject(x).getString("RoleName"));
					
					GetListUserRoleService alternatedService = new GetListUserRoleService();
					alternatedService.addRoleModel(alternated);
					alternated.save();
					final GlobalClass globalVariable = (GlobalClass) context.getApplicationContext();
					stringArray.add(jArray.toString());
					String RoleCode = jArray.getJSONObject(x).getString("RoleCode");
					Log.i("log_tag", RoleCode);
					globalVariable.addRole(RoleCode);
				}
				this.context.setRoleOptions();
			}

			catch (JSONException jex) {
				jex.printStackTrace();
			}
		}

	}

}
 
		
		
	 
 

 
 