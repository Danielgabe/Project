package com.example.frontlinerproject;

import java.util.ArrayList;

import com.example.async.ProcessFinishingServingTicket;
import com.example.async.UpdateServiceGroupTask;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class NonTransferNonSurveyor extends Activity implements OnClickListener {

	private Button mButtonConfirm;
	private TextView nourutNonTransferSVY;
	private Button buttonLayananSVY;
	private LinearLayout linearLayoutUmumNonTransferNonSVY;
	String user, ticketID, serviceGroupID = "", serviceGroupName = "";
	private ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceType;

	// Connection detector class
	ConnectionDetector connectionDetector;

	// flag for Internet connection status
	Boolean isInternetPresent = false;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_non_transfer_non_surveyor);
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
		
		linearLayoutUmumNonTransferNonSVY = (LinearLayout) findViewById(R.id.linearLayoutUmumNonTransferNonSVY);

		mButtonConfirm = (Button) findViewById(R.id.ButtonConfirm);
		mButtonConfirm.setOnClickListener(this);

		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		String ticketNo = globalVariable.getTicketNumber();
		nourutNonTransferSVY.setText(ticketNo);
		user = globalVariable.getUserName();
		ticketID = globalVariable.getTicketID();
		serviceGroupID = globalVariable.getServiceGroupID();
		serviceGroupName = globalVariable.getName();
		if (serviceType != null) {
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			
			for (int index = 0; index < serviceType.size(); index++) {
				GetListActiveServiceTypeByTicketCategoryUmumModel model = serviceType.get(index);

				Button buttonLayanan = new Button(this);
				buttonLayanan.setBackgroundResource(R.drawable.row_data);
				buttonLayanan.setTextColor(color.layanan);
				buttonLayanan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				buttonLayanan.setText(model.getName());
				linearLayoutUmumNonTransferNonSVY.addView(buttonLayanan, layoutParams);
			}
		}
	}

//		new UpdateServiceGroupTask(this)
//				.execute(
//						globalVariable.getselmoFronLinerURL()
//								+ "/Services/SelmoFrontLiner.ashx?method=updateServiceGroup&userName="
//								+ user + "&ticketID=" + ticketID
//								+ "&serviceGroupID=" + serviceGroupID
//								+ "&serviceGroupName=" + serviceGroupName,
//						"updateServiceGroup");
	

	@Override
	public void onClick(View v) {
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		// TODO Auto-generated method stub
		if (v == mButtonConfirm) {
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if (isInternetPresent) {
				new ProcessFinishingServingTicket(this)
						.execute(
								globalVariable.getselmoFronLinerURL()
										+ "/Services/SelmoFrontLiner.ashx?method=ProcessFinishingServingTicket&ticketID="
										+ ticketID + "&userName=" + user,
								"ProcessFinishingServingTicket");
			} else {
				showAlertDialog(NonTransferNonSurveyor.this, "informasi",
						"Not connection.", false);

			}
		}
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
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
