package com.example.async ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast; 

import com.example.frontlinerproject.AntrianActivity;
import com.example.frontlinerproject.CounterActivity;
import com.example.frontlinerproject.GlobalClass; 
import com.example.frontlinerproject.LayananSurveyorActivity;
import com.example.servicemodel.GetTicketOnProcessByUserService;

public class GetTicketOnProcessByUserTask extends AsyncTask<String, Void, GetTicketOnProcessByUserService> {
	
	
	private ProgressDialog pDialog;
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success";
	private CounterActivity activity;
	
	public GetTicketOnProcessByUserTask(CounterActivity activity)
	{
		this.activity=activity;
		pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}
	

	@Override
	protected GetTicketOnProcessByUserService doInBackground(String... params) {

		
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

			GetTicketOnProcessByUserService result = restTemplate.getForObject(url,
					GetTicketOnProcessByUserService.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return new GetTicketOnProcessByUserService();
		}
		

	}

	@Override
	protected void onPostExecute(GetTicketOnProcessByUserService resultData) {
		
		
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		
		if (resultData.getMessageStatus().equals(KEY_MESSAGESTATUS)
				&& resultData.getSuccess().equalsIgnoreCase(KEY_SUCCESS)) {			
						
			resultData.getData().save();
			final GlobalClass globalVariable = (GlobalClass) activity.getApplicationContext();
			globalVariable.setServedBy(resultData.getData().getServedBy());
			globalVariable.setStationNumber(resultData.getData().getStationNumber());
			String lists=globalVariable.getRoleID();
			Intent intent;
			
			if(lists.equals("SVY"))
			{
				intent = new Intent(activity,
						LayananSurveyorActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				activity.startActivity(intent);
			}
			else
			{
				intent = new Intent(activity,
						CounterActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				activity.startActivity(intent);
			}	
		} 
		else
		{
			this.activity.setAntrian();
		}
	}

}
