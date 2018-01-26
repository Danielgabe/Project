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
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.frontlinerproject.CounterActivity;
import com.example.frontlinerproject.GlobalClass;
import com.example.frontlinerproject.LayananNonSurveyorActivity;
import com.example.frontlinerproject.LayananSurveyorActivity;
import com.example.model.GetListUserRoleModel;
import com.example.model.GetTicketCategoryByCodeModel;
import com.example.servicemodel.AddAlertNumberAndVoiceService;
import com.example.servicemodel.GetListUserRoleService;
import com.example.servicemodel.GetTicketCategoryByCodeService;

public class GetTicketCategoryByCodeTask extends AsyncTask<String, Void, GetTicketCategoryByCodeService> {

	private String service="";
	ProgressDialog pDialog;
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success";
	String user="",branchID="",lists="";
	private Context context;
	
	public GetTicketCategoryByCodeTask(Context mainContext)
	{
		context=mainContext;
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}

	@Override
	protected GetTicketCategoryByCodeService doInBackground(String... params) {

		
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

			GetTicketCategoryByCodeService result = restTemplate.getForObject(url,
					GetTicketCategoryByCodeService.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return new GetTicketCategoryByCodeService();
		}
		

	}

	@Override
	protected void onPostExecute(GetTicketCategoryByCodeService resultData) {
		
		
		if (pDialog.isShowing()) {pDialog.dismiss();
		}
		
		if (resultData.getMessageStatus().equals(KEY_MESSAGESTATUS)
				&& resultData.getSuccess().equalsIgnoreCase(KEY_SUCCESS)) {
			
			if(service.equals("GetTicketCategoryByCodeTask"))
			{	
				if(resultData.getData()!=null)
				{
					resultData.getData().save();
					Toast.makeText(context, "Tetep di tempat", Toast.LENGTH_LONG).show();
					Intent intent; 
						intent = new Intent(context,
								LayananSurveyorActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						context.startActivity(intent);
					}
					else
					{
						Intent intent; 
						intent = new Intent(context,
								LayananNonSurveyorActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						context.startActivity(intent);
					}
				}
			 
				 

		 
}
	}

}
 
		
		
	 
 

 
