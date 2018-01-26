package com.example.frontlinerproject;

import java.util.ArrayList;

import com.example.async.GetListLoggedInSurveyorByBranch;
import com.example.async.TransferTask;
import com.example.frontlinerproject.util.ConnectionDetector;
import com.example.frontlinerprojek.R;
import com.example.frontlinerprojek.R.color;
import com.example.model.GetListActiveServiceTypeByTicketCategoryUmumModel;
import com.example.servicemodel.IConstantMessageStatus;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class TransferNonSurveyor extends Activity implements OnClickListener{
	
	private Button mConfirm;
	private Spinner spinnerInisialId;
	private TextView nourutTransferNonSVY;
	private ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceType;
	private LinearLayout linearLayoutLayananUmum;
	
	// Connection detector class
	ConnectionDetector connectionDetector;

	// flag for Internet connection status
	Boolean isInternetPresent = false;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_transfer_non_surveyor);
		
		connectionDetector = new ConnectionDetector(TransferNonSurveyor.this);
		isInternetPresent = connectionDetector.isConnectingToInternet();
		
		/* Remove acionbar*/
		ActionBar bar = getActionBar();
		bar.hide();
		
		/* Get Service*/
		try{
		    // Get the Bundle Object       
		    Bundle bundleObject = getIntent().getExtras();
		             
		    // Get Bundle
		    serviceType = (ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel>)bundleObject.getSerializable(IConstantMessageStatus.LAYANAN_UMUM);
		  
		} catch(Exception e){
		    e.printStackTrace();
		}
		
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		String user = globalVariable.getUserName();
		String branchID = globalVariable.getBranchID();
		String userDomain = globalVariable.getUserDomain();
		String ticketNumber = globalVariable.getTicketNumber();
		
		linearLayoutLayananUmum = (LinearLayout) findViewById(R.id.linearLayoutLayananUmum);
		
		spinnerInisialId = (Spinner)findViewById(R.id.spinnerInisialId);
		nourutTransferNonSVY = (TextView)findViewById(R.id.nourutTransferNonSVY);
		nourutTransferNonSVY.setText(ticketNumber);
		if(serviceType !=null) {
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			
			for( int index=0; index < serviceType.size() ; index++){
				GetListActiveServiceTypeByTicketCategoryUmumModel model = serviceType.get(index);
				
				Button layananButton = new Button(this);
				layananButton.setBackgroundResource(R.drawable.row_data);
				layananButton.setTextColor(color.layanan);
				layananButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				layananButton.setText(model.getName());	
				linearLayoutLayananUmum.addView(layananButton, layoutParams);
			}
		}	
		
		mConfirm=(Button)findViewById(R.id.buttonConfirmTransferNonSurveyor);
		mConfirm.setOnClickListener(this);
		
		if(isInternetPresent)
		{
			String urlString = globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetListLoggedInSurveyorByBranch&branchID="+branchID+"&userName="+user+"&userDomain="+userDomain;
			
			new GetListLoggedInSurveyorByBranch(this).execute(urlString,"GetListLoggedInSurveyorByBranch");
		}
		else
		{
			showAlertDialog(TransferNonSurveyor.this, "Informasi", "No Connection", false);
		}		
	}

	@Override
	public void onClick(View view) {
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		
		switch (view.getId()) {
		case R.id.buttonConfirmTransferNonSurveyor:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if(isInternetPresent)
			{
				String user = globalVariable.getUserName();
				String branchID = globalVariable.getBranchID();
				String ticketId = globalVariable.getTicketID();
				String ticketCategory = globalVariable.getTicketCategoryCode();
				String ticketNumber = globalVariable.getTicketNumber();
				
				String urlString=globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=TransferTicketCategory&currentTicketID="+ticketId+"&branchID="+branchID+"&ticketCategoryCode="+ticketCategory+"&ticketNumber="+ticketNumber+"&userName="+user+"&servedBy=";
				
				new TransferTask(this).execute(urlString,"TransferTicketCategory");
			}
			else
			{
				showAlertDialog(TransferNonSurveyor.this, "Informasi", "No Connection", false);
			}	
			break;

		default:
			break;
		}
	}
	
	public void setInitialId(ArrayList<String> initals){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, initals);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerInisialId.setAdapter(adapter);
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
