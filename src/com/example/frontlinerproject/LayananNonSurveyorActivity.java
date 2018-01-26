package com.example.frontlinerproject;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.async.AddAlertNumberAndVoiceTask;
import com.example.async.GetBookOnlineTask;
import com.example.async.GetListActiveServiceTypeByTicketCategoryUmumTask;
import com.example.async.SetTicketNoShowTask;
import com.example.frontlinerproject.adapter.ServiceTypeAdapter;
import com.example.frontlinerproject.util.ConnectionDetector;
import com.example.frontlinerprojek.R;
import com.example.model.GetListActiveServiceGroupByTicketCategoryModel;
import com.example.model.GetListActiveServiceTypeByTicketCategoryUmumModel;
import com.example.servicemodel.IConstantMessageStatus;

public class LayananNonSurveyorActivity extends Activity implements
		OnItemSelectedListener, View.OnClickListener {
	TextView nourut;
	public String counter = "";
	// private Spinner mSpin;
	
	private LinearLayout linearLayoutLayananUmum;
	private Button btnTransferKeCashier;
	private Button btnTransferCSO;
	private Button btnTransferSurveyor;
	private Button btnSelesai;
	private Button btnPanggilUlang;
	private Button btnNoShow;
	private Button btnPlus;
	private Button btnMinus;
	int lastIndex;

	private ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> selectedServices;
	private ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceList;

	// Connection detector class
	ConnectionDetector connectionDetector;

	// flag for Internet connection status
	Boolean isInternetPresent = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layanan_non_surveyor);

		connectionDetector = new ConnectionDetector(
				LayananNonSurveyorActivity.this);
		selectedServices = new ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel>();
		serviceList = new ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel>();
		
		isInternetPresent = connectionDetector.isConnectingToInternet();

		/* Remove acionbar */
		ActionBar bar = getActionBar();
		bar.hide();
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

		/** Set Fields */
		linearLayoutLayananUmum = (LinearLayout) findViewById(R.id.linearLayoutLayananUmum);

		nourut = (TextView) findViewById(R.id.nourutNSY);
		nourut.setOnClickListener(this);		

		String TicketNumber = globalVariable.getTicketNumber();
		nourut.setText(TicketNumber);
		
		btnPlus=(Button) findViewById(R.id.buttonPlus);
		btnPlus.setOnClickListener(this);
		btnMinus=(Button) findViewById(R.id.buttonMinus);
		btnMinus.setOnClickListener(this);
		btnTransferKeCashier = (Button) findViewById(R.id.btnTransferKeCashier);
		btnTransferKeCashier.setOnClickListener(this);
		btnTransferCSO = (Button) findViewById(R.id.btnTransferCSO);
		btnTransferCSO.setOnClickListener(this);
		btnTransferSurveyor = (Button) findViewById(R.id.btnTransferSurveyor);
		btnTransferSurveyor.setOnClickListener(this);
		btnSelesai = (Button) findViewById(R.id.btnSelesai);
		btnSelesai.setOnClickListener(this);
		btnPanggilUlang = (Button) findViewById(R.id.btnPanggilUlang);
		btnPanggilUlang.setOnClickListener(this);
		btnNoShow = (Button) findViewById(R.id.btnNoShow);
		btnNoShow.setOnClickListener(this);

		if (isInternetPresent) {
			String urlss = globalVariable.getselmoFronLinerURL()
					+ "/Services/SelmoFrontLiner.ashx?method=GetListActiveServiceTypeByTicketCategory&propTicketCategoryOnProcess=SVY";
			new GetListActiveServiceTypeByTicketCategoryUmumTask(this).execute(
					urlss, "GetListActiveServiceTypeByTicketCategory");
		} else {
			showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
					"No Connection", false);
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View view) {
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		String BookOnlineID = globalVariable.getBookOnlineID();
		
		switch (view.getId()) {
		case R.id.nourutNSY:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if(isInternetPresent)
			{
				if(BookOnlineID != null && !BookOnlineID.equals("")){
					String url = globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=GetBookOnline&bookingID="+ BookOnlineID;
					new GetBookOnlineTask(this).execute(url, "GetBookOnline");
				}
				else
				{
					final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
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
			else
			{
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi", "No Connection", false);
			}
			break;
		case R.id.btnTransferKeCashier:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if (isInternetPresent) {
				Intent cashier = new Intent(getApplicationContext(), TransferNonSurveyor.class);
				selectedServices.clear();
				for( int index = 0; index < linearLayoutLayananUmum.getChildCount(); index++){
					Spinner spinner = (Spinner) linearLayoutLayananUmum.getChildAt(index);
					GetListActiveServiceTypeByTicketCategoryUmumModel model = (GetListActiveServiceTypeByTicketCategoryUmumModel)spinner.getSelectedItem();
					selectedServices.add(model);
				}
				
				Bundle bundleObjectCSH = new Bundle(); 
				bundleObjectCSH.putSerializable(IConstantMessageStatus.LAYANAN_UMUM, selectedServices);
				
				cashier.putExtras(bundleObjectCSH);
				
				cashier.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(cashier);
			} else {
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
						"No Connection", false);
			}
			break;
		case R.id.btnTransferCSO:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if (isInternetPresent) {
				Intent csoactivity = new Intent(getApplicationContext(), TransferNonSurveyor.class);
				
				selectedServices.clear();
				for( int index = 0; index < linearLayoutLayananUmum.getChildCount(); index++){
					Spinner spinner = (Spinner) linearLayoutLayananUmum.getChildAt(index);
					GetListActiveServiceTypeByTicketCategoryUmumModel model = (GetListActiveServiceTypeByTicketCategoryUmumModel)spinner.getSelectedItem();
					selectedServices.add(model);
				}
				
				Bundle bundleObjectCSO = new Bundle();
				bundleObjectCSO.putSerializable(IConstantMessageStatus.LAYANAN_UMUM, selectedServices);
				
				csoactivity.putExtras(bundleObjectCSO);
				csoactivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(csoactivity);
			} else {
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
						"No Connection", false);
			}

			break;
		case R.id.btnTransferSurveyor:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if (isInternetPresent) {
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
						"Tidak dapat Mentranfer Ke Surveyor.", false);
			} else {
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
						"No Connection", false);
			}
			break;
		case R.id.btnSelesai:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if (isInternetPresent) {
				Intent intent = new Intent(getApplicationContext(), NonTransferNonSurveyor.class);
			    Bundle bundleObject = new Bundle();
			    
				selectedServices.clear();
				for( int index = 0; index < linearLayoutLayananUmum.getChildCount(); index++){
					Spinner spinner = (Spinner) linearLayoutLayananUmum.getChildAt(index);
					GetListActiveServiceTypeByTicketCategoryUmumModel model = (GetListActiveServiceTypeByTicketCategoryUmumModel)spinner.getSelectedItem();
					selectedServices.add(model);
				}			
				
				bundleObject.putSerializable(IConstantMessageStatus.LAYANAN_UMUM, selectedServices);
				
				intent.putExtras(bundleObject);
				
	            startActivity(intent);      
	            finish();
			} else {
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
						"No Connection", false);
			}
			break;
		case R.id.btnPanggilUlang:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if (isInternetPresent) {
				final String BranchID = globalVariable.getBranchID(); 
				final String UserName = globalVariable.getUserName();
				final String TicketNumberOnProcess = globalVariable.getTicketNumber();
				final String StationNumber = globalVariable.getCounterNO();
				String urlAddAlertNumberAndVoice = globalVariable.getselmoFronLinerURL()+"/Services/selmofrontliner.ashx/?method=AddAlertNumberAndVoice&branchID="
				+BranchID+"&value1="+TicketNumberOnProcess+"&value2="+TicketNumberOnProcess+"&value3="+StationNumber+"&userName="+UserName;
				new AddAlertNumberAndVoiceTask(this).execute(urlAddAlertNumberAndVoice,"AddAlertNumberAndVoice");
			} else {
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
						"No Connection", false);
			}

			break;
		case R.id.btnNoShow:
			isInternetPresent = connectionDetector.isConnectingToInternet();
			if (isInternetPresent) {
				String ticketId = globalVariable.getTicketID();
				String username = globalVariable.getUserName();
				String urlSetTicketNoShow = globalVariable.getselmoFronLinerURL()+"/Services/SelmoFrontLiner.ashx?method=SetTicketNoShow&ticketID="+ticketId+"&userName="+username;
				new SetTicketNoShowTask(this).execute(urlSetTicketNoShow,"SetTicketNoShow");
			} else {
				showAlertDialog(LayananNonSurveyorActivity.this, "Informasi",
						"No Connection", false);
			}
			break;

		case R.id.buttonPlus:
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

			Spinner spinner = new Spinner(this);
			ServiceTypeAdapter adapter = new ServiceTypeAdapter(this,
					serviceList);
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(this);
			linearLayoutLayananUmum.addView(spinner, layoutParams);
			break;
	
		case R.id.buttonMinus:
			lastIndex = linearLayoutLayananUmum.getChildCount() - 1;
			if (lastIndex > 0)
				linearLayoutLayananUmum.removeViewAt(lastIndex);
			break;

		default:
			break;
		}
	}

	public void setActiveServiceType(
			ArrayList<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceList) {
		this.serviceList = serviceList;
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		Spinner spinner = new Spinner(this);
		ServiceTypeAdapter adapter = new ServiceTypeAdapter(this, serviceList);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		linearLayoutLayananUmum.addView(spinner, layoutParams);
	}

	/**
	 * Function to display simple Alert Dialog
	 * 
	 * @param context
	 *            - application context
	 * @param title
	 *            - alert dialog title
	 * @param message
	 *            - alert message
	 * @param status
	 *            - success/failure (used to set icon)
	 * */
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
