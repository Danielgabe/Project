package com.example.frontlinerproject;

 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity; 
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent; 
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast; 

import com.example.async.AntrianTask;
import com.example.async.CounterAvailableTask;
import com.example.async.GetActiveUserInfoTask;
import com.example.async.GetCreateLoggedUserTask;
import com.example.async.GetLatestQueueAlternated;
import com.example.async.GetListUserRoleTask;
import com.example.async.GetTicketOnProcessByUserTask;
import com.example.async.LoginDummyTask;
import com.example.frontlinerproject.AntrianActivity;
import com.example.frontlinerproject.GlobalClass;
import com.example.frontlinerproject.util.ConnectionDetector;
import com.example.frontlinerprojek.R;  
import com.example.frontlinerprojek.R.color;
import com.example.servicemodel.IConstantMessageStatus;

public class CounterActivity extends Activity implements OnItemSelectedListener, View.OnClickListener { 

	private String[] items = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private Spinner mSpin;
	private String counter = ""; 
	private CounterActivity context;
	private String rolecode="";
	
	private String username = "";
	private String domain = "";
	
	private CheckBox checkboxcsh;
	private CheckBox checkboxcso;
	private CheckBox checkboxsvy;
	
	Button btnSubmitCounter;
	
	// Connection detector class
	ConnectionDetector connectionDetector;

	// flag for Internet connection status
	Boolean isInternetPresent = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter);
		
		connectionDetector = new ConnectionDetector(this);
		isInternetPresent=connectionDetector.isConnectingToInternet();
		
		/* Remove acionbar*/
		ActionBar bar = getActionBar();
		bar.hide();
		
		context=this;		
		username = ((GlobalClass) getApplicationContext()).getUserName();
		domain = ((GlobalClass) getApplicationContext()).getUserDomain();
		
		checkboxcsh = (CheckBox) findViewById(R.id.checkboxcsh);
		checkboxcsh.setOnClickListener(this);
		checkboxcso = (CheckBox) findViewById(R.id.checkboxcso);
		checkboxcso.setOnClickListener(this);
		checkboxsvy = (CheckBox) findViewById(R.id.checkboxsvy);
		checkboxsvy.setOnClickListener(this);
		
		btnSubmitCounter = (Button) findViewById(R.id.btnSubmitCounter);
		btnSubmitCounter.setOnClickListener(this);
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		
	 	
		mSpin = (Spinner) findViewById(R.id.spinner1);
		mSpin.setOnItemSelectedListener(this);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpin.setAdapter(aa);
		
		if(isInternetPresent){
			new GetListUserRoleTask(this).execute(globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetListUserRole&userName="+username+"&userDomain="+domain);
		}else{
			showAlertDialog(CounterActivity.this, "No Internet Connection","You don't have internet connection.", false);
		}

	}
	 
 
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		if (parent == mSpin) {
			counter = items[position];
			((GlobalClass)getApplicationContext()).setCounterNO(counter);
			Log.d("position", counter);
		}	 
	}
  
	 

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
	
	public void getActiveUserInfo(String success){
		if("True".equals(success)){			
			Toast.makeText(context, "Counter Tersedia", Toast.LENGTH_LONG).show();				
			new GetActiveUserInfoTask(this).execute(IConstantMessageStatus.BASE_URL+"/Services/SelmoFrontLiner.ashx?method=GetActiveUserInfo&userName="+ username + "&userDomain="+domain);
		}
	}
	
	public void setCheckedUserRole (String isUserActive){
		if("True".equals(isUserActive)){
			
			new GetTicketOnProcessByUserTask(context).execute(IConstantMessageStatus.BASE_URL+"/Services/SelmoFrontLiner.ashx?method=GetTicketOnProcessByUser&userName="+ username);
		}
	}
	
	public void setAntrian (){
		final GlobalClass globalVariable = (GlobalClass) context.getApplicationContext();
		String user=globalVariable.getUserName();
		String branchID=globalVariable.getBranchID();
		String lists=globalVariable.getRoleID();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date date=sdf.parse(sdf.format(new Date()));
			//String dateString=date.toString();
			String dateString = "10/29/2014";
			new GetLatestQueueAlternated(context).execute(globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetLatestQueueAlternated&userName="+user+"&branchID="+branchID+"&ticketDate="+dateString+"&lists="+lists+"&lastServedRole=301","GetLatestQueueAlternated");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRoleOptions(){
		ArrayList<String> listRole = ((GlobalClass) getApplicationContext()).getListRole();
		if(listRole != null){
			if (!listRole.contains(IConstantMessageStatus.CSH)){				
				checkboxcsh.setEnabled(false);
				checkboxcsh.setBackgroundResource(R.drawable.cashier_disable);
			}
			if (!listRole.contains(IConstantMessageStatus.CSO)){
				checkboxcso.setEnabled(false);
				checkboxcso.setBackgroundResource(R.drawable.customer_service_disable);
			}
			if (!listRole.contains(IConstantMessageStatus.SVY)){
				checkboxsvy.setEnabled(false);
				checkboxsvy.setBackgroundResource(R.drawable.surveyor_disable);
			}
		}
		
	}


	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View view) {
		final GlobalClass globalvar = ((GlobalClass)getApplicationContext());
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		switch (view.getId()) {
		case R.id.checkboxcsh:
			if(checkboxcsh.isChecked()){
				checkboxcsh.setBackgroundResource(R.drawable.cashier_check);
			}
			else
			{
				checkboxcsh.setBackgroundResource(R.drawable.cashier_on);
			}
			if(!checkboxsvy.isChecked()){
				globalvar.setRoleID("CSH")	;		
			}
			break;
		case R.id.checkboxcso:
			if(checkboxcso.isChecked()){
				checkboxcso.setBackgroundResource(R.drawable.customer_service_check);
			}
			else
			{
				checkboxcso.setBackgroundResource(R.drawable.customer_service_on);
			}
			if(!checkboxsvy.isChecked()){
				globalvar.setRoleID("CSO")	;
			}
			break;
		case R.id.checkboxsvy:
			if(checkboxsvy.isChecked()){
				checkboxsvy.setBackgroundResource(R.drawable.surveyor_check);
			}
			else
			{
				checkboxsvy.setBackgroundResource(R.drawable.surveyor_on);
			}
			if(checkboxsvy.isChecked())	{
				globalvar.setRoleID("SVY")	;
			}
			break;
		case R.id.btnSubmitCounter:
			if(checkboxcsh.isChecked() || checkboxcso.isChecked() || checkboxsvy.isChecked()){
				isInternetPresent=connectionDetector.isConnectingToInternet();
				if(isInternetPresent){
					String urlIsCounterAvailable= globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=isCounterAvailable&userName=";
					String isCounterAvailable = urlIsCounterAvailable+username+"&userDomain="+domain+"&counterNo="+counter+"&branchID="+((GlobalClass)getApplicationContext()).getBranchID();;
					new CounterAvailableTask(context).execute(isCounterAvailable,"isCounterAvailable");
				}
				else
				{
					showAlertDialog(CounterActivity.this, "No Internet Connection","You don't have internet connection.", false);
				}
				
				
			}
			else
			{
				final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
				alertDialog.setTitle("Informasi");
				alertDialog.setMessage("Pilih salah satu Role.");
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						alertDialog.cancel();
						
					}
				});
				alertDialog.show();	
			}

		default:
			break;
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

	
	
	 