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
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.frontlinerproject.GlobalClass;
import com.example.frontlinerproject.LayananSurveyorActivity;
import com.example.frontlinerproject.TransferSurveyor;
import com.example.servicemodel.TransferSurveyorService;

public class TransferTask extends AsyncTask<String, Void, TransferSurveyorService>{

	private String service="";
	ProgressDialog pDialog;
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success";
	private Activity activity;
	
	public TransferTask(Activity activity)
	{
		this.activity = activity;
		
		pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}
	
	@Override
	protected TransferSurveyorService doInBackground(String... params) {
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

			TransferSurveyorService result = restTemplate.getForObject(url,
					TransferSurveyorService.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return new TransferSurveyorService();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onPostExecute(TransferSurveyorService resultData) {
		
		
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		
		if (service.equals("TransferTicketCategory") && KEY_MESSAGESTATUS.equals(resultData.getMessageStatus())
				&& KEY_SUCCESS.equals(resultData.getSuccess())) {
			
			if(activity instanceof TransferSurveyor)
			{
				Toast.makeText(activity, "disini GetLatestQueueAlternated", Toast.LENGTH_LONG).show();
				final GlobalClass globalVariable = (GlobalClass) activity.getApplicationContext();
				String user=globalVariable.getUserName();
				String ticketID=globalVariable.getTicketID();
				String serviceGroupName=globalVariable.getName();
				
				new UpdateServiceGroupTask(activity).execute(globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=updateServiceGroup&userName="+user+"&ticketID="+ticketID+"&serviceGroupID=1&serviceGroupName="+serviceGroupName,"updateServiceGroup");
			}
			else
			{
				final GlobalClass globalVariable = (GlobalClass) activity.getApplicationContext();			
				String user = globalVariable.getUserName();
				String ticketId = globalVariable.getTicketID();
				
				new ProcessFinishingServingTicket(activity).execute(globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=ProcessFinishingServingTicket&ticketID="+ticketId+"&userName="+user,"ProcessFinishingServingTicket"); 
			}			

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
