package com.example.frontlinerproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.frontlinerprojek.R;
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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.util.Log;
import com.example.async.AntrianTask;
import com.example.async.GetLatestQueueAlternated;
import com.example.async.GetTicketInfoTask;
import com.example.fileconfig.FileConfigUtil;
import com.example.frontlinerproject.adapter.AntrianAdapter;
import com.example.frontlinerproject.util.ConnectionDetector;
import com.example.frontlinerprojek.R;
import com.example.model.GetTicketInfoModel;
import com.example.model.LatestQueueAlternated;
import com.example.model.LoginModel;
import com.example.servicemodel.AntrianService;
import com.example.servicemodel.IConstantMessageStatus;
import android.content.DialogInterface;

public class AntrianActivity extends Activity implements View.OnClickListener{
	
	private static String KEY_SUCCESS="true";
	private static String KEY_MESSAGESTATUS="success";
	private String user="", branchID="", lists="", lastServedRole="";
	public static ArrayList<String> nomorTiket=new ArrayList<String>();
	public ListView listViewTicket;
	private TextView textsisa;
	private static String baseUrl = FileConfigUtil.getBaseUrl();
	
	ArrayList<LatestQueueAlternated> listTicket;
	
	/* Fields decl*/
	private Button buttonLayani;
	private AntrianAdapter antrianAdapter;
	
	// Connection detector class
	ConnectionDetector connectionDetector;

	// flag for Internet connection status
	Boolean isInternetPresent = false;

	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.antrian);
		
		connectionDetector = new ConnectionDetector(getApplicationContext());
		/* Get Ticket*/
		try{
		    // Get the Bundle Object       
		    Bundle bundleObject = getIntent().getExtras();
		             
		    // Get ArrayList Bundle
		    listTicket = (ArrayList<LatestQueueAlternated>) bundleObject.getSerializable(IConstantMessageStatus.LIST_TICKET);
		  
		} catch(Exception e){
		    e.printStackTrace();
		}

		/* Remove acionbar */
		ActionBar bar = getActionBar();
		bar.hide();

		buttonLayani = (Button) findViewById(R.id.buttonLayani);
		buttonLayani.setOnClickListener(this);
		textsisa = (TextView) findViewById(R.id.textsisa);
		if(listTicket != null ) textsisa.setText(String.valueOf(listTicket.size()));

		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		user = globalVariable.getUserName();
		branchID = globalVariable.getBranchID();
		lists = globalVariable.getRoleCode();

		listViewTicket = (ListView) findViewById(R.id.listViewTicket);
		if(listTicket != null)
		{
			antrianAdapter = new AntrianAdapter(this, listTicket);
			listViewTicket.setAdapter(antrianAdapter);
		}
		

	}
	
private class HttpRequestTask extends AsyncTask<String, Void, AntrianService> {
		
		
		private ProgressDialog pDialog;
		private String service="";
		
		protected void onPreExecute() {
            pDialog = new ProgressDialog(AntrianActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
		}
	
		@Override
		protected AntrianService doInBackground(String... params) {

			
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

				AntrianService result = restTemplate.getForObject(url,
						AntrianService.class);
				return result;

			} catch (Exception e) {
				// TODO: handle exception
				
				return new AntrianService();
			}
			

		}

		@Override
		protected void onPostExecute(AntrianService resultData) {
			
			final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}
			
			
			if(service.equals("GetTicketOnProcessByUser"))
			Toast.makeText(getApplicationContext(), "GetTicketOnProcessByUser Masuk "+resultData.getMessageStatus()+" "+resultData.getSuccess(), Toast.LENGTH_LONG).show();
			
