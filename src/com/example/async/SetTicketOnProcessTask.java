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
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;  
import com.example.servicemodel.SetTicketOnProcessService;

public class SetTicketOnProcessTask extends AsyncTask<String, Void, SetTicketOnProcessService> {

	private Context mContext;
	ProgressDialog loadingBar;

	public SetTicketOnProcessTask(Context context) {

		// Set Context
		mContext = context;
	}
	
	@Override
	protected void onPreExecute() {
		// create loading bar
		loadingBar = new ProgressDialog(mContext);
		loadingBar.setMessage("Loading ...");
		loadingBar.setIndeterminate(false);
		loadingBar.setCancelable(true);
		loadingBar.show();
	}

	@Override
	protected SetTicketOnProcessService doInBackground(String... params) {
		
		String url = params[0];

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
			SetTicketOnProcessService result = restTemplate.getForObject(url, SetTicketOnProcessService.class);
			return result;
		} catch (Exception e) {
			Log.e("SetTicketOnProcessServiceTask", e.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(SetTicketOnProcessService result) {
		if (loadingBar.isShowing()) {
			loadingBar.dismiss();
		}
		
		if(result == null){
			Toast.makeText(mContext, "Network error.", Toast.LENGTH_SHORT).show();
		} else {
			if("true".equals(result.getSuccess()) && "success".equals(result.getMessageStatus()) )			
			{
				//result.getData().save();
				Toast.makeText(mContext, "Data saved.", Toast.LENGTH_SHORT).show();
			}
			else 
			{
				result.getData().save();
				Toast.makeText(mContext, "No Data.", Toast.LENGTH_SHORT).show();
			}
		}
	}

}
