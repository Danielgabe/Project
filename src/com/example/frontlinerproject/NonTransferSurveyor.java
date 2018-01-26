package com.example.frontlinerproject;

import java.util.ArrayList;

import com.example.async.ProcessFinishingServingTicket;
import com.example.async.UpdateServiceGroupTask;
import com.example.frontlinerproject.util.ConnectionDetector;
import com.example.frontlinerprojek.R;
import com.example.frontlinerprojek.R.color;
import com.example.model.GetListActiveServiceGroupByTicketCategoryModel;
import com.example.model.GetListActiveServiceTypeByTicketCategoryUmumModel;
import com.example.model.LatestQueueAlternated;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class NonTransferSurveyor extends Activity implements OnClickListener {

	private Button mButtonConfirm;
	private ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceType;
	private TextView nourutNonTransferSVY;
	private Button buttonLayananSVY;
	private LinearLayout linearLayoutUmumNonTransferSVY;
	
	// Connection detector class
	ConnectionDetector connectionDetector;

	// flag for Internet connection status
	Boolean isInternetPresent = false;
		 

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_non_transfer_surveyor);
		connectionDetector = new ConnectionDetector(getApplicationContext());

		/* Remove acionbar */
		ActionBar bar = getActionBar();
		bar.hide();

		/* Get Service */
		try {
			// Get the Bundle Object
			Bundle bundleObject = getIntent().getExtras();

			// Get Bundle
			serviceType = (ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel>) bundleObject
					.getSerializable(IConstantMessageStatus.LAYANAN_UMUM);

		} catch (Exception e) {
			e.printStackTrace();
		}

		 final GlobalClass globalVariable =
		 (GlobalClass)getApplicationContext();
		 String ticketNo = globalVariable.getTicketNumber();
		 String serviceGroupName = globalVariable.getServiceGroupName();

		mButtonConfirm = (Button) findViewById(R.id.buttonConfirm);
		mButtonConfirm.setOnClickListener(this);

		linearLayoutUmumNonTransferSVY = (LinearLayout) findViewById(R.id.linearLayoutUmumNonTransferSVY);

		nourutNonTransferSVY = (TextView) findViewById(R.id.nourutNonTransferSVY);
		nourutNonTransferSVY.setText(ticketNo);

		buttonLayananSVY = (Button) findViewById(R.id.buttonLayananSVY);
		buttonLayananSVY.setText(serviceGroupName);

		if (serviceType != null) {
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			
			for (int index = 0; index < serviceType.size(); index++) {
				GetListActiveServiceTypeByTicketCategoryUmumModel model = serviceType.get(index);

				Button buttonLayanan = new Button(this);
				buttonLayanan.setBackgroundResource(R.drawable.row_data);
				buttonLayanan.setTextColor(color.layanan);
				buttonLayanan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				buttonLayanan.setText(model.getName());
				linearLayoutUmumNonTransferSVY.addView(buttonLayanan, layoutParams);
			}
		}
	}

	@Override
	public void onClick(View view) {
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

		switch (view.getId()) {
		case R.id.buttonConfirm:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if(isInternetPresent){
			String user = globalVariable.getUserName();
			String ticketID = globalVariable.getTicketID();
			String serviceGroupID = globalVariable.getServiceGroupID();
			String serviceGroupName = globalVariable.getServiceGroupName();

			new UpdateServiceGroupTask(this)
					.execute(
							globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=updateServiceGroup&userName="
									+ user
									+ "&ticketID="
									+ ticketID
									+ "&serviceGroupID="
									+ serviceGroupID
									+ "&serviceGroupName=" + serviceGroupName,
							"updateServiceGroup");
			}else{
				
				showAlertDialog(NonTransferSurveyor.this, "Informasi", "Not Connection.", false);
			}
			break;

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
