package com.example.async;

import java.text.DateFormat;
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

import com.example.async.GetLatestQueueAlternated;
import com.example.frontlinerproject.CounterActivity;
import com.example.frontlinerproject.GlobalClass; 
import com.example.frontlinerproject.LayananSurveyorActivity;
import com.example.servicemodel.AntrianService;

public class AntrianTask extends AsyncTask<String, Void, AntrianService> {

	private String service="";
	ProgressDialog pDialog;
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success";
	String user="",branchID="",lists="";
	private Context context;
	
	public AntrianTask(Context mainContext)
	{
		context=mainContext;
		//Toast.makeText(context, "disini constructor", Toast.LENGTH_LONG).show();
		final GlobalClass globalVariable = (GlobalClass) mainContext.getApplicationContext();
		user=globalVariable.getUserName();
		branchID=globalVariable.getBranchID();
		lists=globalVariable.getRoleID();
		
		pDialog = new ProgressDialog(mainContext);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}

	@Override
	protected AntrianService doInBackground(String... params) {

		
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

			AntrianService result = restTemplate.getForObject(url,
					AntrianService.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return new AntrianService();
		}
		

	}

	@Override
	protected void onPostExecute(AntrianService resultData) {
		
		
		if (pDialog.isShowing()) {pDialog.dismiss();
		}
		
		if (resultData.getMessageStatus().equals(KEY_MESSAGESTATUS)
				&& resultData.getSuccess().equals(KEY_SUCCESS)) {
			resultData.getData().save();
		}
			if(service.equals("GetTicketCategoryByCode"))
			{	
			 
				final GlobalClass globalVariable = (GlobalClass) context.getApplicationContext();
				String rodeId = globalVariable.getRoleID();
				new GetTicketCategoryByCodeTask(context).execute(globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetTicketCategoryByCode&ticketCategoryCode="+rodeId,"GetTicketCategoryByCode");
				Toast.makeText(context, "GetTicketCategoryByCodeTask Tereksekusi", Toast.LENGTH_LONG).show();
				
				if(resultData.getData()!=null)
				{
					//resultData.getData().save();
					Toast.makeText(context, "disini GetTicketOnProcessByUser", Toast.LENGTH_LONG).show();
					Intent intent;
					
					if(lists.equals("SVY"))
					{
						intent = new Intent(context,
								LayananSurveyorActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						context.startActivity(intent);
					}
					else
					{
						intent = new Intent(context,
								CounterActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						context.startActivity(intent);
					}
				}
				else
				{
//					Toast.makeText(context, "disini GetLatestQueueAlternated", Toast.LENGTH_LONG).show();
//					//final GlobalClass globalVariable = (GlobalClass) context.getApplicationContext();
//					String user=globalVariable.getUserName();
//					String branchID=globalVariable.getBranchID();
//					String lists=globalVariable.getRoleCode();
//					//String lastServedRole=resultData.getData().getTicketNumberingCode();
//					
//					Date now=new Date();
//					//String dateString=DateFormat.getDateTimeInstance(  DateFormat.SHORT, DateFormat.SHORT).format(now);
//					String dateString = "30/10/2014";
//					
//					new GetLatestQueueAlternated(context).execute(globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetLatestQueueAlternated&userName="+user+"&branchID="+branchID+"&ticketDate="+dateString+"&lists="+lists+"&lastServedRole="+lists,"GetLatestQueueAlternated");
//					Toast.makeText( context, "GetLatestQueueAlternated Tereksekusi", Toast.LENGTH_LONG).show();
				}
			}

		  
	}
	}

 
