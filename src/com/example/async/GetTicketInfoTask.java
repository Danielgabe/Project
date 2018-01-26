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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;  

import com.example.frontlinerproject.AntrianActivity;
import com.example.model.GetTicketInfoModel; 
import com.example.servicemodel.GetTicketInfoService;
import com.example.servicemodel.IConstantMessageStatus;

public class GetTicketInfoTask extends AsyncTask<String, Void, String> {
	AlertDialog dialog; 
	ProgressDialog pDialog;
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success"; 
	private AntrianActivity activity;
	private String service="";
	public GetTicketInfoTask(AntrianActivity mainContext)
	{
		activity = mainContext; 
		pDialog = new ProgressDialog(mainContext);
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
		
		if(service.equals("GetTicketInfoTask"))
		{
			 
			try
			{
				JSONObject json_data=new JSONObject(resultData);
				
				if(IConstantMessageStatus.KEY_SUCCESS.equals(json_data.getString("success"))
						&& IConstantMessageStatus.KEY_MESSAGESTATUS.equals(json_data.getString("messageStatus"))){
				
					JSONObject data= json_data.getJSONObject("data");
					
					if(data != null){
						GetTicketInfoModel ticketInfo=new GetTicketInfoModel();
						ticketInfo.setTicketID(data.getString("TicketID"));
						ticketInfo.setTicketDate(data.getString("TicketDate"));
						ticketInfo.setBranchID(data.getString("BranchID")) ; 
						ticketInfo.setTicketCategoryCode(data.getString("TicketCategoryCode")); 
						ticketInfo.setTicketNumber(data.getString("TicketNumber"));   
						ticketInfo.setComingWhen(data.getString("ComingWhen"));  
						ticketInfo.setTicketStatus(data.getString("TicketStatus"));  
						ticketInfo.setStationNumber(data.getString("StationNumber"));  
						ticketInfo.setServedBy(data.getString("ServedBy")); 
						ticketInfo.setServedWhen(data.getString("ServedWhen")); 
						ticketInfo.setNotes(data.getString("Notes")); 
						ticketInfo.setBookingID(data.getString("BookingID")); 
						ticketInfo.setTicketNumberingCode(data.getString("TicketNumberingCode"));  
						
						GetTicketInfoService alternatedService=new GetTicketInfoService(); 
						alternatedService.setData(ticketInfo);
						alternatedService.getData().save();
						
						if("0".equals(ticketInfo.getTicketStatus())){
							activity.setLayanan(ticketInfo);
						}
					}
				}
			}
			
			catch(JSONException jex)
			{
				jex.printStackTrace();
			}
			 
		}
	}

}
 
	 
