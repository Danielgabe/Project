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

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.servicemodel.LoginDummyService;

public class LoginDummyTask extends AsyncTask<String, Void, LoginDummyService> {
	AlertDialog dialog; 
	ProgressDialog pDialog;
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success"; 
	private Context context;
	
	public LoginDummyTask(Context mainContext)
	{
		context=mainContext; 
		pDialog = new ProgressDialog(mainContext);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
	}

	@Override
	protected LoginDummyService doInBackground(String... params) {
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

			LoginDummyService result = restTemplate.getForObject(url,
					LoginDummyService.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return new LoginDummyService();
		}
		

	}

	@Override
	protected void onPostExecute(LoginDummyService resultData) {
		
	 
	//	Toast.makeText(getApplicationContext(), resultData.getData().toString(), Toast.LENGTH_LONG).show();
		if (resultData.getMessageStatus().equals(KEY_MESSAGESTATUS)
				&& resultData.getSuccess().equalsIgnoreCase(KEY_SUCCESS)) {
			Toast.makeText(context, " Nomer Counter Ada ", Toast.LENGTH_LONG).show();
		} else {
			
			Toast.makeText(context, " Nomer Counter Tidak Ada ", Toast.LENGTH_LONG).show();
	}
	}
	}

 
	 
 