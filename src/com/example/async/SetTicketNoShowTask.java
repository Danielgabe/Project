package com.example.async;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate; 

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast; 
import com.example.frontlinerproject.LayananNonSurveyorActivity;
import com.example.frontlinerproject.LayananSurveyorActivity;
import com.example.servicemodel.SetTicketNoShowService;

public class SetTicketNoShowTask extends AsyncTask<String, Void, SetTicketNoShowService> {

	private String service="";
	ProgressDialog pDialog;
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success";
	String user="",branchID="",lists="";
	private Activity activity;
	
	public SetTicketNoShowTask(Activity activity)
	{
		this.activity = activity;
		pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}

	@Override
	protected SetTicketNoShowService doInBackground(String... params) {

		
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

			SetTicketNoShowService result = restTemplate.getForObject(url,
					SetTicketNoShowService.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return new SetTicketNoShowService();
		}
		

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPostExecute(SetTicketNoShowService resultData) {
		
		
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		
		if (service.equals("SetTicketNoShow") && resultData.getMessageStatus().equals(KEY_MESSAGESTATUS)
				&& resultData.getSuccess().equalsIgnoreCase(KEY_SUCCESS)) {
			
			final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
			alertDialog.setTitle("Informasi");
			alertDialog.setMessage("Status ticket berhasi diubah.");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alertDialog.cancel();
				}
			});
			alertDialog.show();
		}
		else
		{
			final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
			alertDialog.setTitle("Informasi");
			alertDialog.setMessage("Terjadi gangguan. Maaf proses tidak dapat dilanjutkan.");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alertDialog.cancel();
				}
			});
			alertDialog.show();
		}
	}

}
 
		
		
	 
 

 