			if (resultData.getMessageStatus().equals(KEY_MESSAGESTATUS)
					&& resultData.getSuccess().equals(KEY_SUCCESS)) {
				
				if(service.equals("GetTicketOnProcessByUser"))
				{					
					resultData.getData().save();
					
					Intent intent;
					
					if(lists.equals("SVY"))
					{
						intent = new Intent(AntrianActivity.this,
								LayananSurveyorActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
					else
					{
						intent = new Intent(AntrianActivity.this,
								LayananNonSurveyor.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
					
						
				}
				else
				if(service.equals("GetLatestQueueAlternated"))
				{
					resultData.getData().save();
					//final GlobalClass globalVariable = (GlobalClass)getApplicationContext();
					globalVariable.setBranchID(resultData.getData().getBranchID());
					
					Toast.makeText(getApplicationContext(), globalVariable.getUserBranchID()+" "+globalVariable.getBranchID(), Toast.LENGTH_LONG).show();
					
					if(globalVariable.getUserBranchID().equals(globalVariable.getBranchID()))
					{
						Intent dashboard = new Intent(AntrianActivity.this,
								CounterActivity.class);
						dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(dashboard);
						
						
						//show save tabel ResultData to table
						
						globalVariable.getLoginService().getData().save();
						
						//show result data
						
						LoginModel testmodel= new Select().from(LoginModel.class).executeSingle();
						Log.d("Test database" + testmodel.getUserName());
					}
					else
					{
						Toast.makeText(getApplicationContext(), " Incorrect initial/password", Toast.LENGTH_LONG).show();
					}
				}
				else
					if(service.equals("GetTicketOnProcessByUser"))
					{
						
						resultData.getData().save();
						lastServedRole=resultData.getData().getTicketNumberingCode();
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						try {
							Date date=sdf.parse(sdf.format(new Date()));
							//String dateString=date.toString();
							String dateString="10/30/2014";
							Toast.makeText(getApplicationContext(),globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetLatestQueueAlternated&userName="
									+ user
									+ "&branchID="
									+ branchID
									+ "&ticketDate="
									+ dateString
									+ "&lists="
									+ lists, Toast.LENGTH_LONG).show();
						new HttpRequestTask().execute( globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetLatestQueueAlternated&userName="
												+ user
												+ "&branchID="
												+ branchID
												+ "&ticketDate="
												+ dateString
												+ "&lists="
												+ lists);
						
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				

			} else {
				
				new HttpRequestTask().execute(globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetTicketOnProcessByUser&userName="+user,"GetTicketOnProcessByUser");
				Toast.makeText(getApplicationContext(), " Incorrect initial/password", Toast.LENGTH_LONG).show();

			}

		}

	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.buttonLayani:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			
			if(isInternetPresent){
				if(listTicket != null && listTicket.size() > 0){
					String ticketId = ((LatestQueueAlternated)listTicket.get(0)).getTicketID();
					new GetTicketInfoTask(this).execute(IConstantMessageStatus.BASE_URL + "/Services/SelmoFrontLiner.ashx?method=GetTicketInfo&propTicketIDOnProcess="+ticketId,"GetTicketInfoTask");
				}
			}else{
				showAlertDialog(AntrianActivity.this, "Informasi", "not connection", false);
			}
			
			break;

		default:
			break;
		}

	}
	
	public void setLayanan(GetTicketInfoModel ticketInfo){
		final GlobalClass globalVar = (GlobalClass) getApplicationContext();
		globalVar.setBookOnlineID(ticketInfo.getBookingID());
		globalVar.setBranchID(ticketInfo.getBranchID());
		globalVar.setTicketID(ticketInfo.getTicketID());
		globalVar.setTicketNumber(ticketInfo.getTicketNumber());
		globalVar.setStationNumber(ticketInfo.getStationNumber());
		globalVar.setTicketCategoryCode(ticketInfo.getTicketCategoryCode());
		
		String role = globalVar.getRoleID();
		if(IConstantMessageStatus.SVY.equals(role))
		{
			Intent intentSurveyorActivity = new Intent(getApplicationContext(), LayananSurveyorActivity.class);
			startActivity(intentSurveyorActivity);
		}
		else
		{
			Intent intentNonSurveyorActivity = new Intent(getApplicationContext(), LayananNonSurveyorActivity.class);
			startActivity(intentNonSurveyorActivity);
		}
	}
	
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