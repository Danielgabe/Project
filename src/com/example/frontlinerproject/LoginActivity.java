package com.example.frontlinerproject;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.util.Log;
import com.example.fileconfig.FileConfigUtil;
import com.example.frontlinerproject.util.ConnectionDetector;
import com.example.frontlinerprojek.R;
import com.example.model.LoginModel;
import com.example.servicemodel.IConstantMessageStatus;
import com.example.servicemodel.LoginService;

public class LoginActivity extends Activity {

	EditText textUsername;
	EditText textPassword;
	private static String KEY_SUCCESS = "true";
	private static String KEY_MESSAGESTATUS = "success";
	private static String baseUrl = IConstantMessageStatus.BASE_URL;//FileConfigUtil.getBaseUrl();
	
	// Connection detector class
	ConnectionDetector connectionDetector;
	
	// flag for Internet connection status
	Boolean isInternetPresent = false;
	 

	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_frontliner);  
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		globalVariable.setselmoFronLinerURL(baseUrl);
		 
		/* Remove acionbar*/
		ActionBar bar = getActionBar();
		bar.hide();
		
		// creating connection detector class instance
		connectionDetector = new ConnectionDetector(getApplicationContext());
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			textUsername = (EditText) findViewById(R.id.editTextUsername);
			textPassword = (EditText) findViewById(R.id.editTextPassword); 
			textUsername.setText("icn-netdev\\administrator");
			textPassword.setText("Abcd1234");

			Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
			
			buttonLogin.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View paramView) {
					// get Internet status
					isInternetPresent = connectionDetector.isConnectingToInternet();

					// check for Internet status
					if (isInternetPresent) {
						// Internet Connection is Present
						// make HTTP requests
						final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
						String[] domainArr=textUsername.getText().toString().split("\\\\");
						
						if(domainArr.length > 1)
						{
							String domain=domainArr[0];
							globalVariable.setUserDomain(domain);
							String user=domainArr[1];
							String password = textPassword.getText().toString();
							String urlString = globalVariable.getselmoFronLinerURL() +"/Services/SelmoFrontLiner.ashx?method=CheckFrontLinerLogin&userName=";
							String CheckFrontLinerLogin=urlString+user+"&userDomain="+domain+"&userPassword="+password;

							Toast.makeText(getApplicationContext(), CheckFrontLinerLogin, Toast.LENGTH_LONG).show();
							//service must execute in UIThread..
							
							new HttpRequestTask().execute(CheckFrontLinerLogin,"CheckFrontLinerLogin");
						}
						else{
							showAlertDialog(LoginActivity.this, "Information", "Format username salah.", false);
						}
					} else {
						// Internet connection is not present
						// Ask user to connect to Internet
						showAlertDialog(LoginActivity.this, "No Internet Connection","You don't have internet connection.", false);
					}					
				}
			});

			
			

		}

	}

	private class HttpRequestTask extends AsyncTask<String, Void, LoginService> {
		
		
		private ProgressDialog pDialog;
		private String service="";
		
		protected void onPreExecute() {
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
		}
	
		@Override
		protected LoginService doInBackground(String... params) {

			
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

				LoginService result = restTemplate.getForObject(url,
						LoginService.class);
				return result;

			} catch (Exception e) {
				// TODO: handle exception
				
				return new LoginService();
			}
			

		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(LoginService resultData) {
			
			
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}
			
			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); 
			//String imei = telephonyManager.getDeviceId().toString();
			String imei = "000000000000000";
			
			if(service.equals("GetBranchIDFromHostName"))
			Toast.makeText(getApplicationContext(), resultData.getMessageStatus()+" "+resultData.getSuccess(), Toast.LENGTH_LONG).show();
			
			if (resultData != null && KEY_MESSAGESTATUS.equals(resultData.getMessageStatus())
					&& KEY_SUCCESS.equals(resultData.getSuccess())) {
				
				if(service.equals("CheckFrontLinerLogin"))
				{
					final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
					globalVariable.setUserName(resultData.getData().getUserName());
					globalVariable.setUserDomain(resultData.getData().getDomain());
					globalVariable.setUserPassword(resultData.getData().getPassword());
					globalVariable.setUserEmail(resultData.getData().getEmail());
					globalVariable.setUserBranchID(resultData.getData().getBranchID()); 
					globalVariable.setLoginService(resultData);
					resultData.getData().save();
					String GetBranchIDFromHostName =  globalVariable.getselmoFronLinerURL()+"/Services/SelmoTouchScreen.ashx?method=GetBranchIDFromHostName&DeviceName="+imei;
					new HttpRequestTask().execute(GetBranchIDFromHostName,"GetBranchIDFromHostName");
					
					
				}
				else
				{
					final GlobalClass globalVariable = (GlobalClass)getApplicationContext();
					globalVariable.setBranchID(resultData.getData().getBranchID());
					Toast.makeText(getApplicationContext(), globalVariable.getUserBranchID(), Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(), globalVariable.getUserBranchID()+" "+globalVariable.getBranchID(), Toast.LENGTH_LONG).show();
					
					if(globalVariable.getUserBranchID().equals(globalVariable.getBranchID()))
					{
						Intent dashboard = new Intent(LoginActivity.this,
								CounterActivity.class);
						dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(dashboard);
						
						
						//show save tabel ResultData to table
						
						globalVariable.getLoginService().getData().save();
						
					}
					else
					{
						Toast.makeText(getApplicationContext(), " Incorrect initial/password", Toast.LENGTH_LONG).show();
					}
				}		
				

			}  else {			
				final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
				alertDialog.setTitle("Informasi");
				alertDialog.setMessage("Terjadi gangguan koneksi atau login salah. Maaf proses tidak dapat dilanjutkan.");
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						alertDialog.cancel();
						
					}
				});
				alertDialog.show();	

			}
 
		}

	}
	
	/**
	 * Function to display simple Alert Dialog
	 * @param context - application context
	 * @param title - alert dialog title
	 * @param message - alert message
	 * @param status - success/failure (used to set icon)
	 * */
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);
		
		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}

	 
