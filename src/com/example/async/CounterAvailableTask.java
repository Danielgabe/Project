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

import com.example.frontlinerproject.CounterActivity;
import com.example.model.CounterAvailableModel;
import com.example.servicemodel.CounterAvailableService;

public class CounterAvailableTask extends
		AsyncTask<String, Void, CounterAvailableService> {
	AlertDialog dialog;
	
	private static String KEY_SUCCESS = "true";
	private static String KEY_MESSAGESTATUS = "success";
	private CounterActivity context;

	public CounterAvailableTask(CounterActivity mainContext) {
		context = mainContext;
	}

	@Override
	protected CounterAvailableService doInBackground(String... params) {
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

			CounterAvailableService result = restTemplate.getForObject(url,
					CounterAvailableService.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception

			return new CounterAvailableService();
		}

	}

	@Override
	protected void onPostExecute(CounterAvailableService resultData) {
		if (resultData.getMessageStatus().equals(KEY_MESSAGESTATUS)
				&& resultData.getSuccess().equalsIgnoreCase(KEY_SUCCESS)) {
			if(resultData.getData() != null){
				CounterAvailableModel model = (CounterAvailableModel) resultData.getData();
				context.getActiveUserInfo(model.getCounterAvailable());
			}

		} else {
			Toast.makeText(context, "Counter Ter-isi", Toast.LENGTH_LONG).show();
		}
	}
}
