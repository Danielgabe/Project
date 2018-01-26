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

import com.example.frontlinerproject.AntrianActivity;
import com.example.frontlinerproject.LayananSurveyorActivity;
import com.example.model.GetListActiveServiceGroupByTicketCategoryModel;
import com.example.servicemodel.GetListActiveServiceGroupByTicketCategoryService;
import com.example.servicemodel.IConstantMessageStatus;

public class GetListActiveServiceGroupByTicketCategoryTask extends AsyncTask<String, Void, String> {

	ProgressDialog pDialog;
	private String service = "";
	protected Activity activity;
	
	public GetListActiveServiceGroupByTicketCategoryTask(Activity activity)
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
	
		if(service.equals("GetListActiveServiceGroupByTicketCategory"))
		{
			 
			try
			{
				JSONObject json_data = new JSONObject(resultData);
				
				String success = json_data.getString("success");
				String messageStatus = json_data.getString("messageStatus");
				
				if(IConstantMessageStatus.KEY_SUCCESS.equals(success)
						&& IConstantMessageStatus.KEY_MESSAGESTATUS.equals(messageStatus))
				{
					JSONArray data = json_data.getJSONArray("data");
					ArrayList<GetListActiveServiceGroupByTicketCategoryModel> serviceList = new ArrayList<GetListActiveServiceGroupByTicketCategoryModel>();
					for(int index=0; index<data.length() ; index++)
					{
						JSONObject itemData = data.getJSONObject(index);
						
						GetListActiveServiceGroupByTicketCategoryModel groupTicket=new GetListActiveServiceGroupByTicketCategoryModel();
						
						groupTicket.setServiceGroupID(Integer.parseInt(itemData.getString("ServiceGroupID"))); 
						groupTicket.setName(itemData.getString("Name"));
						groupTicket.setTicketCategoryCode(itemData.getString("TicketCategoryCode"));
						if(itemData.getString("IsActive").equals("True"))
						{
							groupTicket.setIsActive(true);
						}
						else
						{
							groupTicket.setIsActive(false);
						}
						groupTicket.save();
												
						GetListActiveServiceGroupByTicketCategoryService groupService=new GetListActiveServiceGroupByTicketCategoryService();
						groupService.setData(groupTicket);
						groupService.getData().save();
						
						serviceList.add(groupTicket);
					}
					updateList(serviceList);
				}				
			}
			catch(JSONException jex)
			{
				jex.printStackTrace();
			}
		}
	}
	
	public void updateList(final ArrayList<GetListActiveServiceGroupByTicketCategoryModel> serviceList){
		if(activity instanceof LayananSurveyorActivity){
			((LayananSurveyorActivity)activity).setActiveServiceGroup(serviceList);
		}
    }

}
