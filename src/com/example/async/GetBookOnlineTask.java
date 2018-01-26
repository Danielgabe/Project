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
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast; 

import com.example.frontlinerproject.GlobalClass; 
import com.example.model.GetBookOnlineModel; 
import com.example.servicemodel.GetBookOnlineService; 
import com.example.servicemodel.IConstantMessageStatus;

public class GetBookOnlineTask extends AsyncTask<String, Void, String> {
	AlertDialog dialog; 
	ProgressDialog pDialog;
	 
	private Context context;
	private String service="";
	
	public GetBookOnlineTask(Context mainContext)
	{
		context=mainContext; 
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

			String result = restTemplate.getForObject(url, String.class);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			
			return null;
		}
		

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPostExecute(String resultData) {
		
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		
		if(service.equals("GetBookOnline"))
		{
			 
			try
			{
				JSONObject json_data=new JSONObject(resultData);
				String success = json_data.getString("success");
				String messageStatus = json_data.getString("messageStatus");
				
				if(IConstantMessageStatus.KEY_SUCCESS.equals(success)
						&& IConstantMessageStatus.KEY_MESSAGESTATUS.equals(messageStatus))
				{
					JSONObject data = json_data.getJSONObject("data");
					
					GetBookOnlineModel bookOnline=new GetBookOnlineModel();
					bookOnline.setBookOnlineID(data.getString("BookOnlineID"));
					bookOnline.setBookDate(data.getString("BookDate"));
					bookOnline.setVerificationCode(data.getString("VerificationCode")) ; 
					bookOnline.setCustomerName(data.getString("CustomerName")); 
					bookOnline.setHPNo(data.getString("HPNo"));   
					bookOnline.setCodeUsed(data.getString("CodeUsed")); 
					bookOnline.save();
					
					GetBookOnlineService bookOnlineService=new GetBookOnlineService(); 
					bookOnlineService.setData(bookOnline);
					bookOnlineService.getData().save();
					
					final GlobalClass globalVariable = (GlobalClass) context.getApplicationContext();
					globalVariable.setBookOnlineID(bookOnline.getBookOnlineID());
					
					final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
					alertDialog.setTitle("Informasi");
					alertDialog.setMessage("BookOnlineID : "
								+ bookOnline.getBookOnlineID()
								+ "\nCustomerName : "
								+ bookOnline.getCustomerName()
								+ "Phone Number :"
								+ bookOnline.getHPNo()
								+ "Book Date :"
								+ bookOnline.getBookDate());
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							alertDialog.cancel();
							
						}
					});
					alertDialog.show();	
					
				}
				else
				{
					final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
					alertDialog.setTitle("Informasi");
					alertDialog.setMessage("Booking Online Tidak Tersedia");
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							alertDialog.cancel();
							
						}
					});
					alertDialog.show();	
				}
			}
			
			catch(JSONException jex)
			{
				jex.printStackTrace();
			}
	
	//	Toast.makeText(getApplicationContext(), resultData.getData().toString(), Toast.LENGTH_LONG).show();
		 
	}
	}

}
 
	 
