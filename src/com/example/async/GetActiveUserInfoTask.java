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

import android.os.AsyncTask;
import android.widget.Toast;

import com.activeandroid.util.Log;
import com.example.frontlinerproject.CounterActivity;
import com.example.servicemodel.IConstantMessageStatus;
import com.example.servicemodel.LoginService;

public class GetActiveUserInfoTask extends AsyncTask<String, Void, LoginService>{
	private CounterActivity context;
	
	public GetActiveUserInfoTask(CounterActivity context){
		this.context = context;
	}

	@Override
	protected LoginService doInBackground(String... params) {
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

			LoginService result = restTemplate.getForObject(url, LoginService.class);
			return result;

		} catch (Exception e) {
			Log.e(e.getMessage());
			// TODO: handle exception			
			return new LoginService();
		}
	}
	
	@Override
	protected void onPostExecute(LoginService result) {
		if(IConstantMessageStatus.KEY_SUCCESS.equals(result.getSuccess())){
			if(result.getData() != null){
				this.context.setCheckedUserRole(result.getData().getUserIsActive());
			}
		} else {
			Toast.makeText(context, "No active user.", Toast.LENGTH_SHORT).show();
		}
	}

}
