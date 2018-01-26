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
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.frontlinerproject.GlobalClass;
import com.example.frontlinerproject.LayananNonSurveyorActivity;
import com.example.frontlinerproject.LayananSurveyorActivity;
import com.example.model.GetListActiveServiceTypeByTicketCategoryUmumModel;
import com.example.servicemodel.GetListActiveServiceTypeByTicketCategoryUmumService;
import com.example.servicemodel.IConstantMessageStatus;

public class GetListActiveServiceTypeByTicketCategoryUmumTask extends
		AsyncTask<String, Void, String> {

	ProgressDialog pDialog;
	private String service = "";
	protected Activity activity;

	public GetListActiveServiceTypeByTicketCategoryUmumTask(Activity activity) {
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
		service = params[1];

		// Template Begin
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		// template end

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

		if (service.equals("GetListActiveServiceTypeByTicketCategory")) {

			try {
				JSONObject json_data = new JSONObject(resultData);
				
				String success = json_data.getString("success");
				String messageStatus = json_data.getString("messageStatus");
				
				if(IConstantMessageStatus.KEY_SUCCESS.equals(success)
						&& IConstantMessageStatus.KEY_MESSAGESTATUS.equals(messageStatus))
				{
					JSONArray data = json_data.getJSONArray("data");					
					ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceList = new ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel>();

					for (int index = 0; index < data.length(); index++) {
						JSONObject itemData = data.getJSONObject(index);
						GetListActiveServiceTypeByTicketCategoryUmumModel serviceModel = new GetListActiveServiceTypeByTicketCategoryUmumModel();
						serviceModel.setServiceTypeID(itemData.getString("ServiceTypeID"));
						serviceModel.setName(itemData.getString("Name"));
						serviceModel.setTicketCategoryCode(itemData.getString("TicketCategoryCode"));
						serviceModel.setIsActive(itemData.getString("IsActive"));
						serviceModel.save();
						
						GetListActiveServiceTypeByTicketCategoryUmumService serviceModelService = new GetListActiveServiceTypeByTicketCategoryUmumService();
						serviceModelService.setData(serviceModel);
						serviceModelService.getData().save();
						
						serviceList.add(serviceModel);
					}
					updateList(serviceList);;
				}
			} catch (JSONException jex) {
				jex.printStackTrace();
			}
		}
	}

	public void updateList(final ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceList) {
		
		if(activity instanceof LayananSurveyorActivity){
			((LayananSurveyorActivity)activity).setActiveServiceType(serviceList);
		}
		if(activity instanceof LayananNonSurveyorActivity){
			((LayananNonSurveyorActivity)activity).setActiveServiceType(serviceList);
		}
	}

}
