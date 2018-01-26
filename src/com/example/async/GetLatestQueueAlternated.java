package com.example.async ;

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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontlinerproject.AntrianActivity;
import com.example.frontlinerproject.CounterActivity;
import com.example.model.LatestQueueAlternated;
import com.example.servicemodel.IConstantMessageStatus;
import com.example.servicemodel.LatestQueueAlternatedService;

public class GetLatestQueueAlternated extends
		AsyncTask<String, Void, String> {
	
	private Context context;
	ProgressDialog pDialog;
	private String service="";
	
	public GetLatestQueueAlternated(CounterActivity mainContext)
	{
		this.context=mainContext;
		Toast.makeText(mainContext, "GetLatestContext "+context.toString(), Toast.LENGTH_LONG).show();
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
		
		ArrayList<LatestQueueAlternated> nomorTicket = new ArrayList<LatestQueueAlternated>();
		
		
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
	
		if(service.equals("GetLatestQueueAlternated"))
		{
			 
			try
			{
				JSONObject json_data=new JSONObject(resultData);
				
				JSONArray jArray=json_data.getJSONArray("data");
				
				for(int x=0;x<jArray.length();x++)
				{
					LatestQueueAlternated alternated=new LatestQueueAlternated();
					alternated.setTicketID(jArray.getJSONObject(x).getString("TicketID")); 
					alternated.setTicketDate(jArray.getJSONObject(x).getString("TicketDate"));
					alternated.setBranchID(jArray.getJSONObject(x).getString("BranchID"));
					alternated.setTicketCategoryCode(jArray.getJSONObject(x).getString("TicketCategoryCode")); 
					alternated.setTicketNumber(jArray.getJSONObject(x).getString("TicketNumber"));
					alternated.setTicketStatus(Integer.parseInt(jArray.getJSONObject(x).getString("TicketStatus")));
					alternated.setSequence(Integer.parseInt(jArray.getJSONObject(x).getString("Sequence")));
					alternated.setQueueTypeCode(jArray.getJSONObject(x).getString("QueueTypeCode")); 
					alternated.setQueueEntryPriority(Integer.parseInt(jArray.getJSONObject(x).getString("QueueEntryPriority")));
					alternated.setQueueReEntryPriority(Integer.parseInt(jArray.getJSONObject(x).getString("QueueReEntryPriority"))); 
					
					nomorTicket.add(alternated);
						
					LatestQueueAlternatedService alternatedService=new LatestQueueAlternatedService();
					alternatedService.setData(alternated);
					alternatedService.getData().save();
				}
				
				updateList(nomorTicket);
			}
			catch(JSONException jex)
			{
				jex.printStackTrace();
			}
		}
	}
	
	private void updateList(final ArrayList<LatestQueueAlternated> nomorTicket){
		Intent intent = new Intent(context, AntrianActivity.class);
		
		// Create a Bundle and Put Bundle in to it
		Bundle bundleObject = new Bundle();
		bundleObject.putSerializable(IConstantMessageStatus.LIST_TICKET, nomorTicket);
		
		intent.putExtras(bundleObject);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
    }

}
